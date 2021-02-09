package control;

import data.ItemDAO;
import data.PneuDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.Item;
import model.Pneu;

public class PneuControl {
    
    Pneu pneu;
    Item item;
    ArrayList<Pneu> arraylist;
    Auxiliar aux;
    PneuDAO pdao;
    ItemControl ictrol;
    public PneuControl(){
        arraylist = new ArrayList<Pneu>();
        pneu = new Pneu();
        aux = new Auxiliar();
        pdao = new PneuDAO();
        ictrol = new ItemControl();
    }
    
    //Método deverá buscar o item pelo ID
    public Pneu getItemByID(int id){
        try{
            pneu = pdao.PesquisarById(id);
            //item = idao.PesquisarById(pneu.getIdItem());
            item = ictrol.getItemByID(pneu.getIdItem());
            pneu.setItem(item);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuControl.getItemByID");
        }
        return pneu;
    }

    public ArrayList<Pneu> getArrayListTodosPneus(){
        try{
            arraylist = pdao.PesquisarTodos();
            for(int i = 0; i< arraylist.size(); i++){
                arraylist.get(i).setItem(ictrol.getItemByID(arraylist.get(i).getIdItem()));
            }
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuControl.getTodosPneus");
        }
        return arraylist;
    }
    
    public ArrayList<Pneu> getArrayListByIds(int[] ids){
         try{
            arraylist = pdao.PesquisarTodosByIds(ids);
            for(int i = 0; i< arraylist.size(); i++){
                arraylist.get(i).setItem(ictrol.getItemByID(arraylist.get(i).getIdItem()));
            }
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuControl.getArrayListByIds");
        }
        return arraylist;
    }
    
    public boolean salvar(String id, String marca, String modelo, String valor, String info, int iditem, String fogo, String km, String kmtracao){
        boolean retorno = false;
        try{
            if(validarCampos(id, marca, modelo, valor, info, iditem, fogo, km, kmtracao)){
                if(!ictrol.salvar(pneu.getItem())){
                    aux.RegistrarLog("Falha ao salvar Item", "PneuControl.salvar");
                    return false;
                }
                
                pneu.setIdItem((iditem==0) ? ictrol.getUltimoItem().getId():  iditem);
                
                if(id.equalsIgnoreCase("0")){
                    retorno = pdao.Inserir(pneu);
                    aux.showMessageInformacao(  (retorno ?"Salvo com sucesso!": "Falha ao salvar!") , "Novo Pneu");
                }else{
                    retorno = pdao.Alterar(pneu);
                    aux.showMessageInformacao(  (retorno ?"Salvo com sucesso!": "Falha ao salvar!") , "Alterar Pneu");
                }
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuControl.salvar");
            retorno = false;
        }
        return retorno;
    }
    
    private boolean validarCampos(String id, String marca, String modelo, String valor, String info, int iditem, String fogo, String km, String kmtracao){
        /*
            Validar os campos e preencher na variável 'pneu' Local
        */
        try{
            if(marca.trim().equals("")||fogo.trim().equals("")||fogo.trim().equals("0")){
                aux.showMessageInformacao("Existem valores inválidos, verifique os campos!", "Validação de Campos");
                return false;
            }
            pneu = new Pneu();
            pneu.setId(Integer.parseInt(id.trim()));
            pneu.setMarca(marca.trim());
            pneu.setModelo(modelo.trim());
            pneu.setValor(Float.parseFloat(valor.trim()));
            pneu.setFogo(Integer.parseInt(fogo.trim()));
            pneu.setKm(Integer.parseInt(km.trim()));
            pneu.setKmTracao(Integer.parseInt(kmtracao.trim()));
            pneu.setIdItem(iditem);
            
            
        }catch(Exception e){
            aux.RegistrarLog("Validação dos campos não implementada!!!!", "PneuControl.validarCampos");
            aux.showMessageInformacao("Existem valores inválidos, verifique os campos!", "Validação de Campos");
                return false;
        }
        return true;
    }
    
    
}
