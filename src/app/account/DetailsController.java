package app.account;

import app.Entities.User;
import app.db.DB;
import app.home.HomeController;
import app.login.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailsController {
    private User user;
    private ResultSet rs;

    @FXML ChoiceBox dropAccName,dropRemoveAcc;
    @FXML TextField txfNewAccName, txfChangeName;
    @FXML Button btnAccName, btnRemoveAcc, btnAddAcc;
    @FXML Label lblAccRemoved, lblNameChanged, lblAccCreated;

    @FXML
    void initialize(){
        System.out.println("initialize details");

        ObservableList<String> data = FXCollections.observableArrayList();

        user = LoginController.getUser();

        try {
            rs = DB.getUserAccounts(user);
            while(rs.next()) {
                String item = rs.getString(1);
                data.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dropAccName.setItems(data);
        dropRemoveAcc.setItems(data);
    }

    @FXML
    private void getButtonInput(ActionEvent event) {
        //change account name
        if(event.getSource() == btnAccName) {
            String accNo = getChoiceBoxText(dropAccName);
            String newName = txfChangeName.getText();
            DB.changeAccName(accNo, newName);
            lblNameChanged.setVisible(true);
        }
        //add new account
        else if(event.getSource() == btnAddAcc) {
            String accName = txfNewAccName.getText();
            DB.newAccount(user, accName);
            lblAccCreated.setVisible(true);
        }
        //remove existing account
        else if(event.getSource() == btnRemoveAcc) {
            String accNo = getChoiceBoxText(dropRemoveAcc);
            DB.removeAcc(accNo);
            lblAccRemoved.setVisible(true);
        }
        else
            System.out.println("what happened here?");
    }

    @FXML
    private String getChoiceBoxText(ChoiceBox<String> box) {
        String acc = box.getValue();
        return acc;
    }

}
