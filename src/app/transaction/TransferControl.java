package app.transaction;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransferControl {
    @FXML TextField txfClearing, txfAccount, txfTestAmount;
    @FXML ChoiceBox dropFromAcc, dropTestAcc;
    @FXML Label lblTextBalance;

    @FXML
    private void initialize(){
        System.out.println("initialize transfer");
    }

}
