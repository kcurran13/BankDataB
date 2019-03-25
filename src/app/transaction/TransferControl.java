package app.transaction;


import app.Entities.User;
import app.db.DB;
import app.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TransferControl {
    @FXML
    TextField txfClearing, txfAccount, txfAmount, txfTestAmount;
    @FXML
    ChoiceBox dropFromAcc, dropTestAcc;
    @FXML
    Label lblTextBalance, lblError, lblSuccess;
    @FXML
    Button btnWithdraw, btnDeposit;
    @FXML
    DatePicker chooseDate;

    private User user;
    private double transferAmt;
    private String receiverAcc = null;
    private int clearingNo;
    private String fromAccNo;
    String date;

    @FXML
    private void initialize() throws SQLException {
        user = LoginController.getUser();
        dropFromAcc.setItems(DB.getUserAccounts(user));
        dropTestAcc.setItems(DB.getUserAccounts(user));
    }

    @FXML
    private void makeTransfer() {
        lblSuccess.setVisible(false);
        lblError.setVisible(false);
        transferAmt = Integer.valueOf(txfAmount.getText());
        clearingNo = Integer.valueOf(txfClearing.getText());
        receiverAcc = txfAccount.getText();
        //listener for choicebox
        fromAccNo = extractAccNo(dropFromAcc);

        date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        if (chooseDate.getValue().isAfter(LocalDate.now())) {
            //DB.planTransaction();
            lblSuccess.setVisible(true);
        } else if(chooseDate.getValue().equals(LocalDate.now())){
            DB.changeBalance(3000, fromAccNo, (transferAmt * -1), receiverAcc, date);
            DB.changeBalance(clearingNo, receiverAcc, transferAmt, fromAccNo, date);
            lblSuccess.setVisible(true);
        } else {
            lblError.setVisible(true);
        }
    }

    @FXML
    private void getChangeBalanceText(ActionEvent event) {
        transferAmt = Integer.valueOf(txfTestAmount.getText());
        fromAccNo = extractAccNo(dropTestAcc);
        date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        if (event.getSource() == btnWithdraw) {
            transferAmt *= -1;
        }
        lblTextBalance.setText(String.format("Your new account balance is: %s", String.valueOf(DB.changeBalance(3000, fromAccNo, transferAmt, receiverAcc, date))));
    }

    private String extractAccNo(ChoiceBox<String> box) {
        String[] b = box.getValue().split("-");
        return b[0];
    }
}
