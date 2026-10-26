package com.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnProperties {
	public static void main(String[] args) {
		Connection conn = null;
		
		// We are using config.properties are security and also at the runtime we can actually change the DB if we alter it in config.
		// So we actually don't need to compile again just if we change the DB.
		// The config.properties file contains the sensitive information like DB URL, DB Username, DB Password
		
		while (true) {
			try {
				FileInputStream fis = new FileInputStream("config.properties");
				// The config.properties is at the root. So we don't need to provide the full FilePath.
				// To read the file we use FileInputStream
				
				Properties properties = new Properties();
				// The Properties is a class which helps to load the key value pair from the FileInputStream read information
				
				properties.load(fis);
				// .load() accepts a FileInputStream object, this is method that actually loads the key value pair
				// This understands that first word is the key and the next part is the value with the separation with space or '='
				
				String url = (String) properties.get("mysql.url");
				String user = (String) properties.get("mysql.user");
				String password = (String) properties.get("mysql.password");
				// The .get() accepts the key and returns us an Object object, so we need to Type cast it.
				
				conn = DriverManager.getConnection(url, user, password);
				// Now the connection happens securely.

				System.out.println(conn + " -> " + conn.getCatalog());
				
				Thread.sleep(2000);

			} catch (Exception e) {
				e.printStackTrace();

			}
			/* OUTPUT:
			 * com.mysql.cj.jdbc.ConnectionImpl@617xxxx -> JDBC01
			 * com.mysql.cj.jdbc.ConnectionImpl@7428xxxx -> JDBC01
			 * com.mysql.cj.jdbc.ConnectionImpl@2bddxxxx -> JDBC01
			 * com.mysql.cj.jdbc.ConnectionImpl@6887xxxx -> JDBC02        --> changed url to JDBC02 in config at the runtime
			 * com.mysql.cj.jdbc.ConnectionImpl@7ac2xxxx -> JDBC02
			 * com.mysql.cj.jdbc.ConnectionImpl@22fxxxx -> JDBC02
			 * com.mysql.cj.jdbc.ConnectionImpl@1661xxxx -> JDBC02
			 * com.mysql.cj.jdbc.ConnectionImpl@9cbxxxx -> JDBC01           --> changed url to JDBC01 in config at the runtime
			 * com.mysql.cj.jdbc.ConnectionImpl@6265xxxx -> JDBC01
			 */
		}
	}
}
