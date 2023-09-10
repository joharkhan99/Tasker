package com.jka.taskerbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	public TaskController() {
	}
	
	@GetMapping("/save")
	public void SaveTask(String s) {
		System.out.println("GOT CALL");
	}
	

}
