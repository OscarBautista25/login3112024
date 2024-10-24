import com.mysql.cj.jdbc.Driver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    private JPanel panelLogin;
    private JTextField usuarioText;
    private JPasswordField contrasenaText;
    private JButton ingresarBoton;
    private JButton agregarUsuarioBoton;
    Connection conexion;
    Statement st;
    ResultSet rs;

    public Login(){

        ingresarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarUsuario();
            }
        });
    }
    void conectar(){
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/clase3112024","root", "1234");
        }catch (SQLException e){

            throw new RuntimeException(e);
        }

    }
    void validarUsuario(){
        conectar();
        int validacion=0;
        String usuario = usuarioText.getText();
        String contrasena = contrasenaText.getText();
        try{
            st = conexion.createStatement();
            rs =st.executeQuery("select * from usuarios where usuario ='"+usuario+"' and contraseña= '"+contrasena+"'");
            if(rs.next()){
                validacion=1;
                if (validacion==1){
                    JOptionPane.showMessageDialog(null, "Usuario validado exitosamente");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Sus credenciales no son correctas");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.getMessage());
        }
    }

    public static void main(String[] args) {
        Login login1 = new Login();
        login1.setContentPane(new Login().panelLogin);
        login1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login1.setVisible(true);
        login1.pack();
    }
}
