import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class Ventana {

    //Escriba los nombres de los Autores
    //Autor1: Lucas Karlsson
    //Autor2: Luis Morales
    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;
    private JList lstProductos;
    private JSpinner spnIdBuscar;
    private JButton btnEditar;
    private JTextField txtNombre;
    private JComboBox cboCategoria;
    private JSpinner spnCantidad;
    private JTextField txtPrecio;
    private JButton btnGuardarCambios;
    private JComboBox cboCategoriaOrden;
    private JButton btnOrdenarPorPrecio;
    private JTextField txtBuscarNombre;
    private JButton btnBuscarNombre;
    private JList lstResultados;
    private JButton btnMostrarMenorPrecio;

    private InventarioProductos inventario = new InventarioProductos();

    public Ventana() {
        llenarJListCompleta();
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (Integer) spnIdBuscar.getValue();
                Producto p = inventario.buscarPorId(id);
                if (p != null) {
                    txtNombre.setText(p.getNombre());
                    cboCategoria.setSelectedItem(p.getCategoria());
                    spnCantidad.setValue(p.getCantidad());
                    txtPrecio.setText(String.valueOf(p.getPrecio()));
                    JOptionPane.showMessageDialog(null, "Producto encontrado, edite los datos y presione Guardar.");
                } else {
                    JOptionPane.showMessageDialog(null, "No existe un producto con ese ID.");
                }
            }
        });

        btnGuardarCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (Integer) spnIdBuscar.getValue();
                Producto p = inventario.buscarPorId(id);
                if (p != null) {
                    p.setNombre(txtNombre.getText());
                    p.setCategoria(cboCategoria.getSelectedItem().toString());
                    p.setCantidad((Integer) spnCantidad.getValue());
                    p.setPrecio(Float.parseFloat(txtPrecio.getText()));
                    llenarJListCompleta();
                    JOptionPane.showMessageDialog(null, "Datos actualizados.");
                }
            }
        });
        btnOrdenarPorPrecio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cat = cboCategoriaOrden.getSelectedItem().toString();
                inventario.ordenarPorPrecioDescCategoria(cat);
                llenarResultados(inventario.buscarPorCategoria(cat));
            }
        });

        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtBuscarNombre.getText();
                Producto p = inventario.buscarPorNombre(nombre);
                if (p != null) {
                    DefaultListModel dlm = new DefaultListModel();
                    dlm.addElement(p);
                    lstResultados.setModel(dlm);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró producto con ese nombre.");
                }
            }
        });

        btnMostrarMenorPrecio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto min = inventario.productoMenorPrecio();
                if (min != null) {
                    DefaultListModel dlm = new DefaultListModel();
                    dlm.addElement(min);
                    lstResultados.setModel(dlm);
                }
            }
        });
    }

    private void llenarJListCompleta() {
        DefaultListModel dlm = new DefaultListModel();
        for (Producto p : inventario.getLista()) {
            dlm.addElement(p);
        }
        lstProductos.setModel(dlm);
    }

    private void llenarResultados(List<Producto> productos) {
        DefaultListModel dlm = new DefaultListModel();
        for (Producto p : productos) {
            dlm.addElement(p);
        }
        lstResultados.setModel(dlm);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventario de Productos");
        frame.setContentPane(new Ventana().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
















