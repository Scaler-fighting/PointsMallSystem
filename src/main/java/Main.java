package java;

import com.pointsmallsystem.www.dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connecion= DatabaseConnection.getConnection();


    }
}