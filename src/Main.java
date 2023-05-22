import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        LoginRegister lg = new LoginRegister();
        lg.frameSetup(lg);
    }
}