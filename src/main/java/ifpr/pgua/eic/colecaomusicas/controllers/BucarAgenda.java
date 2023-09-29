package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class BucarAgenda {

    @FXML
    private ListView<?> lstCadatrados;

    @FXML
    private TextField txBucarCadastrado;

    @FXML
    void alterar(ActionEvent event) {

    }

    @FXML
    void bucar(ActionEvent event) {

    }

    @FXML
    void excluir(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}
