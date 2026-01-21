package com.example.taskapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.taskapi.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
	
//	private final Map<Long,Task> tasks = new HashMap<>();
//	private final AtomicLong counter= new AtomicLong();
//	
//	public TaskRepository() {
//		save(new Task(null,"Studiare spring boot","Completare la lezione"));
//		save(new Task(null,"Fare la spesa","Comprare gli ingredienti per la cena"));
//	}
//	
//	public List<Task> findAll(){
//		return new ArrayList<Task>(tasks.values());
//	}
//	
//	public Optional<Task> findById(Long id){
//		return Optional.ofNullable(tasks.get(id));
//	}
//	
//	public Task save(Task task) {
//		if(task.getId()==null) {
//			task.setId(counter.incrementAndGet());
//		}
//		tasks.put(task.getId(), task);
//		return task;
//	}
//
//	public void deleteById(Long id) {
//		tasks.remove(id);
//	}
//	
//	public boolean existsById(Long id) {
//		return tasks.containsKey(id);
//	}
	
	List<Task> findByCompletato(boolean completato);
	
	List<Task> findByTitoloContainingIgnoreCase(String titolo);
	
	List<Task> findByCategoriaId(Long categoriaId);
	
	@Query("SELECT t FROM Task t WHERE t.completato= false ORDER BY t.dataCreazione DESC")
	List<Task> findTaskNonCompletatiOrdinati();
	
	@Query("SELECT t FROM Task t WHERE t.categoria.nome= :nomeCategoria")
	List<Task> findByCategoriaNome(@Param("nomeCategoria") String nomeCategoria);
	
	@Query(value="SELECT * FROM tasks WHERE data_creazione > CURRENT_DATE - 7", nativeQuery= true)
	List<Task> findTaskUltimaSettimana();
}
