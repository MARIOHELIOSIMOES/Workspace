package Dao;
import org.gjt.mm.mysql.Driver;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConectionDAO {
    protected static String TB_USUARIO = "tb_usuario";
    protected static String TB_PEDIDO = "tb_pedido";
    protected static String TB_RESTAURANTE = "tb_restaurante";
    protected static String ID = "id";
    protected static String ID_USUARIO = "id_usuario";
    protected static String ID_RESTAURANTE = "id_restaurante";
    protected static String NOME = "nome";
    protected static String SENHA = "senha";
    protected static String DATA = "data";
    protected static String ENDERECO = "endereco";
    protected static String TELEFONE = "telefone";
    protected static String DESCRICAO = "descricao";
    protected static String BANCO = "bd_saae_marmitex";
    protected static String HOST = "localhost";
    protected static String PORTA = "3306";
    protected static String USER = "mario";
    protected static String PASSWORD = "2108";



    public Connection getConexao(){
        try {

            return DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORTA+"/"+BANCO,USER,PASSWORD);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro 1: "+e);
        }
        return null;
    }
    public Connection getConexao(String host, int porta, String banco, String user, String senha){
        try {
            return DriverManager.getConnection("jdbc:mysql://"+host+":"+porta+"/"+banco,user,senha);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro 1.1: "+e);
        }
        return null;
    }
}
