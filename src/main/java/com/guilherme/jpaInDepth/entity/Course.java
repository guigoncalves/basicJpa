package com.guilherme.jpaInDepth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedQueries(value={
        @NamedQuery(name="query_get_all_courses", query = "Select c From Course c"),
        @NamedQuery(name="query_get_all_courses_fetch_join", query = "Select c From Course c JOIN FETCH c.students s"),
        @NamedQuery(name="query_get_id_1", query = "Select c From Course c Where id = 1")
})
@SQLDelete(sql="update course set is_deleted=true where id=?")
@Where(clause="is_deleted=false")
@Cacheable
@Entity
public class Course {

    private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    // By default LAZY Fetch
    @OneToMany(mappedBy = "course")
    List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    List<Student> students = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime creationDate;

    private boolean isDeleted;

    @PreRemove
    public void preRemove() {
        //To update the entity
        LOGGER.info("Setting isDeleted to True");
        this.isDeleted = true;
    }

    public Course(String name) {
        this.name = name;
    }

    public Course() { }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
