package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.JpaInDepthApplication;
import com.guilherme.jpaInDepth.entity.Course;
import com.guilherme.jpaInDepth.entity.Review;
import com.guilherme.jpaInDepth.entity.ReviewRating;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
    EntityManager em;

	@Test
	public void findById_Basic() {
		Course course = courseRepository.findById(1001);
		assertEquals("Jpa in 5 Steps", course.getName());
	}

	@Test
	@DirtiesContext
	public void deleteById_Basic() {
		courseRepository.deleteById(1003);
		assertNull(courseRepository.findById(1003));
	}

    @Test
    @DirtiesContext
    public void update_Basic() {
        Course course = courseRepository.findById(1001);
        course.setName("Updated Course Name");
        courseRepository.save(course);

        Course course1 = courseRepository.findById(1001);
        assertEquals("Updated Course Name", course1.getName());
    }

	@Test
	public void contextLoads() { logger.info("Tests running");
	}

}
