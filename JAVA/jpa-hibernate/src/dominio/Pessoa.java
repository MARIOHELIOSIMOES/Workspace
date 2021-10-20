package dominio;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private int id;
    private String nome;
    private String email;

    public Pessoa(){

    }
    public Pessoa(int id,String nome, String email){
        setId(id);
        setNome(nome);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
