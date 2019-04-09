package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Course;
import com.guilherme.jpaInDepth.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteiraTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void criteria_basic() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);


		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		logger.info("Course -> {}", query.getResultList());
	}

	@Test
	public void criteria_like() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);

		Predicate like = cb.like(courseRoot.get("name"), "%Steps");
		cq.where(like);

		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		logger.info("Course -> {}", query.getResultList());
	}

	@Test
	public void criteria_empty() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);

		Predicate empty = cb.isEmpty(courseRoot.get("students"));
		cq.where(empty);

		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		logger.info("Course -> {}", query.getResultList());
	}

	@Test
	public void criteria_join() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);

		Join<Object,Object> join = courseRoot.join("students");

		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		logger.info("Course -> {}", query.getResultList());
	}

}
