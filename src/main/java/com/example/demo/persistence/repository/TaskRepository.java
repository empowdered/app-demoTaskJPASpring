package com.example.demo.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.persistence.TaskStatus;
import com.example.demo.persistence.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	public List<Task> findAllByTaskStatus(TaskStatus status);
	
	@Modifying
	@Query(value = "UPDATE TASK SET FINISHED=true WHERE ID=:id", nativeQuery = true)
	public void markTastkAsFinished(@Param("id") Long id);
}
