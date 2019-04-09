package com.guilherme.jpaInDepth;

import com.guilherme.jpaInDepth.entity.*;
import com.guilherme.jpaInDepth.repository.CourseRepository;
import com.guilherme.jpaInDepth.repository.EmployeeRepository;
import com.guilherme.jpaInDepth.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class JpaInDepthApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaInDepthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee fullTimeEmpl = new FullTimeEmployee("Jack", new BigDecimal(1000));
		Employee partTimeEmpl = new PartTimeEmployee("Fred", new BigDecimal(50));

		employeeRepository.save(fullTimeEmpl);
		employeeRepository.save(partTimeEmpl);

		logger.info("All employees -> {}",employeeRepository.retrieveAllEmployees());
	}
}
