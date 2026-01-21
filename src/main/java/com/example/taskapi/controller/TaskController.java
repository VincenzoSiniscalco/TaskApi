package com.example.taskapi.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskapi.model.Task;
import com.example.taskapi.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	//visualizzo tutte le task
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks(){
		List<Task> tasks= taskService.getAllTasks();
		return ResponseEntity.ok(tasks);
	}
	//ritorno singolo task
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id){
		return taskService.getTaskById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	//creo nuovo task e imposto il codice di risposta http e il body con la task creata
	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task task){
		Task createdTask = taskService.createTask(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
	}
	//aggiorno task e se l'aggiornamento fallisce restituisco codice http not found con body vuoto
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody Task task){
		try {
			Task updatedTask= taskService.updateTask(id, task);
			return ResponseEntity.ok(updatedTask);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	//elimino task e se non esiste restituisco http not found con body vuoto
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id){
		try {
			taskService.deleteTask(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
