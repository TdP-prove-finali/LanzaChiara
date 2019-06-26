package it.polito.tdp.corredino.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corredino.model.Categories;
import it.polito.tdp.corredino.model.Product;


public class ListinoDAO {
	
	
	
	public List<Product> getAllProductCat(String season, Categories cat) {
		
		String sql = "SELECT idnum,NAME,category,price,season,sellerprice  FROM listino WHERE category=?  AND (season=? or season ='')";
		List<Product> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, cat.getCategoria());
			st.setString(2, season);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Product p = new Product(res.getString("idnum"), res.getString("NAME"), res.getDouble("price"), res.getString("season"), res.getDouble("sellerprice"));
				p.setCategory(cat);
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
	
	public List<Categories> getAllCat(String season){
	String sql = "SELECT c.Categoria,c.MIN,c.MAX,c.Proporzione, AVG(l.price) FROM categorie c, listino l WHERE (l.season=? || l.season='') AND  l.category=c.Categoria GROUP BY l.category ORDER BY AVG(l.price) desc";
	List<Categories> result = new ArrayList<>();
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, season);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Categories c = new Categories(res.getString("c.Categoria"),res.getInt("c.Min"),res.getInt("c.Max"),res.getDouble("c.Proporzione"));
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

	public double getmin() {
		String sql = "SELECT MIN(l.price) AS min FROM listino l";
		double r=0;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			if(res.next())
				r=res.getInt("min");
			conn.close();
			
			return r;
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
	
		
	


