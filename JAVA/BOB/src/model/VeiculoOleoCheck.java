package model;

public class VeiculoOleoCheck {
    /*
        Sugestão, excluir.
        Substituir por um propriedade do item que estiver presente na lista de pedidoitem
        Cadastrar um código para cada tipo e verificar se eles estão presentes na lista 
        
    
        Será que precisar ter uma classe para cada, oleo freio, filtro ao invez de apenas a classe item?
        e utilizar o campo tipo para especificar qual
        nas telas de cadastro para filtro, oleo e demais é só passar o valor determinado para o tipo do item cadastrado e boa
        oleo por exemplor terá mais de um tipo(motor, cambio e diferencial) dai é só por um campo para chegar qual tipo especifico 
    
        continuar criando as classes DAO a partir do tb_item, tb_pedido.. 
    
    
    */
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public boolean isFiltroAr() {
        return filtroAr;
    }

    public void setFiltroAr(boolean filtroAr) {
        this.filtroAr = filtroAr;
    }

    public boolean isFiltroCombustivel() {
        return filtroCombustivel;
    }

    public void setFiltroCombustivel(boolean filtroCombustivel) {
        this.filtroCombustivel = filtroCombustivel;
    }

    public boolean isFiltroOleoMotor() {
        return filtroOleoMotor;
    }

    public void setFiltroOleoMotor(boolean filtroOleoMotor) {
        this.filtroOleoMotor = filtroOleoMotor;
    }

    public boolean isCambio() {
        return cambio;
    }

    public void setCambio(boolean cambio) {
        this.cambio = cambio;
    }

    public boolean isDiferencial() {
        return diferencial;
    }

    public void setDiferencial(boolean diferencial) {
        this.diferencial = diferencial;
    }

    public boolean isPu() {
        return pu;
    }

    public void setPu(boolean pu) {
        this.pu = pu;
    }
    public VeiculoOleoCheck(){
        
    }
    private int id = 0, idPedido=0;
    private boolean filtroAr, filtroCombustivel, filtroOleoMotor,motor, cambio, diferencial, pu;

    public void atualizarItem(int tipo){
        
        switch(tipo){
            case Item.FILTRO_AR:
                setFiltroAr(true);
                break;
            case Item.FILTRO_COMBUSTIVEL:
                setFiltroCombustivel(true);
                break;
            case Item.FILTRO_OLEO_MOTOR:
                setFiltroOleoMotor(true);
                break;
            case Item.OLEO_MOTOR:
                setMotor(true);
                break;
            case Item.OLEO_CAMBIO:
                setCambio(true);
                break;
            case Item.OLEO_DIFERENCIAL:
                setDiferencial(true);
                break;
            case Item.PU:
                setPu(true);
                break;
        }
    }
    public boolean isMotor() {
        return motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }
}
