/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.book.service;

import com.book.model.Review;
import com.book.model.ReviewLink;
import com.book.utilities.ReviewInfo;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ReviewService extends AbstractManagerService<Review> {
    
    public ReviewService() {
    super();
  }

  public Review find(int bookId, String language) {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Review> query = cb.createQuery(Review.class);
    Root<Review> request = query.from(Review.class);
    query.select(request).where(cb.and(cb.equal(request.get("_bookId"), bookId)), cb.equal(request.get("_language"), language));
    TypedQuery<Review> q = getEntityManager().createQuery(query);
    return q.getSingleResult();
  }

  public List<Review> findRecent(String language) {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Review> query = cb.createQuery(Review.class);
    Root request = query.from(Review.class);
    query.select(request).where(cb.equal(request.get("_language"), language)).orderBy(cb.desc(request.get("_creationDate")));
    return getEntityManager().createQuery(query).setMaxResults(5).getResultList();
  }

  public List<ReviewInfo> findReviews() {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<ReviewInfo> query = cb.createQuery(ReviewInfo.class);
    Root request = query.from(Review.class);
    query.select(cb.construct(ReviewInfo.class, request.get("_book").get("title"), request.get("_language"), request.get("_bookId")));
    return getEntityManager().createQuery(query).getResultList();
  }

  public List<ReviewInfo> findReviewLinks() {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<ReviewInfo> query = cb.createQuery(ReviewInfo.class);
    Root request = query.from(ReviewLink.class);
    query.select(cb.construct(ReviewInfo.class, request.get("_book").get("title"), request.get("_language"), request.get("_url")));
    return getEntityManager().createQuery(query).getResultList();
  }
    
}
