package Dao;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    protected static String DIA = "dia";
    protected static String MES = "mes";
    protected static String ANO = "ano";
    protected static String TIMEMILI = "timemili";
    protected static String QUANTIDADE = "quantidade";
    protected static String ENDERECO = "endereco";
    protected static String TELEFONE = "telefone";
    protected static String DESCRICAO = "descricao";
    protected static String BANCO = "bd_saae_marmitex";
    protected static String HOST = "localhost";
    protected static String PORTA = "3306";
    protected static String USER = "mario";
    protected static String PASSWORD = "2108";


    private Connection Mainconn;
    public ConectionDAO(){
         try{
            String host = getHost();
                Mainconn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+BANCO,USER,PASSWORD);
           
        }catch (Exception e){
            
        }
    }
    public Connection getMainConn(){
        String host = "";
        try{
            host = getHost();    
            if(Mainconn.equals(null)||Mainconn.isClosed()){
                Mainconn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+BANCO,USER,PASSWORD);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro 0: Não vou possível se conectar ao servidor [ "+ host +" ]. Verifique se a máquina está ligada e o serviço iniciado!! Detalhe:"+e.getMessage());
        }finally{
            return Mainconn;
        }
    }
    public Connection getConexao(){
       String host="";
        try {

          //  return DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORTA+"/"+BANCO,USER,PASSWORD);
           host = getHost();
           
            //return DriverManager.getConnection("jdbc:mysql://"+host+"/"+BANCO,USER,PASSWORD);
            return getMainConn();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro 1: Não vou possível se conectar ao servidor [ "+ host +" ]. Verifique se a máquina está ligada e o serviço iniciado!! Detalhe:"+e.getMessage());
        }
        return null;
    }
    public Connection getConexaoHost(String host){
        try {
            return DriverManager.getConnection("jdbc:mysql://"+host+"/"+BANCO,USER,PASSWORD);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro 1.1: "+e);
        }
        return null;
    }
    public String getHost(){
        
        String host = "192.168.3.62";
        try{
            InputStream input = new FileInputStream("src/Dados/host.txt");
            host = new BufferedReader(new InputStreamReader(input)).readLine();
            input.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Arquivo com endereço do servidor não localizado! Detalhes: "+ e.getMessage());
        }
        return host;
    }
     public Connection getConexao(String host, int porta, String banco, String user, String senha){
        try {
            return DriverManager.getConnection("jdbc:mysql://"+getHost()+":"+porta+"/"+banco,user,senha);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro 1.1: "+e);
        }
        return null;
    }
   
}
