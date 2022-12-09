package com.book.model;

import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
@Named
public class CategoryTranslation implements Serializable {

    private static final long serialVersionUID = 1L;

    // <editor-fold defaultstate="collapsed" desc="Property Id">
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cateId")
    private int _id = -1;

    @Column(name = "ctCategoryId")
    private int _categoryId = -1; 
    
    // <editor-fold defaultstate="collapsed" desc="Property Language">
    @Column(name = "ctLanguage")
    private String _language;    
   
    @Column(name = "ctName")
    private String _name;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }
    // </editor-fold>
   

    public int getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(int id) {
        _categoryId = id;
    }
    

    public String getLanguage() {
        return _language;
    }

    public void setLanguage(String language) {
        _language = language;
    }
        

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="hashcode / equals / toString">
    @Override
    public int hashCode() {
        if (_id < 0) {
            int hash = 3;
            hash = 89 * hash + _categoryId;
            hash = 89 * hash + Objects.hashCode(_language);
            return hash;
        }
        return _id;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CategoryTranslation)) {
            return false;
        }
        CategoryTranslation other = (CategoryTranslation) object;
        if (_id < 0 && other._id < 0) {
            return _categoryId == other._categoryId
                    && _language.equals(other._language);
        }
        return _id == other._id;
    }

    @Override
    public String toString() {
        return "CategoryTranlation[ id=" + _id + "] " + _name;
    }

}
