package app.transaction;


import app.Entities.Transaction;
import app.db.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TransactionController {

    @FXML Label message;
    @FXML Label amount;
    @FXML Label date;

    @FXML
    private void initialize(){
        System.out.println("initialize transaction");
        DB.changeBalance(3, "Savings",  111000);
    }

    public void setTransaction(Transaction transaction) {
        message.setText(transaction.getMessage());
        // etc
        // etc
    }
}
