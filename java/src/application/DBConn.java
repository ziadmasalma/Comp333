package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class DBConn {

	private String dbURL;
	private String dbUsername;
	private String dbPassword;
	private String URL;
	private String port;
	private String dbName;

	// imported sql.Connection
	private Connection con;

	// default constaractor taking given "connecting info" From "DataBaseConnection"
	// _ _ _ _ _ _ _ _ _ _ _ _ _ _ ________ _ _ _ _ _ __ _ _ _ _ _ _ __ ^
	// _ _ _ _ _ _ _ _ _ _ _ _ _ _ ______ _ _ _ _ _ _ __ _ _ _ _ _ ____ |
	// IMPORTANT________________________________________________________|
	DBConn(String URL, String port, String dbName, String dbUsername, String dbPassword) {
		this.URL = URL;
		this.port = port;
		this.dbName = dbName;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}

	// this method should be called after making DataBaseConnection
	// I mean that we need to do the following (at some point)(we do it in the driver) to complete the connecting
	//
	// private static Connection con;
	// con = DataBaseConnection.getConnection().connectDB();

	// making method with name "connectDB" ------->> That returns an Object From type of "Connection"
	public Connection connectDB() throws ClassNotFoundException, SQLException {

//		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";

//		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

//		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT";
//		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT";

//		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?allowPublicKeyRetrieval=true&useSSL=false";
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT";

		System.out.println("Connected to database: " + dbURL);
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");

		// Class.forName("com.mysql.cj.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, p);
		// new com.mysql.jdbc.Driver();
		// con = DriverManager.getConnection(dbURL,p);

		return con;
	}

}