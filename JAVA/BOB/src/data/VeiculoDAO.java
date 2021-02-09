package data;

import java.util.ArrayList;
import model.Veiculo;

public class VeiculoDAO extends  ConectionDAO{

    private static final String TB_VEICULO = "tb_veiculo";
    private static final String ID = "id";
    private static final String PLACA = "placa";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String ANO = "ano";
    private static final String TIPO = "tipo";
    private static final String CARROCERIA = "carroceria";
    private static final String CONFIGURACAO = "configuracao";
    private static final String INFO = "info";
    private static final String COMBUSTIVEL = "combustivel";

    public VeiculoDAO(){
        connection = getConexao();
    }
    public VeiculoDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(Veiculo veiculo){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_veiculo` (`id`, `placa`, `marca`, `modelo`,    `ano`,   `tipo`, `carroceria`,    `configuracao`,    `info`) VALUES (NULL, 'DLH8657', 'HONDA', 'TWISTER', '2004', 'MOTO', 'Não se aplica', '1', 'Moto Twister Preta');
        String sql = "INSERT INTO "+TB_VEICULO+" ("+PLACA+", "+MARCA+", "+MODELO+", "+ANO+", "+TIPO+", "+CARROCERIA+", "+CONFIGURACAO+", "+INFO+", "+COMBUSTIVEL+") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAno());
            stmt.setString(5, veiculo.getTipo());
            stmt.setString(6, veiculo.getCarroceria());
            stmt.setInt(7, veiculo.getConfiguracao());
            stmt.setString(8, veiculo.getInfo());
            stmt.setInt(9, veiculo.getCombustivel());
            
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "VeiculoDAO.Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public boolean Alterar(Veiculo veiculo){
        AbrirConexao();
        boolean retorno = false;
        String sql = "UPDATE "+TB_VEICULO+" SET "+PLACA+" = ?, "+MARCA+" = ?, "+MODELO+" = ?, "+ANO+" = ?, "+TIPO+" = ?, "+CARROCERIA+" = ?, "+CONFIGURACAO+" = ?, "+INFO+" = ?, "+COMBUSTIVEL+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAno());
            stmt.setString(5, veiculo.getTipo());
            stmt.setString(6, veiculo.getCarroceria());
            stmt.setInt(7, veiculo.getConfiguracao());
            stmt.setString(8, veiculo.getInfo());
            stmt.setInt(9, veiculo.getCombustivel());
            stmt.setInt(10, veiculo.getId());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoDAO.Alterar");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public void Excluir(Veiculo veiculo){
        AbrirConexao();
        String sql = "DELETE FROM "+TB_VEICULO+" WHERE "+ID+" = " + veiculo.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoDAO.Excluir");
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<Veiculo> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO; //Script sql para Consultar
        ArrayList<Veiculo> arraylist = new ArrayList<Veiculo>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Veiculo v ;
            while (resultSet.next()){
                v = new Veiculo();
                v.setId(resultSet.getInt(ID));
                v.setPlaca(resultSet.getString(PLACA));
                v.setMarca(resultSet.getString(MARCA));
                v.setModelo(resultSet.getString(MODELO));
                v.setAno(resultSet.getInt(ANO));
                v.setTipo(resultSet.getString(TIPO));
                v.setCarroceria(resultSet.getString(CARROCERIA));
                v.setConfiguracao(resultSet.getInt(CONFIGURACAO));
                v.setInfo(resultSet.getString(INFO));
                v.setCombustivel(resultSet.getInt(COMBUSTIVEL));
                arraylist.add(v);
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public Veiculo PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO+" WHERE "+ID+" = "+id;
        Veiculo v = new Veiculo();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                v.setId(resultSet.getInt(ID));
                v.setPlaca(resultSet.getString(PLACA));
                v.setMarca(resultSet.getString(MARCA));
                v.setModelo(resultSet.getString(MODELO));
                v.setAno(resultSet.getInt(ANO));
                v.setTipo(resultSet.getString(TIPO));
                v.setCarroceria(resultSet.getString(CARROCERIA));
                v.setConfiguracao(resultSet.getInt(CONFIGURACAO));
                v.setInfo(resultSet.getString(INFO));
                v.setCombustivel(resultSet.getInt(COMBUSTIVEL));
                break;
            }
             
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoDAO.PesquisarById");
        }finally{
            FecharConexao();
            return v;
        }
    }
    public ArrayList<Veiculo> PesquisarTodosByPlaca(String placa){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO+" WHERE "+PLACA+" LIKE '%"+placa+"%'"; //Script sql para Consultar
        ArrayList<Veiculo> arraylist = new ArrayList<Veiculo>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Veiculo v ;
            while (resultSet.next()){
                v = new Veiculo();
                v.setId(resultSet.getInt(ID));
                v.setPlaca(resultSet.getString(PLACA));
                v.setMarca(resultSet.getString(MARCA));
                v.setModelo(resultSet.getString(MODELO));
                v.setAno(resultSet.getInt(ANO));
                v.setTipo(resultSet.getString(TIPO));
                v.setCarroceria(resultSet.getString(CARROCERIA));
                v.setConfiguracao(resultSet.getInt(CONFIGURACAO));
                v.setInfo(resultSet.getString(INFO));
                v.setCombustivel(resultSet.getInt(COMBUSTIVEL));
                arraylist.add(v);
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoDAO.PesquisarTodosByPlaca");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public Veiculo PesquisarByPlaca(String placa){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO+" WHERE "+PLACA+" LIKE '%"+placa+"%'"; //Script sql para Consultar
        Veiculo v = new Veiculo();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                v.setId(resultSet.getInt(ID));
                v.setPlaca(resultSet.getString(PLACA));
                v.setMarca(resultSet.getString(MARCA));
                v.setModelo(resultSet.getString(MODELO));
                v.setAno(resultSet.getInt(ANO));
                v.setTipo(resultSet.getString(TIPO));
                v.setCarroceria(resultSet.getString(CARROCERIA));
                v.setConfiguracao(resultSet.getInt(CONFIGURACAO));
                v.setInfo(resultSet.getString(INFO));
                v.setCombustivel(resultSet.getInt(COMBUSTIVEL));
                break;
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoDAO.PesquisarByPlaca");
        }finally{
            FecharConexao();
            return v;
        }
    }
    
    public Veiculo UltimoVeiculo(){
        ArrayList<Veiculo> lista = PesquisarTodos();
        if(lista.size()>0){
            return lista.get(lista.size()-1);
        }else{
            return new Veiculo();
        }
    }    
}
