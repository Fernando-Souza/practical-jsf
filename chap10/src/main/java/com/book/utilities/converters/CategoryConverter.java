
package com.book.utilities.converters;

import com.book.model.Categoria;
import com.book.service.CategoryService;
import com.book.utilities.SessionTools;
import jakarta.annotation.PostConstruct;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.io.Serializable;
import java.util.List;

@FacesConverter(value = "CategoryConverter", forClass = Categoria.class)
@Named
@Singleton
public class CategoryConverter implements Serializable,Converter{

    @Inject
    private CategoryService _categoryService;
    @Inject
    private SessionTools _sessionTools;

    private static List<Categoria> _categories;

    public CategoryConverter() {
        System.out.println("CategoryConverter");
    }

    @PostConstruct
    private void init() {
    }

    public List<Categoria> getCategories() {
        if (_categories == null) {
            _categories = _categoryService.findAll(Categoria.class);
        }
        return _categories;
    }
   
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int id = Integer.parseInt(value);
            return getCategories().stream().filter(c -> c.getId() == id).findFirst().get();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Categoria category = (Categoria) value;
        return "" + category.getId();

    }      
   

}