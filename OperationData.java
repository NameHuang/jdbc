package datastructure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperationData {

	public static void main(String[] args) {
		DBSource db=null;
		Connection con=null;
		Statement state=null;
			try {
				db=new SimpleDBSource();
				 con=db.getconnection();
				 
				 /*ResultSet对象表示数据库结果集的数据表，通常通过执行查询数据库的语句生成
				  * ResultSet对象指向当前数据行的指针，初始时，指针指向第一行之前。使用next()可后移指针。
				  * 默认情况下ResultSet对象不可更新，只有一个向前移动的指针，只能从前往后迭代一次
				  * 但在用Connection对象创建Statement对象时，可指定该对象将生成具有给定类型和并发性的 ResultSet 对象。
				  * state=con.creatStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				  * */
				state=con.createStatement();	
				//用于执行静态的SQL语句，并返回其生成结果，默认时，一个Statement对象只能打开一个ResultSet对象
				//state.executeUpdate("insert into table1 values(4,'wife','woman')");
				//executeUpdate()只能执行不返回任何内容的SQL语句
				ResultSet rs=state.executeQuery("select * from table1");
				//executeQuery()只能执行会返回内容的SQL语句
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
				if(state!=null) {
					try {
						state.close();
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
