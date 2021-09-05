package br.com.sprj.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sprj.forum.modelo.Topic;
import br.com.sprj.forum.modelo.TopicStatus;
import lombok.Getter;

@Getter
public class TopicDetailDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String author;
    private TopicStatus status;
    private List<AnswerDto> answers;

    public TopicDetailDto(Topic topic) {
	this.id = topic.getId();
	this.title = topic.getTitle();
	this.message = topic.getMessage();
	this.creationDate = topic.getCreationDate();
	this.author = topic.getAuthor().getName();
	this.status = topic.getStatus();
	this.answers = new ArrayList<>();
	this.answers.addAll(topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
    }

}
