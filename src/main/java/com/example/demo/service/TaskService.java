package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ToDoExceptions;
import com.example.demo.mapper.TaskInDTOToTask;
import com.example.demo.persistence.TaskStatus;
import com.example.demo.persistence.entity.Task;
import com.example.demo.persistence.repository.TaskRepository;
import com.example.demo.service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Service
@Component
public class TaskService {

	private final TaskRepository repository;
	private final TaskInDTOToTask mapper;

	public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public Task createTask(TaskInDTO taskInDTO) {
		Task task = mapper.map(taskInDTO);
		return this.repository.save(task);
	}

	public List<Task> findAll() {
		return this.repository.findAll();
	}

	public List<Task> findAllByTaskStatus(TaskStatus status) {
		return this.repository.findAllByTaskStatus(status);
	}

	@Transactional
	public void updateTaskAsFinished(Long id) {
		Optional<Task> optionalTask = this.repository.findById(id);
		if (optionalTask.isEmpty()) {
			throw new ToDoExceptions("Task Not found", HttpStatus.NOT_FOUND);
		}
		this.repository.markTastkAsFinished(id);
	}
	
	public void deleteById(Long id) {
		Optional<Task> optionalTask = this.repository.findById(id);
		if(optionalTask.isEmpty()) {
			throw new ToDoExceptions("Task Not found", HttpStatus.NOT_FOUND);
		}
		this.repository.deleteById(id);
	}

}
