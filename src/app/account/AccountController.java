package app.account;


import app.Entities.Transaction;
import app.Entities.User;
import app.Main;
import app.db.DB;
import app.login.LoginController;
import app.transaction.TransactionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccountController {

    @FXML VBox transactionBox;
    @FXML ChoiceBox dropAcc;
    @FXML Button btnBack;
    private User user;
    private int offset = 0;


    @FXML
    private void initialize() throws SQLException {
        user = LoginController.getUser();


        dropAcc.setItems(DB.getUserAccounts(user));
        dropAcc.getSelectionModel().select(0);

        dropAcc.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            transactionBox.getChildren().clear();
            loadTransactions();
        });
        loadTransactions();
    }

    void loadTransactions() {
        String accNo = extractAccNo(dropAcc);
        List<Transaction> transactions = DB.getTransactions(accNo);
        displayTransaction(transactions);
    }

    void loadMoreTransactions() {
        String accNo = extractAccNo(dropAcc);
        offset += 10;
        List<Transaction> transactions = DB.getTransactions(accNo, offset);
        displayTransaction(transactions);
    }

    void displayTransaction(List<Transaction> transactions) {
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
    void clickLoadTransactions() {
        loadMoreTransactions();
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
