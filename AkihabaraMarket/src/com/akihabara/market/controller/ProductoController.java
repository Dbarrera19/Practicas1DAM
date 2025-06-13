package com.akihabara.market.controller;

import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;

import java.util.List;

public class ProductoController {
    private ProductoDAO productoDAO;

    public ProductoController() {
        this.productoDAO = new ProductoDAO();
    }

    public boolean agregarProducto(String nombre, String categoria, double precio, int stock) {
        ProductoOtaku producto = new ProductoOtaku(nombre, categoria, precio, stock);
        try {
            productoDAO.agregarProducto(producto);
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar producto: " + e.getMessage());
            return false;
        }
    }

    public ProductoOtaku obtenerProductoPorId(int id) {
        return productoDAO.obtenerProductoPorId(id);
    }

    public List<ProductoOtaku> obtenerTodosLosProductos() {
        return productoDAO.obtenerTodosLosProductos();
    }

    public boolean actualizarProducto(int id, String nombre, String categoria, double precio, int stock) {
        ProductoOtaku producto = new ProductoOtaku(id, nombre, categoria, precio, stock);
        return productoDAO.actualizarProducto(producto);
    }

    public boolean eliminarProducto(int id) {
        return productoDAO.eliminarProducto(id);
    }

    public List<ProductoOtaku> buscarPorNombre(String nombre) {
        return productoDAO.buscarProductosPorNombre(nombre);
    }

    public List<ProductoOtaku> buscarPorCategoria(String categoria) {
        return productoDAO.buscarProductoPorCategoria(categoria);
    }
}
