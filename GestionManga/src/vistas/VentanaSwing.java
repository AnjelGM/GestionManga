package vistas;

import clases.ColeccionManga;
import interfaces.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import run.ControladorColeccion;

/**
 * 
 * @author Angel
 */
public class VentanaSwing extends JFrame implements IVentana{
    private ColeccionManga manga;
    private VentanaBusquedaSwing busqueda;
    private VentanaMensajeSwing mensaje;
    private ControladorColeccion controlador;
    private byte operacion;
    
    private JLabel jlEstado, jlEdicionEspecial, jlNumeroTomos, jlDemografia; //El estado hace referencia a si está terminado o no
    private JLabel jlTipoDeTomo, jlEditorial, jlCodigo, jlTitulo, jlAutor, jlDibujo;
    private ButtonGroup bgEstado;
    private JRadioButton jrbEnCurso, jrbTerminado;
    private JCheckBox jcbEdicionEspecial;
    private JSpinner jsNumeroTomos;
    private JComboBox<String> jcobDemografia, jcobEditorial;
    private JList<String> jliTipoDeTomo;
    private JTextField jtfCodigo, jtfTitulo, jtfAutor, jtfDibujo;
    
    private JButton jbNuevo, jbBorrar, jbModificar, jbConsulta, jbLimpiar; //Las acciones
    private JButton jbPrimero, jbAnterior, jbSiguiente, jbUltimo; //La navegacion
    
    private JPanel jpBotonesAccion, jpNavegacion, jpInformacion, jpRadioButtons;
    
    public VentanaSwing(){
        manga = new ColeccionManga();
        busqueda = new VentanaBusquedaSwing();
        mensaje = new VentanaMensajeSwing();
        busqueda.setVistaPrincipal(this);
        
        iniciarLabels();
        iniciarRadioButtons();
        iniciarCheckBox();
        iniciarSpinner();
        iniciarComboBox();
        iniciarList();
        iniciarTextField();
        iniciarButtons();
        iniciarPaneles();
        listeners();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GestionManga");
        setLocationRelativeTo(null);
        pack();
    }
    
    private void iniciarLabels(){
        jlCodigo = new JLabel("Codigo del manga ");
        jlTitulo = new JLabel("Titulo del manga ");
        jlAutor = new JLabel("Autor del manga ");
        jlDibujo = new JLabel("Ilustrador del manga ");
        jlEditorial = new JLabel("Editorial del manga ");
        jlDemografia = new JLabel("Demografia del manga (modificable)");
        jlTipoDeTomo = new JLabel("Tipo de tomo (modificable)");
        jlNumeroTomos = new JLabel("Numero de tomos (modificable)");
        jlEdicionEspecial = new JLabel("Edicion especial ");
        jlEstado = new JLabel("Estado del manga (modificable)");        
    }
    
    private void iniciarRadioButtons(){
        bgEstado = new ButtonGroup();
        
        jrbEnCurso = new JRadioButton("En curso", true);
        jrbTerminado = new JRadioButton("Terminado", false);
        
        bgEstado.add(jrbEnCurso);
        bgEstado.add(jrbTerminado);
    }
    
    private void iniciarCheckBox(){
        jcbEdicionEspecial = new JCheckBox("");
    }
    
    private void iniciarSpinner(){
        SpinnerNumberModel modeloSpinner= new SpinnerNumberModel(1, 1, 500, 1);
        jsNumeroTomos = new JSpinner(modeloSpinner);
    }
    
    private void iniciarComboBox(){
        jcobDemografia = new JComboBox<String>();
        jcobEditorial = new JComboBox<String>();
        
        jcobDemografia.addItem("Shonen");
        jcobDemografia.addItem("Shoujo");
        jcobDemografia.addItem("Seinen");
        jcobDemografia.addItem("Josei");
        
        jcobEditorial.addItem("Planeta");
        jcobEditorial.addItem("Norma");
        jcobEditorial.addItem("ECC");
        jcobEditorial.addItem("Panini");
        jcobEditorial.addItem("Ivrea");
        jcobEditorial.addItem("Milky Way");
        jcobEditorial.addItem("Fandogamia");
        jcobEditorial.addItem("Kodai");
        jcobEditorial.addItem("Satori Ediciones");
        jcobEditorial.addItem("Ediciones Babylon");
        jcobEditorial.addItem("Arechi");
        jcobEditorial.addItem("Kitsune Manga");
        jcobEditorial.addItem("Ooso");
    }
    
    private void iniciarList(){
        String[] tipoTomos = {"Tankobon", "Kanzenban", "Shinsoban"};
        jliTipoDeTomo = new JList<String>(tipoTomos);
    }
    
    private void iniciarTextField(){
        jtfCodigo = new JTextField("Inserte aquí el codigo del manga");
        jtfTitulo = new JTextField("Inserte aquí el nombre del manga");
        jtfAutor = new JTextField("Inserte aquí el autor del manga");
        jtfDibujo = new JTextField("Inserte aquí el ilustrador del manga");
    }
    
    private void iniciarButtons(){
        iniciarButtonsAccion();
        iniciarButtonsNavegacion();
    }
    
    private void iniciarButtonsAccion(){
        ImageIcon nuevo = new ImageIcon(getClass().getResource("/gfx/nuevo.png"));
        ImageIcon editar = new ImageIcon(getClass().getResource("/gfx/editar.png"));
        ImageIcon borrar = new ImageIcon(getClass().getResource("/gfx/borrar.png"));
        ImageIcon consultar = new ImageIcon(getClass().getResource("/gfx/consulta.png"));
        ImageIcon limpiar = new ImageIcon(getClass().getResource("/gfx/limpiar.png"));
        
        jbNuevo = new JButton(nuevo);
        jbModificar = new JButton(editar);
        jbBorrar = new JButton(borrar);
        jbConsulta = new JButton(consultar);
        jbLimpiar = new JButton(limpiar);
    }
    
    private void iniciarButtonsNavegacion(){
        ImageIcon primero = new ImageIcon(getClass().getResource("/gfx/primero.png"));
        ImageIcon anterior = new ImageIcon(getClass().getResource("/gfx/anterior.png"));
        ImageIcon siguiente = new ImageIcon(getClass().getResource("/gfx/siguiente.png"));
        ImageIcon ultimo = new ImageIcon(getClass().getResource("/gfx/ultimo.png"));
        
        jbPrimero = new JButton(primero);
        jbAnterior = new JButton(anterior);
        jbSiguiente = new JButton(siguiente);
        jbUltimo = new JButton(ultimo);
    }
    
    private void iniciarPaneles(){
        BorderLayout blInicial = new BorderLayout();
        getContentPane().setLayout(blInicial);
                
        iniciarPanelBotonesAcciones();
        iniciarPanelInformacion();
        iniciarPanelNavegacion();
                
        getContentPane().add(jpBotonesAccion, BorderLayout.NORTH);
        getContentPane().add(jpInformacion, BorderLayout.CENTER);
        getContentPane().add(jpNavegacion, BorderLayout.SOUTH);
    }
    
    private void iniciarPanelBotonesAcciones(){
        FlowLayout flBotonesAccion = new FlowLayout();
        jpBotonesAccion = new JPanel(flBotonesAccion);
        
        jpBotonesAccion.add(jbNuevo);
        jpBotonesAccion.add(jbModificar);
        jpBotonesAccion.add(jbBorrar);
        jpBotonesAccion.add(jbConsulta);
        jpBotonesAccion.add(jbLimpiar);
    }
    
    private void iniciarPanelInformacion(){
        JPanel informacion1 = new JPanel(new GridLayout(5, 2));
        JPanel informacion2 = new JPanel(new GridLayout(5, 2));
        
        GridLayout glInformacion = new GridLayout(1, 2);
        GridLayout glRadioButtons = new GridLayout(1, 2);
        jpInformacion = new JPanel(glInformacion);
        jpRadioButtons = new JPanel(glRadioButtons);
        
        jpRadioButtons.add(jrbTerminado);
        jpRadioButtons.add(jrbEnCurso);
        
        informacion1.add(jlCodigo);
        informacion1.add(jtfCodigo);
        
        informacion1.add(jlTitulo);
        informacion1.add(jtfTitulo);
        
        informacion1.add(jlAutor);
        informacion1.add(jtfAutor);
        
        informacion1.add(jlDibujo);
        informacion1.add(jtfDibujo);
        
        informacion2.add(jlEditorial);
        informacion2.add(jcobEditorial);
        
        informacion2.add(jlDemografia);
        informacion2.add(jcobDemografia);
        
        informacion2.add(jlTipoDeTomo);
        informacion2.add(jliTipoDeTomo);
        
        informacion2.add(jlNumeroTomos);
        informacion2.add(jsNumeroTomos);
        
        informacion1.add(jlEdicionEspecial);
        informacion1.add(jcbEdicionEspecial);
        
        informacion2.add(jlEstado);
        informacion2.add(jpRadioButtons);
        
        jpInformacion.add(informacion1);
        jpInformacion.add(informacion2);
    }
    
    private void iniciarPanelNavegacion(){
        FlowLayout flNavegacion = new FlowLayout();
        jpNavegacion = new JPanel(flNavegacion);
        
        jpNavegacion.add(jbPrimero);
        jpNavegacion.add(jbAnterior);
        jpNavegacion.add(jbSiguiente);
        jpNavegacion.add(jbUltimo);
        
    }
    
    private void listeners(){
        jbNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                operacion = 1;
                manga.setCodigo(jtfCodigo.getText());
                manga.setTitulo(jtfTitulo.getText());
                manga.setAutor(jtfAutor.getText());
                manga.setDibujo(jtfDibujo.getText());
                manga.setEditorial((String) jcobEditorial.getSelectedItem());
                manga.setDemografia((String) jcobDemografia.getSelectedItem());
                manga.setTipoDeTomo(jliTipoDeTomo.getSelectedValue());
                manga.setNumeroTomos((int) jsNumeroTomos.getValue());
                manga.setEdicionEspecial(jcbEdicionEspecial.isSelected());
                manga.setTerminado(jrbTerminado.isSelected());
                try {
                    controlador.notifiacion();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jbBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                operacion = 2;
                manga.setCodigo(jtfCodigo.getText());
                try {
                    controlador.notifiacion();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                operacion = 3;
                manga.setCodigo(jtfCodigo.getText());
                manga.setDemografia((String) jcobDemografia.getSelectedItem());
                manga.setTipoDeTomo(jliTipoDeTomo.getSelectedValue());
                manga.setNumeroTomos((int) jsNumeroTomos.getValue());
                manga.setTerminado(jrbTerminado.isSelected());
                try {
                    controlador.notifiacion();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jbConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                busqueda.mostrar();
            }
        });
        
        jbSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                operacion = 6;
                try {
                    controlador.notifiacion();
                    mostrarManga();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jbAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                operacion = 7;
                try {
                    controlador.notifiacion();
                    mostrarManga();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jbPrimero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                operacion = 8;
                try {
                    controlador.notifiacion();
                    mostrarManga();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jbUltimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                operacion = 9;
                try {
                    controlador.notifiacion();
                    mostrarManga();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jbLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jtfCodigo.setText("");
                jtfTitulo.setText("");
                jtfAutor.setText("");
                jtfDibujo.setText("");
                jcobDemografia.setSelectedItem("Shonen");
                jcobEditorial.setSelectedItem("Planeta");
                jliTipoDeTomo.setSelectedIndex(0);
                jsNumeroTomos.setValue(1);
                jcbEdicionEspecial.setSelected(false);
                jrbTerminado.setSelected(false);
                jrbEnCurso.setSelected(true);
            }
        });
    } 

    @Override
    public ColeccionManga getColeccionManga() {
        return manga;
    }
    
    public byte getOperacion(){
        return operacion;
    }

    @Override
    public IVentanaBusqueda getVentanaBusqueda() {
        return busqueda;
    }

    @Override
    public IVentanaMensaje getVentanaMensaje() {
        return mensaje;
    }

    public void setOperacion(byte operacion) {
        this.operacion = operacion;
    }

    @Override
    public void setColeccionManga(ColeccionManga manga) {
        this.manga = manga;
    }

    @Override
    public void mostrarManga() throws IOException {
        if(manga != null){
            jtfCodigo.setText(manga.getCodigo());
            jtfTitulo.setText(manga.getTitulo());
            jtfAutor.setText(manga.getAutor());
            jtfDibujo.setText(manga.getDibujo());
            jcobDemografia.setSelectedItem(manga.getDemografia());
            jcobEditorial.setSelectedItem(manga.getEditorial());
            switch (manga.getTipoDeTomo()){
                case "Tankobon":
                    jliTipoDeTomo.setSelectedIndex(0);
                    break;
                case "Kanzenban":
                    jliTipoDeTomo.setSelectedIndex(1);
                    break;
                case "Shinsoban":
                    jliTipoDeTomo.setSelectedIndex(2);
                    break;
            }
            jsNumeroTomos.setValue(manga.getNumeroTomos());
            if(manga.isEdicionEspecial()){
                jcbEdicionEspecial.setSelected(true);
            }else{
                jcbEdicionEspecial.setSelected(false);
            }
            if(manga.isTerminado()){
                jrbTerminado.setSelected(true);
                jrbEnCurso.setSelected(false);
            }else{
                jrbTerminado.setSelected(false);
                jrbEnCurso.setSelected(true);
            }
        }
    }

    @Override
    public void setControlador(ControladorColeccion cc) {
        controlador = cc;
        busqueda.setControlador(controlador);
    }

    @Override
    public void mostrar() throws IOException {
        setVisible(true);
    }
}
