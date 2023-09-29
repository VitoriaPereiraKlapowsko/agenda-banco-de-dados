package ifpr.pgua.eic.colecaomusicas.models;

import java.util.List;

public class Playlist {

    private String nome;
    private int id;
    private List<Musica> musicas;

    public Playlist(int id, String nome, List<Musica> musicas) {
        this.id = id;
        this.nome = nome;
        this.musicas = musicas;
    }

    public Playlist(String nome, List<Musica> musicas) {
        this.nome = nome;
        this.musicas = musicas;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
