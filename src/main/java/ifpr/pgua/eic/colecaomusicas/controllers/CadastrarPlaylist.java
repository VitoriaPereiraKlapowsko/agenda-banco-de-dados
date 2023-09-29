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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class CadastrarPlaylist implements Initializable {

    @FXML
    private ListView<Musica> lstMusicas;

    @FXML
    private TextField txNomeDaPlaylist;

    private RepositorioMusicas repositorioMusica;
    private RepositorioPlaylist repositorio;

    public CadastrarPlaylist(RepositorioMusicas repositorioMusica, RepositorioPlaylist repositorio) {
        this.repositorioMusica = repositorioMusica;
        this.repositorio = repositorio;
  }

   @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstMusicas.getItems().clear();
        lstMusicas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Listei as músicas a partir do repositório e adicionei elas na ListView
        Resultado resposta = repositorioMusica.listar();
        //Listei as músicas a partir do repositório de músicas e armazenei o seu resultado na variavel resposta

        if(resposta.foiSucesso()) {
            List<Musica> lista = (List) resposta.comoSucesso().getObj();
            lstMusicas.getItems().addAll(lista);
        }else{
            Alert alert = new Alert(AlertType.ERROR, resposta.getMsg());
            alert.showAndWait();
        }
    }

    @FXML
    void cadastrarNomePlaylist(ActionEvent event) {
        String nomePlaylist = txNomeDaPlaylist.getText();
        //Pegando as musicas selecionadas no Listview
        List<Musica> musicasSelecionadas = lstMusicas.getSelectionModel().getSelectedItems();

        if(nomePlaylist.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("O nome da playlist não pode estar vazio... Tente Novamente");
            alert.showAndWait();
            return;
        }
        // Criando uma nova playlist com o nome e as músicas selecionadas.
        Playlist novaPlaylist = new Playlist(0, nomePlaylist, null);
        novaPlaylist.setMusicas(musicasSelecionadas);
        //criando a playlist no repositório de playlists
        Resultado resultado = repositorio.getPlaylistDAO().criar(novaPlaylist);

        if(resultado.foiSucesso()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Playlist cadastrada");
            alert.setContentText("A playlist '" + nomePlaylist + "' foi cadastrada com sucesso!");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Erro ao cadastrar a playlist: " + resultado.getMsg());
            alert.showAndWait();
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
}
