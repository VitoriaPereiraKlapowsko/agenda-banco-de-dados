package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.colecaomusicas.models.Playlist;

public interface PlaylistDAO {
    Resultado atualizar(int id, Playlist playlist);
    Resultado apagar(int id);
    Resultado criar(Playlist playlist);
    Resultado listar();
}
