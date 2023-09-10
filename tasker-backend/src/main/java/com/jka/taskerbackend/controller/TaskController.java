package com.jka.taskerbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public List<Task> SaveTask(@RequestBody Task task) {
		task.setId(id++);
		taskList.add(task);
		
		return taskList;
	}
	
	@DeleteMapping("/delete/{id}")
	public List<Task> DeleteTask(@PathVariable int id) {
		if(!taskList.isEmpty()) 
			taskList.removeIf(task -> task.getId() == id);
		return taskList;
	}

	@PutMapping("/update/{id}")
	public List<Task> UpdateTask(@PathVariable int id, @RequestBody Task updatedTask) {
		for (Task task: taskList) {
			if (task.getId() == id) {
				task.setTask(updatedTask.getTask());
				break;
			}
		}
		return taskList;
	}
}
