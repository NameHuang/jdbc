package datastructure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSQL { 
 
	public static void main(String[] args) {
        try {
			DBSource db=new SimpleDBSource();
			Connection con=db.getconnection();
			if(!con.isClosed()) {
				System.out.println("database is open");
			}
			db.closeConnection(con);
			if(con.isClosed()) {
				System.out.println("database is closeed");
			}
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
