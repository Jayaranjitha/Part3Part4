package com.nokia.atf.tef.smp.uiflow.scenarios;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Sqlrowset {
	
	   String DB_URL="jdbc:oracle:thin:@localhost:1562:IVZWDB";
        String USER="MDM_DBUSER_MR2";
        String PASS="IVZW";
        public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
        public static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1562:IVZWDB";
        public static final String USERNAME = "MDM_DBUSER_MR2";
        public static final String PASSWORD = "IVZW";

        public void checkdb() throws SQLException {
       	   
    //   	   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
       	     
      	    //   Class.forName("oracle.jdbc.driver.OracleDriver");  
      	       Connection conn=null;
      	        String DB_URL="jdbc:oracle:thin:@localhost:1562:IVZWDB";
      	        String USER="MDM_DBUSER_MR2";
      	        String PASS="IVZW";
      	        System.out.println("Connected database successfully..1." + DB_URL + USER + PASS);
      			conn = DriverManager.getConnection(DB_URL,USER,PASS);
      			
      			Statement stmt = null;
      			
      			
      			
          }
     	 
	public SqlRowSet doExecute() throws SQLException {
		SqlRowSet srs=null;
		 checkdb();
		 DataSource source = getDataSource();
		     JdbcTemplate template = new JdbcTemplate(source);
		     System.out.println("DataSource = " + template.getDataSource().getConnection());
			try {
			 srs = template.queryForRowSet("Select * from Device where DEVICE_ID='990004709835800'");
			
			int rowCount = 0;
			System.out.println("Number of records : " + srs.findColumn("ID"));
			while (srs.next()) {
				System.out.println("Number ssof records :");
				System.out.println(srs.getString("ID") + " - "
						+ srs.getString("INSERTED") + " - "
						+ srs.getString("UPDATED") + " - "
						+ srs.getString("DEVICE_ID"));
				rowCount++;
			}
			System.out.println("Number of records : " + rowCount);
			
			return srs;
		} catch (InvalidResultSetAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return srs;
			
		

	}
	
	 private static DataSource getDataSource() {
	        // Creates a new instance of DriverManagerDataSource and sets
	        // the required parameters such as the Jdbc Driver class,
	        // Jdbc URL, database user name and password.
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(Sqlrowset.DRIVER);
	        dataSource.setUrl(Sqlrowset.JDBC_URL);
	        dataSource.setUsername(Sqlrowset.USERNAME);
	        dataSource.setPassword(Sqlrowset.PASSWORD);
	        return dataSource;
	    }
	 
	 public static void main(String args[]) throws Exception{
		 
		 Sqlrowset s = new Sqlrowset();
		 SqlRowSet s1= s.doExecute();
	 }

}