package com.example.taskapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskapi.model.Task;
import com.example.taskapi.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	public Optional<Task> getTaskById(Long id){
		return taskRepository.findById(id);
	}
	
	public List<Task> getTaskByStatus(boolean completato){
		return taskRepository.findByCompletato(completato);
	}
	
	public List<Task> searchTaskByTitolo(String titolo){
		return taskRepository.findByTitoloContainingIgnoreCase(titolo);
	}
	public List<Task> searchTaskByCategoria(Long categoriaId){
		return taskRepository.findByCategoriaId(categoriaId);
	}
	
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	public Task updateTask(Long id, Task details) {
		Task task= taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task con id "+ id + "non trovato!"));
		task.setTitolo(details.getTitolo());
		task.setDescrizione(details.getDescrizione());
		task.setCompletato(details.isCompletato());
		
		if(details.getCategoria()!=null) {
			task.setCategoria(details.getCategoria());
		}
		
		return taskRepository.save(task);
	}
	public void deleteTask(Long id) {
		if(!taskRepository.existsById(id)) {
			throw new RuntimeException("Task con id "+ id + "non trovato!");
		}
		taskRepository.deleteById(id);
	}
 }
