package app.transaction;


import app.Entities.Transaction;
import app.db.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class TransactionController {

    @FXML Label receiver, amount, date, newBalance;

    @FXML
    private void initialize() throws SQLException {
        System.out.println("initialize transaction");
    }

    public void setTransaction(Transaction transaction) {
        receiver.setText(transaction.getReceiver());
        amount.setText(String.valueOf(transaction.getAmount()));
        date.setText(transaction.getDateAsString());
        newBalance.setText(String.valueOf(transaction.getNewBalance()));
    }
}
