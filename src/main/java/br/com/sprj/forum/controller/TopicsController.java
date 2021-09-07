package br.com.sprj.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sprj.forum.controller.dto.TopicDetailDto;
import br.com.sprj.forum.controller.dto.TopicDto;
import br.com.sprj.forum.controller.form.TopicForm;
import br.com.sprj.forum.controller.form.TopicUpdateForm;
import br.com.sprj.forum.modelo.Topic;
import br.com.sprj.forum.repository.CourseRepository;
import br.com.sprj.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @Cacheable(value = "topicsList")
    public Page<TopicDto> list(@RequestParam(required = false) String courseName,
	    @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
	if (courseName == null) {
	    Page<Topic> topics = topicRepository.findAll(pageable);
	    return TopicDto.converter(topics);
	} else {
	    Page<Topic> topics = topicRepository.findByCourseName(courseName, pageable);
	    return TopicDto.converter(topics);
	}
    }
    
    /*
    @GetMapping
    public Page<TopicDto> list(@RequestParam(required = false) String courseName, @RequestParam int page, @RequestParam int size, @RequestParam String order ) {
	Pageable pageable = PageRequest.of(page, sizes, Direction.ASC, order);
	if (courseName == null) {
	    Page<Topic> topics = topicRepository.findAll(pageable);
	    return TopicDto.converter(topics);
	} else {
	    Page<Topic> topics = topicRepository.findByCourseName(courseName, pageable);
	    return TopicDto.converter(topics);
	}
    } 
    */   

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailDto> detail(@PathVariable("id") Long id) {
	Optional<Topic> optionalTopic = topicRepository.findById(id);
	if (optionalTopic.isPresent()) {
	    return ResponseEntity.ok(new TopicDetailDto(optionalTopic.get()));
	}
	return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "topicsList", allEntries = true)
    public ResponseEntity<TopicDto> add(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) {
	Topic topic = topicForm.converter(courseRepository);
	topicRepository.save(topic);
	URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
	return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicsList", allEntries = true)
    public ResponseEntity<TopicDto> update(@PathVariable("id") Long id,
	    @RequestBody @Valid TopicUpdateForm topicUpdateForm) {
	Optional<Topic> optionalTopic = topicRepository.findById(id);
	if (optionalTopic.isPresent()) {
	    Topic topic = topicUpdateForm.update(id, topicRepository);
	    return ResponseEntity.ok(new TopicDto(topic));
	}
	return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicsList", allEntries = true)
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
	Optional<Topic> optionalTopic = topicRepository.findById(id);
	if (optionalTopic.isPresent()) {
	    topicRepository.deleteById(id);
	    return ResponseEntity.ok().build();
	}
	return ResponseEntity.notFound().build();
    }

}