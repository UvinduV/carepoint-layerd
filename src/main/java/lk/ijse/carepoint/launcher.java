package lk.ijse.carepoint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(launcher.class.getResource("/view/signup_page.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(launcher.class.getResource("/view/login_form.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(launcher.class.getResource("/view/shedule_form.fxml"));
       // FXMLLoader fxmlLoader = new FXMLLoader(launcher.class.getResource("/view/serviceAppoint_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("carepoint");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}