package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Manage.*;
import ManageUsers.*;
import Orders.*;
import OrdersHistory.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class Driver {

	private static Connection con;

	// default constaractor to finally make the connection out from those two classes:
	//
	// 1-DataBaseConnection a = new DataBaseConnection();
	// {Calling default constractor :-->
	// --> so it calls the second class we need (DBConn) and make it ready to call its method that does the finall connection process(connectDB method)}.
	//
	// 2-the second class is "DBConn" that have been used by deafault by making an object from "DataBaseConnection" which make an object from this class(DBConn).
	public Driver() throws SQLException, ClassNotFoundException {
		DataBaseConnection a = new DataBaseConnection();

		con = a.getConnection().connectDB();
		con.createStatement();

		System.out.println("Connection established");
	}

////////////////////////////////////////////////////////////

	public static int checkUser(String username, String passsword) throws SQLException, SQLException {
		String STR_admins = "SELECT uPassword FROM SystemUsers WHERE Username = '" + username + "'";
		Statement STT_admins = con.createStatement();
		ResultSet RS_admins = STT_admins.executeQuery(STR_admins);
		if (RS_admins.next()) {
			if (RS_admins.getString(1).equals(passsword))
				return 1;
		}

		String STR_employees = "SELECT uPassword FROM EmployeeUsers WHERE eID = " + username;

		if (isInteger(username)) {
			Statement STT_employees = con.createStatement();
			ResultSet RS_employees = STT_employees.executeQuery(STR_employees);
			if (RS_employees.next()) {
				if (RS_employees.getString(1).equals(passsword))
					return 2;
			}
		}

		return 0;
	}

	private static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);

		} catch (Exception e) {
			return false;

		}

		return true;
	}

	public static ObservableList<User> getUsersFromDatabase() throws SQLException {
		ObservableList<User> list = FXCollections.observableArrayList();

//		String STR_admins = "SELECT * FROM SystemUsers";
//		Statement STT_admins = con.createStatement();
//		ResultSet RS_admins = STT_admins.executeQuery(STR_admins);
//
//		while (RS_admins.next())
//			list.add(new User(RS_admins.getString(3), RS_admins.getString(1), RS_admins.getString(2), "Admin"));
//
		String STR_employees = "SELECT E.eName, U.eID, U.uPassword FROM Employee E, EmployeeUsers U WHERE U.eID = E.ID";
		Statement STT_employees = con.createStatement();
		ResultSet RS_employees = STT_employees.executeQuery(STR_employees);

		while (RS_employees.next())
			list.add(new User(RS_employees.getString(1), RS_employees.getInt(2), RS_employees.getString(3)));

		return list;
	}

	public static void updateEmployeeUser(int id, String newPassword) throws SQLException {
		String STR = String.format("UPDATE EmployeeUsers SET uPassword = '%s' WHERE eID = %d", newPassword, id);
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void deleteEmployeeUser(int id) throws SQLException {
		String STR = "DELETE FROM EmployeeUsers WHERE eID = " + id;
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void addEmployeeUser(int id, String password) throws SQLException {
		String STR = String.format("INSERT INTO EmployeeUsers(eID, uPassword) VALUE (%d, '%s')", id, password);
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static ObservableList<Employee> getEmployeesFromDatabase() throws SQLException {
		ObservableList<Employee> list = FXCollections.observableArrayList();
		String STR = "SELECT * FROM Employee;";
		Statement STT = con.createStatement();
		ResultSet RS = STT.executeQuery(STR);

		while (RS.next()) {
			Date grabbedDate = RS.getDate("EndDate");////
			String endDate = (grabbedDate == null) ? "Still Working" : grabbedDate.toString();
			list.add(new Employee(RS.getInt("ID"), RS.getString("eName"), RS.getInt("SSN"), RS.getInt("Phone"), RS.getString("Address"), RS.getDate("StartDate").toString(), endDate,
					RS.getDouble("Salary")));
		}

		return list;
	}

	public static void addNewEmployeeToTheDatabase(String name, int ssn, int phoneNumber, String address, double salary) throws SQLException {
		String STR = String.format("INSERT INTO Employee(eName, SSN, Phone, Address, Salary) VALUE ('%s', %d, %d, '%s', %f)", name, ssn, phoneNumber, address, salary);
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void updateEmployee(int id, String name, int ssn, int phoneNumber, String address, double salary, String startDate, String endDate) throws SQLException {
		String STR = String.format("UPDATE Employee SET eName = '%s', SSN =  %d, Phone = %d, Address = '%s', Salary = %f, StartDate = '%s'," + "EndDate = %s WHERE ID = %d", name, ssn, phoneNumber,
				address, salary, startDate, endDate, id);
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void deleteEmployee(int ID) throws SQLException {
		String STR = "DELETE FROM Employee WHERE ID = " + ID;
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void fireEmployee(int ID) throws SQLException {
		String STR = "UPDATE Employee SET endDate = (NOW()) WHERE ID = " + ID + "  AND EndDate IS NULL";
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static ObservableList<Supplier> getSuppliersFromDatabase() throws SQLException {
		ObservableList<Supplier> list = FXCollections.observableArrayList();
		String STR = "SELECT * FROM Supplier;";
		Statement STT = con.createStatement();
		ResultSet RS = STT.executeQuery(STR);

		while (RS.next()) {
			list.add(new Supplier(RS.getInt("ID"), RS.getString("sName"), RS.getInt("Phone"), RS.getString("Address"), RS.getDate("DateOfAdding").toString(), RS.getString("MoreInfo")));
		}

		return list;
	}

	public static void addNewSupplierToTheDatabase(String name, int phoneNumber, String info, String address) throws SQLException {
		String STR = String.format("INSERT INTO Supplier(sName, Phone, MoreInfo, Address) VALUE ('%s', %d,  '%s', '%s')", name, phoneNumber, info, address);
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void updateSupplier(int id, String name, int phoneNumber, String info, String address) throws SQLException {
		String STR = String.format("UPDATE Supplier SET sName = '%s', Phone =  %d,  MoreInfo = '%s', Address = '%s' WHERE ID = %d", name, phoneNumber, info, address, id);
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void deleteSupplier(int ID) throws SQLException {
		String STR = "DELETE FROM Supplier WHERE ID = " + ID;
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static ArrayList<ArrayList<String>> getTypesOfSupplier(int ID) throws SQLException {
		ArrayList<ArrayList<String>> twoLists = new ArrayList<>();
		ArrayList<String> allTypes = new ArrayList<>();
		ArrayList<String> supplierTypes = new ArrayList<>();

		String STR_getAllTypesSQL = "SELECT tName FROM ItemType";
		Statement STT_getAllTypes = con.createStatement();
		ResultSet RS_allTypes = STT_getAllTypes.executeQuery(STR_getAllTypesSQL);
		while (RS_allTypes.next())
			allTypes.add(RS_allTypes.getString(1));

		String STR_getSuppTypesSQL = "SELECT T.tName FROM ItemType T, Supplier_Type ST WHERE T.ID = tID AND sID = " + ID;
		Statement STT_getSuppTypes = con.createStatement();
		ResultSet RS_suppTypes = STT_getSuppTypes.executeQuery(STR_getSuppTypesSQL);
		while (RS_suppTypes.next())
			supplierTypes.add(RS_suppTypes.getString(1));

		for (String str : supplierTypes)
			allTypes.remove(str);

		twoLists.add(allTypes);
		twoLists.add(supplierTypes);
		return twoLists;
	}

	public static void updateSupplierTypes(int ID, ArrayList<String> supplierTypes) throws SQLException {
		String STR_removeAllTypesOfSupplier = "DELETE FROM Supplier_Type WHERE sID = " + ID;
		Statement STT_removeAllTypesOfSupplier = con.createStatement();
		STT_removeAllTypesOfSupplier.execute(STR_removeAllTypesOfSupplier);

		for (String str : supplierTypes) {
			String STR_getItemID = "SELECT ID FROM ItemType WHERE tName = '" + str + "'";
			Statement STT_getItemID = con.createStatement();
			ResultSet RS_itemID = STT_getItemID.executeQuery(STR_getItemID);
			RS_itemID.next();
			int ItemID = RS_itemID.getInt(1);

			String STR_InsertSupplierItem = String.format("INSERT INTO Supplier_Type(sID, tID) VALUE (%d, %d)", ID, ItemID);
			Statement STT_InsertSupplierItem = con.createStatement();
			STT_InsertSupplierItem.execute(STR_InsertSupplierItem);
		}
	}

	public static ObservableList<Customer> getCustomersFromDatabase() throws SQLException {
		ObservableList<Customer> list = FXCollections.observableArrayList();
		String STR = "SELECT * FROM Customer";
		Statement STT = con.createStatement();
		ResultSet RS = STT.executeQuery(STR);

		while (RS.next()) {
			list.add(new Customer(RS.getInt("ID"), RS.getString("cName"), RS.getInt("PhoneNumber"), RS.getDate("DateOfAdding").toString(), RS.getString("Address")));
		}

		return list;
	}

	public static void addNewCustomerToTheDatabase(String name, int phoneNumber, String address) throws SQLException {
		String STR = String.format("INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('%s', %d, '%s')", name, phoneNumber, address);
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void addNewItemToTheDatabase(String TF_iModelNumber, double TF_iPurchasePrice, double TF_iSellingPrice, String TF_iDescription, int TF_Type1, int TF_isupplier1, int TF_iQuantity)
			throws SQLException {
		String STR = String.format("INSERT INTO Item(ModelNumber, PurchasePrice, SellingPrice, ItemType, SupplierID, iDescription,  Quantity) VALUE ('%s', %f, %f, %d,'%s','%s',%d)", TF_iModelNumber,
				TF_iPurchasePrice, TF_iSellingPrice, TF_Type1, TF_isupplier1, TF_iDescription, TF_iQuantity);
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void updateCustomer(int id, String name, int phoneNumber, String address) throws SQLException {
		String STR = String.format("UPDATE Customer SET cName = '%s', PhoneNumber =  %d, Address = '%s' WHERE ID = %d", name, phoneNumber, address, id);
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void deleteCustomer(int ID) throws SQLException {
		String STR = "DELETE FROM Customer WHERE ID = " + ID;
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static void deleteItems(int ID) throws SQLException {
		String STR = "DELETE FROM Item WHERE ID = " + ID;
		Statement STT = con.createStatement();
		STT.execute(STR);
	}

	public static ObservableList<Item> getItemsFromDatabase() throws SQLException {
		ObservableList<Item> list = FXCollections.observableArrayList();
		String STR = "SELECT I.ID, I.ModelNumber, I.PurchasePrice, I.SellingPrice, I.DateOfAdding, I.iDescription, S.sName, T.tName,  I.Quantity"
				+ "	FROM Item I, Supplier S, ItemType T WHERE I.ItemType = T.ID AND I.SupplierID = S.ID";
		Statement STT = con.createStatement();
		ResultSet RS = STT.executeQuery(STR);

		while (RS.next()) {
			list.add(new Item(RS.getInt("ID"), RS.getString("ModelNumber"), RS.getDouble("PurchasePrice"), RS.getDouble("SellingPrice"), RS.getDate("DateOfAdding").toString(),
					RS.getString("iDescription"), RS.getString("sName"), RS.getString("tName"), RS.getInt("Quantity")));
		}

		return list;
	}

	public static int insertOrderData(ObservableList<Item> iList, ObservableList<CartItem> cList, int employeeID, int customerID) throws SQLException {
		for (Item item : iList) {
			String STR_updateItems = String.format("UPDATE Item SET Quantity = %d WHERE ID = %d", item.getQuantity(), item.getId());
			Statement STT_updateItems = con.createStatement();
			STT_updateItems.execute(STR_updateItems);
		}

		String STR_insertOrder = String.format("INSERT INTO Orders (cID, eID) VALUE (%d, %d)", customerID, employeeID);
		Statement STT_insertOrder = con.createStatement();
		STT_insertOrder.execute(STR_insertOrder);

		String STR_getOrderID = "SELECT ID FROM Orders ORDER BY ID DESC LIMIT 1";
		Statement STT_getOrderID = con.createStatement();
		ResultSet RS = STT_getOrderID.executeQuery(STR_getOrderID);
		RS.next();
		int orderID = RS.getInt(1);

		for (CartItem item : cList) {
			String STR_insertOrderDetails = String.format("INSERT INTO OrderDetails (oID, iID, Price, Quantity) VALUE (%d, %d, %f, %d)", orderID, item.getId(), item.getUnitPrice(),
					item.getQuantity());
			Statement STT_insertOrderDetails = con.createStatement();
			STT_insertOrderDetails.execute(STR_insertOrderDetails);
		}

		return orderID;
	}

	public static ObservableList<CartItem> itemsToReturn(int orderID) throws SQLException {
		ObservableList<CartItem> items = FXCollections.observableArrayList();

		String STR = "SELECT I.ID, I.ModelNumber, I.iDescription,  D.Quantity, D.Price, D.Quantity*D.Price AS TotalPrice " + "FROM OrderDetails D, Item I WHERE D.iID = I.ID AND D.oID = " + orderID;
		Statement STT = con.createStatement();
		ResultSet RS = STT.executeQuery(STR);

		while (RS.next()) {
			items.add(new CartItem(RS.getInt(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getDouble(5), RS.getShort(6)));
		}

		return items;
	}

	public static void returnItem(int itemID, int quantity, int orderID) throws SQLException {
		String STR_updateItemQuantity = String.format("UPDATE Item SET Quantity = Quantity+%d WHERE ID = %d", quantity, itemID);
		Statement STT_updateItemQuantity = con.createStatement();
		STT_updateItemQuantity.execute(STR_updateItemQuantity);

		String STR_deleteOrderDetail = String.format("DELETE FROM OrderDetails WHERE oID = %d AND iID = %d", orderID, itemID);
		Statement STT_deleteOrderDetail = con.createStatement();
		STT_deleteOrderDetail.execute(STR_deleteOrderDetail);
	}

	public static ObservableList<OrderHistory> getOrderHistory() throws SQLException {
		ObservableList<OrderHistory> list = FXCollections.observableArrayList();

		String STR = "SELECT O.DateAndTime, CONCAT(I.ModelNumber) AS Item, D.Price, D.Quantity, C.cName, E.eName " + "FROM OrderDetails D, Orders O, Item I, Customer C, Employee E "
				+ "WHERE D.oID = O.ID AND D.iID = I.ID AND O.CID = C.ID AND O.eID = E.ID";
		Statement STT = con.createStatement();
		ResultSet RS = STT.executeQuery(STR);

		while (RS.next())
			list.add(new OrderHistory(RS.getString(1), RS.getString(2), RS.getDouble(3), RS.getInt(4), RS.getString(5), RS.getString(6)));

		return list;
	}

	public static ObservableList<PieChart.Data> getPieChartSoldData() throws SQLException {
		ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

		String STR = "SELECT T.tName, Count(*) FROM Item I, ItemType T, OrderDetails D WHERE I.ItemType = T.ID AND D.iID = I.ID GROUP BY (T.tName)";
		Statement STT = con.createStatement();
		ResultSet RS = STT.executeQuery(STR);

		while (RS.next())
			list.add(new PieChart.Data(RS.getString(1), RS.getInt(2)));

		return list;
	}

	public static ObservableList<PieChart.Data> getPieChartAllData() throws SQLException {
		ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

		String STR = "SELECT T.tName, Count(*) FROM Item I, ItemType T WHERE I.ItemType = T.ID GROUP BY (T.tName)";
		Statement STT = con.createStatement();
		ResultSet RS = STT.executeQuery(STR);

		while (RS.next())
			list.add(new PieChart.Data(RS.getString(1), RS.getInt(2)));

		return list;
	}

	public static double[] getProfitAndSales(String start, String end) throws SQLException {
		double[] array = new double[2];

		String STR_sales = String.format("SELECT SUM(D.Price*D.Quantity) AS SUM FROM OrderDetails D, Orders O WHERE D.oID = O.ID AND DateAndTime BETWEEN '%s' AND '%s'", start, end);
		Statement STT_sales = con.createStatement();
		ResultSet RS_sales = STT_sales.executeQuery(STR_sales);
		RS_sales.next();
		array[0] = RS_sales.getDouble(1);

		String STR_profit = String.format(
				"SELECT SUM((D.Price-I.PurchasePrice)*D.Quantity) AS SUM FROM OrderDetails D, Orders O, Item I WHERE D.oID = O.ID AND I.ID = D.iID AND DateAndTime BETWEEN '%s' AND '%s'", start, end);
		Statement STT_profit = con.createStatement();
		ResultSet RS_profit = STT_profit.executeQuery(STR_profit);
		RS_profit.next();
		array[1] = RS_profit.getDouble(1);

		return array;
	}
}
