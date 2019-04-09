package com.guilherme.jpaInDepth.repository;

import com.guilherme.jpaInDepth.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="/courses")
public interface CourseSpringRepository extends JpaRepository<Course, Integer> {
    List<Course> findByName(String name);
    List<Course> findByNameAndId(String name, Integer id);
    List<Course> countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    List<Course> deleteByName(String name);

    @Query("Select c From Course c Where name like '%Steps%'")
    List<Course> findLikeName(String name);

    @Query(value="Select * From Course c", nativeQuery = true)
    List<Course> findAllNative();

    @Query(name="query_get_all_courses")
    List<Course> getAllNamed();
}