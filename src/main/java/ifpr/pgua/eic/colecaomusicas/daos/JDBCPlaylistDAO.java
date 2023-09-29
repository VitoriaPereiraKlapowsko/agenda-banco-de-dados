package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.colecaomusicas.models.Musica;
import ifpr.pgua.eic.colecaomusicas.models.Playlist;

public class JDBCPlaylistDAO implements PlaylistDAO {
    private static final String INSERTSQL = "INSERT INTO playlist(nomePlaylist) VALUES (?)";
    private static final String INSERTSQL_MUSICAS = "INSERT INTO playlistMusica(idPlaylist, idMusica) VALUES (?,?)";
    
    private FabricaConexoes fabrica;

    public JDBCPlaylistDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Playlist playlist) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, playlist.getNome()); //Defini o nome da playlist no parâmetro da consulta SQL
            int ret = pstm.executeUpdate(); // Executando a minha consulta no SQL 

            if (ret == 1) { //começando em 1 pois representa a posição do primeiro parâmetro na consulta SQL
                //Estou pegando as chaves geradas automaticamente
                ResultSet resposta1 = pstm.getGeneratedKeys();

                if (resposta1.next()) {
                    int id = resposta1.getInt(1);
                    //Definindo o ID da playlist com a chave gerada automaticamente
                    playlist.setId(id);
                }
                
                //Percorrendo a lista de musicas da playlist e inserindo na tabela de playlistMusica
                for (Musica musica : playlist.getMusicas()) {
                    PreparedStatement pstm1 = con.prepareStatement(INSERTSQL_MUSICAS, Statement.RETURN_GENERATED_KEYS);

                    pstm1.setInt(1, playlist.getId());
                    pstm1.setInt(2, musica.getId());
                    pstm1.executeUpdate();
                }
                return Resultado.sucesso("Playlist cadastrada com sucesso!!", playlist);
            }
            return Resultado.erro("Erro!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado apagar(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM playlist");

            ResultSet resposta1 = pstm.executeQuery();//Executando a consulta no SQL

            ArrayList<Playlist> lista = new ArrayList<>();
            while (resposta1.next()) {
                int id = resposta1.getInt("id");
                String nome = resposta1.getString("nomePlaylist");

                // Criando um objeto Playlist com os dados do banco de dados.
                Playlist playlist = new Playlist(id, nome, null);
                lista.add(playlist); //E adicionando na minha lista
            }
            return Resultado.sucesso("Playlists", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Playlist playlist) {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }
}
