import oracle.jdbc.proxy.annotation.Pre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;

public class LoginRegister extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JButton logheazaTeButton;
    private JButton inregistreazaTeButton;
    private JPasswordField passwordField1;
    private Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "db");

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JButton getLogheazaTeButton() {
        return logheazaTeButton;
    }

    public void setLogheazaTeButton(JButton logheazaTeButton) {
        this.logheazaTeButton = logheazaTeButton;
    }

    public JButton getInregistreazaTeButton() {
        return inregistreazaTeButton;
    }

    public void setInregistreazaTeButton(JButton inregistreazaTeButton) {
        this.inregistreazaTeButton = inregistreazaTeButton;
    }

    public LoginRegister() throws SQLException {
        logheazaTeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM UTILIZATORI WHERE USERNAME=? and PAROLA=?");
                    ps.setString(1, textField1.getText());
                    ps.setString(2, passwordField1.getText());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        String functie = rs.getString(3);
                        JOptionPane.showMessageDialog(null, "Te-ai logat cu succes!");
                        if (functie.equalsIgnoreCase("user")) {
                            BibliotecaUSER b = new BibliotecaUSER();
                            b.frameSetup(b);
                            dispose();
                        } else if (functie.equalsIgnoreCase("admin")) {
                            BibliotecaADMIN b = new BibliotecaADMIN();
                            b.frameSetup(b);
                            dispose();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Utilizator sau parola incorecta!");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        inregistreazaTeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps1 = con.prepareStatement("SELECT * FROM UTILIZATORI WHERE USERNAME=?");
                    ps1.setString(1, textField1.getText());
                    ResultSet rs = ps1.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Numele de utilizator este folosit deja!");
                    } else {
                        if (textField1.getText().equalsIgnoreCase("") || passwordField1.getText().equalsIgnoreCase(""))
                            JOptionPane.showMessageDialog(null, "Ambele campuri trebuie completate!");
                        else {
                            User user = new User(textField1.getText(), passwordField1.getText());
                            PreparedStatement ps = con.prepareStatement("INSERT INTO UTILIZATORI values (?,?,?)");
                            ps.setString(1, user.getUsername());
                            ps.setString(2, user.getPassword());
                            ps.setString(3, "user");
                            if (ps.executeUpdate() != 0)
                                JOptionPane.showMessageDialog(null, "Te-ai inregistrat cu succes!");
                            else
                                JOptionPane.showMessageDialog(null, "Numele de utilizator este folosit deja!");
                            ps.close();
                            textField1.setText("");
                            passwordField1.setText("");
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    protected void frameSetup(LoginRegister lg) {
        lg.setContentPane(panel1);
        lg.setTitle("Librarie");
        lg.setSize(1920, 1080);
        lg.setVisible(true);
        lg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
