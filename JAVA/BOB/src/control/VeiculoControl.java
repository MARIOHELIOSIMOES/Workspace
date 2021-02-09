
package control;

import Exception.ValorInvalidoException;
import data.VeiculoDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.Veiculo;

public class VeiculoControl {
    Veiculo veiculo;
    ArrayList<Veiculo> lista;
    Auxiliar aux;
    VeiculoDAO vDao;
    
    private void inicializar(){
        lista = new ArrayList<Veiculo>();
        veiculo = new Veiculo();
        aux = new Auxiliar();
        vDao =new VeiculoDAO();
    }

    public VeiculoControl(){
        inicializar();
    }
    public VeiculoControl(Veiculo v){
        inicializar();
        this.veiculo = v;
    }
    public Veiculo getVeiculoById(int id){
        veiculo = new Veiculo();
        try{
            veiculo = vDao.PesquisarById(id);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoControl.getVeiculoById");
        }finally{
            return veiculo;
        }
    }
    
    //Método receberá os parametros do veiculo, deverá validar os valores e salvar/alterar o registro na base de dados
    public int addVeiculo(int id, String placa, String marca, String modelo, String ano, String tipo, int config, String carroceria, String info, int combustivel){
       int idNovo=0;
        
        try{
           Veiculo veiculo = new Veiculo();
           //Confirmar quais campos serão obrigatórios
           veiculo.setId(id);
           if(placa.equals("")){//Lança um exception para capturar a placa em branco
               throw new ValorInvalidoException("placa", placa);
           }else{
                veiculo.setPlaca(placa);
           }
           veiculo.setMarca(marca);
           veiculo.setModelo(modelo);
           veiculo.setTipo(tipo);
           veiculo.setConfiguracao(config);//vem setado pelo combobox
           veiculo.setCarroceria(carroceria);
           veiculo.setInfo(info);//pode ser vazio, em branco
           veiculo.setAno(Integer.parseInt(ano));//vai gerar um NumberFormatException, já capturado!
           veiculo.setCombustivel(combustivel);
           boolean retorno = false;
           if(id==0){
               retorno = vDao.Inserir(veiculo);
           }else{
               retorno = vDao.Alterar(veiculo);
           }
           if(retorno){
               aux.showMessageInformacao("Salvo com sucesso!", "Cadastro de Veículo");
               idNovo = getVeiculoByPlaca(placa).getId();
           }
       }catch (NumberFormatException nfe){
           aux.showMessageWarning("Espera-se um valor numérico para o ANO do veículo..", "Verifique os campos");
           aux.RegistrarLog(nfe.getMessage(), "VeiculoControl.addVeiculo");
           
       }catch (Exception e){
            aux.showMessageWarning("Ocorreu um erro: "+e.getMessage(), "Verifique os campos");
            aux.RegistrarLog(e.getMessage(), "VeiculoControl.addVeiculo");
       }finally{
            return idNovo;
       }
    }
    public Veiculo getVeiculoByPlaca(String placa){
        Veiculo veiculo = new Veiculo();
        veiculo = vDao.PesquisarByPlaca(placa);
        return veiculo;
    }
    //Lista completa de veiculos
    public ArrayList<Veiculo> getArrayListTodosVeiculos(){
        lista = vDao.PesquisarTodos();
        return lista;
    }
    //Método deve verificar o último ID atual e retornar o próximo ID de Veículo,
    //Sugestão é gerar um número aleatório negativo, e quando efetivamente salvar o veiculo atualizar o ID nos demais objetos como documentos, e anexos.
    public int getNovoID(){
        Veiculo v = new Veiculo();
        v.preencherPadrao();
        if(vDao.Inserir(v)){
            return vDao.UltimoVeiculo().getId();
        }
        return 0;
    }
    

}
