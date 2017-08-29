package com.ctli.it.lib;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import com.mongodb.MongoClient;

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

	//The below 5 methods  are for Mongo DB
	public class MongoDB{
		
		public void getAllDataMongoCollection(String CollectionName){
		 try{   
	   	  
				//Connecting to the mongoDB instance
			        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			        
				//Selecting the database
			        @SuppressWarnings("deprecation")
					DB db = mongoClient.getDB("test");
				
			        //Selecting the collection
				DBCollection dbCollection = db.getCollection(CollectionName);
			        
				//Setting search query with the required key-value pair
				/*BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put(key,value);*/
			
				 
				//DBCursor with the find query result
				DBCursor cursor = dbCollection.find();
			 
			        //Fetching the response			
				    while(cursor.hasNext()) {
				    	System.out.println(cursor.next());
				    	//cursor.close();
				    }
				    
		 }
			      catch(Exception e){
				    System.out.println(e.getMessage());
			      }

		}
		
		public void getQueriedDatafromMongoCollection(String key, String value, String CollectionName){
			 try{   
		   	  
					//Connecting to the mongoDB instance
				        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
				        
					//Selecting the database
				        @SuppressWarnings("deprecation")
						DB db = mongoClient.getDB("test");
					
				        //Selecting the collection
					DBCollection dbCollection = db.getCollection(CollectionName);
				        
					//Setting search query with the required key-value pair
					BasicDBObject searchQuery = new BasicDBObject();
					searchQuery.put(key,value);
				
					 
					//DBCursor with the find query result
					DBCursor cursor = dbCollection.find(searchQuery);
				 
				        //Fetching the response			
					    while(cursor.hasNext()) {
					    	System.out.println(cursor.next());
					    	//cursor.close();
					    }
					    
			 }
				      catch(Exception e){
					    System.out.println(e.getMessage());
				      }

			}
		
		public void insertMongoData(String CollectionName){
			 try{   
		   	  
					//Connecting to the mongoDB instance
				        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
				        
					//Selecting the database
				        @SuppressWarnings("deprecation")
						DB db = mongoClient.getDB("test");
					
				        //Selecting the collection
					DBCollection dbCollection = db.getCollection(CollectionName);
				        
					//Inserting Data
				/*	BasicDBObject doc = new BasicDBObject("name", "MongoDB").
	                        append("type", "database").
	                        append("count", 1).
	                        append("info", new BasicDBObject("title", 203).append("airdate", 102));*/
					BasicDBObject doc = new BasicDBObject();
					/*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					*/
					doc.put("title", "Biplab");
			//		doc.put("airdate", new Date());
					doc.put("Network", "AXN");
					/*do{
					System.out.print("Enter the Key and Value: \n");
					doc.put(reader.readLine(), reader.readLine());
					}
					while(!reader.readLine().equalsIgnoreCase("Done"));*/
					dbCollection.insert(doc);
					
			 }
				      catch(Exception e){
					    System.out.println(e.getMessage());
				      }

			}
		
		public void updateMongoData(String searchvalue,String updatedvalue, String CollectionName){
			 try{   
		   	  
					//Connecting to the mongoDB instance
				        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
				        
					//Selecting the database
				        @SuppressWarnings("deprecation")
						DB db = mongoClient.getDB("test");
					
				        //Selecting the collection
					DBCollection dbCollection = db.getCollection(CollectionName);
				        
					//Updating Data

					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append("network", updatedvalue));

					BasicDBObject searchQuery = new BasicDBObject().append("title", searchvalue);

					dbCollection.update(searchQuery, newDocument);
					
			 }
				      catch(Exception e){
					    System.out.println(e.getMessage());
				      }
			 
		}
			 
				public void deleteMongoData(String key, String value, String CollectionName){
					 try{   
				   	  
							//Connecting to the mongoDB instance
						        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
						        
							//Selecting the database
						        @SuppressWarnings("deprecation")
								DB db = mongoClient.getDB("test");
							
						        //Selecting the collection
							DBCollection dbCollection = db.getCollection(CollectionName);
						        
							//Deleting Data

							BasicDBObject searchQuery = new BasicDBObject();
							searchQuery.put(key, value);		// Find documents with key in name field

							dbCollection.remove(searchQuery);	// Delete those matching documents
							
					 }
						      catch(Exception e){
							    System.out.println(e.getMessage());
						      }

			}
			
		
	}


}

