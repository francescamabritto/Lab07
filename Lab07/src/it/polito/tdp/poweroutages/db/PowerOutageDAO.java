package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutages> getPowerOutages(String nerc){
		List<PowerOutages> lista = new ArrayList<>();
		String sql = "SELECT date_event_began , date_event_finished, customers_affected FROM PowerOutages as po, Nerc as n WHERE n.id = po.nerc_id AND n.value=? ORDER BY date_event_began";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nerc);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				
				LocalDateTime dateStart = res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime dateFinish = res.getTimestamp("date_event_finished").toLocalDateTime();
				int affected = res.getInt("customers_affected");
				long hours = Duration.between(dateStart, dateFinish).toHours();
				
				PowerOutages po = new PowerOutages(dateStart, dateFinish , affected);
				
				po.setAnno(dateStart.getYear());
				po.setOreDisservizio(hours);	
				
				lista.add(po);
			}
			
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return lista;
	}
	

}
