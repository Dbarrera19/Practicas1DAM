package Main;

import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;
import com.akihabara.market.services.IAService;
import com.akihabara.market.util.ConfiguracionSistema;
import com.akihabara.market.view.InterfazConsola;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CONFIGURACIÓN DEL SISTEMA ===");
        System.out.println("Color de fondo: " + ConfiguracionSistema.get("COLOR_FONDO"));
        System.out.println("Color de texto: " + ConfiguracionSistema.get("COLOR_TEXTO"));
        System.out.println("Fuente: " + ConfiguracionSistema.get("FUENTE"));
        System.out.println("===================================");


        InterfazConsola vista = new InterfazConsola();
        ProductoDAO dao = new ProductoDAO();
        IAService ia = new IAService();
        boolean salir = false;

        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();

            switch (opcion) {
                case 1:
                    ProductoOtaku nuevo = vista.pedirDatosProductoConIA(ia);
                    dao.agregarProducto(nuevo);
                    break;
                case 2:
                    vista.mostrarProducto(dao.obtenerProductoPorId(vista.pedirIdProducto()));
                    break;
                case 3:
                    vista.mostrarListaProductos(dao.obtenerTodosLosProductos());
                    break;
                case 4:
                    vista.mostrarListaProductos(dao.buscarProductosPorNombre(vista.pedirNombreProducto()));
                    break;
                case 5:
                    vista.mostrarListaProductos(dao.buscarProductoPorCategoria(vista.pedirCategoriaProducto()));
                    break;
                case 6:
                    int id = vista.pedirIdProducto();
                    ProductoOtaku producto = dao.obtenerProductoPorId(id);
                    if (producto != null) {
                        ProductoOtaku actualizado = vista.pedirDatosProducto();
                        actualizado.setId(id);
                        dao.actualizarProducto(actualizado);
                    }
                    break;
                case 7:
                    dao.eliminarProducto(vista.pedirIdProducto());
                    break;
                case 8:
                    salir = true;
                    vista.mostrarMensaje("Gracias por usar AkihabaraMarket.");
                    break;
                case 9:
                    String descripcion = vista.pedirDescripcionParaIA();
                    String generado = ia.generarTexto("Sugiere un nombre creativo para un producto otaku del tipo: " + descripcion);
                    vista.mostrarMensaje("Nombre sugerido por la IA: " + generado);
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
    }
}
