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

    public Resultado buscarContatoPorCodigo(int codigo) {
        return dao.buscarPorCodigo(codigo);
    }

    public Resultado buscarContatoPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }

    public Resultado buscarContatoPorTelefone(int telefone) {  
        return dao.buscarPorTelefone(telefone);
    }

    public Resultado buscarContatoPorEmail(String email) {
        return dao.buscarPorEmail(email);
    }

    public Resultado atualizarContato(int codigo, Agenda novoContato) {
        return dao.atualizar(codigo, novoContato);
    }

    public Resultado excluirContato(int codigo) {
        return dao.excluir(codigo);
    }

    public Resultado buscarPorCriterio(String criterio) {
        if (criterio == null || criterio.isEmpty()) {
            return Resultado.erro("Erro... informe um critério de busca!");
        } else {
            try {
                int codigo = Integer.parseInt(criterio);
                return dao.buscarPorCodigo(codigo);
            } catch (NumberFormatException e) {
                return dao.buscarPorNome(criterio); 
            }
    }
}}

