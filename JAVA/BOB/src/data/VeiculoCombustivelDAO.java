package data;

import java.util.ArrayList;
import model.VeiculoCombustivel;


public class VeiculoCombustivelDAO extends  ConectionDAO{

    private static final String TB_VEICULO_COMBUSTIVEL = "tb_veiculo_combustivel";
    private static final String ID = "id";
    private static final String ID_VEICULO = "id_veiculo";
    private static final String ID_USUARIO = "id_usuario";
    private static final String DATAMILI = "datamili";
    private static final String VALOR = "valor";
    private static final String KM = "km";
    private static final String POSTO = "posto";
    private static final String COMBUSTIVEL = "combustivel";
    private static final String LITROS = "litros";
    private static final String MOTORISTA = "motorista";
    public VeiculoCombustivelDAO(){
        connection = getConexao();
    }
    public VeiculoCombustivelDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(VeiculoCombustivel vc){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_veiculo_combustivel` (`id`, `id_veiculo`, `id_usuario`, `km`, `posto`, `combustivel`, `litros`, `valor`, `datamili`) VALUES (NULL, '9', '1', '1', 'RVM MOGI GUAÇU', 'GASOLINA', '10', '44.5', '1515151515151515');
        String sql = "INSERT INTO "+TB_VEICULO_COMBUSTIVEL+" ("+ID_VEICULO+", "+ID_USUARIO+", "+KM+", "+POSTO+", "+COMBUSTIVEL+", "+LITROS+", "+VALOR+", "+DATAMILI+", "+MOTORISTA+") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
    
            stmt.setInt(1, vc.getIdVeiculo());
            stmt.setInt(2, vc.getIdUsuario());
            stmt.setInt(3, vc.getKm());
            stmt.setString(4, vc.getPosto());
            stmt.setString(5, vc.getCombustivel());
            stmt.setFloat(6, vc.getLitros());
            stmt.setFloat(7, vc.getValor());
            stmt.setLong(8, vc.getDataMilis());
            stmt.setString(9, vc.getMotorista());
            
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelDAO.Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public boolean Alterar(VeiculoCombustivel vc){
        AbrirConexao();
        boolean retorno = false;
        String sql = "UPDATE "+TB_VEICULO_COMBUSTIVEL+" SET "+ID_VEICULO+" = ?, "+ID_USUARIO+" = ?, "+KM+" = ?, "+
                POSTO+" = ?, "+COMBUSTIVEL+" = ?, "+LITROS+" = ?, "+VALOR+" = ?, "+DATAMILI+" = ?, "+MOTORISTA+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, vc.getIdVeiculo());
            stmt.setInt(2, vc.getIdUsuario());
            stmt.setInt(3, vc.getKm());
            stmt.setString(4, vc.getPosto());
            stmt.setString(5, vc.getCombustivel());
            stmt.setFloat(6, vc.getLitros());
            stmt.setFloat(7, vc.getValor());
            stmt.setLong(8, vc.getDataMilis());
            stmt.setString(9, vc.getMotorista());
            stmt.setInt(10, vc.getId());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelDAO.Alterar");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public void Excluir(VeiculoCombustivel vc){
        AbrirConexao();
        String sql = "DELETE FROM "+TB_VEICULO_COMBUSTIVEL+" WHERE "+ID+" = " + vc.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelDAO.Excluir");
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<VeiculoCombustivel> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO_COMBUSTIVEL; //Script sql para Consultar
        ArrayList<VeiculoCombustivel> arraylist = new ArrayList<VeiculoCombustivel>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoCombustivel vc;
            while (resultSet.next()){
                vc = new VeiculoCombustivel();
                vc.setId(resultSet.getInt(ID));
                vc.setIdVeiculo(resultSet.getInt(ID_VEICULO));
                vc.setIdUsuario(resultSet.getInt(ID_USUARIO));
                vc.setValor(resultSet.getInt(VALOR));
                vc.setDataMilis(resultSet.getLong(DATAMILI));
                vc.setKm(resultSet.getInt(KM));
                vc.setPosto(resultSet.getString(POSTO));
                vc.setCombustivel(resultSet.getString(COMBUSTIVEL));
                vc.setLitros(resultSet.getFloat(LITROS));
                vc.setMotorista(resultSet.getString(MOTORISTA));
                                
                arraylist.add(vc);
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public VeiculoCombustivel PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO_COMBUSTIVEL+" WHERE "+ID+" = "+id;
        VeiculoCombustivel vc = new VeiculoCombustivel();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                vc.setId(resultSet.getInt(ID));
                vc.setIdVeiculo(resultSet.getInt(ID_VEICULO));
                vc.setIdUsuario(resultSet.getInt(ID_USUARIO));
                vc.setValor(resultSet.getInt(VALOR));
                vc.setDataMilis(resultSet.getLong(DATAMILI));
                vc.setKm(resultSet.getInt(KM));
                vc.setPosto(resultSet.getString(POSTO));
                vc.setCombustivel(resultSet.getString(COMBUSTIVEL));
                vc.setLitros(resultSet.getFloat(LITROS));
                vc.setMotorista(resultSet.getString(MOTORISTA));
                break;
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelDAO.PesquisarById");
        }finally{
            FecharConexao();
            return vc;    
        }
    }
    public ArrayList<VeiculoCombustivel> PesquisarTodosByIDVeiculo(int id_veiculo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO_COMBUSTIVEL+" WHERE "+ID_VEICULO+" = "+id_veiculo; //Script sql para Consultar
        ArrayList<VeiculoCombustivel> arraylist = new ArrayList<VeiculoCombustivel>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoCombustivel vc;
            while (resultSet.next()){
                vc = new VeiculoCombustivel();
                vc.setId(resultSet.getInt(ID));
                vc.setIdVeiculo(resultSet.getInt(ID_VEICULO));
                vc.setIdUsuario(resultSet.getInt(ID_USUARIO));
                vc.setValor(resultSet.getInt(VALOR));
                vc.setDataMilis(resultSet.getLong(DATAMILI));
                vc.setKm(resultSet.getInt(KM));
                vc.setPosto(resultSet.getString(POSTO));
                vc.setCombustivel(resultSet.getString(COMBUSTIVEL));
                vc.setLitros(resultSet.getFloat(LITROS));
                vc.setMotorista(resultSet.getString(MOTORISTA));
                                
                arraylist.add(vc);
            }
           
        }catch (Exception e){
           aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelDAO.PesquisarTodosByIDVeiculo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    } 

    public boolean excluirById(int id) {
      AbrirConexao();
      boolean retorno = false;
        String sql = "DELETE FROM "+TB_VEICULO_COMBUSTIVEL+" WHERE "+ID+" = " + id; //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelDAO.excluirById");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    
}
