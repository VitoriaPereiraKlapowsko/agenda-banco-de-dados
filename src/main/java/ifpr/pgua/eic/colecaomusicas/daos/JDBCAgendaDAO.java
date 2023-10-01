package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.colecaomusicas.models.Agenda;

public class JDBCAgendaDAO implements AgendaDAO {
    private static final String INSERTSQL = "INSERT INTO agenda(codigo, nome, telefone, email) VALUES (?, ?, ?, ?)";
    private static final String SELECTSQL = "SELECT * FROM agenda";
    private static final String UPDATESQL = "UPDATE agenda SET nome = ? WHERE codigo = ?";
    private static final String DELETESQL = "DELETE FROM agenda WHERE codigo = ?";

    private FabricaConexoes fabrica;

    public JDBCAgendaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Agenda contato) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, contato.getCodigo());
            pstm.setString(2, contato.getNome());
            pstm.setInt(3, contato.getTelefone());
            pstm.setString(4, contato.getEmail());

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Contato cadastrado com sucesso!!!", contato);
            } else {
                return Resultado.erro("Erro ao cadastrar!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);
            ResultSet rs = pstm.executeQuery();

            ArrayList<Agenda> lista = new ArrayList<>();
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");

                Agenda contato = new Agenda(codigo, nome, telefone, email);
                lista.add(contato);
            }

            return Resultado.sucesso("Contatos listados com sucesso", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarPorCodigo(int codigo) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);
            pstm.setInt(1, codigo);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");
                Agenda contato = new Agenda(codigo, nome, telefone, email);
                return Resultado.sucesso("Contato encontrado", contato);
            } else {
                return Resultado.erro("Contato não encontrado...");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarPorNome(String nome) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM agenda WHERE nome = ?");
            pstm.setString(1, nome);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Agenda> lista = new ArrayList<>();
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String contatoNome = rs.getString("nome");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");

                Agenda contato = new Agenda(codigo, contatoNome, telefone, email);
                lista.add(contato);
            }

            if (!lista.isEmpty()) {
                return Resultado.sucesso("Contatos encontrados!", lista);
            } else {
                return Resultado.erro("Nenhum contato foi encontrado com o Nome fornecido...");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }


    @Override
    public Resultado atualizar(int codigo, Agenda novoContato) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(UPDATESQL);

            pstm.setString(1, novoContato.getNome());
            pstm.setInt(2, codigo);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Contato atualizado com sucesso!", novoContato);
            } else {
                return Resultado.erro("Contato não encontrado ou Erro...");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado excluir(int codigo) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(DELETESQL);
            pstm.setInt(1, codigo);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Contato excluído com sucesso", con);
            } else {
                return Resultado.erro("Contato não encontrado ou Erro...");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
