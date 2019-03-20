package app.transaction;


import app.db.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransferControl {
    @FXML TextField txfClearing, txfAccount, txfAmount, txfTestAmount;
    @FXML ChoiceBox dropFromAcc, dropTestAcc;
    @FXML Label lblTextBalance;
    @FXML Button btnWithdraw, btnDeposit;

    double transferAmt;
    int accountNo;
    int clearingNo;

    @FXML
    private void initialize(){
        System.out.println("initialize transfer");
    }

    @FXML
    private void getTransferText(ActionEvent event) {
        transferAmt = Integer.valueOf(txfAmount.getText());
        clearingNo = Integer.valueOf(txfClearing.getText());
        accountNo = Integer.valueOf(txfAccount.getText());
        System.out.println(transferAmt + " " + clearingNo + " " + accountNo);

        DB.changeBalance(clearingNo, accountNo, transferAmt);
    }

    @FXML
    private void getChangeBalanceText(ActionEvent event) {
        transferAmt = Integer.valueOf(txfTestAmount.getText());
        if(event.getSource() == btnWithdraw) {
            transferAmt *= -1;
        }
        lblTextBalance.setText(String.format("Your new account balance is: %s", String.valueOf(DB.changeBalance(3000, 11223344, transferAmt))));

    }
}
