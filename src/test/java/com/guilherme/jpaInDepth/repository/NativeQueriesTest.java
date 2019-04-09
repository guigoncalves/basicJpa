package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void nativeQuery_basic() {
		Query query = em.createNativeQuery("SELECT * FROM Course WHERE is_deleted=0", Course.class);
		List resultList = query.getResultList();
		logger.info("Select * From Course -> {}", resultList);
	}

	@Test
	public void nativeQuery_with_parameters() {
		Query query = em.createNativeQuery("Select * From Course Where id = ? and is_deleted=0", Course.class);
		query.setParameter(1, 1001);
		List<Course> resultList = query.getResultList();
		logger.info("Select * From Course Where id = ? -> {}", resultList);
	}

	@Test
	public void nativeQuery_with_named_Parameter() {
		Query query = em.createNativeQuery("Select * From Course Where id = :id AND is_deleted=0", Course.class);
		query.setParameter("id", 1001);
		List<Course> resultList = query.getResultList();
		logger.info("Select * From Course Where id = ? -> {}", resultList);
	}

	@Test
	@Transactional
	public void executeQuery() {
		Query query = em.createNativeQuery("Update Course set last_updated_date=sysdate()", Course.class);
		Integer nrOfRows = query.executeUpdate();
		logger.info("Update Course set last_updated_date=sysdate() -> {}", nrOfRows);
	}


}
