package app.transaction;


import app.Entities.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TransactionController {

    @FXML Label receiver, amount, date, newBalance;

    @FXML
    private void initialize(){}

    public void setTransaction(Transaction transaction) {
        receiver.setText(transaction.getReceiver());
        amount.setText(String.valueOf(transaction.getAmount()));
        date.setText(transaction.getDateAsString());
        newBalance.setText(String.valueOf(transaction.getNewBalance()));
    }
}
