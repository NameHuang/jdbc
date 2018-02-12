package datastructure;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBSource{
    public Connection getconnection() throws SQLException; 
    public void closeConnection(Connection con) throws SQLException;
}
