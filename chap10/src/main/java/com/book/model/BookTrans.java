/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "BookTrans")
public class BookTrans implements Serializable {

    private static final long serialVersionUID = 1L;

    // <editor-fold defaultstate="collapsed" desc="Property Id">
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "btId")
    private Integer _id = -1;

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        _id = id;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property BookId">
    @Column(name = "btBookId")
    private Integer _bookId;

    public Integer getBookId() {
        return _bookId;
    }

    public void setBookId(Integer id) {
        _bookId = id;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Language">
    @Column(name = "btLanguage")
    private String _language;

    public String getLanguage() {
        return _language;
    }

    public void setLanguage(String language) {
        _language = language;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Shorttext">
    @Column(name = "btShorttext")
    private String _shorttext;

    public String getShorttext() {
        return _shorttext;
    }

    public void setShorttext(String shorttext) {
        _shorttext = shorttext;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="hashcode / equals / toString">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (_id != null ? _id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BookTrans)) {
            return false;
        }
        BookTrans other = (BookTrans) object;
        if ((_id == null && other.getId() != null) || (_id != null && !_id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.book.model.Book[ id=" + _id + " ]";
    }
}
