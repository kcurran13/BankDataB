package app.transaction;


import app.Entities.User;
import app.db.DB;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferControl {
    @FXML TextField txfClearing, txfAccount, txfAmount, txfTestAmount;
    @FXML ChoiceBox dropFromAcc, dropTestAcc;
    @FXML Label lblTextBalance;
    @FXML Button btnWithdraw, btnDeposit;

    private User user;
    private double transferAmt;
    private String receiverAcc = null;
    private int clearingNo;
    private String fromAccNo;
    private static ResultSet rs;
    String date;

    @FXML
    private void initialize(){
        System.out.println("initialize transfer");
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

        dropFromAcc.setItems(data);
        dropTestAcc.setItems(data);
    }

    @FXML
    private void getTransferText() {
        transferAmt = Integer.valueOf(txfAmount.getText());
        clearingNo = Integer.valueOf(txfClearing.getText());
        receiverAcc = txfAccount.getText();
        //listener for choicebox
        fromAccNo = String.valueOf(getChoiceBoxText(dropFromAcc));
        date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        DB.changeBalance(3000, fromAccNo, (transferAmt*-1), receiverAcc, date);
        DB.changeBalance(clearingNo, receiverAcc, transferAmt, fromAccNo, date);
    }

    @FXML
    private void getChangeBalanceText(ActionEvent event) {
        transferAmt = Integer.valueOf(txfTestAmount.getText());
        fromAccNo = getChoiceBoxText(dropTestAcc);
        date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        if(event.getSource() == btnWithdraw) {
            transferAmt *= -1;
        }
        lblTextBalance.setText(String.format("Your new account balance is: %s", String.valueOf(DB.changeBalance(3000, fromAccNo, transferAmt, receiverAcc, date))));
    }

    @FXML
    private String getChoiceBoxText(ChoiceBox<String> box) {
        String acc = box.getValue();
        return acc;
    }

}
