package br.com.sprj.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.sprj.forum.modelo.Topic;
import br.com.sprj.forum.repository.TopicRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicUpdateForm {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String message;

    public Topic update(Long id, TopicRepository topicRepository) {
	Topic topic = topicRepository.getById(id);
	topic.setTitle(title);
	topic.setMessage(message);
	return topic;
    }

}