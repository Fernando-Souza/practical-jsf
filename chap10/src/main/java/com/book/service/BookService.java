package com.book.service;

import com.book.model.Book;
import jakarta.ejb.Stateless;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Stateless
public class BookService extends AbstractManagerService<Book> {

    public BookService() {
        super();
    }

    public List<Book> findAllBy(String field) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root request = cq.from(Book.class);
        Order order = cb.asc(request.get(field));
        cq.select(request).orderBy(order);
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Book> findByCategory(int categoryId) {
        String statement = "SELECT Book.* FROM Book join mapBookCategory on bookId = bcBookId and bcCategoryId = ?1;";
        return getEntityManager().createNativeQuery(statement, Book.class).setParameter(1, categoryId).getResultList();

    }

    public List<Book> findBySearchText(String fragment, String languageCode) {
        String sqlQuery = "select Book.*"
                + " from Book left join BookTrans on bookId = btBookId and btLanguage = ?1"
                //" where bookTitle like (?2) or bookSubtitle like (?2) or bookAuthor like (?2) or bookPublisher like (?2) or isnull (btShortText, bookShorttext) like (?2) or bookISBN like (?2) or replace (bookISBN, '-', '') like (?2)" // MS SQL Server
                + " where bookTitle like (?2) or bookSubtitle like (?2) or bookAuthor like (?2) or bookPublisher like (?2) or ifnull (btShortText, bookShorttext) like (?2) or bookISBN like (?2) or replace (bookISBN, '-', '') like (?2)"
                + " order by bookId desc;";
        return getEntityManager().createNativeQuery(sqlQuery, Book.class).setParameter(1, languageCode).setParameter(2, "%" + fragment + "%").getResultList();
    }

}
