package br.com.sprj.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sprj.forum.modelo.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByName(String courseName);

}
