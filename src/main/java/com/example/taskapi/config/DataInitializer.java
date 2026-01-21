package com.example.taskapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.taskapi.model.Categoria;
import com.example.taskapi.model.Task;
import com.example.taskapi.repository.CategoriaRepository;
import com.example.taskapi.repository.TaskRepository;

@Component
public class DataInitializer implements CommandLineRunner{
	 @Autowired
	    private TaskRepository taskRepository;
	    
	    @Autowired
	    private CategoriaRepository categoriaRepository;
	    
	    @Override
	    public void run(String... args) throws Exception {
	        
	        // Controlla se il database è già popolato
	        if (categoriaRepository.count() > 0) {
	            System.out.println("Database già inizializzato.");
	            return;
	        }
	        
	        System.out.println("Inizializzazione database MySQL...");
	        
	        // Crea categorie
	        Categoria lavoro = new Categoria("Lavoro", "#FF5733");
	        Categoria personale = new Categoria("Personale", "#33FF57");
	        Categoria studio = new Categoria("Studio", "#3357FF");
	        Categoria urgente = new Categoria("Urgente", "#FF0000");
	        
	        categoriaRepository.save(lavoro);
	        categoriaRepository.save(personale);
	        categoriaRepository.save(studio);
	        categoriaRepository.save(urgente);
	        
	        System.out.println("Categorie create: " + categoriaRepository.count());
	        
	        // Crea task
	        Task task1 = new Task(null,"Preparare presentazione", "Per il meeting di lunedì");
	        task1.setCategoria(lavoro);
	        
	        Task task2 = new Task(null,"Studiare Spring Boot", "Completare esercizi JPA");
	        task2.setCategoria(studio);
	        
	        Task task3 = new Task(null,"Fare la spesa", "Comprare verdure e frutta");
	        task3.setCategoria(personale);
	        task3.setCompletato(true);
	        
	        Task task4 = new Task(null,"Preparare colloquio", "Ripassare Java e Design Patterns");
	        task4.setCategoria(studio);
	        
	        Task task5 = new Task(null,"Chiamare cliente urgente", "Rispondere entro oggi");
	        task5.setCategoria(urgente);
	        
	        taskRepository.save(task1);
	        taskRepository.save(task2);
	        taskRepository.save(task3);
	        taskRepository.save(task4);
	        taskRepository.save(task5);

	}

}
