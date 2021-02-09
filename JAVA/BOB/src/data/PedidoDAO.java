package data;

import java.util.ArrayList;
import model.Pedido;
import model.PedidoItem;

public class PedidoDAO extends  ConectionDAO{

    private static final String TB_PEDIDO = "tb_pedido", TB_PEDIDO_ITEM = "tb_pedido_item";
    private static final String ID = "id";
    private static final String ID_VEICULO = "id_veiculo", ID_PEDIDO = "id_pedido", ID_ITEM = "id_item";
    private static final String TIPO = "tipo";
    private static final String KM = "km";
    private static final String DATAMILIS = "datamilis";
    private static final String INFO = "info";
    private static final String VALOR = "valor";
    private static final String QTDE = "qtde";
    public PedidoDAO(){
       connection = getConexao();
    }
    public PedidoDAO(String host){
        connection = getConexaoHost(host);
    }
    public boolean Inserir(Pedido pedido){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_pedido` (`id`, `id_veiculo`, `tipo`, `km`, `datamilis`, `info`) VALUES (NULL, '9', '1', '120', '0', 'teste pedido ');
        String sql = "INSERT INTO "+TB_PEDIDO+" ("+ID_VEICULO+", "+TIPO+", "+KM+", "+DATAMILIS+", "+INFO+") VALUES (?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedido.getId_veiculo());
            stmt.setInt(2, pedido.getTipo());
            stmt.setInt(3, pedido.getKm());
            stmt.setLong(4, pedido.getDatamilis());
            stmt.setString(5, pedido.getInfo());
            
            stmt.execute();
            int id = getIdPedido(pedido);
            if(id==0){
                retorno = false;
            }
            for(PedidoItem item: pedido.getArraylist()){
                item.setIdPedido(id);
                if(!inserirPedidoItem(item)){
                    retorno = false;
                }
            }
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "PedidoDAO.Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    //METODO DEVE CONFERIR DOS OS CAMPOS PARA RETORNAR O ID CORRESPONDENTE. SERÁ CHAMADO LOGO APÓS A INSERÇÃO PARA POSTERIOR
        //CHAMAR O METODO DE INSERIR PEDIDOITEM
    private int getIdPedido(Pedido pedido){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PEDIDO+" WHERE "+ID_VEICULO+" = "+pedido.getId_veiculo()+" AND "
                                                         +TIPO+" = "+pedido.getTipo()+" AND "
                                                         +KM+" = "+pedido.getKm()+" AND "
                                                         +DATAMILIS+" = "+pedido.getDatamilis()+" AND "
                                                         +INFO+" LIKE '"+pedido.getInfo()+"' ";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
           while(resultSet.next()){
                pedido.setId(resultSet.getInt(ID));
           }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoDAO.getIdPedido");
        }finally{
            FecharConexao();
            return pedido.getId();    
        }
    }
    // VERFICAR SE O PEDIDO ID ESTÁ SENDO PASSADO POR CADA PEDIDOITEM QUE CHEGAR
    private boolean inserirPedidoItem(PedidoItem pedidoItem){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_pedido_item` (`id_pedido`, `id_item`, `valor`, `qtde`) VALUES ('1', '11', '10.00', '1')
        String sql = "INSERT INTO "+TB_PEDIDO_ITEM+" ("+ID_PEDIDO+", "+ID_ITEM+", "+VALOR+", "+QTDE+") VALUES (?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedidoItem.getIdPedido());
            stmt.setInt(2, pedidoItem.getIdItem());
            stmt.setFloat(3, pedidoItem.getValor());
            stmt.setFloat(4, pedidoItem.getQuantidade());
            
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "PedidoDAO.inserirPedidoItem");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    private boolean ExcluirPedidoItemByIdPedido(int idPedido){
        AbrirConexao();
        boolean retorno = false;
        String sql = "DELETE FROM "+TB_PEDIDO_ITEM+" WHERE "+ID_PEDIDO+" = " + idPedido; //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoDAO.ExcluirPedidoItemByIdPedido");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean Alterar(Pedido pedido){
        AbrirConexao();
        boolean retorno=false;
        if(!ExcluirPedidoItemByIdPedido(pedido.getId_veiculo())){
            return false;
        }
        
        String sql = "UPDATE "+TB_PEDIDO+" SET "+ID_VEICULO+" = ?, "+TIPO+" = ?, "+KM+" = ?, "+DATAMILIS+" = ?, "+INFO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, pedido.getId_veiculo());
            stmt.setInt(2, pedido.getTipo());
            stmt.setInt(3, pedido.getKm());
            stmt.setLong(4, pedido.getDatamilis());
            stmt.setString(5, pedido.getInfo());
            stmt.setInt(6, pedido.getId());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoDAO.Alterar");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean Excluir(Pedido pedido){
        boolean retorno = false;
        if(!ExcluirPedidoItemByIdPedido(pedido.getId())){
            return false;
        }
        AbrirConexao();
        String sql = "DELETE FROM "+TB_PEDIDO+" WHERE "+ID+" = " + pedido.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoDAO.Excluir");
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public ArrayList<Pedido> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PEDIDO; //Script sql para Consultar
        ArrayList<Pedido> arraylist = new ArrayList<Pedido>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Pedido pedido;
            while (resultSet.next()){
                pedido = new Pedido();
                pedido.setId(resultSet.getInt(ID));
                pedido.setId_veiculo(resultSet.getInt(ID_VEICULO));
                pedido.setTipo(resultSet.getInt(TIPO));
                pedido.setKm(resultSet.getInt(KM));
                pedido.setDatamilis(resultSet.getLong(DATAMILIS));
                pedido.setInfo(resultSet.getString(INFO));
                arraylist.add(pedido);
            }
            for(Pedido p: arraylist){
                p.setArraylist(getArrayListPedidoItemByIdPedido(p.getId()));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<PedidoItem> getArrayListPedidoItemByIdPedido(int idPedido){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PEDIDO_ITEM+" WHERE "+ID_PEDIDO +" = "+idPedido; //Script sql para Consultar
        ArrayList<PedidoItem> arraylist = new ArrayList<PedidoItem>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            PedidoItem pedido;
            while (resultSet.next()){
                pedido = new PedidoItem();
                pedido.setIdPedido(resultSet.getInt(ID_PEDIDO));
                pedido.setIdItem(resultSet.getInt(ID_ITEM));
                pedido.setValor(resultSet.getFloat(VALOR));
                pedido.setQuantidade(resultSet.getFloat(QTDE));
                arraylist.add(pedido);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoDAO.getArrayListPedidoItemByIdPedido");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public Pedido PesquisarPedidoById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PEDIDO+" WHERE "+ID+" = "+id;
        Pedido pedido = new Pedido();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                pedido.setId(resultSet.getInt(ID));
                pedido.setId_veiculo(resultSet.getInt(ID_VEICULO));
                pedido.setTipo(resultSet.getInt(TIPO));
                pedido.setKm(resultSet.getInt(KM));
                pedido.setDatamilis(resultSet.getLong(DATAMILIS));
                pedido.setInfo(resultSet.getString(INFO));
                pedido.setArraylist(getArrayListPedidoItemByIdPedido(pedido.getId()));
                break;
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoDAO.PesquisarPedidoById");
        }finally{
            FecharConexao();
            return pedido;    
        }
        
    }
    public ArrayList<Pedido> PesquisarTodosPedidoByIDVeiculo(int idVeiculo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PEDIDO+" WHERE "+ID_VEICULO+" = "+idVeiculo; //Script sql para Consultar
        ArrayList<Pedido> arraylist = new ArrayList<Pedido>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Pedido pedido;
            while (resultSet.next()){
                pedido = new Pedido();
                pedido.setId(resultSet.getInt(ID));
                pedido.setId_veiculo(resultSet.getInt(ID_VEICULO));
                pedido.setTipo(resultSet.getInt(TIPO));
                pedido.setKm(resultSet.getInt(KM));
                pedido.setDatamilis(resultSet.getLong(DATAMILIS));
                pedido.setInfo(resultSet.getString(INFO));
                arraylist.add(pedido);
            }
            for(Pedido p: arraylist){
                p.setArraylist(getArrayListPedidoItemByIdPedido(p.getId()));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoDAO.PesquisarTodosPedidoByIdVeiculo");
        }finally{
            FecharConexao();
            return arraylist;
        }
        
    }
    public ArrayList<Pedido> PesquisarTodosPedidoByIDVeiculoAndByTipo(int idVeiculo, int tipoPedido){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PEDIDO+" WHERE "+ID_VEICULO+" = "+idVeiculo+" AND "+ TIPO+" = "+tipoPedido; //Script sql para Consultar
        ArrayList<Pedido> arraylist = new ArrayList<Pedido>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Pedido pedido;
            while (resultSet.next()){
                pedido = new Pedido();
                pedido.setId(resultSet.getInt(ID));
                pedido.setId_veiculo(resultSet.getInt(ID_VEICULO));
                pedido.setTipo(resultSet.getInt(TIPO));
                pedido.setKm(resultSet.getInt(KM));
                pedido.setDatamilis(resultSet.getLong(DATAMILIS));
                pedido.setInfo(resultSet.getString(INFO));
                arraylist.add(pedido);
            }
            for(Pedido p: arraylist){
                p.setArraylist(getArrayListPedidoItemByIdPedido(p.getId()));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoDAO.PesquisarTodosPedidoByIDVeiculoAndByTipo");
        }finally{
            FecharConexao();
            return arraylist;
        }
        
    }
        
}
