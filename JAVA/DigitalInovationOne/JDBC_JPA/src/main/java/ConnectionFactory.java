import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static String URL_CONECTION = "jdbc:mysql://localhost/dio";
    private static String USER = "mario";
    private static String PASSWORD = "2108";


    public static Connection getConnection() throws SQLException{
        try {
            Connection conn = DriverManager.getConnection(URL_CONECTION,  USER, PASSWORD);
            System.out.println("Sucesso na conexão");
            return conn;
        }catch (Exception e){
            System.out.println("Falha na conexão");
            return null;
        }
    }
}
