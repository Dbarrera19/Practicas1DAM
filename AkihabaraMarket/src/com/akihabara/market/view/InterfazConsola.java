package com.akihabara.market.view; 
 
import com.akihabara.market.model.ProductoOtaku; 
import com.akihabara.market.services.IAService; 
 
import java.util.List; 
import java.util.Scanner; 
 
public class InterfazConsola { 
    private Scanner scanner = new Scanner(System.in); 
 
    public void mostrarMenu() { 
        System.out.println("\n=== Menú Principal ==="); 
        System.out.println("1. Añadir producto"); 
        System.out.println("2. Consultar producto por ID"); 
        System.out.println("3. Listar todos los productos"); 
        System.out.println("4. Buscar productos por nombre"); 
        System.out.println("5. Buscar productos por categoría"); 
        System.out.println("6. Actualizar producto"); 
        System.out.println("7. Eliminar producto"); 
        System.out.println("8. Salir"); 
        System.out.println("9. Generar nombre de producto con IA"); 
        System.out.print("Opción: "); 
    } 
 
 
    public int leerOpcion() { 
        try { 
            return Integer.parseInt(scanner.nextLine()); 
        } catch (Exception e) { 
            return -1; 
        } 
    } 
 
    public ProductoOtaku pedirDatosProducto() { 
        System.out.print("Nombre: "); 
        String nombre = scanner.nextLine(); 
 
        System.out.print("Categoría: "); 
        String categoria = scanner.nextLine(); 
 
        System.out.print("Precio: "); 
        double precio = Double.parseDouble(scanner.nextLine()); 
 
        System.out.print("Stock: "); 
        int stock = Integer.parseInt(scanner.nextLine()); 
 
        return new ProductoOtaku(nombre, categoria, precio, stock); 
    } 
 
    public ProductoOtaku pedirDatosProductoConIA(IAService ia) { 
        System.out.print("¿Deseas usar la IA para generar el nombre del producto? (s/n): "); 
        String usarIA = scanner.nextLine(); 
 
        String nombre; 
        if (usarIA.equalsIgnoreCase("s")) { 
            System.out.print("Describe el tipo de producto: "); 
            String tipo = scanner.nextLine(); 
            nombre = ia.generarTexto("Sugiere un nombre creativo para un producto otaku del tipo: " + tipo); 
            System.out.println("Nombre generado: " + nombre); 
        } else { 
            System.out.print("Nombre: "); 
            nombre = scanner.nextLine(); 
        } 
 
        System.out.print("Categoría: "); 
        String categoria = scanner.nextLine(); 
 
        System.out.print("Precio: "); 
        double precio = Double.parseDouble(scanner.nextLine()); 
 
        System.out.print("Stock: "); 
        int stock = Integer.parseInt(scanner.nextLine()); 
 
        return new ProductoOtaku(nombre.trim(), categoria.trim(), precio, 
stock); 
    } 
 
    public int pedirIdProducto() { 
        System.out.print("ID del producto: "); 
        return Integer.parseInt(scanner.nextLine()); 
    } 
 
    public String pedirNombreProducto() { 
        System.out.print("Nombre a buscar: "); 
        return scanner.nextLine(); 
    } 
 
    public String pedirCategoriaProducto() { 
        System.out.print("Categoría a buscar: "); 
        return scanner.nextLine(); 
    } 
 
    public void mostrarProducto(ProductoOtaku producto) { 
        if (producto != null) { 
            System.out.println(producto); 
        } else { 
            System.out.println("Producto no encontrado."); 
        } 
    } 
 
    public void mostrarListaProductos(List<ProductoOtaku> productos) { 
        if (productos.isEmpty()) { 
            System.out.println("No hay productos."); 
        } else { 
            for (ProductoOtaku p : productos) { 
                System.out.println(p); 
            } 
        } 
    } 
     
    public String pedirDescripcionParaIA() { 
        System.out.print("Describe el tipo de producto para que la IA genere un nombre: "); 
        return scanner.nextLine(); 
    } 
 
 
    public void mostrarMensaje(String mensaje) { 
        System.out.println(mensaje); 
    } 
}