package br.com.sprj.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sprj.forum.modelo.Course;
import br.com.sprj.forum.modelo.Topic;
import br.com.sprj.forum.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String message;

    @NotNull
    @NotEmpty
    private String courseName;

    public Topic converter(CourseRepository courseRepository) {
	Course course = courseRepository.findByName(courseName);
	return new Topic(title, message, course);
    }

}