package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroAgenda {

    @FXML
    private TextField txCodigo;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txNome;

    @FXML
    private TextField txTelefone;

    @FXML
    void cadastrar(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}
