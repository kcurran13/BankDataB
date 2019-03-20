package app.account;

import app.Entities.User;
import app.db.DB;
import app.login.LoginController;
import javafx.fxml.FXML;

public class DetailsController {
    @FXML
    void initialize(){
        System.out.println("initialize details");
    }

    private void loadAccounts() {
        User user = LoginController.getUser();
        //get accounts in a list of some sort and then populate the drop down
    }
}
