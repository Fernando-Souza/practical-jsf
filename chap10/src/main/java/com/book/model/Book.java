
package com.book.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

     private int _id = -1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

   
    
    @Size(max = 200, message = "Title must be less or equal {max} characters")
    @Column(name = "bookTitle")
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    private String _title = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property SubTitle">
    @Column(name = "bookSubtitle")
    @Size(max = 200)
    public String getSubTitle() {
        return _subTitle;
    }

    public void setSubTitle(String subTitle) {
        _subTitle = subTitle;
    }

    private String _subTitle = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Author">
    @Size(max = 255)
    @Column(name = "bookAuthor")
    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        _author = author;
    }

    private String _author = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Publisher">
    @Size(max = 45)
    @Column(name = "bookPublisher")
    public String getPublisher() {
        return _publisher;
    }

    public void setPublisher(String publisher) {
        _publisher = publisher;
    }

    private String _publisher = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Year">
    @Column(name = "bookYear")
    public int getYear() {
        return _year;
    }

    public void setYear(int year) {
        _year = year;
    }

    private int _year;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Language">
    @Column(name = "bookLanguage")
    public String getLanguage() {
        return _language;
    }

    public void setLanguage(String language) {
        _language = language;
    }

    private String _language = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property ISBN">
    @Size(max = 45)
    @Column(name = "bookISBN")
    public String getIsbn() {
        return _isbn;
    }

    public void setIsbn(String isbn) {
        _isbn = isbn;
    }

    private String _isbn = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property ShortText">
    @Size(max = 500)
    @Column(name = "bookShorttext")
    public String getShortText() {
        return _shortText;
    }

    public void setShortText(String shorttext) {
        _shortText = shorttext;
    }

    private String _shortText = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Reference">
    @Size(max = 500)
    @Column(name = "bookReference")
    public String getReference() {
        return _reference;
    }

    public void setReference(String reference) {
        _reference = reference;
    }

    private String _reference = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property AdReference">
    @Size(max = 50)
    @Column(name = "bookAdReference")
    public String getAdReference() {
        return _adReference;
    }

    public void setAdReference(String adReference) {
        _adReference = adReference;
    }

    private String _adReference = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property BookCategories">
    @OneToMany
    @JoinTable(name = "mapBookCategory",
            joinColumns = @JoinColumn(name = "bookid"),
            inverseJoinColumns = @JoinColumn(name = "Categoria_id"))
    public List<Categoria> getCategories() {
        return _categories;
    }

    public void setCategories(List<Categoria> categories) {
        _categories = categories;
    }

    private List<Categoria> _categories = new ArrayList<>();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property ReviewLinks">
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "BookId", referencedColumnName = "bookId")
    public List<ReviewLink> getReviewLinks() {
        return _reviewLinks;
    }

    public void setReviewLinks(List<ReviewLink> reviewLinks) {
        _reviewLinks = reviewLinks;
    }

    private List<ReviewLink> _reviewLinks = new ArrayList<>();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Reviews">
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "rvBookId", referencedColumnName = "bookId")
    public List<Review> getReviews() {
        return _reviews;
    }

    public void setReviews(List<Review> reviews) {
        _reviews = reviews;
    }

    private List<Review> _reviews = new ArrayList<>();

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Property Tranlations">
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "btBookId", referencedColumnName = "bookId")
    public List<BookTrans> getTranslations() {
        return _translations;
    }

    public void setTranslations(List<BookTrans> translations) {
        _translations = translations;
    }

    private List<BookTrans> _translations = new ArrayList<>();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="hashcode / equals / toString">
    @Override
    public int hashCode() {
        return _id;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        return _id == other._id;
    }

    @Override
    public String toString() {
        return "com.book.model.Book[ id=" + _id + " ]";
    }
    // </editor-fold>

   
    public boolean hasReview(String lang) {
//    return getReviews()
//            .stream()
//            .anyMatch(review -> review.getLanguage().equals(lang));
        return true;
    }

   
    public String getShortTextOrDefault(String lang) {
        String shortText = getShortText(lang);
        return shortText.isEmpty() ? getShortText() : shortText;
    }

    
    public String getShortText(String lang) {
        // Java 8: return getTranslations().stream().filter(trans -> trans.getLanguage().equals(lang)).findAny();
        for (BookTrans trans : getTranslations()) {
            if (trans.getLanguage().equals(lang)) {
                return trans.getShorttext();
            }
        }
        return "";
    }

    /**
     * sets the book short text for the desired language
     *
     * @param lang
     * @param shortText
     */
    public void setShortText(String lang, String shortText) {
        String trimText = shortText.trim();
        for (BookTrans translation : getTranslations()) {
            if (translation.getLanguage().equals(lang)) {
                // found existing translation
                if (trimText.length() > 0) {
                    translation.setShorttext(trimText);
                } else {
                    getTranslations().remove(translation);
                }
                return;
            }
        }

        // no translation exists, create new one
        if (!trimText.isEmpty()) {
            BookTrans translation = new BookTrans();
            translation.setShorttext(trimText);
            translation.setBookId(_id);
            translation.setLanguage(lang);
            getTranslations().add(translation);
        }
    }

}
