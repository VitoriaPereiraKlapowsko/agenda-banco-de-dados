package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.colecaomusicas.models.Agenda;

public interface AgendaDAO {
    Resultado criar(Agenda contato);
    Resultado listar();
    Resultado buscarPorCodigo(int codigo); 
    Resultado buscarPorNome(String nome);
    Resultado buscarPorTelefone(int telefone); 
    Resultado buscarPorEmail(String email); 
    Resultado atualizar(int codigo, Agenda novoContato);
    Resultado excluir(int codigo);
}
