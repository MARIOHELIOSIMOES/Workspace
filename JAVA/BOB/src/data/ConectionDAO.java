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
    

    //private static Connection Mainconn;
    private Connection Mainconn;
    protected Connection connection;
    protected PreparedStatement stmt;
    protected Statement statement;
    protected ResultSet resultSet;
    private static int nconexoes = 0;
    public ConectionDAO(){
         try{
            String host = getHost();
            aux = new Auxiliar();
            //Mainconn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+BANCO,USER,PASSWORD);
            Mainconn = getConexao();
        //    criarBancoDeDados();
        }catch (Exception e){
            
        }
    } 
    public boolean criarBancoDeDados(){
        AbrirConexao();
        boolean retorno = false;
        //INSERT INTO `tb_documento` (`id`, `n_registro`, `validade`, `info`) VALUES (NULL, '51551515', '151515', 'teste');
        String sql = "CREATE DATABASE IF NOT EXISTS bd_bob; \n" +
                            
                            "USE bd_saae; \n" +
                            
                            "CREATE TABLE IF NOT EXISTS tb_veiculo ( 	id int PRIMARY KEY AUTO_INCREMENT,     placa varchar(20),     marca varchar(50),     modelo varchar(70),     ano int,     tipo varchar(30),     carroceria varchar(50),     configuracao int,     info varchar(150), combustivel int ); \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_documento( 	id int PRIMARY KEY AUTO_INCREMENT,     n_registro varchar(50),     validade bigint,     info varchar(150) ); \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_veiculo_documento( 	id_veiculo int,     id_documento int,     FOREIGN KEY (id_veiculo) REFERENCES tb_veiculo (id),     FOREIGN KEY (id_documento) REFERENCES tb_documento(id) );  \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_usuario_tipo( 	id INT PRIMARY KEY,     descricao VARCHAR(50) );  \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_usuario( 	id INT AUTO_INCREMENT PRIMARY KEY,     nome VARCHAR(75),     id_tipo INT,     FOREIGN KEY (id_tipo) REFERENCES tb_usuario_tipo(id) );  \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_veiculo_km( 	id INT AUTO_INCREMENT PRIMARY KEY,     id_veiculo INT,     id_usuario INT,     valor INT,     datamili BIGINT,     FOREIGN KEY (id_veiculo) REFERENCES tb_veiculo (id),     FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id)     );  \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_veiculo_combustivel( 	 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,      id_veiculo INT NOT NULL,      id_usuario INT NOT NULL,       km INT NOT NULL,      posto VARCHAR(100),      combustivel VARCHAR(50),      litros float,      valor float,      datamili BIGINT,      FOREIGN KEY (id_veiculo) REFERENCES tb_veiculo(id),      FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id)  );  \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_item( 	 id INT AUTO_INCREMENT PRIMARY KEY,      tipo INT,      marca VARCHAR (150),      modelo VARCHAR (150),      valor float,      info VARCHAR(250)      );  \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_pedido( 	 id INT PRIMARY KEY AUTO_INCREMENT,      id_veiculo INT NOT NULL,      tipo INT,      km INT,      datamilis BIGINT,      info VARCHAR(250),      FOREIGN KEY (id_veiculo) REFERENCES tb_veiculo (id) );  \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_pedido_item( 	 id_pedido INT NOT NULL,      id_item INT NOT NULL,      valor float,      qtde float,      FOREIGN KEY (id_pedido) REFERENCES tb_pedido (id),      FOREIGN KEY (id_item) REFERENCES tb_item (id) );  \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_veiculo_preventiva( 	 	id int PRIMARY KEY AUTO_INCREMENT,      	id_veiculo int NOT NULL,      	tipo int,    		 	km int, kmprox int,       	datamilis bigint,      	id_pedido int,      	info varchar(250),      	FOREIGN KEY (id_veiculo) REFERENCES tb_veiculo (id)   );	\n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_manutencao(\n" +
                            "	id int PRIMARY KEY AUTO_INCREMENT,\n" +
                            "    id_veiculo int,\n" +
                            "    km int,\n" +
                            "    valor float,\n" +
                            "    datamilis bigint,\n" +
                            "    oficina varchar (150),\n" +
                            "    servico varchar (250),\n" +
                            "    FOREIGN KEY (id_veiculo) REFERENCES tb_veiculo (id)\n" +
                            "); \n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_pneu(\n" +
                            "	id int PRIMARY KEY AUTO_INCREMENT,\n" +
                            "    iditem int, \n" +
                            "    fogo int,\n" +
                            "    km int,\n" +
                            "    kmtracao int,\n" +
                            "    FOREIGN KEY (iditem) REFERENCES tb_item (id)\n" +
                            "\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE IF NOT EXISTS tb_veiculo_pneu( 	\n" +
                            "	idveiculo int NOT NULL,     \n" +
                            "	idpneu int NOT NULL,     \n" +
                            "	posicao int,     \n" +
                            "	datamilis bigint,     \n" +
                            "	FOREIGN KEY (idveiculo) REFERENCES tb_veiculo (id),     \n" +
                            "	FOREIGN KEY (idpneu) REFERENCES tb_pneu (id)\n" +
                            " );\n" +
                            "\n" +
                            "";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "ConectionDAO.criarBancodeDados");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    private Connection getMainConn(){
        String host = "";
        try{
            if(Mainconn ==null || Mainconn.isClosed()){
                host = getHost();    
                Mainconn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+BANCO,USER,PASSWORD);
            }
        }catch (Exception e){
            aux.showMessageWarning("Não foi possível se conectar ao servidor!!\nVerique se o serviço foi iniciado e depois abra o programa novamente!", "Falha de Conexão");
            aux.RegistrarLog(e.getMessage()+"\n"+e.getLocalizedMessage(), "ConectionDAO.getMainConn");
            //JOptionPane.showMessageDialog(null,"Erro 0: Não vou possível se conectar ao servidor [ "+ host +" ]. Verifique se a máquina está ligada e o serviço iniciado!! Detalhe:"+e.getMessage());
        }finally{
            return Mainconn;
        }
    }
    protected void AbrirConexao(){
        try{
           // nconexoes++;
            connection = getConexao();
           // aux.RegistrarLog("nConexao: "+nconexoes, "AbrirConexao");
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "ConectionDAO.AbrirConexao");
        }
    }
    protected void FecharConexao(){
        try{
            if(!connection.isClosed()){
                connection.close();
            }
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
        }/*finally{
            nconexoes--;
            aux.RegistrarLog("nConexao: "+nconexoes, "FecharConexao");
        }*/
    }
    public Connection getConexao(){
        try {
            //aux.RegistrarLog("nConexoes: "+nconexoes, "getConexao");
            //aux.RegistrarLog(Mainconn.getCatalog(), "");
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
