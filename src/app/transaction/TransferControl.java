package app.transaction;


import app.Entities.User;
import app.Main;
import app.db.DB;
import app.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

public class TransferControl {
    @FXML
    TextField txfClearing, txfAccount, txfAmount, txfTestAmount;
    @FXML
    ChoiceBox dropFromAcc, dropTestAcc;
    @FXML
    Label lblTextBalance, lblError, lblSuccess;
    @FXML
    Button btnWithdraw, btnDeposit, btnBack;
    @FXML
    DatePicker chooseDate;

    private User user;
    private double transferAmt;
    private String receiverAcc = null;
    private String fromAccNo;
    private java.sql.Timestamp date;

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
        receiverAcc = txfAccount.getText();
        fromAccNo = extractAccNo(dropFromAcc);
        date = new Timestamp(System.currentTimeMillis());

        if (chooseDate.getValue().isAfter(LocalDate.now())) {
            java.util.Date date1 = java.util.Date.from(chooseDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date1.getTime());
            DB.planTransaction(fromAccNo, (transferAmt * -1), receiverAcc, sqlDate);
            lblSuccess.setVisible(true);
        } else if(chooseDate.getValue().equals(LocalDate.now())){
            DB.changeBalance(fromAccNo, (transferAmt * -1), receiverAcc, date);
            DB.changeBalance(receiverAcc, transferAmt, fromAccNo, date);
            lblSuccess.setVisible(true);
        } else {
            lblError.setVisible(true);
        }
    }

    @FXML
    private void getChangeBalanceText(ActionEvent event) {
        transferAmt = Integer.valueOf(txfTestAmount.getText());
        fromAccNo = extractAccNo(dropTestAcc);
        date = new Timestamp(System.currentTimeMillis());

        if (event.getSource() == btnWithdraw) {
            transferAmt *= -1;
        }
        lblTextBalance.setText(String.format("Your new account balance is: %s", String.valueOf(DB.changeBalance(fromAccNo, transferAmt, receiverAcc, date))));
    }

    private String extractAccNo(ChoiceBox<String> box) {
        String[] b = box.getValue().split("-");
        return b[0];
    }

    @FXML
    public void goToHome() {
        try {
            Parent bla = FXMLLoader.load(getClass().getResource("/app/home/home.fxml"));
            Scene scene = new Scene(bla, 800, 600);
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
