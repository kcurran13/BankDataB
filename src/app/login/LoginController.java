package app.login;


import app.Entities.User;
import app.Main;
import app.db.DB;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.event.ActionEvent;

import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;

public class LoginController {

    @FXML TextField txfUser;
    @FXML TextField txfPass;
    @FXML Button btnSubmit;

    private String userName;
    private String pass;

    // Use this in other Controllers to get "the currently logged in user".
    private static User user = null;
    public static User getUser() {
        return user; }

    @FXML
    private void initialize() {
        System.out.println("initialize login");
    }

    @FXML
    private void getUserText(ActionEvent event) {
            userName = txfUser.getText();
            pass = txfPass.getText();
        loadUser(userName, pass);
    }

    @FXML
    private void loadUser(String userName, String pass){
        user = DB.getMatchingUser(userName, pass);
        if(user == null) {
            System.out.println("sorry, wrong something!");
        }
        else {
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

    @FXML void goToHome() { switchScene("/app/home/home.fxml"); }

}
