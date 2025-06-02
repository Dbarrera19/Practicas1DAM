package Main;
import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProductoDAO dao = new ProductoDAO();

        // 1. Crear y agregar un nuevo producto
        ProductoOtaku nuevoProducto = new ProductoOtaku("Póster Naruto", "Póster", 12.99, 5);
        dao.agregarProducto(nuevoProducto);

        // 2. Obtener todos los productos
        List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
        System.out.println("Lista de productos:");
        for (ProductoOtaku p : productos) {
            System.out.println(p);
        }

        // 3. Obtener un producto por ID 
        ProductoOtaku buscado = dao.obtenerProductoPorId(1);
        if (buscado != null) {
            System.out.println("Producto con ID 1: " + buscado);
        } else {
            System.out.println("No se encontró el producto con ID 1.");
        }

        // 4. Actualizar un producto 
        if (buscado != null) {
            buscado.setStock(20);
            boolean actualizado = dao.actualizarProducto(buscado);
            System.out.println(actualizado ? "Producto actualizado correctamente." : "Error al actualizar.");
        }

        // 5. Buscar por nombre
        List<ProductoOtaku> porNombre = dao.buscarProductosPorNombre("Naruto");
        System.out.println("Búsqueda por nombre 'Naruto':");
        porNombre.forEach(System.out::println);

        // 6. Buscar por categoría
        List<ProductoOtaku> porCategoria = dao.buscarProductoPorCategoria("Póster");
        System.out.println("Búsqueda por categoría 'Póster':");
        porCategoria.forEach(System.out::println);

        // 7. Eliminar producto por ID
        boolean eliminado = dao.eliminarProducto(1); // reemplaza por un ID real si quieres probar
        System.out.println(eliminado ? "Producto eliminado." : "No se pudo eliminar el producto.");
    }
}
