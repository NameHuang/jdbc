package datastructure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OprationDataBase {

	public static void main(String[] args) {
		DBSource db=null;
		Connection con=null;
		PreparedStatement ps=null;
		
			try {
				db=new SimpleDBSource();
				con=db.getconnection();
				ps=con.prepareStatement("insert into table1 values (?,?,?)");
				ps.setInt(1,5 );
				ps.setString(2,"child");
				ps.setString(3,"man");
				ps.executeUpdate();
				ps.clearParameters();
				
				ps.setInt(1,6);
				ps.setString(2,"child");
				ps.setString(3,"woman");
				ps.executeUpdate();
				ps.clearParameters();
				
				ResultSet rs=ps.executeQuery("select * from"
						+ " table1");
				while(rs.next()) {
					System.out.print(rs.getInt(1)+"\t");
					System.out.print(rs.getString(2)+"\t");
					System.out.println(rs.getString(3));
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(ps!=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con!=null) {
					try {
						db.closeConnection(con);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
	}

}
