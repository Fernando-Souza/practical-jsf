package com.book.service;

import com.book.model.Categoria;
import jakarta.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class CategoryService extends AbstractManagerService<Categoria> {

    public CategoryService() {

        super();
    }

    public Map<Integer, Integer> countBooksPerCategory() {
        String statement = "SELECT bcCategoryId, count(0) as cntBooks FROM Book join mapBookCategory on bookId = bcBookId group by bcCategoryId;";
        List data = getEntityManager().createNativeQuery(statement).getResultList();
        Map<Integer, Integer> result = new HashMap<>();
        for (Object x : data) {
            Object[] info = (Object[]) x;
            int categoryId = (int) info[0];
            int count = (int) (long) info[1];
            result.put(categoryId, count);
        }
        return result;
    }

}
