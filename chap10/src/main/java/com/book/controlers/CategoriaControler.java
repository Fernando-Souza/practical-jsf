package com.book.controlers;

import com.book.model.Categoria;
import com.book.service.CategoryService;
import com.book.utilities.SessionTools;
import com.book.utilities.Topic;
import com.book.utilities.Topics;
import com.book.utilities.Utilities;
import com.book.utilities.Utilities.HandleDefault;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Named
@SessionScoped
public class CategoriaControler implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String CATEGORY = "category";

    @Inject
    CategoryService categoriaService;    
    
    
    @Inject
    SessionTools sessionTools;

    private List<Categoria> categorias;
    private List<Categoria> categoriasDeletadas;
    private Topics _topics;

    @PostConstruct
    private void init() {
        loadCategrories();
        categoriasDeletadas = new ArrayList<>();
        initTopics();
       
    }       

    private void loadCategrories() {
        categorias = categoriaService.findAll(Categoria.class);
        Map<Integer, Integer> mapCounter = categoriaService.countBooksPerCategory();
        int max = mapCounter.values().stream().max(Integer::compare).get();
        float factor = 9f / max;
        for (Categoria category : categorias) {
            if (!mapCounter.containsKey(category.getId())) {
                continue;
            }
            int quantity = mapCounter.get(category.getId());
            category.setQuantity(quantity);
            int quantityClass = Math.round(1 + quantity * factor);
            category.setQuantityClass(quantityClass);
        }
    }

    private void initTopics() {
        _topics = new Topics();
        Topic topic = Topic.TopicBuilder
                .createBuilder(CATEGORY)
                .setTitle(Utilities.getMessage("lblCategory"))
                .setOutcome("categoryEditor.xhtml")
                .build();
        _topics.addTopic(topic);
        for (String lang : Utilities.getSupportedLanguages(HandleDefault.Exclude)) {
            topic = Topic.TopicBuilder
                    .createBuilder(lang)
                    .setOutcome("categoryTranslator.xhtml")
                    .build();
            _topics.addTopic(topic);
        }
        _topics.setActive(CATEGORY);
    }

    public String changeTab(String newTopicKey) {
        if (_topics.getActiveTopic().get().getKey().equals(newTopicKey)) {
            return "";
        }
        _topics.setActive(newTopicKey);
        if (!newTopicKey.equals(CATEGORY)) {
            initTranslation(newTopicKey);
        }
        return _topics.getActiveTopic().get().getOutcome();
    }

    private void initTranslation(String langCode) {
        // ensure there is an element in the map for this language
        categorias.stream().forEach(c -> {
            if (c.getTranslatedName(langCode).isEmpty()) {
                c.setTranslatedName(langCode, "");
            }
        });
    }

    public List<Topic> getTopics() {
        return new ArrayList(_topics.getTopics());
    }

    public boolean isActive(Topic topic) {
        Optional<Topic> activeTopic = _topics.getActiveTopic();
        if (activeTopic.isPresent()) {
            return activeTopic.get().equals(topic);
        }
        return false;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> _categorias) {
        this.categorias = _categorias;
    }

    public String addCategoria() {

        categorias.add(new Categoria());
        return "";
    }

    public String deleteCategoria(Categoria categoria) {

        if (categoria.getId() >= 0) {
            categoriasDeletadas.add(categoria);
        }
        categorias.remove(categoria);
        return "";
    }

    public void deletarTudo() {

        categorias = new ArrayList<>();
    }

    public String salvar() {

        for (Categoria cat : categorias) {
            categoriaService.create(cat);
        }

        for (Categoria cat : categoriasDeletadas) {
            categoriaService.delete(cat);
        }

        categoriasDeletadas = new ArrayList<>();
        return "";
    }

}
