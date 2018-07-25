package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;



import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	private long numOre = 0;
	private int maxPersone = 0;
	List<PowerOutages> soluzione;
	
	
	public Model() {
		podao = new PowerOutageDAO();
		soluzione = new ArrayList<>();
	}
	

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<String> getListaNerc() {
		List<String> lista = new ArrayList<>();
		for(Nerc n: getNercList()) {
			lista.add(n.getValue());
		}
		return lista;
	}
	
	public List<PowerOutages> trovaSequenza(String nerc, int numAnniMax, int numOreMax) {
		
		List<PowerOutages> parziale = new ArrayList<>();
		List<PowerOutages> eventiDisponibili = this.podao.getPowerOutages(nerc);
		
		ricorsione(eventiDisponibili, numAnniMax, numOreMax, parziale);
		
		this.numOre = this.sommaOreParziale(this.soluzione);
		
		return soluzione;
	}
	
	private void ricorsione(List<PowerOutages> eventiDisponibili, int numAnniMax, long numOreMax, List<PowerOutages> parziale) {
		
		if(!parziale.isEmpty()) 
			eventiDisponibili = this.determinaAggiungibili(parziale, eventiDisponibili, numAnniMax, numOreMax);
		
		List<PowerOutages> eventiAncoraDisponibili = new ArrayList<>(eventiDisponibili);

		//caso terminale
		if( eventiDisponibili.isEmpty()  ) { //CONDIZIONE DI TERMINAZIONE
			
			int clientiColpiti = this.totClientiColpiti(parziale);

			if(clientiColpiti >  maxPersone) { 
				maxPersone = clientiColpiti;
				soluzione = new ArrayList<>(parziale);
			}
			return;
		}
		
		// ricorsione
		for(PowerOutages po: eventiDisponibili) {	
					
			parziale.add(po);
			eventiAncoraDisponibili.remove(po);
			ricorsione(eventiAncoraDisponibili, numAnniMax, numOreMax, parziale);
			parziale.remove(po);
			eventiAncoraDisponibili.add(po);
				
			
		}
	
	}
	
	private int totClientiColpiti(List<PowerOutages> parziale) {
		int totClienti = 0;
		for(PowerOutages p: parziale) {
			totClienti += p.getClienti();
		}
		return totClienti;
	}


	private List<PowerOutages> determinaAggiungibili(List<PowerOutages> parziale,
			List<PowerOutages> eventiDisponibili, int numAnniMax, long numOreMax) {
		
		List<PowerOutages> res = new ArrayList<>();
		
		for(PowerOutages po: eventiDisponibili) 
				if(parziale.get(0).getAnno()+numAnniMax >= po.getAnno() &&  
						this.sommaOreParziale(parziale)+po.getOreDisservizio() <= numOreMax) 
					
					res.add(po);		
			
		return res;
	}


	private long sommaOreParziale(List<PowerOutages> parziale) {
		long sum = 0;
		for(PowerOutages p: parziale) {
			sum += p.getOreDisservizio();
		}
		return sum;
	}
		
		
		public long getNumOre() {
			return numOre;
		}
		
		public int getMaxPersone() {
			return maxPersone;
		}
		public void setMaxPersone(int maxPersone) {
			this.maxPersone = maxPersone;
		}
}
