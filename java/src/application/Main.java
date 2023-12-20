package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage stage;
	public static int permission;
	public static String username;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/Login/loginSample.fxml"));

			Scene scene = new Scene(root, 600, 600);

//			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/c_styles.css").toExternalForm());


			primaryStage.setScene(scene);
			primaryStage.setTitle("Butcher Shop Management System");
			primaryStage.setResizable(true);
//			primaryStage.setMaximized(true);
			primaryStage.show();

			primaryStage.setOnCloseRequest(e -> {
				root.setDisable(false);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		@SuppressWarnings("unused")
		Driver d = new Driver();
		launch(args);

	}

}
