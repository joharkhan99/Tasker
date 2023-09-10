package com.jka.taskerbackend.common;

public class Task {
	private int Id;
	private String task;
	
	public Task(int id, String task) {
		Id = id;
		this.task = task;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Task [Id=" + Id + ", task=" + task + "]";
	}
	

}
