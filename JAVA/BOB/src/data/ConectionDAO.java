package data;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Auxiliar;



public class ConectionDAO {
    protected static String NOME = "nome";
    protected static String SENHA = "senha";
    protected static String BANCO = "bd_bob";
    protected static String HOST = "localhost";
    protected static String PORTA = "3306";
    protected static String USER = "mario";
    protected static String PASSWORD = "2108";
    protected Auxiliar aux;
    

    private Connection Mainconn;
    protected Connection connection;
    protected PreparedStatement stmt;
    protected Statement statement;
    protected ResultSet resultSet;
    
    public ConectionDAO(){
         try{
            String host = getHost();
            aux = new Auxiliar();
            Mainconn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+BANCO,USER,PASSWORD);
            
        }catch (Exception e){
            
        }
    } 
    private Connection getMainConn(){
        String host = "";
        try{
            host = getHost();    
            if(Mainconn ==null || Mainconn.isClosed()){
                Mainconn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+BANCO,USER,PASSWORD);
            }
        }catch (Exception e){
            aux.showMessageWarning("Não foi possível se conectar ao servidor!!\nVerique se o serviço foi iniciado e depois abra o programa novamente!", "Falha de Conexão");
            aux.RegistrarLog(e.getMessage(), "ConectionDAO.getMainConn");
            //JOptionPane.showMessageDialog(null,"Erro 0: Não vou possível se conectar ao servidor [ "+ host +" ]. Verifique se a máquina está ligada e o serviço iniciado!! Detalhe:"+e.getMessage());
        }finally{
            return Mainconn;
        }
    }
    protected void AbrirConexao(){
        try{
            connection = getConexao();
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "ConectionDAO.AbrirConexao");
        }
    }
    protected void FecharConexao(){
        try{
            if(connection!=null){
                connection.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
            if(stmt!=null){
                stmt.close();
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "ConectionDAO.FecharConexao");
        }
    }
    public Connection getConexao(){
        try {
          return getMainConn();
        }catch (Exception e){
            aux.showMessageWarning("Não foi possível se conectar ao servidor!!\nVerique se o serviço foi iniciado e depois abra o programa novamente!", "Falha de Conexão");
            aux.RegistrarLog(e.getMessage(), "ConectionDAO.getConexao");
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
        
        String host = "localhost";
        try{
            InputStream input = new FileInputStream("src/arquivos/host.txt");
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
