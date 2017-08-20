package com.ctli.it.lib;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	
/*	public  String connection="com.mysql.jdbc.Driver";
	public  String dbname="jdbc:mysql://localhost:3306/sonoo";
	public   String username="sample";
	public   String password="pass";
	
*/
	public static String value;
	public static void mysqldb ( String connection,String dbname,String username, String password )
	{
	try{  
		
		Class.forName(connection);  
		Connection con=DriverManager.getConnection(dbname,username,password);  
	
		//String sql= "TRUNCATE  table REGISTRATION ";
		//here sonoo is database name, root is username and password  
	/*	String sql = "CREATE TABLE PRATIM " +
               "(id INTEGER not NULL, " +
              " first VARCHAR(255), " + 
                " last VARCHAR(255), " + 
               " age INTEGER, " + 
              " PRIMARY KEY ( id ))"; 
*/
	//	String	sql="INSERT INTO REGISTRATION " + "VALUES (1, 'Sandeep', 'Test', 25)";
		Statement stmt=con.createStatement(); 
		//stmt.executeUpdate(sql);
	/*	stmt.executeUpdate("INSERT INTO REGISTRATION " + "VALUES (1, 'Sandeep', 'Test', 25)");
		System.out.println("one record added");
		stmt.executeUpdate("INSERT INTO REGISTRATION " + "VALUES (2, 'Pratim', 'Dev', 30)");
		System.out.println("record two added");
		//System.out.println("delete");
	*/	
		System.out.println("coonected");
		System.out.println("records inserted");
		ResultSet rst=stmt.executeQuery("select first from REGISTRATION where id=2");
		while (rst.next())
		{	
		System.out.println(rst.getString("first"));
		
		Database.value=rst.getString("first");
		System.out.println(Database.value);
		}
		System.out.println("end");
		
		       
	//	ResultSet rs=stmt.executeQuery("update table REGISTRATION set id=10,first='sandeep',last='jaya',age='25'");  
	//	while(rs.next())  
	//	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		con.close();  
	
	}catch(Exception e){ System.out.println(e);}
	
	//return value;
	
}  



}

