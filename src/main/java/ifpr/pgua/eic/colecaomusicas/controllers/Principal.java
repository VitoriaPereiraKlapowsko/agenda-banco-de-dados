package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Principal {

    @FXML
    public void cadastro(ActionEvent event) {
        App.pushScreen("CADASTROAGENDA");
    }

    @FXML
    public void busca(ActionEvent event) {
        App.pushScreen("BUSCARAGENDA");
    }

}
