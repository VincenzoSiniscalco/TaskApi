package com.example.taskapi.model;

import java.time.LocalDateTime;

public class Task {
	private Long id;
	private String titolo;
	private String descrizione;
	private boolean completato;
	private LocalDateTime dataCreazione;
	
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
	
	
}
