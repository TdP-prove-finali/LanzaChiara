package it.polito.tdp.corredino.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corredino.model.Product;


public class ListinoDAO {
	
	List<Product> result = new ArrayList<>();
	
	public List<Product> getAllProduct(String season) {
		
		String sql = "SELECT idnum,NAME,category,price,season,sellerprice  FROM listino WHERE season=? or season =''";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, season);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Product p = new Product(res.getString("idnum"), res.getString("NAME"), res.getString("category"), res.getFloat("price"), res.getString("season"), res.getFloat("sellerprice"));
				result.add(p);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}

/*	public List<Product> getAll(String string) {
		if(result.isEmpty())
			this.getAllProduct();
		List<Product> cat = new ArrayList<>();
		for(Product p : result) {
			if(p.getCategory().equals(string)) {
				Product p2 = new Product(p);
				cat.add(p2);
		}
		
	}
		return cat;
	
	}*/
	
	public List<String> getAllCat(){
	String sql = "SELECT DISTINCT(category) FROM listino ";
	List<String> result = new ArrayList<>();
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				String c = res.getString("category");
				result.add(c);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	public int totProdotti() {
		String sql = "SELECT COUNT(*) as tot FROM listino";
		int r=0;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			if(res.next())
				r=res.getInt("tot");
			conn.close();
			
			return r;
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
	
		
	


