package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Musica;
import ifpr.pgua.eic.colecaomusicas.models.Playlist;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioMusicas;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPlaylist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class ListarPlaylist implements Initializable {

    @FXML
    private ListView<Playlist> lstListaPlaylists;

    @FXML
    private ListView<Musica> lstMusicas;

    private RepositorioMusicas repositorioMusicas;
    private RepositorioPlaylist repositorioPlaylist;

    public ListarPlaylist(RepositorioMusicas repositorioMusicas, RepositorioPlaylist repositorioPlaylist) {
        this.repositorioMusicas = repositorioMusicas;
        this.repositorioPlaylist = repositorioPlaylist;
    }

    @FXML
    void exibirMusicasDaPlaylist(MouseEvent event) {
        lstMusicas.getItems().clear();
        // Consigo a lista selecionada
        Playlist play = lstListaPlaylists.getSelectionModel().getSelectedItem();
        // Adicionando as músicas da lista no meu ListView.
        lstMusicas.getItems().addAll(play.getMusicas());
    }

     @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstListaPlaylists.getItems().clear();
        // Lista as listas a partir do repositório
        Resultado resposta1 = repositorioPlaylist.listarPlaylist();
        // Listei as listas a partir do repositório de listas de Playlists e armazenei na variavel resposta1

        if (resposta1.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, resposta1.getMsg());
            alert.showAndWait();
            return;
        }
        List<Playlist> lista = (List) resposta1.comoSucesso().getObj();
        lstListaPlaylists.getItems().addAll(lista);
    }

}
