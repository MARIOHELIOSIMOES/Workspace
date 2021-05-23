import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    //private static final String TABELA = "aluno";
    public List<Aluno> list(){

        List<Aluno> alunos = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection()){
            PreparedStatement prst = conn.prepareStatement("SELECT * FROM aluno");
            ResultSet rs = prst.executeQuery();
            while (rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEstado(rs.getString("estado"));
                alunos.add(aluno);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            return alunos;
        }

    }
    public Aluno getAlunoById(int id){

        Aluno aluno = new Aluno();
        try(Connection conn = ConnectionFactory.getConnection()){
            PreparedStatement prst = conn.prepareStatement("SELECT * FROM aluno WHERE id = ?");
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();

            while (rs.next()){
                aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEstado(rs.getString("estado"));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            return aluno;
        }
    }
    public void createAluno(Aluno aluno){
        try(Connection conn = ConnectionFactory.getConnection();){
            String SQL = "INSERT INTO aluno (nome, idade, estado) VALUES( ?, ?, ?)";
            PreparedStatement prst = conn.prepareStatement(SQL);
            prst.setString(1, aluno.getNome());
            prst.setInt(2, aluno.getIdade());
            prst.setString(3, aluno.getEstado());

            int rowsAffected =prst.executeUpdate();
            System.out.printf("Inserção bem sucedida. %d linha(s) afetada(s).", rowsAffected);

        }catch (Exception e){
            System.out.printf("Inserção falhou!");
            e.printStackTrace();
        }
    }
}
