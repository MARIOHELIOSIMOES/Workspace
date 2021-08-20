
package control;

import Exception.ValorInvalidoException;
import data.VeiculoCombustivelDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Auxiliar;
import model.GraficoItem;
import model.GraficoItemC;
import model.VeiculoCombustivel;

public class VeiculoCombustivelControl {

    ArrayList<VeiculoCombustivel> arraylist;
    VeiculoKMControl vkm;
    Auxiliar aux;
    VeiculoCombustivelDAO vcDAO;
    
    public VeiculoCombustivelControl(){
        arraylist = new ArrayList<VeiculoCombustivel>();
        vkm = new VeiculoKMControl();
        aux = new Auxiliar();
        vcDAO = new VeiculoCombustivelDAO();
    }
    
    // deverá fazer uma consulta na base de dados e retornar a lista com todos os registros
    public ArrayList<VeiculoCombustivel> getArrayListByIDVeiculo(int idVeiculo){
        //precisa implementar
        //arraylist = new VeiculoKmMock().getLista(); //  para Teste com dados Mock
        try{
            arraylist = vcDAO.PesquisarTodosByIDVeiculo(idVeiculo);
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.getArrayListByIDVeiculo");
        }finally{
            return arraylist;
        }
    }
    public VeiculoCombustivel getUltimoVeiculoCombustivelByIdVeiculo(int idVeiculo){
        arraylist = getArrayListByIDVeiculo(idVeiculo);
        VeiculoCombustivel vc = new VeiculoCombustivel();
        if(arraylist.size()>0){
            vc = arraylist.get(arraylist.size() - 1);
        }
        return vc;
    }
    private GraficoItemC getCalcularGraficoC(VeiculoCombustivel atual, VeiculoCombustivel proximo, String id){
        GraficoItemC grafC = new GraficoItemC();
        try{
            int km = proximo.getKm() - atual.getKm();
            km = (km<=0)? 1 : km;
            float litro = atual.getLitros();
            litro = (litro<=0)? 1 : litro;
            float kmXlitro =  km / litro;
            grafC.setNome(id);
            grafC.setCat1("Km percorrido");
            grafC.setValor(km);
            grafC.setCat2("Litragem");
            grafC.setValor2(atual.getLitros());
            grafC.setCat3("Km/Litro");
            grafC.setValor3(kmXlitro);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.getCalcularGraficoC");
        }finally{
            return grafC;
        }
    }
    private GraficoItem getCalcularGraficoCustoKm(VeiculoCombustivel atual, VeiculoCombustivel proximo, String id){
        GraficoItem graficoItem = new GraficoItem();
        try{
            int km = proximo.getKm() - atual.getKm();
            km = (km<=0)? 1 : km;
            float litro = atual.getLitros();
            litro = (litro<=0)? 1 : litro;
            float kmXlitro =  km / litro;
            graficoItem.setNome(id);
            graficoItem.setValor(kmXlitro);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.getCalcularGraficoCustoKm");
        }finally{
            return graficoItem;
        }
    }
    
    
    public ArrayList<GraficoItemC> getArrayGraficoC(int idVeiculo, int nRegistros){
        int limite = 0;
        
        ArrayList<GraficoItemC> arrayGrafico = new ArrayList<GraficoItemC>();
        VeiculoCombustivel proximo = new VeiculoCombustivel();
        try{
            arraylist = getArrayListByIDVeiculo(idVeiculo);
            if(arraylist.size()<=0){
                GraficoItemC grafC = new GraficoItemC();
                grafC.setCat1("KM Percorrido");
                grafC.setValor(0);
                grafC.setCat2("Litragem");
                grafC.setValor2(0);
                grafC.setCat3("KM/Litro");
                grafC.setValor3(0);
                arrayGrafico.add(grafC);
                return arrayGrafico;
            }
            
            limite = arraylist.size()- nRegistros - 1;
            limite = limite>=0? limite : 0;
            
            VeiculoCombustivel atual;
            proximo.setKm(arraylist.get(arraylist.size()-1).getKm());
            
            for(int i = limite ; i < arraylist.size()-1; i++){ //-1
                //for(int i = arraylist.size()-2; i>=limite; i--){ //-1
                atual = arraylist.get(i);
                arrayGrafico.add(getCalcularGraficoC(atual, proximo, (i+1)+""));
                proximo = atual;
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoOleoControl.getArrayGraficoC");
        }
        return arrayGrafico;
    }
    public ArrayList<GraficoItem> getArrayGraficoLinha(int idVeiculo, int nRegistros){
        int limite = 0;
        
        ArrayList<GraficoItem> arrayGrafico = new ArrayList<GraficoItem>();
        try{
            arraylist = getArrayListByIDVeiculo(idVeiculo);
            if(arraylist.size()<=0){
                GraficoItem graficoItem = new GraficoItem();
                graficoItem.setNome("KM/Litro");
                graficoItem.setValor(0);
                arrayGrafico.add(graficoItem);
                return arrayGrafico;
            }
            
            limite = arraylist.size()- nRegistros - 1;
            limite = limite>=0? limite : 0;
            
            VeiculoCombustivel atual, proximo;
            for(int i = limite ; i < arraylist.size()-1; i++){ //-1
                atual = arraylist.get(i);
                proximo = arraylist.get(i+1);
                arrayGrafico.add(getCalcularGraficoCustoKm(atual, proximo, (i+1)+""));
             }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoOleoControl.getArrayGraficoLinha");
        }
        return arrayGrafico;
    }
    
    public float mediaConsumoUltByIdVeiculo(int idVeiculo){
        
        float media = 0, custo =0;
 
        try{
        
            int kmAtual = vkm.getUltimoKmByIDVeiculo(idVeiculo);
            int kmAnterior = 0;
                arraylist = getArrayListByIDVeiculo(idVeiculo);

            int i=arraylist.size()-1;
                kmAnterior = arraylist.get(i).getKm();
                media = mediaConsumo(kmAtual,kmAnterior , arraylist.get(i).getLitros());

            //custo = vcc.CustoKm(arraylist.get(i).getValor(), arraylist.get(i).getLitros(), media);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.getArrayListByIDVeiculo");
        }finally{
            return media;
        }
        
    }
    public float mediaConsumoGeralByIdVeiculo(int idVeiculo, int numRegistros){
        float media = 0, custo =0, mediaGeral =0, custoGeral = 0;
        arraylist = getArrayListByIDVeiculo(idVeiculo);
            try{    
                int kmAtual = vkm.getUltimoKmByIDVeiculo(idVeiculo);
                int kmAnterior = 0, iteracoes = 0;
                int limite = arraylist.size() - numRegistros;
                limite = (limite<=0) ? 0 : limite;
                
                for(int i=arraylist.size()-2; i>= limite; i--){
                    kmAtual = arraylist.get(i+1).getKm();
                    kmAnterior = arraylist.get(i).getKm();
                    media = mediaConsumo(kmAtual,kmAnterior , arraylist.get(i).getLitros());
                    kmAtual = kmAnterior;
                    custo = CustoKm(arraylist.get(i).getValor(), arraylist.get(i).getLitros(), media);
                    mediaGeral+=media;
                    custoGeral+=custo;
                    iteracoes++;
                }
                iteracoes = (iteracoes == 0) ? 1 : iteracoes;
                custo = custoGeral/iteracoes;
                media = mediaGeral/iteracoes;
            
            }catch(Exception e){
                aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.custoKmGeralByIdVeiculo");
            }finally{
                return media;
            }
    }
    public float custoKmUltByIdVeiculo(int idVeiculo){
        float media = 0, custo =0;
        try{
        
            int kmAtual = vkm.getUltimoKmByIDVeiculo(idVeiculo);
            int kmAnterior = 0;
                arraylist = getArrayListByIDVeiculo(idVeiculo);

            int i=arraylist.size()-1;
                kmAnterior = arraylist.get(i).getKm();
                media = mediaConsumo(kmAtual,kmAnterior , arraylist.get(i).getLitros());
                custo = CustoKm(arraylist.get(i).getValor(), arraylist.get(i).getLitros(), media);
        }catch(Exception e){
           aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.custoKmUltByIdVeiculo"); 
        }finally{
            return custo;
        }
    }
    
    public float custoKmGeralByIdVeiculo(int idVeiculo, int numRegistros){
        float media = 0, custo =0, mediaGeral =0, custoGeral = 0;
        arraylist = getArrayListByIDVeiculo(idVeiculo);
            try{    
                
                int kmAtual = vkm.getUltimoKmByIDVeiculo(idVeiculo);
                int kmAnterior = 0, iteracoes = 0;
                int limite = arraylist.size() - numRegistros;
                limite = (limite<=0) ? 0 : limite;
                
                for(int i=arraylist.size()-2; i>= limite; i--){
                    kmAtual = arraylist.get(i+1).getKm();
                    kmAnterior = arraylist.get(i).getKm();
                    media = mediaConsumo(kmAtual,kmAnterior , arraylist.get(i).getLitros());
                    kmAtual = kmAnterior;
                    custo = CustoKm(arraylist.get(i).getValor(), arraylist.get(i).getLitros(), media);
                    mediaGeral+=media;
                    custoGeral+=custo;
                    iteracoes++;
                }
                iteracoes = (iteracoes == 0) ? 1 : iteracoes;
                custo = custoGeral/iteracoes;
                media = mediaGeral/iteracoes;
            
            }catch(Exception e){
                aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.custoKMgeralByIdVeiculo");
            }finally{
                return custo;
            }
    }
    public float mediaConsumo(int km_atual, int km_anterior, float litros_anterior){
        //((J23-J22)/L23)) ***Km - Km Anterior / Litros
        float media = 0;
        try{
            media = (km_atual - km_anterior)/litros_anterior;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.mediaConsumo");
        }
        finally{
            return media;
        }
        
    }
    public float CustoKm(float valor, float litragem, float mediaConsumo){
        //((N24/L24)/P24))//valor (total / litros) / media consumo
        float custo = 0;
        try{
            mediaConsumo = (mediaConsumo<=0) ? 1 : mediaConsumo;
            custo = (valor/litragem)/mediaConsumo;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.CustoKm");
        }
        finally{
            return custo;
        }
    }
    
    private boolean validacaoCampos(String id, int idVeiculo, int idUsuario,String km, String posto, String combustivel, 
                                                        String litros, String valor, String data, String motorista){
        if(km.equals("") || posto.equals("")||combustivel.equals("")|| litros.equals("")||valor.equals("")||data.equals("")||motorista.equals("")){
            aux.showMessageWarning("Valor inválido. Verifique os campos!", "Validação de valores");
            return false;
        }
        return true;
    }
    public boolean addVeiculoCombustivel(String id, int idVeiculo, int idUsuario,String km, String posto, String combustivel, 
                                                        String litros, String valor, String data, String motorista){
        try{
            VeiculoCombustivel vc = new VeiculoCombustivel();
            if(!validacaoCampos(id, idVeiculo, idUsuario, km.trim(), posto.trim(), combustivel.trim(), litros.trim(), valor.trim(), data.trim(), motorista.trim())){
                return false;
            }
            vc.setIdVeiculo(idVeiculo);
            vc.setIdUsuario(idUsuario);
            vc.setKm(Integer.parseInt(km));
            if(vc.getKm()==0){
                throw new ValorInvalidoException();
            }
            vc.setPosto(posto);
            vc.setCombustivel(combustivel);
            vc.setLitros(Float.parseFloat(litros));
            vc.setValor(Float.parseFloat(valor));
            vc.setDataMilis(aux.dataStringLong(data));
            vc.setMotorista(motorista);
            if((vc.getKm()<getUltimoVeiculoCombustivelByIdVeiculo(idVeiculo).getKm()) && id.equals("0")){
                aux.showMessageInformacao("Já existe registro de abastecimento com KM do veiculo maior que o valor informado!", "Validação de valor");
                return false;
            }
            
            if(vc.getKm()>vkm.getUltimoKmByIDVeiculo(idVeiculo)){
                if(JOptionPane.showConfirmDialog(null, "O valor atual do KM do veiculo é menor que o valor no momento do abastecimento.\n Deseja atualizar o KM atual do veiculo para '"+vc.getKm()+"' ?", "Validação de Campos", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    if(!vkm.addVeiculoKM(idVeiculo, idUsuario, data, km)){
                        return false;
                    }
                }else{
                    return false;
                }
            }
            
            
            if(!id.equals("")){
                vc.setId(Integer.parseInt(id));
            }
            if(vc.getId()==0){
                vcDAO.Inserir(vc);
            }else{
                vcDAO.Alterar(vc);
            }
            return true;
            
        }catch (NumberFormatException nfe){
            aux.showMessageWarning("Verifique o valor informado!", "Valor inválido");
            aux.RegistrarLog(nfe.getMessage(), "VeiculoCombustivelControl.addVeiculoCombustivel");
            return false;
        }
        catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.addVeiculoCombustivel");
            aux.showMessageWarning("Verifique os campos informados!", "Valor inválido");
            return false;
        }
        
      }

    public boolean excluirById(String txtId) {
        try{
            String confirmacao = aux.InputText("Informe o ID do item para confirmar a exclusão: ");
            if(confirmacao.trim().equalsIgnoreCase(txtId.trim())){
                int id = Integer.parseInt(txtId);
                if(id==0){
                    return false;
                }
                return vcDAO.excluirById(id);
            }else{
                return false;
            }
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoCombustivelControl.excluirById");
            return false;
        }
    }
    
}
