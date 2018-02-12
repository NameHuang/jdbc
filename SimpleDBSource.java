package datastructure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SimpleDBSource implements DBSource{
    private Properties pro;
    private String url;
    private String user;
    private String password;
    
    public SimpleDBSource() throws FileNotFoundException, IOException, ClassNotFoundException {
    	this("E:\\JavaseReview\\javase\\src\\main\\java\\datastructure\\jdbc.properties");
    }
    
    public SimpleDBSource(String confige) throws FileNotFoundException, IOException, ClassNotFoundException {
    	pro=new Properties(); 
    	pro.load(new FileInputStream(confige));
    	url=pro.getProperty("datastructure.url");
    	user=pro.getProperty("datastructure.user");
    	password=pro.getProperty("datastructure.password");
    	Class.forName(pro.getProperty("datastructure.driver"));
    	
    }

	public Connection getconnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public void closeConnection(Connection con) throws SQLException {
        con.close();		
	}
    
}
