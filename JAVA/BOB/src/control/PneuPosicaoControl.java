package control;

import data.PneuPosicaoDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.Pneu;
import model.PneuDesgaste;
import model.PneuPosicao;

public class PneuPosicaoControl {
    private ArrayList<PneuPosicao> arraylist;
    private Auxiliar aux;
    private PneuPosicaoDAO ppDao;
    
    
    public PneuPosicaoControl(){
        aux = new Auxiliar();
        ppDao = new PneuPosicaoDAO();
        arraylist = new ArrayList<PneuPosicao>();
    }
    
    public ArrayList<PneuPosicao> getArrayListVeiculoPneuByIdVeiculo(int idVeiculo){
        try{
            arraylist = ppDao.PesquisarByIdVeiculo(idVeiculo);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneuControl.getArrayListVeiculoPneuByIdVeiculo");
            arraylist = new ArrayList<PneuPosicao>();
        }finally{
            return arraylist;
        }
    }
    public int[][] getIdPneusByIdVeiculo(int idveiculo){
        try{
            arraylist = getArrayListVeiculoPneuByIdVeiculo(idveiculo);
            int[][] ids = new int[arraylist.size()][2];
            for(int i = 0; i< arraylist.size(); i++){
                ids[i][0]=arraylist.get(i).getIdPneu();
                ids[i][1]=arraylist.get(i).getPosicao();
            }
            return ids;
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneuControl.getIdPneusByIdVeiculo");
            return new int[][]{};
        }
    }
    
    public PneuPosicao getVeiculoPneuByIdPneu(int idPneu){
        PneuPosicao vp = new PneuPosicao();
        try{
            vp = ppDao.PesquisarByIDPneu(idPneu);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneuControl.getVeiculoPneuByidPneu");
        }
        return vp;
    }
    public PneuPosicao getVeiculoPneuByIdVeiculoAndPosicao(int idVeiculo, int posicao){
        PneuPosicao vp = new PneuPosicao();
        try{
            vp = ppDao.PesquisarByIDVeiculoAndByPosicao(idVeiculo, posicao);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneuControl.getVeiculoPneuByidVeiculoAndPosicao");
        }
        return vp;
    }
    public int getIdPneuByIdVeiculoAndPosicao(int idveiculo, int posicao){
        return getVeiculoPneuByIdVeiculoAndPosicao(idveiculo, posicao).getIdPneu();
    }
    
    public ArrayList<PneuPosicao> getArrayListTodos() {
        try{
            arraylist = ppDao.PesquisarTodos();
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneuControl.getArrayListTodos");
            arraylist = new ArrayList<PneuPosicao>();
        }finally{
            return arraylist;
        }
    }

    public boolean Salvar(int idVeiculo,Pneu pneuAntigo, Pneu pneuNovo, int posicao, int operacao, float valor){
        try{
            
            PneuControl pc = new PneuControl();
            if(operacao == Pneu.VENDIDO){
                valor *= -1;
            }
            
            //PneuPosicao antigo que será excluído
            if(pneuAntigo.getId()!=0){
                PneuPosicao ppExcluir = ppDao.PesquisarByIDPneu(pneuAntigo.getId());
                if (ppDao.Excluir(ppExcluir)) {
                    //Se a exclusão ocorrer deverá atualizar o status do pneu com o destino do pneu antigo
                    pc.atualizarStatusPneuByIdPneu(pneuAntigo.getId(), operacao);
                }

                //Inserir PneuDesgaste
                PneuDesgaste pneuDesgaste = new PneuDesgaste();
                pneuDesgaste.setDatamilis(aux.getDataMilisAtual());
                pneuDesgaste.setIdPneu(pneuAntigo.getId());
                pneuDesgaste.setIdVeiculo(idVeiculo);
                pneuDesgaste.setKmEntrada(ppExcluir.getKm());
                pneuDesgaste.setKmSaida(pneuAntigo.getKmTotal());
                pneuDesgaste.setValor(valor);
                pneuDesgaste.setPosicao(posicao);

                PneuDesgasteControl pdc = new PneuDesgasteControl();
                pdc.inserir(pneuDesgaste);

            }
                        
             //Nova PneuPosicao com os dados do pneu novo 
            if(pneuNovo.getId()!=0){
                PneuPosicao ppNovo = new PneuPosicao();
                    ppNovo.setDatamilis(aux.getDataMilisAtual());
                    ppNovo.setIdPneu(pneuNovo.getId());
                    ppNovo.setIdVeiculo(idVeiculo);
                    ppNovo.setKm(pneuNovo.getKmTotal());
                    ppNovo.setPosicao(posicao);

                if(ppDao.Inserir(ppNovo)){
                    //Se conseguir incluir novo PneuPosicao, deverá atualizar o status do pneu para rodando
                    pc.atualizarStatusPneuByIdPneu(pneuNovo.getId(), Pneu.RODANDO);
                }
            }
            return true;
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneuControl.Salvar");
            return false;
        }
    }
}
