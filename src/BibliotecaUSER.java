import oracle.jdbc.proxy.annotation.Pre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BibliotecaUSER extends JFrame {
    private JTextField textCitesteCartea;
    private JPanel panel1;
    private JButton delogheazaTeButton;
    private JButton cautaTitlu;
    private JTextField textAutor;
    private JButton cautaAutor;
    private JTable table1;
    private JButton arataCartiButton;
    private JTextField textTitlu;
    private Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "db");

    public JTextField getTextTitlu() {
        return textTitlu;
    }

    public void setTextTitlu(JTextField textTitlu) {
        this.textTitlu = textTitlu;
    }

    public JTextField getTextCitesteCartea() {
        return textCitesteCartea;
    }

    public void setTextCitesteCartea(JTextField textCitesteCartea) {
        this.textCitesteCartea = textCitesteCartea;
    }

    public JButton getArataCartiButton() {
        return arataCartiButton;
    }

    public void setArataCartiButton(JButton arataCartiButton) {
        this.arataCartiButton = arataCartiButton;
    }

    public JTextField getTextField1() {
        return textCitesteCartea;
    }

    public void setTextField1(JTextField textField1) {
        this.textCitesteCartea = textField1;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JButton getDelogheazaTeButton() {
        return delogheazaTeButton;
    }

    public void setDelogheazaTeButton(JButton delogheazaTeButton) {
        this.delogheazaTeButton = delogheazaTeButton;
    }

    public JButton getCautaTitlu() {
        return cautaTitlu;
    }

    public void setCautaTitlu(JButton cautaTitlu) {
        this.cautaTitlu = cautaTitlu;
    }

    public JTextField getTextAutor() {
        return textAutor;
    }

    public void setTextAutor(JTextField textAutor) {
        this.textAutor = textAutor;
    }

    public JButton getCautaAutor() {
        return cautaAutor;
    }

    public void setCautaAutor(JButton cautaAutor) {
        this.cautaAutor = cautaAutor;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public BibliotecaUSER() throws SQLException {
        cautaTitlu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showTableTitle(table1);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cautaAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showTableAuthor(table1);
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
                JOptionPane.showMessageDialog(null, "Te-ai delogat cu succes!");
                lg.frameSetup(lg);
                dispose();
            }
        });
        arataCartiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showTable(table1);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    protected void frameSetup(BibliotecaUSER b) throws SQLException {
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

    protected void showTableTitle(JTable table) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"C1", "C2", "C3"});
        PreparedStatement ps = con.prepareStatement("SELECT * FROM CARTI WHERE NUMECARTE=?");
        ps.setString(1, textTitlu.getText());
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
        textTitlu.setText("");
    }

    protected void showTableAuthor(JTable table) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"C1", "C2", "C3"});
        PreparedStatement ps = con.prepareStatement("SELECT * FROM CARTI WHERE NUMEAUTOR=?");
        ps.setString(1, textAutor.getText());
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
        textAutor.setText("");
    }
}
