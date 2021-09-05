package br.com.sprj.forum.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String message;

    @ManyToOne
    private Topic topic;

    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    private User author;
    
    private Boolean solution = false;

}