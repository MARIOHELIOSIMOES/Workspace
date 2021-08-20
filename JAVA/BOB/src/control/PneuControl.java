package control;

import data.PneuDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.Item;
import model.Pneu;
import model.PneuPosicao;

public class PneuControl {

    Pneu pneu;
    Item item;
    ArrayList<Pneu> arraylist;
    Auxiliar aux;
    PneuDAO pdao;
    ItemControl ictrol;
    PneuPosicaoControl vpc;
    VeiculoKMControl vkm;
    VeiculoConfiguracaoControl vcc;
    PneuReformaControl prc;
    PneuDesgasteControl pdc;
    

    public PneuControl() {
        arraylist = new ArrayList<Pneu>();
        pneu = new Pneu();
        aux = new Auxiliar();
        pdao = new PneuDAO();
        ictrol = new ItemControl();
        vpc = new PneuPosicaoControl();
        // vkm = new VeiculoKMControl();
        vcc = new VeiculoConfiguracaoControl();
        prc = new PneuReformaControl();
        pdc = new PneuDesgasteControl();
    }

    // deverá buscar o item pelo ID
    public Pneu getItemByID(int id) {
        try {
            pneu = pdao.PesquisarById(id);
            //item = idao.PesquisarById(pneu.getIdItem());
            item = ictrol.getItemByID(pneu.getIdItem());
            pneu.setItem(item);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.getItemByID");
        }
        return pneu;
    }

    public ArrayList<Pneu> getArrayListTodosPneus() {
        try {
            arraylist = pdao.PesquisarTodos(); //inativado temporariamente
            for (int i = 0; i < arraylist.size(); i++) {
                arraylist.get(i).setItem(ictrol.getItemByID(arraylist.get(i).getIdItem()));
            }

        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.getTodosPneus");
        }
        return arraylist;
    }

    public ArrayList<Pneu> getArrayListByIds(int[] ids) {
        try {
            arraylist = pdao.PesquisarTodosByIds(ids);
            for (int i = 0; i < arraylist.size(); i++) {
                arraylist.get(i).setItem(ictrol.getItemByID(arraylist.get(i).getIdItem()));
            }

        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.getArrayListByIds");
        }
        return arraylist;
    }

    public boolean atualizarKMsByIdVeiculo(int idveiculo) {
        try {
            VeiculoKMControl vkm = new VeiculoKMControl();
            int[][] ids = vpc.getIdPneusByIdVeiculo(idveiculo); // Lista de (IdPneu , Posicao)
            int kmPercorrido = vkm.getKmUltPercorridoByIdVeiculo(idveiculo); // Km percorrido ultima atualização de km
            int[] posicaoTracao = vcc.getPosicoesTracaoByIdVeiculo(idveiculo); //Posicoes de rodas com tração
            for (int i = 0; i < ids.length; i++) {
                atualizarKmByIdPneu(ids[i][0], kmPercorrido, contemId(posicaoTracao, ids[i][1]));
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean atualizarStatusPneuByIdPneu(int idPneu, int status){
        return pdao.alterarStatusByIdPneu(idPneu, status);
    }
    private boolean contemId(int ids[], int alvo) {
        for (int i : ids) {
            if (i == alvo) {
                return true;
            }
        }
        return false;
    }

    private void atualizarKmByIdPneu(int idPneu, int km, boolean tracao) {
        try {
            pdao.AtualizarKMsByIdPneu(idPneu, km, tracao);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.atualizarKmByIdPneu");
        }
    }

    public boolean salvar(String id, String marca, String modelo, String valor, String info, int iditem, String fogo, String km, String kmtracao, int status) {
        boolean retorno = false;
        try {
            if (validarCampos(id, marca, modelo, valor, info, iditem, fogo, km, kmtracao)) {
                if (!ictrol.salvar(pneu.getItem())) {
                    aux.RegistrarLog("Falha ao salvar Item", "PneuControl.salvar");
                    return false;
                }

                //  pneu.setIdItem((iditem==0) ? ictrol.getUltimoItem().getId():  iditem);
                pneu.setIdItem(ictrol.getIdItemByMarcaModelo(pneu.getMarca(), pneu.getModelo()));
                pneu.setStatus(status);
                if (id.equalsIgnoreCase("0")) {
                    retorno = pdao.Inserir(pneu);
                    aux.showMessageInformacao((retorno ? "Salvo com sucesso!" : "Falha ao salvar!"), "Novo Pneu");
                } else {
                    retorno = pdao.Alterar(pneu);
                    aux.showMessageInformacao((retorno ? "Salvo com sucesso!" : "Falha ao salvar!"), "Alterar Pneu");
                }
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.salvar");
            retorno = false;
        }
        return retorno;
    }

    private boolean validarCampos(String id, String marca, String modelo, String valor, String info, int iditem, String fogo, String km, String kmtracao) {
        /*
            Validar os campos e preencher na variável 'pneu' Local
         */
        try {
            if (marca.trim().equals("") || fogo.trim().equals("") || fogo.trim().equals("0")) {
                aux.showMessageInformacao("Existem valores inválidos, verifique os campos!", "Validação de Campos");
                return false;
            }
            pneu = new Pneu();
            pneu.setId(Integer.parseInt(id.trim()));
            pneu.setMarca(marca.trim());
            pneu.setModelo(modelo.trim());
            pneu.setValor(Float.parseFloat(valor.trim().replace(",", ".")));
            pneu.setFogo(Integer.parseInt(fogo.trim()));
            pneu.setKm(Integer.parseInt(km.trim()));
            pneu.setKmTracao(Integer.parseInt(kmtracao.trim()));
            pneu.setIdItem(iditem);
            pneu.setInfo(info);

        } catch (Exception e) {
            aux.RegistrarLog("Validação dos campos não implementada!!!!", "PneuControl.validarCampos");
            aux.showMessageInformacao("Existem valores inválidos, verifique os campos!", "Validação de Campos");
            return false;
        }
        return true;
    }

    public ArrayList<Pneu> getArrayListPneusEstoque() {
        try {
            arraylist = pdao.PesquisarTodosByStatus(Pneu.ESTOQUE);
            for (int i = 0; i < arraylist.size(); i++) {
                arraylist.get(i).setItem(ictrol.getItemByID(arraylist.get(i).getIdItem()));
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.getArryListPneusLivres");
            arraylist = new ArrayList<Pneu>();
        } finally {
            return arraylist;
        }

    }

    public ArrayList<Pneu> getArrayListPneusRodando() {
        try {
            arraylist = pdao.PesquisarTodosByStatus(Pneu.RODANDO);
            for (int i = 0; i < arraylist.size(); i++) {
                arraylist.get(i).setItem(ictrol.getItemByID(arraylist.get(i).getIdItem()));
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.getArryListPneusLivres");
            arraylist = new ArrayList<Pneu>();
        } finally {
            return arraylist;
        }

    }

    public int getQtdeReformasByIdPneu(int id) {
        int qtde = 0;
        try {
            qtde = prc.getQtdeReformasByIdPneu(id);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.getQtdeReformasByIdPneu");
        } finally {
            return qtde;
        }
    }

    public int getQtdeMovimentacaoByIdPneu(int id) {
        int qtde = 0;
        try {
            qtde = pdc.getQtdeMovimentacaoByIdPneu(id);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.getQtdeReformasByIdPneu");
        } finally {
            return qtde;
        }
    }

    public float getValorAtualByIdPneu(int id) {
        float valor = 0;

        try {
            float custoDesgaste = pdc.getCustoDesgasteByIdPneu(id);
            float custoReforma = prc.getCustoReformasByIdPneu(id);
            Pneu pneu = getItemByID(id);

            valor = (pneu.getValor() + custoReforma) - custoDesgaste;

        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "PneuControl.getValorAtualByIdPneu");
        }

        return valor;
    }

    public String getPlacaRodandoByIdPneu(int idPneu) {
       VeiculoControl vc = new VeiculoControl();
       String placa = vc.getPlacaByIdVeiculo(vpc.getVeiculoPneuByIdPneu(idPneu).getIdVeiculo())+"";
       return placa;
    }

}
