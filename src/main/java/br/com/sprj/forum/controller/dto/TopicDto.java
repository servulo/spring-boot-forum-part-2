package br.com.sprj.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sprj.forum.modelo.Topic;
import lombok.Getter;

@Getter
public class TopicDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;

    public TopicDto(Topic topic) {
	this.id = topic.getId();
	this.title = topic.getTitle();
	this.message = topic.getMessage();
	this.creationDate = topic.getCreationDate();
    }

    public static List<TopicDto> converter(List<Topic> topics) {
	return topics.stream().map(TopicDto::new).collect(Collectors.toList());
    }

}