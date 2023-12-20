package Login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import application.Driver;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

	@FXML
	private Button BTN_login;

	@FXML
	private TextField TF_username;

	@FXML
	private TextField TF_password;

	@FXML
	private Button BTN_exit;

	@FXML
	private Label LBL_msg;

	public void login(ActionEvent event) {
		String strUsername = TF_username.getText().trim();
		String strPassword = TF_password.getText().trim();

		try {
			if (strUsername.isEmpty() || strPassword.isEmpty()) {
				LBL_msg.setText("Something Missing!");
			}

			else {
				int x = Driver.checkUser(strUsername, strPassword);
				if (x == 1 || x == 2) {
					Main.permission = x;
					Main.username = strUsername;
					goToMainMenu();
				}

				else
					LBL_msg.setText("Check Your Info!");
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	private void goToMainMenu() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenuSample.fxml"));
			Scene scene = new Scene(root, 600, 700);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			Main.stage.setScene(scene);

		} catch (IOException e) {
//			e.printStackTrace();
			showErrorMesaage(e.getMessage());
		}
	}

	public void exit(ActionEvent event) {
		Alert ALRT_exit = new Alert(AlertType.CONFIRMATION);
		ALRT_exit.setTitle("EXIT?");
		ALRT_exit.setHeaderText("Are you sure you want to exit?");
		ALRT_exit.setContentText(null);
		Optional<ButtonType> result = ALRT_exit.showAndWait();

		if (result.get() == ButtonType.OK) {
			Main.stage.close();
			System.exit(0);
		}
	}

	private void showErrorMesaage(String str) {
		Alert ALRT_databaseError = new Alert(AlertType.ERROR);
		ALRT_databaseError.setTitle("Error!!");
		ALRT_databaseError.setHeaderText("Error Message: " + str);
		ALRT_databaseError.setContentText(null);
		ALRT_databaseError.show();
	}

}
