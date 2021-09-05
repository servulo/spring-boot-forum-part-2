package br.com.sprj.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.sprj.forum.modelo.Answer;
import lombok.Getter;

@Getter
public class AnswerDto {
    
    private Long id;
    private String message;
    private LocalDateTime creationDate;
    private String authorName;
    
    public AnswerDto(Answer answer) {
	this.id = answer.getId();
	this.message = answer.getMessage();
	this.creationDate = answer.getCreationDate();
	this.authorName = answer.getAuthor().getName();
    }
    
}
