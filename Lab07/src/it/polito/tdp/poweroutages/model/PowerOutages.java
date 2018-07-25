package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PowerOutages {
	private int anno;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	private long oreDisservizio;
	private int clienti;
	
	public PowerOutages() {		
	}
	
	public PowerOutages(LocalDateTime dataInizio, LocalDateTime dataFine, int clienti) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.clienti = clienti;
	}
	
	// getter e setter
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}
	public long getOreDisservizio() {
		return oreDisservizio;
	}
	public void setOreDisservizio(long oreDisservizio) {
		this.oreDisservizio = oreDisservizio;
	}
	public int getClienti() {
		return clienti;
	}
	public void setClienti(int clienti) {
		this.clienti = clienti;
	}
	
	
	@Override
	public String toString() {
		return "\n" + anno + "  " + dataInizio + "  " + dataFine + "  "
				+ oreDisservizio + "  " + clienti;
	}
	
	
		
	
	
}
