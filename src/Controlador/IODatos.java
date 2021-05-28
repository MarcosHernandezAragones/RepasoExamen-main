/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.Alumno;
import Modelo.Usuario;
import Vista.VentanaUsuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author javierlegua
 */
public class IODatos {
    
    private static final String RUTA = "jdbc:mysql://34.229.80.204:3306/javier";
    private static final String USU = "javier";
    private static final String PASS = "javier";
    
    public static void InsertarUsuEjemplo(){
        
        try (Connection con = DriverManager.getConnection(RUTA, USU, PASS)){
           
            System.out.println("Conexión realizada");
            
            String sql = "insert into usuario values('javier', 'Admin1234')";
            
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Conexion cerrada");
        
    }

    public static Usuario comprobarlogin(String nombre, String pass) {
     
        
        try (Connection con = DriverManager.getConnection(RUTA, USU, PASS);){
           
            System.out.println("Conexión realizada");
            
            String sql = "select * from usuario where nombre= '"+nombre+"'and pass='"+pass+"';";
            
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                System.out.println(rs.getString(1) + rs.getString(2));
                
                if((rs.getString(1).equalsIgnoreCase(nombre)) && (rs.getString(2).equalsIgnoreCase(pass))){
                    Usuario u = new Usuario(rs.getString(1), rs.getString(2));
                    return u;
                }
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Conexion cerrada");
        return null;
    }

    public static DefaultTableModel rellenarTablba() {
        DefaultTableModel mTabla= new DefaultTableModel();
        
        try ( Connection con = DriverManager.getConnection(RUTA, USU, PASS);){
           
            String consulta = "select * from usuario";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            mTabla.addColumn("Nombre");
            mTabla.addColumn("Contraseña");

            rs = st.executeQuery(consulta);
            
            while (rs.next()) {                
                Object[] fila = {rs.getString(1),rs.getString(2)};
                mTabla.addRow(fila);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mTabla;
    }

    public static void guardarAlumno(Alumno a) {
        
    }
    
    
    
    
    
}
