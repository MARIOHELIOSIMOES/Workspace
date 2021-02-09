
package control;

import Exception.ValorInvalidoException;
import data.DocumentoDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.Documento;

public class DocumentoControl {

    ArrayList<Documento> arrayListDocs;
    DocumentoDAO docDao;
    Auxiliar aux;
    int id_veiculo =0;
    
    //Construtor da Classe deve receber o ID do veículo para conseguir buscar os registros
    public DocumentoControl(int idVeiculo){
        arrayListDocs = new ArrayList<Documento>();
        docDao = new DocumentoDAO();
        aux = new Auxiliar();
        id_veiculo = idVeiculo;
    }
    
    //método para excluir um documento
    public boolean excluirDocumento(Documento documento){
        if(docDao.Excluir(documento)){
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList<Documento> getArrayListDocumentosVeiculo(){
        arrayListDocs = docDao.PesquisarTodosByIDVeiculo(id_veiculo);
        return this.arrayListDocs;
    }
    public void add(Documento documento){
        
    }
    //Método para validar e atualizar o registro na base de dados
    public int add(String id, String nRegistro, String Validade, String Info){
        int idDocumento=0;
        Documento documento = new Documento();
        try{
            documento.setId(Integer.parseInt(id));
            
            if(nRegistro.equals("")){
                throw new ValorInvalidoException("Nome de Registro", nRegistro);
            }else{
                documento.setN_registro(nRegistro);
            }
            
            documento.setValidade_milis(new Auxiliar().dataStringLong(Validade));
            documento.setInfo(Info);
            if(documento.getId()==0){       //'novo'
                if(docDao.Inserir(documento, id_veiculo)){
                    idDocumento = docDao.UltimoDocumento().getId();
                }
            }else{          //'alterar'
                docDao.Alterar(documento);
                idDocumento = documento.getId();
            }
            
        }catch(Exception e){
           aux.showMessageWarning("Verifique os campos", "Falha ao salvar");
        }finally{
            if(idDocumento!=0){
                aux.showMessageInformacao("Salvo com sucesso", "Registro de Documento");
            }
            return idDocumento;
        }
        
    }
}
