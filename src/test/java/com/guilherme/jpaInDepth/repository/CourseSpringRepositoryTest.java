package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseSpringRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringRepository courseRepository;

	@Test
	public void findById_Present() {
		Optional<Course> course = courseRepository.findById(1001);
		assertTrue("Course is present", course.isPresent());
	}

	@Test
	public void findById_NotPresent() {
		Optional<Course> course = courseRepository.findById(2001);
		assertFalse("Course is not present", course.isPresent());
	}

	@Test
	public void findById_PlayWithRepo() {
		Course course = courseRepository.save(new Course("Spring Data in 50 steps"));
		course.setName("New Name");

		courseRepository.save(course);
		logger.info("Courses -> {}", courseRepository.findAll());
	}

	@Test
	public void sort() {
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		logger.info("Courses -> {}", courseRepository.findAll(sort));
	}

	@Test
	public void pagination() {
		PageRequest page = PageRequest.of(0,2);
		Page<Course> firstPage = courseRepository.findAll(page);
		logger.info("First Page-> {}", firstPage.getContent());

		Pageable secondPageble = firstPage.nextPageable();
		Page<Course> secondPage = courseRepository.findAll(secondPageble);
		logger.info("Second Page-> {}", secondPage.getContent());
	}

}