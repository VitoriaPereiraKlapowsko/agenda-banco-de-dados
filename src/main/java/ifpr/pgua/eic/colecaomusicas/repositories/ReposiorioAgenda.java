package ifpr.pgua.eic.colecaomusicas.repositories;

import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.colecaomusicas.daos.AgendaDAO;
import ifpr.pgua.eic.colecaomusicas.models.Agenda;

public class ReposiorioAgenda {
    private AgendaDAO dao;

    public ReposiorioAgenda(AgendaDAO dao) {
        this.dao = dao;
    }

    public Resultado cadastrarContato(Agenda contato) {
        return dao.criar(contato);
    }

    public Resultado listarContatos() {
        return dao.listar();
    }

    public Resultado excluirContato(int codigo) {
        return dao.excluir(codigo);
    }

    public Resultado buscarPorNome(String nomeContato) {
        if (nomeContato == null || nomeContato.isEmpty()) {
            return Resultado.erro("Erro... informe um Nome para a busca!");
        } else {
            try {
                return dao.buscarPorNome(nomeContato);
            } catch (NumberFormatException e) {
                return dao.buscarPorNome(nomeContato);
            }
        }
    }

    public Resultado alterarContato(int codigo, String nome, int telefone, String email) {
        Agenda novoContato = new Agenda(codigo, nome, telefone, email);
        return dao.atualizar(codigo, novoContato);
    }
}
