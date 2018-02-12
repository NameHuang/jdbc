package datastructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertFile {

	public static void main(String[] args) {
			DBSource db=null;
			Connection con=null;
			PreparedStatement ps=null;
			try {
				db=new SimpleDBSource();
				con=db.getconnection();
				
				File file1=new File("C:\\Users\\黄筱\\Pictures\\FromPC\\同步文件至码云1.png");
				File file2=new File("C:\\Users\\黄筱\\Pictures\\FromPC\\新建文本文档.txt");
				InputStream in1=new FileInputStream(file1);
				int length1=(int) file1.length();
				InputStream in2=new FileInputStream(file2);
				int length2=(int) file2.length();
				ps=con.prepareStatement("insert into table2 values(?,?,?)");
				ps.setInt(1,1);
				ps.setBinaryStream(2, in1,length1);
				ps.setBinaryStream(3,in2, length2);
				ps.executeUpdate();
				in1.close();
				in2.close();
				ResultSet rs=ps.executeQuery("select * from table2");
				while(rs.next()) {
					System.out.print(rs.getInt(1)+"\t");
					System.out.print(rs.getBlob(2).toString()+"\t");
					System.out.println(rs.getClob(3).toString());
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
