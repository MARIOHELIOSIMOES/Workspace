
package model;

import java.math.BigInteger;
import java.security.MessageDigest;

public class UsuarioLogin {

    public static int TAMANHO_MININO_SENHA = 4;
    private static String SENHA_VAZIA = "SENHA_VAZIA";
    public static String SENHA_ATUAL_INVALIDA = "SENHA ATUAL INVÁLIDA";
    public static String SENHA_ATUALIZADA = "SENHA ATUALIZADA";
    public UsuarioLogin(){
        setIdUsuario(Usuario.ID_USUARIO_VAZIO);
        setSenha(SENHA_VAZIA);
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public boolean gerarCompararSHA(String senha){//implementar o algoritmo SHA e verificar se corresponde com o atual
        return getSenha().equalsIgnoreCase(gerarSHA(senha));
    }
    public String gerarSHA(String senha){
       try {
                String sha1;
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(senha.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
                //System.out.println(sha1);
                return sha1;
        } catch (Exception e){
            e.printStackTrace();
        }
       return SENHA_VAZIA;

    }
    
    private int idUsuario;
    private String senha;
    
}
