package app.transaction;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class TransferControl {
    @FXML TextField txfClearing, txfAccount;
    @FXML ChoiceBox dropAcc;

    @FXML
    private void initialize(){
        System.out.println("initialize transfer");
    }

}
