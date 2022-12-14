package com.nttdata.mvn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class App {
	public static void main(String[] args) {
		// Datos de la conexion
		String username = "?user=usuario";
		String sslSecure = "&useSSL=false";
		String password = "&password=1234";
		String url = "jdbc:mysql://localhost:3306/nttdata_hibernate_taller1" + username + password + sslSecure;

		System.out.println("Conectando a la base de datos...");

		Connection connection;

		// Conexion a la base de datos
		try {
			connection = DriverManager.getConnection(url);
			System.out.println("Conectado a la base de datos!");
		} catch (SQLException e) {
			throw new IllegalStateException("No se ha podido conectar a la base de datos", e);
		}

		// Sentencia de consulta (query)
		try {
			String query = "SELECT * FROM nttdata_t1mysql_services";

			Statement st = (Statement) connection.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id_service");
				String name = rs.getString("name");
				String description = rs.getString("description");

				System.out.format("%s, %s, %s", id, name, description + "\n");
			}
			st.close();
		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
