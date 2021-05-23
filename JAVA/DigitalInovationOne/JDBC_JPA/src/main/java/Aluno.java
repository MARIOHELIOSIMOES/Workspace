import javax.persistence.*;

@Entity
public class Aluno {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int idade;

    @Column
    private String nome;
/*
    @Column
    private String estado;
*/
    //
    @ManyToOne(fetch = FetchType.LAZY)
    private String estado;

    public Aluno(){

    }
    public Aluno(String nome, int idade, String estado) {
        setNome(nome);
        setIdade(idade);
        setEstado(estado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
