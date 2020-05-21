package sample.Controllers;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.RSA.RSA;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputText;

    @FXML
    private TextField outputText;

    @FXML
    private Button mainButton;

    @FXML
    private TextField codingMessage;

    @FXML
    void initialize() {
        assert inputText != null : "fx:id=\"inputText\" was not injected: check your FXML file 'sample.fxml'.";
        assert outputText != null : "fx:id=\"outputText\" was not injected: check your FXML file 'sample.fxml'.";
        assert mainButton != null : "fx:id=\"mainButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert codingMessage != null : "fx:id=\"codingMessage\" was not injected: check your FXML file 'sample.fxml'.";
        // Лишняя логика в контроллере
        // Убрать
        RSA temp = new RSA();
        mainButton.setOnAction(actionEvent ->{
            ArrayList<BigInteger> list = temp.encrypt(inputText.getText());
            String rez = "";
            int size = list.size();
            for (int i = 0; i < size; i ++) rez += list.get(i).toString();
            codingMessage.setText(rez);
            outputText.setText(temp.decode(list));
        });

    }
}
