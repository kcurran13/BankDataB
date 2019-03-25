package app.home;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {

    @FXML Button btnAccount, btnTransfer, btnDetails;

    @FXML
    void initialize(){}

    @FXML
    void changeScene(ActionEvent event) throws IOException {
        String pathname = null;

        if(event.getSource() == btnAccount) {
            pathname = "/app/account/account.fxml";
        }
        else if(event.getSource() == btnTransfer) {
            pathname = "/app/transaction/transfer.fxml";
        }
        else if(event.getSource() == btnDetails) {
            pathname = "/app/account/changedetails.fxml";
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathname));
        Parent fxmlInstance = loader.load();
        Scene scene = new Scene(fxmlInstance, 800, 600);

        Main.stage.setScene(scene);
        Main.stage.show();
    }

}
