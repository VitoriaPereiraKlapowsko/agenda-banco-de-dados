package ifpr.pgua.eic.colecaomusicas.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.ArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.GeneroDAO;
import ifpr.pgua.eic.colecaomusicas.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.PlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.models.Artista;
import ifpr.pgua.eic.colecaomusicas.models.Genero;
import ifpr.pgua.eic.colecaomusicas.models.Musica;
import ifpr.pgua.eic.colecaomusicas.models.Playlist;

public class RepositorioPlaylist {

    private GeneroDAO generoDAO;
    private ArtistaDAO artistaDAO;
    private PlaylistDAO playlistDAO;
    private MusicaDAO musicaDAO;

    public RepositorioPlaylist(PlaylistDAO playlistDAO, MusicaDAO musicaDAO, ArtistaDAO artistaDAO, GeneroDAO generoDAO) {
        this.playlistDAO = playlistDAO;
        this.musicaDAO = musicaDAO;
        this.generoDAO = generoDAO;
        this.artistaDAO = artistaDAO;
    }

    public PlaylistDAO getPlaylistDAO() {
        return playlistDAO;
    }

    public Resultado listarPlaylist() {
        // Lista todas as playlists do repositório
        Resultado resultadoPlaylists = playlistDAO.listar();

        if(resultadoPlaylists.foiSucesso()){
            List<Playlist> playlists = (List<Playlist>) resultadoPlaylists.comoSucesso().getObj();
                
                for(Playlist playlist : playlists) {
                    //Para cada playlist faço uma busca das músicas associadas
                    Resultado resposta = musicaDAO.buscarMusicasDaPlaylist(playlist.getId());
                    //Este reposta vai servir para serve para armazenar o resultado da chamada do método buscarMusicasDaPlaylist(playlist.getId())
                    List<Musica> listaDeMusicas = (List<Musica>) resposta.comoSucesso().getObj();
                        
                        for(Musica musica : listaDeMusicas) {
                            // Para cada música, busco o gênero associado
                            Resultado resposta2 = generoDAO.buscarGeneroMusica(musica.getId());
                                if(resposta2.foiErro()) {
                                    //Este resposta2 vai armazenar o resultado de uma consulta para buscar o gênero associado a uma música específica
                                    return resposta2;
                                }
                                Genero genero = (Genero) resposta2.comoSucesso().getObj();
                                musica.setGenero(genero);
                                // Para cada música, busco o artista associado
                                Resultado resposta3 = artistaDAO.buscarArtistaMusica(musica.getId());

                                if(resposta3.foiErro()){
                                    //Este resposta3 vai armazenar o resultado de uma consulta para buscar o artista associado a uma música específica 
                                    return resposta3;
                                }
                                Artista artista = (Artista) resposta3.comoSucesso().getObj();
                                musica.setArtista(artista);
                            }
                            playlist.setMusicas(listaDeMusicas);
                        }
                    }
                return resultadoPlaylists; // Retornando os resultados com as playlists e as informações delas
            }

    public Resultado carregarPlaylistsDoBanco(String nome, List<Musica> musicas) {
        if (nome.isBlank() || nome.isEmpty()) {
            // Verificando se o nome da playlist está em branco ou até mesmo vazio
            return Resultado.erro("Este Campo não pode estar vazio!!");
        } else if (musicas.size() == 0) {
             // Verificando aqui se minha lista de músicas está vazia tipo se nenhuma música foi selecionada
            return Resultado.erro("Nenhuma música foi Selecionada, por favor tente novamente!!");
        }
        Playlist novaPlaylist = new Playlist(nome, musicas);
        // Cria uma nova playlist no repositório e depois a retorna
        return playlistDAO.criar(novaPlaylist);
    }
}
