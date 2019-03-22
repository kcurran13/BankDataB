package app.account;


import app.Entities.Transaction;
import app.db.DB;
import app.transaction.TransactionController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class AccountController {

    @FXML
    VBox transactionBox;

    @FXML
    private void initialize() {
        System.out.println("initialize app.account");
        loadMoreTransactions();
    }

    void loadMoreTransactions() {
        List<Transaction> transactions = DB.getTransactions(11223344);
        displayTransaction(transactions);
    }

    void displayTransaction(List<Transaction> transactions) {
        // For every app.transaction, do the following:
        for (Transaction t : transactions)
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/transaction/transaction.fxml"));
                Parent fxmlInstance = loader.load();
                Scene scene = new Scene(fxmlInstance);

                TransactionController controller = loader.getController();
                controller.setTransaction(t);

                transactionBox.getChildren().add(scene.getRoot());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    @FXML
    void clickLoadTransactions(Event e) {
        loadMoreTransactions();
    }
}
