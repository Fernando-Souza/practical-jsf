package com.book.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catId")
    private int id;
    @Column(name = "catNome")
    private String nome;

    @Transient
    private int _quantity;
    
    @Transient
    private int _quantityClass;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ctCategoryId", referencedColumnName = "catId")
    @MapKey(name = "_language")
    private Map<String, CategoryTranslation> _catTranslations = new HashMap<>();

    public Map<String, CategoryTranslation> getCategoryTranslations() {
        return _catTranslations;
    }

    public void setCategoryTranslations(Map<String, CategoryTranslation> catTranslations) {
        _catTranslations = catTranslations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTranslatedName(String langCode) {
        if (_catTranslations.containsKey(langCode)) {
            return _catTranslations.get(langCode).getName();
        }
        return "";
    }

    public void setTranslatedName(String langCode, String name) {
        if (_catTranslations.containsKey(langCode)) {
            _catTranslations.get(langCode).setName(name);
        } else {
            CategoryTranslation translation = new CategoryTranslation();
            translation.setLanguage(langCode);
            translation.setCategoryId(id);
            translation.setName(name);
            _catTranslations.put(langCode, translation);
        }
    }

    public String getTranslatedNameOrDefault(String langCode) {
        if (_catTranslations.containsKey(langCode)) {
            String name = _catTranslations.get(langCode).getName();
            if (name.isEmpty()) {
                return nome;
            }
            return name;
        }
        return nome;
    }

    public int getQuantity() {
        return _quantity;
    }

    public void setQuantity(int quantity) {
        _quantity = quantity;
    }    

    public int getQuantityClass() {
        return _quantityClass;
    }

    public void setQuantityClass(int quantityClass) {
        _quantityClass = quantityClass;
    }
    // </editor-fold>

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nome=" + nome + '}';
    }

}
