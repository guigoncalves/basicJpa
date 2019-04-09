package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Course;
import com.guilherme.jpaInDepth.entity.Employee;
import com.guilherme.jpaInDepth.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

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

    public Employee save(Employee employee) {
        if (employee.getId()==null) {
            em.persist(employee);
        } else {
            em.merge(employee);
        }
        return employee;
    }

    public List<Employee> retrieveAllEmployees() {
        return em.createQuery("select e from Employee e").getResultList();
    }
}
