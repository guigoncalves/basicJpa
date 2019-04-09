package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Course;
import com.guilherme.jpaInDepth.entity.Passport;
import com.guilherme.jpaInDepth.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager em;

    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Integer id) {
        Student student = findById(id);
        em.remove(student);
    }

    public Student save(Student student) {
        if (student.getId()==null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public void playWithEntityManager() {
        Passport passport = new Passport("E1234");
        em.persist(passport);

        Student student = new Student("Guilherme");
        student.setPassport(passport);
        em.persist(passport);
    }

    public void insertStudentAndCourse() {
        Student student = new Student("Jack");
        Course course = new Course("100 steps to fall");
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
    }
}
