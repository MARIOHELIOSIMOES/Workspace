
package model;

public class Usuario {

    public static int ID_USUARIO_VAZIO = 0;
    public static int ADMIN = 0;
    public static int OPERA = 1;
    public static int VISUA = 2;

    public static String[] TIPOS = {"Administrador", "Operação", "Visualização"};
    
    public Usuario(){
        setId(ID_USUARIO_VAZIO);
        setTipo(VISUA);
        setNome("User");
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public String getTipoDescricao(){
        return TIPOS[getTipo()];
    }
    
    private int id;
    private String nome;
    private int tipo;
}
