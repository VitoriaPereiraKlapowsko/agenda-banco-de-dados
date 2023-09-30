package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Agenda;
import ifpr.pgua.eic.colecaomusicas.repositories.ReposiorioAgenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class BucarAgenda implements Initializable{

    @FXML
    private ListView<Agenda> lstCadatrados;

    @FXML
    private TextField txBucarCadastrado;

    private ReposiorioAgenda repositorioAgenda;

    public BucarAgenda(ReposiorioAgenda repositorioAgenda) {
        this.repositorioAgenda = repositorioAgenda;
    }

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

     @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstCadatrados.getItems().clear();
        Resultado resultado = repositorioAgenda.listarContatos();

        if (resultado.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        } else {
            List<Agenda> lista = (List<Agenda>) resultado.comoSucesso().getObj();
            lstCadatrados.getItems().addAll(lista);
        }
    }

}
