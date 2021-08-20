package control;

import data.PneuReformaDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.PneuReforma;

public class PneuReformaControl {
    Auxiliar aux;
    PneuReformaDAO prDao;
    ArrayList<PneuReforma> arrayList;
    PneuReforma pr;
    public static final String ORIGEM = "PneuReformaControl.";
    
    
    public PneuReformaControl(){
        aux = new Auxiliar();
        prDao = new PneuReformaDAO();
        arrayList = new ArrayList<PneuReforma>();
        pr = new PneuReforma();
    }

    
    public ArrayList<PneuReforma> getArrayListReformasByIdPneu(int idpneu){
        try{
            arrayList = prDao.PesquisarByIDPneu(idpneu);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuReformaControl.getArrayListReformaByIdPneu");
            arrayList = new ArrayList<PneuReforma>();
        }finally{
            return arrayList;
        }
    }
    public float getCustoReformasByIdPneu(int idpneu){
        float custo = 0;
        try{
            arrayList = getArrayListReformasByIdPneu(idpneu);
            for(PneuReforma pr: arrayList){
                custo+=pr.getValor();
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuReformaControl.getCustoReformaByIdPneu");
            custo = 0;
        }finally{
            return custo;
        }
    }
    public int getQtdeReformasByIdPneu(int idpneu){
         int qtde = 0;
        try{
            arrayList = getArrayListReformasByIdPneu(idpneu);
            qtde = arrayList.size();
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuReformaControl.getQtdeReformasByIdPneu");
            qtde = 0;
        }finally{
            return qtde;
        }
    }
    /*
    try{
            
        }catch(Exception e){
            
        }finally{
            
        }
    */

    public boolean validarCampos(String _idreforma, String _custo, String _data, String _km, String _oficina, String _info){
        try{
            int id = Integer.parseInt(_idreforma);
            float custo = Float.parseFloat(_custo);
            long data = aux.dataStringLong(_data);
            int km = Integer.parseInt(_km);
            if(id>=0 && custo>0 && km>0 && !_oficina.equalsIgnoreCase("")){
                pr = new PneuReforma();
                pr.setId(id);
                pr.setDatamilis(data);
                pr.setOficina(_oficina);
                pr.setInfo(_info);
                pr.setValor(custo);
                pr.setKm(km);
                
                return true;
            }
            aux.showMessageValoresInvalidos();
            return false;
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), ORIGEM+"validarCampos");
            aux.showMessageValoresInvalidos();
            return false;
        }
        
    }
    
    public boolean salvar(String idreforma, int idPneu, String custo, String data, String km, String oficina, String info) {
        boolean retorno = false;
        try{
            if(validarCampos(idreforma, custo, data, km, oficina, info)){
                pr.setIdPneu(idPneu);
                if(pr.getId()==0){
                    retorno = prDao.Inserir(pr);
                }else{
                    retorno = prDao.Alterar(pr);
                }
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), ORIGEM+"salvar");
            retorno = false;
        }finally{
            aux.showMessageConfirmacao(retorno, "Reforma de Pneu");
            return retorno;
        }
        
    }

    public boolean excluir(String _id) {
        try {
            int id = Integer.parseInt(_id);
            if(id>0){
                prDao.Excluir(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), ORIGEM+"excluir");
            return false;
        }
    }
}
