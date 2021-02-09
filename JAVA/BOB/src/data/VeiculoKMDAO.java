package data;

import java.util.ArrayList;
import model.VeiculoKM;

public class VeiculoKMDAO extends  ConectionDAO{

    private static final String TB_VEICULO_KM = "tb_veiculo_km";
    private static final String ID = "id";
    private static final String ID_VEICULO = "id_veiculo";
    private static final String ID_USUARIO = "id_usuario";
    private static final String DATAMILI = "datamili";
    private static final String VALOR = "valor";
    public VeiculoKMDAO(){
        connection = getConexao();
    }
    public VeiculoKMDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(VeiculoKM vKM){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_veiculo_km` (`id`, `id_veiculo`, `id_usuario`, `valor`, `datamili`) VALUES (NULL, '1', '1', '100', '11111111111');
        String sql = "INSERT INTO "+TB_VEICULO_KM+" ("+ID_VEICULO+", "+ID_USUARIO+", "+VALOR+", "+DATAMILI+") VALUES (?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
    
            stmt.setInt(1, vKM.getIdVeiculo());
            stmt.setInt(2, vKM.getIdUsuario());
            stmt.setInt(3, vKM.getValor());
            stmt.setLong(4, vKM.getDataMilis());
            
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "VeiculoKMDAO.Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public boolean Alterar(VeiculoKM vKM){
        AbrirConexao();
        boolean retorno = false;
        String sql = "UPDATE "+TB_VEICULO_KM+" SET "+ID_VEICULO+" = ?, "+ID_USUARIO+" = ?, "+VALOR+" = ?, "+DATAMILI+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, vKM.getIdVeiculo());
            stmt.setInt(2, vKM.getIdUsuario());
            stmt.setInt(3, vKM.getValor());
            stmt.setLong(4, vKM.getDataMilis());
            stmt.setInt(5, vKM.getId());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoKMDAO.Alterar");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public void Excluir(VeiculoKM veiculo){
        AbrirConexao();
        String sql = "DELETE FROM "+TB_VEICULO_KM+" WHERE "+ID+" = " + veiculo.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoKMDAO.Excluir");
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<VeiculoKM> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO_KM; //Script sql para Consultar
        ArrayList<VeiculoKM> arraylist = new ArrayList<VeiculoKM>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoKM v ;
            while (resultSet.next()){
                v = new VeiculoKM();
                v.setId(resultSet.getInt(ID));
                v.setIdVeiculo(resultSet.getInt(ID_VEICULO));
                v.setIdUsuario(resultSet.getInt(ID_USUARIO));
                v.setValor(resultSet.getInt(VALOR));
                v.setDataMilis(resultSet.getLong(DATAMILI));
                arraylist.add(v);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoKMDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public VeiculoKM PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO_KM+" WHERE "+ID+" = "+id;
        VeiculoKM v = new VeiculoKM();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                v.setId(resultSet.getInt(ID));
                v.setIdVeiculo(resultSet.getInt(ID_VEICULO));
                v.setIdUsuario(resultSet.getInt(ID_USUARIO));
                v.setValor(resultSet.getInt(VALOR));
                v.setDataMilis(resultSet.getLong(DATAMILI));
                break;
            }
             
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoKMDAO.PesquisarById");
        }finally{
            FecharConexao();
            return v;
        }
    }
    public ArrayList<VeiculoKM> PesquisarTodosByIDVeiculo(int id_veiculo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO_KM+" WHERE "+ID_VEICULO+" = "+id_veiculo; //Script sql para Consultar
        ArrayList<VeiculoKM> arraylist = new ArrayList<VeiculoKM>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoKM v ;
            while (resultSet.next()){
                v = new VeiculoKM();
                v.setId(resultSet.getInt(ID));
                v.setIdVeiculo(resultSet.getInt(ID_VEICULO));
                v.setIdUsuario(resultSet.getInt(ID_USUARIO));
                v.setValor(resultSet.getInt(VALOR));
                v.setDataMilis(resultSet.getLong(DATAMILI));
                arraylist.add(v);
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoKMDAO.PesquisarTodosByIdVeiculo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<VeiculoKM> PesquisarTodosByIDVeiculoOrderByKM(int id_veiculo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO_KM+" WHERE "+ID_VEICULO+" = "+id_veiculo+ " ORDER BY "+ VALOR + " ASC"; //Script sql para Consultar
        ArrayList<VeiculoKM> arraylist = new ArrayList<VeiculoKM>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoKM v ;
            while (resultSet.next()){
                v = new VeiculoKM();
                v.setId(resultSet.getInt(ID));
                v.setIdVeiculo(resultSet.getInt(ID_VEICULO));
                v.setIdUsuario(resultSet.getInt(ID_USUARIO));
                v.setValor(resultSet.getInt(VALOR));
                v.setDataMilis(resultSet.getLong(DATAMILI));
                arraylist.add(v);
            }
           
        }catch (Exception e){
           aux.RegistrarLog(e.getMessage(), "VeiculoKMDAO.PesquisarTodosByIDVeiculoOrderByKM");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public int getUltimoKMByIDVeiculo(int id_veiculo){
        ArrayList<VeiculoKM> lista = PesquisarTodosByIDVeiculo(id_veiculo);
        if(lista.size()>0){
            return lista.get(lista.size()-1).getValor();
        }else{
            return 0;
        }
    }
    public VeiculoKM getUltimoVeiculoKMByIDVeiculo(int id_veiculo){
        ArrayList<VeiculoKM> lista = PesquisarTodosByIDVeiculo(id_veiculo);
        if(lista.size()>0){
            return lista.get(lista.size()-1);
        }else{
            return new VeiculoKM();
        }
    }
    public VeiculoKM UltimoVeiculo(){
        ArrayList<VeiculoKM> lista = PesquisarTodos();
        if(lista.size()>0){
            return lista.get(lista.size()-1);
        }else{
            return new VeiculoKM();
        }
    }    
}
