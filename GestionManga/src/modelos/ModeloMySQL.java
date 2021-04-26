package modelos;

import clases.ColeccionManga;
import java.sql.*;
import interfaces.IModelo;
import java.io.IOException;

/**
 * 
 * @author Angel
 */
public class ModeloMySQL implements IModelo{
    private short numeroRegistros, registroActual;
    private String mensaje;
    private static final String url = "jdbc:mysql://localhost:3306/gestion_manga?zeroDateTimeBehavior=convertToNull";
    
    public ModeloMySQL(){
        numeroRegistros = cuentaFilas();
        registroActual = -1;
        mensaje = "";
    }

    @Override
    public void alta(ColeccionManga cm) throws IOException {
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
            
            sentenciaString = "INSERT INTO mangas VALUES ('" + cm.getCodigo() + "', '" 
                    + cm.getTitulo() + "', '" + cm.getAutor() + "', '" + cm.getDibujo()
                    + "', '" + cm.getEditorial() + "', '" + cm.getDemografia() + "', '"
                    + cm.getTipoDeTomo() + "', " + cm.getNumeroTomos() + ", '" + cm.isEdicionEspecial()
                    +"', '"+cm.isTerminado() + "');";
            
            Statement sentencia = conexion.createStatement();
            try{
                int filas;
                filas = sentencia.executeUpdate(sentenciaString);
                numeroRegistros++;
                if(filas == 0){
                    mensaje = "El manga ya existe";
                }else{
                    mensaje = "El manga ha sido creado";
                }
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar el alta";
                System.out.println(ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
    }

    @Override
    public void baja(ColeccionManga cm) throws IOException {
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
            
            sentenciaString = "DELETE FROM mangas where codigo = '" + cm.getCodigo() + "';";
            
            Statement sentencia = conexion.createStatement();
            try{
                int filas;
                filas = sentencia.executeUpdate(sentenciaString);
                numeroRegistros--;
                if(filas == 0){
                    mensaje = "El manga no existe";
                }else{
                    mensaje = "El manga ha sido eliminado";
                }
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar la baja";
                System.out.println("Ha ocurrido un error en la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
    }

    @Override
    public void modificar(ColeccionManga cm) throws IOException {
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
                        
            sentenciaString = "UPDATE mangas SET demografia = '" + cm.getDemografia() 
                    + "', tipo_de_tomo = '" + cm.getTipoDeTomo() + "', numero_de_tomos =" 
                    + cm.getNumeroTomos() + ", terminado = '" + cm.isTerminado() 
                    + "' WHERE codigo = '" + cm.getCodigo() + "';";
            
            Statement sentencia = conexion.createStatement();
            try{
                int filas;
                filas = sentencia.executeUpdate(sentenciaString);
                if(filas == 0){
                    mensaje = "El manga no existe";
                }else{
                    mensaje = "El manga ha sido modificado";
                }
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar la modificacion";
                System.out.println("Ha ocurrido un error en la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
    }

    @Override
    public ColeccionManga consultaClave(String clave) throws IOException {
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
                        
            sentenciaString = "SELECT * FROM mangas WHERE codigo = '" + clave + "';";
            
            Statement sentencia = conexion.createStatement();
            
            try{
                sentencia.execute(sentenciaString);
                return resultSetAManga(sentencia.getResultSet());
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar la consulta";
                System.out.println("Ha ocurrido un error en la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
        return null;
    }

    @Override
    public ColeccionManga consultaNombre(String nombre) throws IOException {
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
                        
            sentenciaString = "SELECT * FROM mangas WHERE codigo = '" + nombre + "';";
            
            Statement sentencia = conexion.createStatement();
            
            try{
                sentencia.execute(sentenciaString);
                return resultSetAManga(sentencia.getResultSet());
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar la consulta";
                System.out.println("Ha ocurrido un error en la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
        return null;
    }

    @Override
    public ColeccionManga siguiente() throws IOException {
        if (registroActual != numeroRegistros - 1) {
            registroActual++;
        } else if (registroActual > numeroRegistros - 1) {
            registroActual = numeroRegistros;
        }
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
                        
            sentenciaString = "SELECT * FROM mangas LIMIT " + registroActual + ",1 ;";
            
            Statement sentencia = conexion.createStatement();
            
            try{
                sentencia.execute(sentenciaString);
                return resultSetAManga(sentencia.getResultSet());
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar la consulta";
                System.out.println("Ha ocurrido un error en la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
        return null;
    }

    @Override
    public ColeccionManga anterior() throws IOException {
        if (registroActual != 0) {
            registroActual--;
        }else if(registroActual < 0){
            registroActual = 0;
        }
        
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
                        
            sentenciaString = "SELECT * FROM mangas LIMIT " + registroActual + ",1 ;";
            
            Statement sentencia = conexion.createStatement();
            
            try{
                sentencia.execute(sentenciaString);
                return resultSetAManga(sentencia.getResultSet());
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar la consulta";
                System.out.println("Ha ocurrido un error en la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
        return null;
    }

    @Override
    public ColeccionManga primero() throws IOException {
        registroActual = 0;
        
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
                        
            sentenciaString = "SELECT * FROM mangas LIMIT " + registroActual + ",1 ;";
            
            Statement sentencia = conexion.createStatement();
            
            try{
                sentencia.execute(sentenciaString);
                return resultSetAManga(sentencia.getResultSet());
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar la consulta";
                System.out.println("Ha ocurrido un error en la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
        return null;
    }

    @Override
    public ColeccionManga ultimo() throws IOException {
        registroActual = (short) (numeroRegistros - 1);
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
                        
            sentenciaString = "SELECT * FROM mangas LIMIT " + registroActual + ",1 ;";
            
            Statement sentencia = conexion.createStatement();
            
            try{
                sentencia.execute(sentenciaString);
                return resultSetAManga(sentencia.getResultSet());
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar la consulta";
                System.out.println("Ha ocurrido un error en la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
        return null;
    }

    @Override
    public String getMensaje() {
        return mensaje;
    }
    
    private short cuentaFilas(){
        short nFilas = 0;
        
        String sentenciaString;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "");
                        
            sentenciaString = "SELECT COUNT(codigo) FROM mangas;";
            
            Statement sentencia = conexion.createStatement();
            
            try{
                ResultSet rs;
                sentencia.execute(sentenciaString);
                rs = sentencia.getResultSet();
                rs.next();
                return rs.getShort(1);
            }catch(SQLException ex){
                mensaje = "No se ha podido realizar la consulta";
                System.out.println("Ha ocurrido un error en la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            mensaje = "No se pudo conectar con la base de datos";
            System.out.println("Hubo un error con la conexion de la base de datos");
        }
        
        return nFilas;
    }
    
    private ColeccionManga resultSetAManga(ResultSet rs){
        ColeccionManga manga;
        try {
            manga = new ColeccionManga();
            rs.next();
            manga.setCodigo(rs.getString("codigo"));
            manga.setTitulo(rs.getString("titulo"));
            manga.setAutor(rs.getString("autor"));
            manga.setDibujo(rs.getString("dibujo"));
            manga.setEditorial(rs.getString("editorial"));
            manga.setDemografia(rs.getString("demografia"));
            manga.setTipoDeTomo(rs.getString("tipo_de_tomo"));
            manga.setNumeroTomos(rs.getInt("numero_de_tomos"));
            if(rs.getString("edicion_especial").equals("true")){
                manga.setEdicionEspecial(true);
            }else{
                manga.setEdicionEspecial(false);
            }
            if(rs.getString("terminado").equals("true")){
                manga.setTerminado(true);
            }else{
                manga.setTerminado(false);
            }
            return manga;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        manga = null;
        return manga;
    }
}
