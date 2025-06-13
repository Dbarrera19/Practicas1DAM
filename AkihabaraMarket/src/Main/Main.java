package Main;
import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;
import com.akihabara.market.view.InterfazConsola;

public class Main {
    public static void main(String[] args) {
        InterfazConsola vista = new InterfazConsola();
        ProductoDAO dao = new ProductoDAO();
        boolean salir = false;

        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();

            switch (opcion) {
                case 1:
                    dao.agregarProducto(vista.pedirDatosProducto());
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
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
    }
}
