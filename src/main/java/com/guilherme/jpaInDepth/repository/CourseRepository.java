package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Course;
import com.guilherme.jpaInDepth.entity.Review;
import com.guilherme.jpaInDepth.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Course findById(Integer id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Integer id) {
        Course course = findById(id);
        em.remove(course);
    }

    public Course save(Course course) {
        if (course.getId()==null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Angular JS - mit Guigo");
        Course course2 = new Course("React JS - mit gilgo");
        em.persist(course1);
        em.persist(course2);

        em.flush();
        em.clear();
        //em.detach(course1);s

        course1.setName("Updated Angular Js");
        course2.setName("Updated React Js");

        //get data from database
        em.refresh(course1);
    }

    public void addReviews(Integer courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("Course reviews -> {}", course.getReviews());

        for(Review review : reviews) {
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }

    public void addHardcodedReview() {
        Course course = em.find(Course.class,1001);
        Review review = new Review(ReviewRating.FIVE, "Very good");

        course.addReview(review);
        review.setCourse(course);

        em.persist(review);
    }

}
