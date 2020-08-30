package com.bvn.demo.jerseyrest.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bvn.demo.jerseyrest.model.Alien;


public class AlienRepository {
	
	Connection con = null;
	
	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/restdb";
		String uname = "root";
		String passwd = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, uname, passwd);
			System.out.println("Connection is created ...");
		}
		catch(Exception e) {
			System.out.println("Caught Exception: " + e);
			e.printStackTrace();
		}
	}
	
	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<>();
		String sql = "select * from alien";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while( rs.next() ) {
				Alien alien = new Alien();
				alien.setId(rs.getInt(1));
				alien.setName(rs.getString(2));
				alien.setPoints(rs.getInt(3));
				aliens.add(alien);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return aliens;
	}
	
	public Alien getAlien(int id) {
		Alien alien = new Alien();
		String sql = "select * from alien where id=" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if( rs.next() ) {
				alien.setId(rs.getInt(1));
				alien.setName(rs.getString(2));
				alien.setPoints(rs.getInt(3));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return alien;
	}

	public void create(Alien alien) {
		String sql = "insert into alien values(?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, alien.getId());
			st.setString(2, alien.getName());
			st.setInt(3, alien.getPoints());
			System.out.println("About to create " + alien);
			st.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Alien alien) {
		String sql = "update alien set name=?, points=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, alien.getName());
			st.setInt(2, alien.getPoints());
			st.setInt(3, alien.getId());
			st.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "delete from alien where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
