package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Agenda;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroAgenda;
import ifpr.pgua.eic.colecaomusicas.repositories.ReposiorioAgenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class BucarAgenda implements Initializable {

    @FXML
    private ListView<Agenda> lstCadatrados;

    @FXML
    private TextField txBucarCadastrado;

    private ReposiorioAgenda repositorioAgenda;

    public BucarAgenda(ReposiorioAgenda repositorioAgenda) {
        this.repositorioAgenda = repositorioAgenda;
    }

    @FXML
    private void alterar() {
        Agenda agenda = lstCadatrados.getSelectionModel().getSelectedItem();
        if (agenda != null) {
           App.pushScreen("CADASTROAGENDA");
        }
    }

    @FXML
    void buscar(ActionEvent event) {
        String criterio = txBucarCadastrado.getText();

        if (criterio.isEmpty()) {
            alertaUsuario("Erro... informe um critério de busca!");
            return;
        }

        Resultado resultadoBusca = repositorioAgenda.buscarPorCriterio(criterio);

        if (resultadoBusca.foiSucesso()) {
            List<Agenda> contatosEncontrados = (List<Agenda>) resultadoBusca.comoSucesso().getObj();
            exibirContatos(contatosEncontrados);
        } else {
            erroUsuario("Nenhum contato encontrado com o critério fornecido.");
        }
    }

    private void alertaUsuario(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void erroUsuario(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void exibirContatos(List<Agenda> contatos) {
        lstCadatrados.getItems().setAll(contatos);
    }

    @FXML
    void excluir(ActionEvent event) {
        Agenda contatoSelecionado = lstCadatrados.getSelectionModel().getSelectedItem();

        if (contatoSelecionado != null) {
            Resultado resultado = repositorioAgenda.excluirContato(contatoSelecionado.getCodigo());

            if (resultado.foiSucesso()) {
                exibirMensagem("Sucesso", resultado.getMsg());
                atualizarListaContatos();
            } else {
                exibirMensagem("Erro", resultado.getMsg());
            }
        } else {
            exibirMensagem("Erro", "Nenhum contato selecionado.");
        }
    }

    private void atualizarListaContatos() {
        lstCadatrados.getItems().clear();
        Resultado resultado = repositorioAgenda.listarContatos();

        if (resultado.foiErro()) {
            exibirMensagem("Erro", resultado.getMsg());
        } else {
            List<Agenda> lista = (List<Agenda>) resultado.comoSucesso().getObj();
            lstCadatrados.getItems().addAll(lista);
        }
    }

    private void exibirMensagem(String string, String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(string);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
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
