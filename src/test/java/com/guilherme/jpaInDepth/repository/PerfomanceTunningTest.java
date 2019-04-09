package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Course;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.List;

public class PerfomanceTunningTest {

    @Autowired
    EntityManager em;

    // For each course we make another query to retrieve students
    @Test
    public void nPlus_Problem() {
        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
        for (Course course: courses) {
            course.getStudents();
        }
    }

    @Test
    public void nPlus_solvint_EntityGraph() {
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");

        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        for (Course course: courses) {
            course.getStudents();
        }
    }
}
