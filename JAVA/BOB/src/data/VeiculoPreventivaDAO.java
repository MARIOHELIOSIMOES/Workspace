package data;

import java.util.ArrayList;
import model.VeiculoPreventiva;

public class VeiculoPreventivaDAO extends  ConectionDAO{

    private static final String TB_PREVENTIVA = "tb_veiculo_preventiva";
    private static final String ID = "id";
    private static final String ID_VEICULO = "id_veiculo", ID_PEDIDO = "id_pedido";
    private static final String TIPO = "tipo";
    private static final String KM = "km", KMPROX = "kmprox";
    private static final String DATAMILIS = "datamilis";
    private static final String INFO = "info";  
    public VeiculoPreventivaDAO(){
       connection = getConexao();
    }
    public VeiculoPreventivaDAO(String host){
        connection = getConexaoHost(host);
    }
    public boolean Inserir(VeiculoPreventiva vp){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_veiculo_preventiva` 
                    //('id`, `id_veiculo`, `tipo`, `km`, `datamilis`, `id_pedido`, `info`) 
                    //VALUES (NULL, '9', '1', '300', '0', '0', 'teste');
        String sql = "INSERT INTO "+TB_PREVENTIVA
                       +" ("+ID_VEICULO+", "+TIPO+", "+KM+", "+KMPROX+", "+DATAMILIS+", "+ID_PEDIDO+", "+INFO+") VALUES (?, ?, ?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, vp.getId_veiculo());
            stmt.setInt(2, vp.getTipo());
            stmt.setInt(3, vp.getKm());
            stmt.setInt(4, vp.getKmProx());
            stmt.setLong(5, vp.getDatamilis());
            stmt.setInt(6, vp.getIdPedido());
            stmt.setString(7, vp.getInfo());
            
            stmt.execute();
            retorno = true;
            
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.Inserir");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean InserirArray(ArrayList<VeiculoPreventiva> vp){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_veiculo_preventiva` 
                    //('id`, `id_veiculo`, `tipo`, `km`, `datamilis`, `id_pedido`, `info`) 
                    //VALUES (NULL, '9', '1', '300', '0', '0', 'teste');
        String sql = "INSERT INTO "+TB_PREVENTIVA
                       +" ("+ID_VEICULO+", "+TIPO+", "+KM+", "+KMPROX+", "+DATAMILIS+", "+ID_PEDIDO+", "+INFO+") VALUES (?, ?, ?, ?, ?, ?, ?)"; //Script sql para inserção
        
        try {
            int qtde = 1;
            for(int i = 1; i<vp.size(); i++){
                sql += ", (?, ?, ?, ?, ?, ?, ?)";
                qtde++;
            }
            stmt = connection.prepareStatement(sql);
            for(int i = 0; i<qtde; i++){
                stmt.setInt((1+(i*7)), vp.get(i).getId_veiculo());
                stmt.setInt((2+(i*7)), vp.get(i).getTipo());
                stmt.setInt((3+(i*7)), vp.get(i).getKm());
                stmt.setInt((4+(i*7)), vp.get(i).getKmProx());
                stmt.setLong((5+(i*7)), vp.get(i).getDatamilis());
                stmt.setInt((6+(i*7)), vp.get(i).getIdPedido());
                stmt.setString((7+(i*7)), vp.get(i).getInfo());
            }
            stmt.execute();
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.InserirArray");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    
    public boolean Alterar(VeiculoPreventiva vp){
        AbrirConexao();
        boolean retorno=false;
        String sql = "UPDATE "+TB_PREVENTIVA+" SET "
                                                    +ID_VEICULO+" = ?, "
                                                    +TIPO+" = ?, "
                                                    +KM+" = ?, "
                                                    +KMPROX+" = ?, "
                                                    +DATAMILIS+" = ?, "
                                                    +ID_PEDIDO+" = ?, "
                                                    +INFO+" = ? WHERE "
                                                    +ID+" = ? "; //Script sql para alterar
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, vp.getId_veiculo());
            stmt.setInt(2, vp.getTipo());
            stmt.setInt(3, vp.getKm());
            stmt.setInt(4, vp.getKmProx());
            stmt.setLong(5, vp.getDatamilis());
            stmt.setInt(6, vp.getIdPedido());
            stmt.setString(7, vp.getInfo());
            stmt.setInt(8, vp.getId());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.Alterar");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean AlterarPedido(int idVeiculo,int idpedido, String tipos){
        AbrirConexao();
        boolean retorno=false;
        
        String sql = "UPDATE "+TB_PREVENTIVA+" SET "
                                                    +ID_PEDIDO+" = ? WHERE "
                                                    +ID_VEICULO+" = ? AND "
                                                    +ID_PEDIDO+ " = 0 AND "
                                                    +TIPO+" IN("+tipos+")"; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, idpedido);
            stmt.setInt(2, idVeiculo);
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.AlterarPedido");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean Excluir(VeiculoPreventiva vp){
        AbrirConexao();
        boolean retorno = false;
        
        String sql = "DELETE FROM "+TB_PREVENTIVA+" WHERE "+ID+" = " + vp.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.Excluir");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    
    private String getVariacaoIDPedido(boolean pendente){
        return (pendente ? " = " : " != ");
    }
    
    public ArrayList<VeiculoPreventiva> getArrayListPesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PREVENTIVA; //Script sql para Consultar
        ArrayList<VeiculoPreventiva> arraylist = new ArrayList<VeiculoPreventiva>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoPreventiva vp;
            while (resultSet.next()){
                vp = new VeiculoPreventiva();
                vp.setId(resultSet.getInt(ID));
                vp.setId_veiculo(resultSet.getInt(ID_VEICULO));
                vp.setTipo(resultSet.getInt(TIPO));
                vp.setKm(resultSet.getInt(KM));
                vp.setKmProx(resultSet.getInt(KMPROX));
                vp.setDatamilis(resultSet.getLong(DATAMILIS));
                vp.setIdPedido(resultSet.getInt(ID_PEDIDO));
                vp.setInfo(resultSet.getString(INFO));
                arraylist.add(vp);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.getArrayListPesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<VeiculoPreventiva> getArrayListPreventivaByIdVeiculo(int idVeiculo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PREVENTIVA+" WHERE "+ID_VEICULO +" = "+idVeiculo; //Script sql para Consultar
        ArrayList<VeiculoPreventiva> arraylist = new ArrayList<VeiculoPreventiva>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoPreventiva vp;
            while (resultSet.next()){
                vp = new VeiculoPreventiva();
                vp.setId(resultSet.getInt(ID));
                vp.setId_veiculo(resultSet.getInt(ID_VEICULO));
                vp.setTipo(resultSet.getInt(TIPO));
                vp.setKm(resultSet.getInt(KM));
                vp.setKmProx(resultSet.getInt(KMPROX));
                vp.setDatamilis(resultSet.getLong(DATAMILIS));
                vp.setIdPedido(resultSet.getInt(ID_PEDIDO));
                vp.setInfo(resultSet.getString(INFO));
                arraylist.add(vp);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.getArrayListPreventivaByIdVeiculo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<VeiculoPreventiva> getArrayListPreventivaByIdVeiculoByIDPedido(int idVeiculo, int idPedido, boolean pendente){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PREVENTIVA+" WHERE "+ID_VEICULO +" = "+idVeiculo + " AND " + ID_PEDIDO +getVariacaoIDPedido(pendente)+idPedido; //Script sql para Consultar
        ArrayList<VeiculoPreventiva> arraylist = new ArrayList<VeiculoPreventiva>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoPreventiva vp;
            while (resultSet.next()){
                vp = new VeiculoPreventiva();
                vp.setId(resultSet.getInt(ID));
                vp.setId_veiculo(resultSet.getInt(ID_VEICULO));
                vp.setTipo(resultSet.getInt(TIPO));
                vp.setKm(resultSet.getInt(KM));
                vp.setKmProx(resultSet.getInt(KMPROX));
                vp.setDatamilis(resultSet.getLong(DATAMILIS));
                vp.setIdPedido(resultSet.getInt(ID_PEDIDO));
                vp.setInfo(resultSet.getString(INFO));
                arraylist.add(vp);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.getArrayListPreventivaByIdVeiculoByIDPedido");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<VeiculoPreventiva> getArrayListPreventivaByIdVeiculoByIDPedidoByTipo(int idVeiculo, int idPedido, boolean pendente, int tipo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PREVENTIVA+" WHERE "+ID_VEICULO +" = "+idVeiculo + " AND " + ID_PEDIDO +getVariacaoIDPedido(pendente)+idPedido + " AND " + TIPO +" = "+tipo; //Script sql para Consultar
        ArrayList<VeiculoPreventiva> arraylist = new ArrayList<VeiculoPreventiva>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoPreventiva vp;
            while (resultSet.next()){
                vp = new VeiculoPreventiva();
                vp.setId(resultSet.getInt(ID));
                vp.setId_veiculo(resultSet.getInt(ID_VEICULO));
                vp.setTipo(resultSet.getInt(TIPO));
                vp.setKm(resultSet.getInt(KM));
                vp.setKmProx(resultSet.getInt(KMPROX));
                vp.setDatamilis(resultSet.getLong(DATAMILIS));
                vp.setIdPedido(resultSet.getInt(ID_PEDIDO));
                vp.setInfo(resultSet.getString(INFO));
                arraylist.add(vp);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.getArrayListByIdVeiculoByIdPedidoByTipo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public VeiculoPreventiva PesquisarVeiculoPreventivaById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PREVENTIVA+" WHERE "+ID+" = "+id;
        VeiculoPreventiva vp = new VeiculoPreventiva();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                vp.setId(resultSet.getInt(ID));
                vp.setId_veiculo(resultSet.getInt(ID_VEICULO));
                vp.setTipo(resultSet.getInt(TIPO));
                vp.setKm(resultSet.getInt(KM));
                vp.setKmProx(resultSet.getInt(KMPROX));
                vp.setDatamilis(resultSet.getLong(DATAMILIS));
                vp.setIdPedido(resultSet.getInt(ID_PEDIDO));
                vp.setInfo(resultSet.getString(INFO));
                break;
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaDAO.PesquisarVeiculoPreventivaById");
        }finally{
            FecharConexao();
            return vp;    
        }
        
    }
        
}
