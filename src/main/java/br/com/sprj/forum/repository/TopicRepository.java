package br.com.sprj.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sprj.forum.modelo.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    
    List<Topic> findByCourseName(String courseName);

}