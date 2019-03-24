package app.account;


import app.Entities.Transaction;
import app.Entities.User;
import app.db.DB;
import app.login.LoginController;
import app.transaction.TransactionController;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountController {

    @FXML VBox transactionBox;
    @FXML ChoiceBox dropAcc;
    private User user;
    private ResultSet rs;
    private int offset = 0;

    @FXML
    private void initialize() {
        System.out.println("initialize app.account");
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

        dropAcc.setItems(data);
        dropAcc.getSelectionModel().select(0);

        dropAcc.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            transactionBox.getChildren().clear();
            loadTransactions();
        });
        loadTransactions();
    }

    void loadTransactions() {
        String accNo = getChoiceBoxText(dropAcc);
        List<Transaction> transactions = DB.getTransactions(accNo);
        displayTransaction(transactions);
    }

    void loadMoreTransactions() {
        String accNo = getChoiceBoxText(dropAcc);
        offset += 10;
        List<Transaction> transactions = DB.getTransactions(accNo, offset);
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

    @FXML
    private String getChoiceBoxText(ChoiceBox<String> box) {
        String acc = box.getValue();
        return acc;
    }
}
