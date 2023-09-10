package com.jka.taskerbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jka.taskerbackend.common.Task;

@RestController
@RequestMapping("/api")
@CrossOrigin()
public class TaskController {
	private List<Task> taskList;
	private int id = 0;
	
	public TaskController() {
		taskList = new ArrayList<>();
	}
	
	@GetMapping("/")
	public List<Task> getTasks(){
		return taskList;
	}
	
	@PostMapping("/save")
	public Task SaveTask(@RequestBody Task task) {
		task.setId(id++);
//		Task task1 = new Task(id, task.getTask());
		taskList.add(task);
		
		return task;
	}
	

}
