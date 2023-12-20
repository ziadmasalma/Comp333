package application;

public class DataBaseConnection {

	// creating "connecting info" for the object (Connector)
	private String dbUsername = "root"; // mysql user name
	private String dbPassword = "0518"; // mysql password
	private String URL = "127.0.0.1"; // location of db server
	private String port = "3306"; // constant
	private String dbName = "StoreSystem"; // most likely will not change

	// IMPORTANT: making an object of a connector (DBConn)
	private DBConn connection;

//////////////////////////////////////////////////////////////////
//constaractors

	// constaractor with given "connecting info"
	public DataBaseConnection(String dbUsername, String dbPassword, String URL, String port, String dbName) {

		// IMPORTANT: setting values in the object we made { connector (DBConn) }
		this.connection = new DBConn(URL, port, dbName, dbUsername, dbPassword);
	}

	// default constaractor with NO given "connecting info"
	public DataBaseConnection() {
		dbUsername = "root";
		dbPassword = "0518";
		URL = "127.0.0.1";
		port = "3306";
		dbName = "StoreSystem";

		// IMPORTANT: setting values in the object we made { connector (DBConn) }
		this.connection = new DBConn(URL, port, dbName, dbUsername, dbPassword);
	}

//////////////////////////////////////////////////////////////////
// getters and setters

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public DBConn getCon() {
		return connection;
	}

	public DBConn getConnection() {
		return connection;
	}
}
