package it.polito.tdp.corredino.db;

import java.sql.Connection;

import it.polito.tdp.corredino.db.ConnectDB;

public class TestDB {
public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Test PASSED");

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
	}

}
