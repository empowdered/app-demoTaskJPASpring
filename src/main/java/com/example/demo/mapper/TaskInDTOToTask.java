package com.example.demo.mapper;

import java.time.LocalDateTime;

import com.example.demo.persistence.TaskStatus;
import com.example.demo.persistence.entity.Task;
import com.example.demo.service.dto.TaskInDTO;


public class TaskInDTOToTask implements IMapper<TaskInDTO,Task> {

	@Override
	public Task map(TaskInDTO in) {
		// TODO Auto-generated method stub
		Task task = new Task();
		task.setTitle(in.getTitle());
		task.setDescription(in.getDescription());
		task.setEta(in.getEta());
		task.setCreatedDate(LocalDateTime.now());
		task.setFinished(false);
		task.setTaskStatus(TaskStatus.ON_TIME);
		return task;
	}

}
