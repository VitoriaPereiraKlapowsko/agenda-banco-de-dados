package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
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
import javafx.scene.control.TextField;

public class CadastroAgenda implements Initializable {

    @FXML
    private TextField txCodigo;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txNome;

    @FXML
    private TextField txTelefone;

    private ReposiorioAgenda repositorioAgenda;

    private Agenda antigo;

    public CadastroAgenda(ReposiorioAgenda repositorioAgenda) {
        this.repositorioAgenda = repositorioAgenda;
    }

    public CadastroAgenda(ReposiorioAgenda repositorioAgenda, Agenda agenda) {
        this.repositorioAgenda = repositorioAgenda;
        this.antigo = agenda;
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
            exibirMensagem("Erro", "O código e telefone devem ser números inteiros...");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (antigo != null) {
            txCodigo.setText(String.valueOf(antigo.getCodigo()));
            txEmail.setText(antigo.getEmail());
            txNome.setText(antigo.getNome());
            txTelefone.setText(String.valueOf(antigo.getTelefone()));
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
