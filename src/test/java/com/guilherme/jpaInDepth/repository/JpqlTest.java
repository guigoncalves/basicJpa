package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Course;
import com.guilherme.jpaInDepth.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpqlTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpql_basic() {
		Query query = em.createQuery("SELECT c FROM Course c");
		List resultList = ((Query) query).getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_typed() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_id_1", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c Where id = 1001 -> {}", resultList);
	}

	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c Where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Courses With no Student -> {}", resultList);
	}

	@Test
	public void jpql_courses_atleast_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c Where size(c.students) > 2 ", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Courses at least 2 students -> {}", resultList);
	}

	@Test
	public void jpql_courses_ordered() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Courses Ordered -> {}", resultList);
	}

	@Test
	public void jpql_student_passports_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s From Student s Where s.passport.number like '%123%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Student with passport -> {}", resultList);
	}

	/*
		JOIN - select c, s from Course c Join c.students s
		LEFT JOIN - select c, s from Course c Left Join c.students s
		CROSS JOIN - select c, s from Course c, Student s
	 */

	@Test
	public void jpql_joins() {
		Query query = em.createQuery("Select c, s From Course c Join c.students s");
		List<Object[]> resultList = query.getResultList();
		for(Object[] result : resultList) {
			logger.info("Courses {}, Students {}", result[0], result[1]);
		}
	}

}
