package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Agenda;
import ifpr.pgua.eic.colecaomusicas.repositories.ReposiorioAgenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    private ReposiorioAgenda repositorioAgenda;

    public CadastroAgenda(ReposiorioAgenda repositorioAgenda) {
        this.repositorioAgenda = repositorioAgenda;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String codigoStr = txCodigo.getText();
        String nome = txNome.getText();
        String telefoneStr = txTelefone.getText();
        String email = txEmail.getText();

        try {
            int codigo = Integer.parseInt(codigoStr);
            int telefone = Integer.parseInt(telefoneStr);
            Agenda novoContato = new Agenda(codigo, nome, telefone, email);
            Resultado resultado = repositorioAgenda.cadastrarContato(novoContato);

            if (resultado.foiSucesso()) {
                exibirMensagem("Sucesso", "Contato cadastrado com sucesso!");
                limparCampos();
            } else {
                exibirMensagem("Erro", resultado.getMsg());
            }
        } catch (NumberFormatException e) {
            exibirMensagem("Erro", "O código e telefone devem ser números inteiros.");
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    private void exibirMensagem(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        txCodigo.clear();
        txNome.clear();
        txTelefone.clear();
        txEmail.clear();
    }

}
