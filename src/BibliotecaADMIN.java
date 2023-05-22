import oracle.jdbc.proxy.annotation.Pre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BibliotecaADMIN extends JFrame {
    private JTextField textAdauga;
    private JButton adaugaButton;
    private JTextField textSterge;
    private JButton stergeButton;
    private JTextField textStergeUser;
    private JButton stergeUserButton;
    private JButton delogheazaTeButton;
    private JTable table1;
    private JPanel Panel1;
    private JTextField textAdauga2;
    private JTextField textAdauga1;
    private Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "db");

    public JTextField getTextAdauga2() {
        return textAdauga2;
    }

    public void setTextAdauga2(JTextField textAdauga2) {
        this.textAdauga2 = textAdauga2;
    }

    public JTextField getTextAdauga1() {
        return textAdauga1;
    }

    public void setTextAdauga1(JTextField textAdauga1) {
        this.textAdauga1 = textAdauga1;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public JPanel getPanel1() {
        return Panel1;
    }

    public void setPanel1(JPanel panel1) {
        Panel1 = panel1;
    }

    public JTextField getTextAdauga() {
        return textAdauga;
    }

    public void setTextAdauga(JTextField textAdauga) {
        this.textAdauga = textAdauga;
    }

    public JButton getAdaugaButton() {
        return adaugaButton;
    }

    public void setAdaugaButton(JButton adaugaButton) {
        this.adaugaButton = adaugaButton;
    }

    public JTextField getTextSterge() {
        return textSterge;
    }

    public void setTextSterge(JTextField textSterge) {
        this.textSterge = textSterge;
    }

    public JButton getStergeButton() {
        return stergeButton;
    }

    public void setStergeButton(JButton stergeButton) {
        this.stergeButton = stergeButton;
    }

    public JTextField getTextStergeUser() {
        return textStergeUser;
    }

    public void setTextStergeUser(JTextField textStergeUser) {
        this.textStergeUser = textStergeUser;
    }

    public JButton getStergeUserButton() {
        return stergeUserButton;
    }

    public void setStergeUserButton(JButton stergeUserButton) {
        this.stergeUserButton = stergeUserButton;
    }

    public JButton getDelogheazaTeButton() {
        return delogheazaTeButton;
    }

    public void setDelogheazaTeButton(JButton delogheazaTeButton) {
        this.delogheazaTeButton = delogheazaTeButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public BibliotecaADMIN() throws SQLException {
        adaugaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textAdauga.getText().equalsIgnoreCase("") || textAdauga1.getText().equalsIgnoreCase("") || textAdauga2.getText().equalsIgnoreCase(""))
                        JOptionPane.showMessageDialog(null, "Toate campurile trebuie completate!");
                    else {
                        Carte carte = new Carte(textAdauga.getText(), textAdauga1.getText(), textAdauga2.getText());
                        PreparedStatement ps = con.prepareStatement("INSERT INTO CARTI VALUES (?,?,?)");
                        ps.setString(1, carte.getNumeCarte());
                        ps.setString(2, carte.getNumeAutor());
                        ps.setString(3, carte.getContinutCarte());
                        if (ps.executeUpdate() != 0) {
                            JOptionPane.showMessageDialog(null, "Cartea " + textAdauga.getText() + " a fost adaugata cu succes!");
                            showTable(table1);
                        }
                    }
                    textAdauga.setText("Titlu");
                    textAdauga1.setText("Autor");
                    textAdauga2.setText("Continut");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        stergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textSterge.getText().equalsIgnoreCase(""))
                        JOptionPane.showMessageDialog(null, "Campul trebuie completat!");
                    else {
                        PreparedStatement ps = con.prepareStatement("DELETE FROM CARTI WHERE NUMECARTE=?");
                        ps.setString(1, textSterge.getText());
                        if (ps.executeUpdate() != 0) {
                            JOptionPane.showMessageDialog(null, "Cartea " + textSterge.getText() + " a fost stearsa din bliblioteca!");
                            showTable(table1);
                        } else
                            JOptionPane.showMessageDialog(null, "Cartea " + textSterge.getText() + " nu este in biblioteca!");
                        textSterge.setText("");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        stergeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textStergeUser.getText().equalsIgnoreCase(""))
                        JOptionPane.showMessageDialog(null, "Campul trebuie completat!");
                    else {
                        PreparedStatement ps = con.prepareStatement("DELETE FROM UTILIZATORI WHERE USERNAME=?");
                        ps.setString(1, textStergeUser.getText());
                        if (ps.executeUpdate() != 0)
                            JOptionPane.showMessageDialog(null, "Utilizatorul " + textStergeUser.getText() + " a fost sters!");
                        else
                            JOptionPane.showMessageDialog(null, "Utilizatorul " + textStergeUser.getText() + " nu a fost gasit!");
                        textStergeUser.setText("");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        delogheazaTeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginRegister lg = null;
                try {
                    lg = new LoginRegister();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                lg.frameSetup(lg);
                dispose();
            }
        });
    }

    protected void frameSetup(BibliotecaADMIN b) throws SQLException {
        b.setContentPane(getPanel1());
        b.setTitle("Librarie");
        b.setSize(1920, 1080);
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showTable(table1);
    }

    protected void showTable(JTable table) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"C1", "C2", "C3"});
        PreparedStatement ps = con.prepareStatement("SELECT * FROM CARTI");
        ResultSet rs = ps.executeQuery();
        model.setRowCount(0);
        while (rs.next()) {
            Object[] row = new Object[3];
            row[0] = rs.getString("NUMECARTE");
            row[1] = rs.getString("NUMEAUTOR");
            row[2] = rs.getString("CONTINUT");
            model.addRow(row);
        }
        table.setModel(model);
    }
}
