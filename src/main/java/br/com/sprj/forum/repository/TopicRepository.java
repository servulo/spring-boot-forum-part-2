package br.com.sprj.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sprj.forum.modelo.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    
    Page<Topic> findByCourseName(String courseName, Pageable pageable);

}