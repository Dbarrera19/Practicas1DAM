package com.akihabara.market.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/akihabara_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "curso";
    private Connection conexion;

    public DatabaseConnection() {
        try {
            // Carga del driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("[INFO] Driver JDBC cargado correctamente.");

            // Establecer conexión
            conexion = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("[INFO] Conexión establecida con la base de datos.");

        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] No se encontró el driver JDBC de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("[ERROR] Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("[INFO] Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
