package app.login;


import app.Entities.User;
import app.Main;
import app.db.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField txfUser;
    @FXML
    TextField txfPass;
    @FXML
    Button btnSubmit;
    @FXML
    Label lblLoginError;

    private String userName;
    private String pass;
    private static User user = null;

    public static User getUser() {
        return user;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void getUserText() {
        userName = txfUser.getText();
        pass = txfPass.getText();
        loadUser(userName, pass);
    }

    @FXML
    private void loadUser(String userName, String pass) {
        try {
            user = DB.getMatchingUser(userName, pass);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (user == null) {
            lblLoginError.setVisible(true);
        } else {
            goToHome();
        }
    }

    private void switchScene(String pathname) {
        try {
            Parent bla = FXMLLoader.load(getClass().getResource(pathname));
            Scene scene = new Scene(bla, 800, 600);
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void goToHome() {
        switchScene("/app/home/home.fxml");
    }

}
