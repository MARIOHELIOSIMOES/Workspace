package control;

import data.ItemDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.Item;

public class ItemControl {
    
    ItemDAO itemDao;
    Auxiliar aux;
    public ItemControl(){
        itemDao = new ItemDAO();
        aux = new Auxiliar();
    }
    public ItemControl(Item item){
        itemDao = new ItemDAO();
        aux = new Auxiliar();
    }
    
    //Método deverá buscar o item pelo ID
    public Item getItemByID(int id){
        return itemDao.PesquisarById(id);
    }
    public ArrayList<Item> getArrayListItemByTipo(int[] tipos){
        ArrayList<Item> arraylist = new ArrayList<Item>();
        try{
            boolean todos=false;
            String tiposString = " ";
            for(int num: tipos){
                if(num==Item.TIPO_TODOS){
                    todos = true;
                }
                tiposString +=num+", ";
            }
            if(tipos.length>0){
                tiposString+=tipos[0];
                arraylist = (todos)? itemDao.PesquisarTodos() : itemDao.PesquisarTodosByTipo(tiposString);
            }    
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "ItemControl.getArrayListItemByTipo");
        }finally{
            return arraylist;
        }
        
    }
    public ArrayList<Item> getArrayListIDS(int[] ids){
        ArrayList<Item> arraylist = new ArrayList<Item>();
        try{
            String idsString = " ";
            for(int num: ids){
                idsString +=num+", ";
            }
            if(ids.length>0){
                idsString+=ids[0];
                arraylist = itemDao.PesquisarByIDs(idsString);
            }    
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "ItemControl.getArrayListIDS");
        }finally{
            return arraylist;
        }
        
    }
    //Método buscará todos os itens e retornará a lista
     public ArrayList<Item> getArrayListTodosItem(){
        return itemDao.PesquisarTodos();
    }
    public boolean salvar(String id, int tipo, String marca, String modelo, String valor, String info){
        Item item = new Item();
        boolean retorno = false;
        try{
            item.setId(Integer.parseInt(id));
            item.setTipo(tipo);
            item.setMarca(marca);
            item.setModelo(modelo);
            item.setValor(Float.parseFloat(valor));
            item.setInfo(info);
            if(item.getTipo()==Item.TIPO_TODOS){
                aux.showMessageWarning("Escolha um Tipo", "Valor Inválido");
                return false;
            }
            if(item.getId()==0){
                itemDao.Inserir(item);
                aux.showMessageInformacao("Salvo com sucesso!", "Inserir");
            }else{
                itemDao.Alterar(item);
                aux.showMessageInformacao("Alterado com sucesso!", "Alterar");
            }
            retorno = true;
        }catch(Exception e){
            aux.showMessageWarning("Verifique os campos!", "Valor Inválido");
            
        }finally{
            return retorno;
        }
    }
    public boolean salvar(Item item){
        boolean retorno = false;
        try{
            if(item.getId()==0){
                itemDao.Inserir(item);
                
            }else{
                itemDao.Alterar(item);
                
            }
            retorno = true;
        }catch(Exception e){
            aux.showMessageWarning("Verifique os campos!", "Valor Inválido");
        }finally{
            return retorno;
        }
    }
    public Item getUltimoItem(){
        Item item = new Item();
        ArrayList<Item> arrayList = getArrayListTodosItem();
        if(arrayList.size()>0){
            item = arrayList.get(arrayList.size()-1);
        }
        return item;
    }
    
}
