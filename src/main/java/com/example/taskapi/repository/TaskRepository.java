package com.example.taskapi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.taskapi.model.Task;

@Repository
public class TaskRepository {
	
	private final Map<Long,Task> tasks = new HashMap<>();
	private final AtomicLong counter= new AtomicLong();
	
	public TaskRepository() {
		save(new Task(null,"Studiare spring boot","Completare la lezione"));
		save(new Task(null,"Fare la spesa","Comprare gli ingredienti per la cena"));
	}
	
	public List<Task> findAll(){
		return new ArrayList<Task>(tasks.values());
	}
	
	public Optional<Task> findById(Long id){
		return Optional.ofNullable(tasks.get(id));
	}
	
	public Task save(Task task) {
		if(task.getId()==null) {
			task.setId(counter.incrementAndGet());
		}
		tasks.put(task.getId(), task);
		return task;
	}

	public void deleteById(Long id) {
		tasks.remove(id);
	}
	
	public boolean existsById(Long id) {
		return tasks.containsKey(id);
	}
}
