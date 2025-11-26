
import java.util.ArrayList;
import java.util.List;
//Escriba los nombres de los Autores
//Autor1: Lucas Karlsson
//Autor2: Luis Morales

    public class InventarioProductos {

        private List<Producto> lista;

        public InventarioProductos() {
            lista = new ArrayList<>();
            predefinir();
        }

        private void predefinir() {
            lista.add(new Producto(5, "Router Netlife", "ROUTER", 10, 50.0f));
            lista.add(new Producto(1, "Lampara", "SWITCH", 5, 80.0f));
            lista.add(new Producto(3, "Expansor Wifi", "EXPANSOR", 3, 30.0f));
            lista.add(new Producto(2, "Router Pro V2", "ROUTER", 8, 120.0f));
            lista.add(new Producto(6, "Switch Lite", "SWITCH", 4, 60.0f));
            lista.add(new Producto(4, "Expansor Internet", "EXPANSOR", 6, 40.0f));
        }

        public List<Producto> getLista() {
            return lista;
        }

        public boolean agregarProducto(Producto nuevo) {
            for (Producto p : lista) {
                if (p.getId() == nuevo.getId()) {
                    return false; // ID repetido
                }
            }
            lista.add(nuevo);
            return true;
        }

        public Producto buscarPorId(int id) {
            for (Producto p : lista) {
                if (p.getId() == id) {
                    return p;
                }
            }
            return null;
        }

        public List<Producto> buscarPorCategoria(String categoria) {
            List<Producto> resultado = new ArrayList<>();
            for (Producto p : lista) {
                if (p.getCategoria().equalsIgnoreCase(categoria)) {
                    resultado.add(p);
                }
            }
            return resultado;
        }

        public void ordenarPorPrecioDescCategoria(String categoria) {
            for (int i = 1; i < lista.size(); i++) {
                Producto aux = lista.get(i);
                int j = i - 1;

                while (j >= 0
                        && lista.get(j).getCategoria().equalsIgnoreCase(categoria)
                        && aux.getCategoria().equalsIgnoreCase(categoria)
                        && aux.getPrecio() > lista.get(j).getPrecio()) {

                    lista.set(j + 1, lista.get(j));
                    j--;
                }
                lista.set(j + 1, aux);
            }
        }

        public Producto productoMenorPrecio() {
            if (lista.isEmpty()) return null;

            Producto min = lista.get(0);
            for (Producto p : lista) {
                if (p.getPrecio() < min.getPrecio()) {
                    min = p;
                }
            }
            return min;
        }

        public Producto buscarPorNombre(String nombre) {
            for (Producto p : lista) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    return p;
                }
            }
            return null;
        }
    }


