package simoes.mario.crudandroid.model;

import androidx.annotation.NonNull;

public class Pessoa {
    private String uid;
    private String nome;
    private String email;


    @NonNull
    @Override
    public String toString() {
        return getNome();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}
