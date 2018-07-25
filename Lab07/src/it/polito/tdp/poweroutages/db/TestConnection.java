package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.PowerOutages;

public class TestConnection {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Test PASSED");

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
		
		PowerOutageDAO dao = new PowerOutageDAO();
		List<PowerOutages> eventi = new ArrayList<>(dao.getPowerOutages("MAAC"));
		System.out.println(eventi.toString());
		
	}

}
