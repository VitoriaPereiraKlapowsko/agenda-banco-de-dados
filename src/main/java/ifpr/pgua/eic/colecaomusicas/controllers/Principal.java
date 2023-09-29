package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.fxml.FXML;

public class Principal {

    @FXML
    private void cadastro() {
        App.pushScreen("CADASTRO");
    }
    
}
