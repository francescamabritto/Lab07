package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		// System.out.println(model.getNercList());
		// System.out.println(model.getTuttiGliEventi("MAAC"));
		
		
		System.out.println(model.trovaSequenza("FRCC", 4, 200));
		
		System.out.println("Tot people affected: " + model.getMaxPersone());
		System.out.println("Tot hours of outages: " + model.getNumOre());
		
	}
}
