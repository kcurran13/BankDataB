package app.account;


import app.Entities.User;
import app.db.DB;
import app.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class DetailsController {
    private User user;

    @FXML ChoiceBox dropAccName,dropRemoveAcc;
    @FXML TextField txfNewAccName, txfChangeName;
    @FXML Button btnAccName, btnRemoveAcc, btnAddAcc;
    @FXML Label lblAccRemoved, lblNameChanged, lblAccCreated;

    @FXML
    void initialize() throws SQLException {
        user = LoginController.getUser();

        dropAccName.setItems(DB.getUserAccounts(user));
        dropAccName.getSelectionModel().select(0);
        dropRemoveAcc.setItems(DB.getUserAccounts(user));
        dropRemoveAcc.getSelectionModel().select(0);
    }

    @FXML
    private void getButtonInput(ActionEvent event) {
        //change account name
        if(event.getSource() == btnAccName) {
            String accNo = extractAccNo(dropAccName);
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
            String accNo = extractAccNo(dropRemoveAcc);
            DB.removeAcc(accNo);
            lblAccRemoved.setVisible(true);
        }
    }

    private String extractAccNo(ChoiceBox<String> box) {
        String[] b = box.getValue().split("-");
        return b[0];
    }
}
