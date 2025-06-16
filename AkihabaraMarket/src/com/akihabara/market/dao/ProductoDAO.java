package com.akihabara.market.dao;

import com.akihabara.market.model.ProductoOtaku;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private DatabaseConnection db = new DatabaseConnection();

    public void agregarProducto(ProductoOtaku p) {
        String sql = "INSERT INTO producto (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getCategoria());
            stmt.setDouble(3, p.getPrecio());
            stmt.setInt(4, p.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    public ProductoOtaku obtenerProductoPorId(int id) {
        String sql = "SELECT * FROM producto WHERE id = ?";
        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProductoOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener producto por ID: " + e.getMessage());
        }
        return null;
    }

    public List<ProductoOtaku> obtenerTodosLosProductos() {
        List<ProductoOtaku> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new ProductoOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizarProducto(ProductoOtaku p) {
        String sql = "UPDATE producto SET nombre=?, categoria=?, precio=?, stock=? WHERE id=?";
        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getCategoria());
            stmt.setDouble(3, p.getPrecio());
            stmt.setInt(4, p.getStock());
            stmt.setInt(5, p.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM producto WHERE id=?";
        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    public List<ProductoOtaku> buscarProductosPorNombre(String nombre) {
        List<ProductoOtaku> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE nombre LIKE ?";
        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new ProductoOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por nombre: " + e.getMessage());
        }
        return lista;
    }

    public List<ProductoOtaku> buscarProductoPorCategoria(String categoria) {
        List<ProductoOtaku> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE categoria=?";
        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
            stmt.setString(1, categoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new ProductoOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por categoría: " + e.getMessage());
        }
        return lista;
    }
    
}
