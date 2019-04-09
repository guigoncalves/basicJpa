package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Address;
import com.guilherme.jpaInDepth.entity.Course;
import com.guilherme.jpaInDepth.entity.Passport;
import com.guilherme.jpaInDepth.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	public void retrieveStudentWithPassport() {
		Student student = em.find(Student.class, 4001);
		logger.info("Student details -> {}", student);
		logger.info("Passport details -> {}", student.getPassport());
	}

	@Test
	@Transactional
	public void saveAddressEmbedded() {
		Student student = em.find(Student.class, 4001);
		student.setAddress(new Address("line 1","line 2","London"));
		em.flush();
		logger.info("Student details -> {}", student);
		logger.info("Address details -> {}", student.getAddress());
	}

	@Test
	@Transactional
	public void retrievePassportWithStudent() {
		Passport passport = em.find(Passport.class, 2001);
		logger.info("Passport details -> {}", passport);
		logger.info("Student details -> {}", passport.getStudent());
	}


}
