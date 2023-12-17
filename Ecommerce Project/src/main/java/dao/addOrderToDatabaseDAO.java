package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class addOrderToDatabaseDAO implements addOrderToDatabaseDAOImpl {
        private DataSource dSource;

    public addOrderToDatabaseDAO() throws NamingException {
        Context ctx = new InitialContext();
        dSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ecomDB");
    }
 
    private Connection getConnection() throws SQLException {
        return dSource.getConnection();
    }

    

}
