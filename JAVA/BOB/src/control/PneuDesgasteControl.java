package control;

import data.PneuDesgasteDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.PneuDesgaste;

public class PneuDesgasteControl {

    Auxiliar aux;
    ArrayList<PneuDesgaste> arrayList;
    PneuDesgasteDAO pdDao;
    public PneuDesgasteControl(){
        aux = new Auxiliar();
        pdDao = new PneuDesgasteDAO();
    }
    public ArrayList<PneuDesgaste> getArrayListDesgasteByIdPneu(int idpneu){
        try{
            arrayList = pdDao.PesquisarByIdPneu(idpneu);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteControl.getArrayListDesgasteIdPneu");
        }finally{
            return arrayList;
        }
    }
    public int getQtdeMovimentacaoByIdPneu(int idpneu){
        int qtde = 0;
        try {
            arrayList = getArrayListDesgasteByIdPneu(idpneu);
            qtde = arrayList.size();
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteControl.getQtdeMovimentacaoByIdPneu");
        }finally{
            return qtde;
        }
    }
    public float getCustoDesgasteByIdPneu(int idpneu){
        float custo = 0;
        try{
            arrayList = getArrayListDesgasteByIdPneu(idpneu);
            for(PneuDesgaste pd : arrayList){
                if(pd.getValor()<0){
                    aux.RegistrarLog("Tratar valor negativo", "PneuDesgasteControl.getCustoDesgasteByIdPneu");
                    throw new Exception("Tratar valor negativo");
                }else{
                    custo+=pd.getValor();
                }
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteControl.getCustoDesgasteByIdPneu");
            custo = 0;
        }finally{
            return custo;
        }
    }
    public float getCustoDesgasteByIdVeiculoByPosicao(int idVeiculo, int posicao){
        float custoMedio = 0;
        arrayList = pdDao.pesquisarByIdVeiculoAndPosicao(idVeiculo, posicao);
        for(PneuDesgaste pd : arrayList){
            int kmPercorrido = pd.getKmPercorrido();
            kmPercorrido = kmPercorrido==0 ? 1 : kmPercorrido;
            float custo = pd.getValor() / kmPercorrido;
            custoMedio +=custo;
        }
        custoMedio = arrayList.size()>0 ? custoMedio / arrayList.size(): custoMedio;
        return custoMedio;
    }
    public void inserir(PneuDesgaste pneuDesgaste) {
        try{
            pdDao.Inserir(pneuDesgaste);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteControl.inserir");
        }
    }

    public ArrayList<PneuDesgaste> getArrayListDesgasteByIdVeiculoAndPosicao(int idVeiculo, int posicao) {
        try {
            if(idVeiculo==0){
                return new ArrayList<PneuDesgaste>();
            }
            arrayList = pdDao.pesquisarByIdVeiculoAndPosicao(idVeiculo, posicao);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteControl.getArrayListDesgasteByIdVeiculoAndPosicao");
            arrayList = new ArrayList<PneuDesgaste>();
        }
        return arrayList;
        
    }
    
    
}
