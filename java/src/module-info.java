//module COMP333 {
//	requires javafx.controls;
//	requires java.sql;
//	requires javafx.base;
//	requires javafx.fxml;
//	
//	opens application to javafx.graphics, javafx.fxml;
//}

module COMP333 {

	requires java.sql;
//    requires mysql-connector-java;
	requires javafx.fxml;
	requires javafx.controls;

	opens application to javafx.fxml, javafx.controls;

	exports application;

	opens Login to javafx.fxml, javafx.controls;

	exports Login;

	opens MainMenu to javafx.fxml, javafx.controls;

	exports MainMenu;

	opens Manage to javafx.fxml, javafx.controls;

	exports Manage;

	opens ManageUsers to javafx.fxml, javafx.controls;

	exports ManageUsers;

	opens Orders to javafx.fxml, javafx.controls;

	exports Orders;

	opens OrdersHistory to javafx.fxml, javafx.controls;

	exports OrdersHistory;

	opens Statistics to javafx.fxml, javafx.controls;

	exports Statistics;

}