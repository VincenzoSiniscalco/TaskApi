package com.example.taskapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tasks")
public class Task {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotBlank(message = "Il titolo è obbligatorio")
	    @Size(min = 3, max = 100, message = "Il titolo deve essere tra 3 e 100 caratteri")
	    @Column(nullable = false, length = 100)
	    private String titolo;
	    
	    @Size(max = 500, message = "La descrizione non può superare 500 caratteri")
	    @Column(length = 500)
	    private String descrizione;
	    
	    @Column(nullable = false)
	    private boolean completato = false;
	    
	    @Column(name = "data_creazione", nullable = false, updatable = false)
	    private LocalDateTime dataCreazione;
	    
	    @ManyToOne
	    @JoinColumn(name = "categoria_id")
	    private Categoria categoria;
	    
	    @PrePersist
	    protected void onCreate() {
	        dataCreazione = LocalDateTime.now();
	    }
	
	public Task() {
		this.dataCreazione= LocalDateTime.now();
		this.completato=false;
	}
	
	public Task(Long id, String titolo, String descrizione) {
		this();
		this.id=id;
		this.titolo=titolo;
		this.descrizione=descrizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isCompletato() {
		return completato;
	}

	public void setCompletato(boolean completato) {
		this.completato = completato;
	}

	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
