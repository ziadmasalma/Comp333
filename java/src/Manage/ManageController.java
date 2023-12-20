package Manage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Driver;
import application.Main;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ManageController implements Initializable {

	@FXML
	private TabPane tp;
	@FXML
	private Tab eTab;
	@FXML
	private Tab sTab;
	@FXML
	private Tab cTab;
	@FXML
	private Tab iTab;

	@FXML
	private TableView<Employee> eTable;
	@FXML
	private TableColumn<Employee, Integer> CLM_id;
	@FXML
	private TableColumn<Employee, String> CLM_name;
	@FXML
	private TableColumn<Employee, Integer> CLM_ssn;
	@FXML
	private TableColumn<Employee, Integer> CLM_phoneNumber;
	@FXML
	private TableColumn<Employee, String> CLM_address;
	@FXML
	private TableColumn<Employee, String> CLM_startDate;
	@FXML
	private TableColumn<Employee, String> CLM_endDate;
	@FXML
	private TableColumn<Employee, Double> CLM_salary;
	@FXML
	private TextField TF_name;
	@FXML
	private TextField TF_ssn;
	@FXML
	private TextField TF_phoneNumber;
	@FXML
	private TextField TF_salary;
	@FXML
	private TextField TF_address;
	@FXML
	private DatePicker TF_startDate;
	@FXML
	private DatePicker TF_endDate;

	@FXML
	private TableView<Supplier> sTable;
	@FXML
	private TableColumn<Supplier, Integer> CLM_sID;
	@FXML
	private TableColumn<Supplier, String> CLM_sName;
	@FXML
	private TableColumn<Supplier, Integer> CLM_sPhone;
	@FXML

	private TableColumn<Supplier, String> CLM_sAddress;
	@FXML
	private TableColumn<Supplier, String> CLM_sDateOfAdding;
	@FXML
	private TableColumn<Supplier, String> CLM_sInfo;
	@FXML
	private TableColumn<Supplier, Button> CLM_sTypes;
	@FXML
	private TextField TF_sName;
	@FXML
	private TextField TF_sPhoneNumber;
	@FXML
	private TextField TF_sAddress;
	@FXML
	private TextField TF_sInfo;

	@FXML
	private TableView<Customer> cTable;
	@FXML
	private TableColumn<Customer, Integer> CLM_cID;
	@FXML
	private TableColumn<Customer, String> CLM_cName;
	@FXML
	private TableColumn<Customer, Integer> CLM_cPhone;
	@FXML
	private TableColumn<Customer, String> CLM_cDateOfAdding;
	@FXML
	private TableColumn<Customer, String> CLM_cAddress;
	@FXML
	private TextField TF_cName;
	@FXML
	private TextField TF_cPhoneNumber;
	@FXML
	private TextField TF_cAddress;

	@FXML
	private TableView<Item> iTable;
	@FXML
	private TableColumn<Item, Integer> CLM_iID;
	@FXML
	private TableColumn<Item, String> CLM_iModel;
	@FXML
	private TableColumn<Item, Double> CLM_iPurchase;
	@FXML
	private TableColumn<Item, Double> CLM_iSelling;
	@FXML
	private TableColumn<Item, String> CLM_iDateOfAdding;
	@FXML
	private TableColumn<Item, String> CLM_iDescription;
	@FXML
	private TableColumn<Item, String> CLM_iSupplier;
	@FXML
	private TableColumn<Item, String> CLM_iType;

	@FXML
	private TableColumn<Item, Integer> CLM_iQuantity;
	@FXML
	private TextField TF_iModelNumber;
	@FXML
	private TextField TF_iPurchasePrice;
	@FXML
	private TextField TF_iSellingPrice;
	@FXML
	private TextField TF_iDescription;
	@FXML
	private TextField TF_Type1;
	@FXML
	private TextField TF_isupplier1;
	@FXML
	private TextField TF_iQuantity;

	@FXML
	private Button BTN_bk;
	@FXML
	private Button BTN_deleteCustomer;
	@FXML
	private TextField TF_eSearch;
	@FXML
	private TextField TF_sSearch;
	@FXML
	private TextField TF_cSearch;
	@FXML
	private TextField TF_iSearch;

	ObservableList<Employee> eList;
	int eIndex = -1;

	ObservableList<Supplier> sList;
	int sIndex = -1;

	ObservableList<Customer> cList;
	int cIndex = -1;

	ObservableList<Item> iList;
	int iIndex = -1;

	static int flag = 0;

	public static void fromOrders() {
		flag = 1;
	}

	public static void fromMainMenu() {
		flag = 0;
	}

	public void goBack(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenuSample.fxml"));

			Scene scene = new Scene(root, 600, 700);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

			Main.stage.setScene(scene);

		} catch (IOException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	private void updateEmployeeTable() throws SQLException, ClassNotFoundException {
		eList = Driver.getEmployeesFromDatabase();

		CLM_id.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
		CLM_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		CLM_ssn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("ssn"));
		CLM_phoneNumber.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("phoneNumber"));
		CLM_address.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
		CLM_startDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("startDate"));
		CLM_endDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("endDate"));
		CLM_salary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
		eTable.setItems(eList);

		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Employee> filteredData = new FilteredList<>(eList, b -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		TF_eSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(employee.getId()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (employee.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(employee.getSsn()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(employee.getPhoneNumber()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else
					return false; // Does not match.
			});
		});

		SortedList<Employee> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(eTable.comparatorProperty());
		eTable.setItems(sortedData);
	}

	public void addNewEmployee(ActionEvent event) throws ClassNotFoundException {
		try {
			if (isAllFieldsFilledForDeleteEmployee()) {
				Driver.addNewEmployeeToTheDatabase(TF_name.getText(), Integer.parseInt(TF_ssn.getText()), Integer.parseInt(TF_phoneNumber.getText()), TF_address.getText(),
						Double.parseDouble(TF_salary.getText()));

				updateEmployeeTable();
				clearEmployeesTextFields(event);
			}

			else {
				showErrorMesaage("All fields should be filled");
			}
		} catch (NumberFormatException | SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void updateEmployee(ActionEvent event) throws ClassNotFoundException {
		try {
			eIndex = eTable.getSelectionModel().getSelectedIndex();

			if (eIndex > -1) {
				if (isAllFieldsFilledForUpdateEmployee()) {
					String endDate = (TF_endDate.getValue() == null) ? "NULL" : "'" + TF_endDate.getValue().toString() + "'";
					int id = Integer.parseInt(CLM_id.getCellData(eIndex).toString());

					Driver.updateEmployee(id, TF_name.getText(), Integer.parseInt(TF_ssn.getText()), Integer.parseInt(TF_phoneNumber.getText()), TF_address.getText(),
							Double.parseDouble(TF_salary.getText()), TF_startDate.getValue().toString(), endDate);

					updateEmployeeTable();
					clearEmployeesTextFields(event);
				}

				else {
					showErrorMesaage("All fields should be filled");
				}
			}

			else {
				showErrorMesaage("Select employee to update information");
			}

		} catch (NumberFormatException | SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void deleteEmployee(ActionEvent event) throws ClassNotFoundException {
		try {
			eIndex = eTable.getSelectionModel().getSelectedIndex();
			int id = Integer.parseInt(CLM_id.getCellData(eIndex).toString());

			if (eIndex > -1) {
				Driver.deleteEmployee(id);
				updateEmployeeTable();
				clearEmployeesTextFields(event);
			}

			else
				showErrorMesaage("Select Emplyee To Remove");

		} catch (SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void fireEmployee(ActionEvent event) throws ClassNotFoundException {
		try {
			eIndex = eTable.getSelectionModel().getSelectedIndex();
			int id = Integer.parseInt(CLM_id.getCellData(eIndex).toString());

			if (eIndex > -1) {
				Driver.fireEmployee(id);

				updateEmployeeTable();
				clearEmployeesTextFields(event);
			}

			else {
				showErrorMesaage("Select employee to fire");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void clearEmployeesTextFields(ActionEvent event) {
		TF_name.setText("");
		TF_ssn.setText("");
		TF_phoneNumber.setText("");
		TF_address.setText("");
		TF_salary.setText("");
		TF_startDate.setValue(null);
		TF_endDate.setValue(null);
	}

	private boolean isAllFieldsFilledForUpdateEmployee() {
		return !(TF_name.getText() == "" || TF_ssn.getText() == "" || TF_phoneNumber.getText() == "" || TF_salary.getText() == "" || TF_startDate.getValue() == null);
	}

	private boolean isAllFieldsFilledForDeleteEmployee() {
		return !(TF_name.getText() == "" || TF_ssn.getText() == "" || TF_phoneNumber.getText() == "" || TF_salary.getText() == "");
	}

	@FXML
	public void getSelectedEmployeeOnMouse(MouseEvent event) {
		getSelectedEmployee();
	}

	@FXML
	public void getSelectedEmployeeOnKey(KeyEvent event) {
		getSelectedEmployee();
	}

	private void getSelectedEmployee() {

		eIndex = eTable.getSelectionModel().getSelectedIndex();

		if (eIndex > -1) {
			TF_name.setText(CLM_name.getCellData(eIndex).toString());

			TF_ssn.setText(CLM_ssn.getCellData(eIndex).toString());

			TF_phoneNumber.setText(CLM_phoneNumber.getCellData(eIndex).toString());

			TF_address.setText(CLM_address.getCellData(eIndex).toString());

			TF_salary.setText(CLM_salary.getCellData(eIndex).toString());

			TF_startDate.setValue(LocalDate.parse((CLM_startDate.getCellData(eIndex).toString())));

			if (!(CLM_endDate.getCellData(eIndex).toString().equals("Still Working")))
				TF_endDate.setValue(LocalDate.parse(CLM_endDate.getCellData(eIndex).toString()));
			else
				TF_endDate.setValue(null);

			TF_name.setText(CLM_name.getCellData(eIndex).toString());
		}

	}

	private void updateSupplierTable() throws SQLException, ClassNotFoundException {
		sList = Driver.getSuppliersFromDatabase();

		CLM_sID.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
		CLM_sName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
		CLM_sPhone.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("phoneNumber"));
		CLM_sAddress.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
		CLM_sDateOfAdding.setCellValueFactory(new PropertyValueFactory<Supplier, String>("dateOfAdding"));
		CLM_sInfo.setCellValueFactory(new PropertyValueFactory<Supplier, String>("info"));
		CLM_sTypes.setCellValueFactory(new PropertyValueFactory<Supplier, Button>("BTN_types"));

		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Supplier> filteredData = new FilteredList<>(sList, b -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		TF_sSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(supplier -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(supplier.getId()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (supplier.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(supplier.getPhoneNumber()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (supplier.getAddress().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (supplier.getInfo().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else
					return false; // Does not match.
			});
		});

		SortedList<Supplier> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(sTable.comparatorProperty());
		sTable.setItems(sortedData);

	}

	public void getSelectedSupplierOnMouse(MouseEvent event) {
		getSelectedSupplier();
	}

	@FXML
	public void getSelectedSupplierOnKey(KeyEvent event) {
		getSelectedSupplier();
	}

	private void getSelectedSupplier() {

		sIndex = sTable.getSelectionModel().getSelectedIndex();

		if (sIndex > -1) {
			TF_sName.setText(CLM_sName.getCellData(sIndex));
			TF_sPhoneNumber.setText(CLM_sPhone.getCellData(sIndex).toString());
			TF_sInfo.setText(CLM_sInfo.getCellData(sIndex));
			TF_sAddress.setText(CLM_sAddress.getCellData(sIndex));
		}

	}

	public void addNewSupplier(ActionEvent event) throws ClassNotFoundException {
		try {
			if (TF_sName.getText() != "" && TF_sPhoneNumber.getText() != "") {
				Driver.addNewSupplierToTheDatabase(TF_sName.getText(), Integer.parseInt(TF_sPhoneNumber.getText()), TF_sInfo.getText(), TF_sAddress.getText());
				updateSupplierTable();
				clearSuppliersTextFields(event);
			} else
				showErrorMesaage("Name and phone number should filled");

		} catch (SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void updateSupplier(ActionEvent event) throws ClassNotFoundException {
		try {
			sIndex = sTable.getSelectionModel().getSelectedIndex();
			if (sIndex > -1) {
				if (TF_sName.getText() != "" && TF_sPhoneNumber.getText() != "") {
					int id = Integer.parseInt(CLM_sID.getCellData(sIndex).toString());

					Driver.updateSupplier(id, TF_sName.getText(), Integer.parseInt(TF_sPhoneNumber.getText()), TF_sInfo.getText(), TF_sAddress.getText());
					updateSupplierTable();
					clearSuppliersTextFields(event);
				} else
					showErrorMesaage("Name and phone number should filled");
			} else {
				showErrorMesaage("Select Supplier To Update Info");
			}

		} catch (NumberFormatException | SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void deleteSupplier(ActionEvent event) throws ClassNotFoundException {
		try {
			sIndex = sTable.getSelectionModel().getSelectedIndex();
			int id = Integer.parseInt(CLM_sID.getCellData(sIndex).toString());

			if (sIndex > -1) {
				Driver.deleteSupplier(id);
				updateSupplierTable();
				clearSuppliersTextFields(event);
			}

			else
				showErrorMesaage("Select Supplier To Remove");

		} catch (SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void clearSuppliersTextFields(ActionEvent event) {
		TF_sName.setText("");
		TF_sPhoneNumber.setText("");
		TF_sAddress.setText("");
		TF_sInfo.setText("");
	}

	public static void showTypes(int id, String name) {
		try {
			SupplierTypesController.initializeID(id, name);
			Parent root = FXMLLoader.load(Main.class.getResource("/Manage/SupplierTypes.fxml"));
			Scene scene = new Scene(root, 700, 400);
			scene.getStylesheets().add(Main.class.getResource("/application.css").toExternalForm());
			Main.stage.setScene(scene);

		} catch (IOException e) {
//			e.printStackTrace();
			Alert ALRT_databaseError = new Alert(AlertType.ERROR);
			ALRT_databaseError.setTitle("Error!!");
			ALRT_databaseError.setHeaderText("Error Message: " + e.getMessage());
			ALRT_databaseError.setContentText(null);
			ALRT_databaseError.show();
		}
	}

	private void updateCustomersTable() throws SQLException, ClassNotFoundException {
		cList = Driver.getCustomersFromDatabase();

		CLM_cID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		CLM_cName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		CLM_cPhone.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("phone"));
		CLM_cAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		CLM_cDateOfAdding.setCellValueFactory(new PropertyValueFactory<Customer, String>("dateOfAdding"));

		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Customer> filteredData = new FilteredList<>(cList, b -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		TF_cSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(customer -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(customer.getId()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (customer.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(customer.getPhone()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (customer.getAddress().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else
					return false; // Does not match.
			});
		});

		SortedList<Customer> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(cTable.comparatorProperty());
		cTable.setItems(sortedData);

	}

	public void getSelectedCustomerOnMouse(MouseEvent event) {
		getSelectedCustomer();
	}

	@FXML
	public void getSelectedCustomerOnKey(KeyEvent event) {
		getSelectedCustomer();
	}

	private void getSelectedCustomer() {

		cIndex = cTable.getSelectionModel().getSelectedIndex();

		if (cIndex > -1) {
			TF_cName.setText(CLM_cName.getCellData(cIndex));
			TF_cPhoneNumber.setText(CLM_cPhone.getCellData(cIndex).toString());
			TF_cAddress.setText(CLM_cAddress.getCellData(cIndex));
		}

	}

	public void addNewCustomer(ActionEvent event) throws ClassNotFoundException {
		try {
			if (TF_cName.getText() != "") {
				Driver.addNewCustomerToTheDatabase(TF_cName.getText(), Integer.parseInt(TF_cPhoneNumber.getText()), TF_cAddress.getText());
				updateCustomersTable();
				clearCustomersTextFields(event);
			} else
				showErrorMesaage("Name should be filled");

		} catch (SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void updateCustomer(ActionEvent event) throws ClassNotFoundException {
		try {
			cIndex = cTable.getSelectionModel().getSelectedIndex();
			if (cIndex > -1) {
				if (TF_cName.getText() != "") {
					int id = Integer.parseInt(CLM_cID.getCellData(cIndex).toString());

					Driver.updateCustomer(id, TF_cName.getText(), Integer.parseInt(TF_cPhoneNumber.getText()), TF_cAddress.getText());
					updateCustomersTable();
					clearCustomersTextFields(event);
				} else
					showErrorMesaage("Name should be filled");
			} else {
				showErrorMesaage("Select Customer To Update Info");
			}

		} catch (NumberFormatException | SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void deleteCustomer(ActionEvent event) throws ClassNotFoundException {
		try {
			cIndex = cTable.getSelectionModel().getSelectedIndex();
			int id = Integer.parseInt(CLM_cID.getCellData(cIndex).toString());

			if (cIndex > -1) {
				Driver.deleteCustomer(id);
				updateCustomersTable();
				clearCustomersTextFields(event);
			}

			else
				showErrorMesaage("Select Customer To Remove");

		} catch (SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void deleteItems(ActionEvent event) throws ClassNotFoundException {
		try {
			cIndex = iTable.getSelectionModel().getSelectedIndex();
			int id = Integer.parseInt(CLM_iID.getCellData(cIndex).toString());

			if (cIndex > -1) {
				Driver.deleteItems(id);
				updateItemsTable();
				clearItemsTextFields(event);
			}

			else
				showErrorMesaage("Select Customer To Remove");

		} catch (SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void clearCustomersTextFields(ActionEvent event) {
		TF_cName.setText("");
		TF_cPhoneNumber.setText("");
		TF_cAddress.setText("");
	}

	private void updateItemsTable() throws SQLException, ClassNotFoundException {
		iList = Driver.getItemsFromDatabase();

		CLM_iID.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
		CLM_iModel.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
		CLM_iPurchase.setCellValueFactory(new PropertyValueFactory<Item, Double>("purchasePrice"));
		CLM_iSelling.setCellValueFactory(new PropertyValueFactory<Item, Double>("sellingPrice"));
		CLM_iDateOfAdding.setCellValueFactory(new PropertyValueFactory<Item, String>("dateOfAdding"));
		CLM_iDescription.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
		CLM_iSupplier.setCellValueFactory(new PropertyValueFactory<Item, String>("supplierName"));
		CLM_iType.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));

		CLM_iQuantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("Quantity"));

		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Item> filteredData = new FilteredList<>(iList, b -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		TF_iSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(item.getId()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (item.getModel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (item.getDescription().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (item.getSupplierName().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (item.getType().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else
					return false; // Does not match.
			});
		});

		SortedList<Item> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(iTable.comparatorProperty());
		iTable.setItems(sortedData);

	}

	public void getSelectedItemOnMouse(MouseEvent event) {
		getSelectedItem();
	}

	@FXML
	public void getSelectedItemOnKey(KeyEvent event) {
		getSelectedItem();
	}

	private void getSelectedItem() {

		iIndex = iTable.getSelectionModel().getSelectedIndex();

		if (iIndex > -1) {
			TF_iModelNumber.setText(CLM_iModel.getCellData(iIndex));
			TF_iPurchasePrice.setText(CLM_iPurchase.getCellData(iIndex).toString());
			TF_iSellingPrice.setText(CLM_iSelling.getCellData(iIndex).toString());
			TF_iDescription.setText(CLM_iDescription.getCellData(iIndex));

			TF_iQuantity.setText(CLM_iQuantity.getCellData(iIndex).toString());
		}

	}

	public void clearItemsTextFields(ActionEvent event) {
		TF_iModelNumber.setText("");
		TF_iPurchasePrice.setText("");
		TF_iSellingPrice.setText("");
		TF_iDescription.setText("");

		TF_iQuantity.setText("");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			if (flag == 1) {
				SingleSelectionModel<Tab> selectionModel = tp.getSelectionModel();
				selectionModel.select(cTab);
				eTab.setDisable(true);
				sTab.setDisable(true);
				iTab.setDisable(true);
				BTN_bk.setVisible(false);
				BTN_deleteCustomer.setVisible(false);
			}

			else {
				SingleSelectionModel<Tab> selectionModel = tp.getSelectionModel();
				selectionModel.select(eTab);
				eTab.setDisable(false);
				cTab.setDisable(false);
				sTab.setDisable(false);
				iTab.setDisable(false);
				BTN_bk.setVisible(true);
				BTN_deleteCustomer.setVisible(true);
			}

			updateEmployeeTable();
			updateSupplierTable();
			updateCustomersTable();
			updateItemsTable();

			TF_startDate.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
				if (TF_startDate.getEditor().getText().length() == 0)
					TF_startDate.setValue(null);
			});

			TF_endDate.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
				if (TF_endDate.getEditor().getText() == "")
					TF_endDate.setValue(null);

			});

		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void addNewItem(ActionEvent event) throws ClassNotFoundException {
		try {
			if (TF_iModelNumber.getText() != "") {

//					   addNewItemToTheDatabase(String TF_iModelNumber, double TF_iPurchasePrice, double TF_iSellingPrice, String TF_iDescription, String TF_Type1, int TF_isupplier1, int TF_iQuantity)
				Driver.addNewItemToTheDatabase(TF_iModelNumber.getText(), Double.parseDouble(TF_iPurchasePrice.getText()), Double.parseDouble(TF_iSellingPrice.getText()), TF_iDescription.getText(),
						Integer.parseInt(TF_Type1.getText()), Integer.parseInt(TF_isupplier1.getText()), Integer.parseInt(TF_iQuantity.getText()));
				updateItemsTable();
				clearItemsTextFields(event);
			} else
				showErrorMesaage("Name should be filled");

		} catch (SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void onlyInteger(KeyEvent event) {
		TextField TF = (TextField) event.getSource();
		TF.setText(TF.getText().replaceAll("[^\\d]", ""));
		TF.positionCaret(TF.getText().length());
	}

	private void showErrorMesaage(String str) {
		Alert ALRT_databaseError = new Alert(AlertType.ERROR);
		ALRT_databaseError.setTitle("Error!!");
		ALRT_databaseError.setHeaderText("Error Message: " + str);
		ALRT_databaseError.setContentText(null);
		ALRT_databaseError.show();
	}

}
