package data;

import java.util.ArrayList;
import model.Documento;

public class DocumentoDAO extends  ConectionDAO{

    private static final String TB_DOCUMENTO = "tb_documento";
    private static final String TB_VEICULO_DOCUMENTO = "tb_veiculo_documento";
    private static final String ID = "id";
    private static final String N_REGISTRO = "n_registro";
    private static final String VALIDADE = "validade";
    private static final String ID_VEICULO = "id_veiculo";
    private static final String ID_DOCUMENTO = "id_documento";
    private static final String INFO = "info";
    
    
    public DocumentoDAO(){
        connection = getConexao();
    }
    public DocumentoDAO(String host){
        connection = getConexaoHost(host);
    }
    public boolean Inserir(Documento documento,int id_veiculo){
        AbrirConexao();
        try {
            if(Inserir(documento)){
                if(InserirVeiculoDocumento(id_veiculo, UltimoDocumento().getId())){
                    FecharConexao();
                    return true;
                }
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "DocumentoDAO.Inserir");
            return false;
        }
        FecharConexao();
        return false;
    }
    private boolean Inserir(Documento documento){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_documento` (`id`, `n_registro`, `validade`, `info`) VALUES (NULL, '51551515', '151515', 'teste');
        String sql = "INSERT INTO "+TB_DOCUMENTO+" ("+N_REGISTRO+", "+VALIDADE+", "+INFO+") VALUES (?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, documento.getN_registro());
            stmt.setLong(2, documento.getValidade_milis());
            stmt.setString(3, documento.getInfo());
            
            stmt.execute();
            stmt.close();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "DocumentoDAO.Inserir2");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean InserirVeiculoDocumento(int id_veiculo, int id_documento){
        AbrirConexao();
        boolean retorno = false;
        //INSERT INTO `tb_documento` (`id`, `n_registro`, `validade`, `info`) VALUES (NULL, '51551515', '151515', 'teste');
        String sql = "INSERT INTO "+TB_VEICULO_DOCUMENTO+" ("+ID_VEICULO+", "+ID_DOCUMENTO+") VALUES (?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id_veiculo);
            stmt.setInt(2, id_documento);
            
            stmt.execute();
            stmt.close();
            
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "DocumentoDAO.InserirVeiculoDocumento");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean Alterar(Documento documento){
        AbrirConexao();
        boolean retorno=false;
        String sql = "UPDATE "+TB_DOCUMENTO+" SET "+N_REGISTRO+" = ?, "+VALIDADE+" = ?, "+INFO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, documento.getN_registro());
            stmt.setLong(2, documento.getValidade_milis());
            stmt.setString(3, documento.getInfo());
            stmt.setInt(4, documento.getId());
            
            stmt.execute();
            stmt.close();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "DocumentoDAO.Alterar");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean Excluir(Documento documento){
        AbrirConexao();
        boolean retorno = false;
        String sql1 = "DELETE FROM "+TB_VEICULO_DOCUMENTO+" WHERE "+ID_DOCUMENTO+" = " + documento.getId(); //Script sql para Excluir
        String sql = "DELETE FROM "+TB_DOCUMENTO+" WHERE "+ID+" = " + documento.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql1);
            statement.execute(sql);
            
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "DocumentoDAO.Excluir");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public ArrayList<Documento> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_DOCUMENTO; //Script sql para Consultar
        ArrayList<Documento> arraylist = new ArrayList<Documento>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Documento d;
            while (resultSet.next()){
                d = new Documento();
                d.setId(resultSet.getInt(ID));
                d.setN_registro(resultSet.getString(N_REGISTRO));
                d.setValidade_milis(resultSet.getLong(VALIDADE));
                d.setInfo(resultSet.getString(INFO));
                arraylist.add(d);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "DocumentoDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
        
    }
    public Documento PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_DOCUMENTO+" WHERE "+ID+" = "+id;
        Documento d = new Documento();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                d.setId(resultSet.getInt(ID));
                d.setN_registro(resultSet.getString(N_REGISTRO));
                d.setValidade_milis(resultSet.getLong(VALIDADE));
                d.setInfo(resultSet.getString(INFO));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "DocumentoDAO.PesquisarById");
        }finally{
            FecharConexao();
            return d;    
        }
        
    }
    public ArrayList<Documento> PesquisarTodosByIDVeiculo(int id_veiculo){
        //SELECT * FROM tb_documento WHERE tb_documento.id IN (SELECT tb_veiculo_documento.id_documento FROM tb_veiculo_documento WHERE tb_veiculo_documento.id_veiculo = 2)
        AbrirConexao();
        String sql = "SELECT * FROM " +TB_DOCUMENTO+" WHERE "+ID+" IN (SELECT "+ ID_DOCUMENTO+" FROM "+TB_VEICULO_DOCUMENTO+" WHERE "+ID_VEICULO+" = "+id_veiculo+")";
        ArrayList<Documento> arraylist = new ArrayList<Documento>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Documento d;
            while (resultSet.next()){
                d = new Documento();
                d.setId(resultSet.getInt(ID));
                d.setN_registro(resultSet.getString(N_REGISTRO));
                d.setValidade_milis(resultSet.getLong(VALIDADE));
                d.setInfo(resultSet.getString(INFO));
                arraylist.add(d);
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "DocumentoDAO.PesquisarTodosByIDVeiculo");
        }
        finally{
            FecharConexao();
            return arraylist;
        }
        
    }
    public Documento UltimoDocumento(){
        ArrayList<Documento> lista = PesquisarTodos();
        if(lista.size()>0){
            return lista.get(lista.size()-1);
        }else{
            return new Documento();
        }
    }    
}
