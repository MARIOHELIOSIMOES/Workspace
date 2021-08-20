package model;

public class Carro extends Veiculo{
    
    public Carro(){
        super();
        setTipo(CARRO);
        setConfiguracao(CONF_4X2);
        setVeiculoConfiguracao(veiculoConfiguracaoArray[CONF_4X2]);
    }
    private static int CONF_4X2 = 0;
    private static int CONF_4X4 = 1;
    public static final String[] CONF_LABELS={"4x2","4x4"};
    public static final int[] QTDE_PNEUS = {4, 4};
    public static final int[] QTDE_EIXOS = {2, 2};
    public static VeiculoConfiguracao[] veiculoConfiguracaoArray = {
        new VeiculoConfiguracao(CONF_4X2, CONF_LABELS[0], QTDE_PNEUS[0], QTDE_EIXOS[0], new int[]{0,0,0,0}),
        new VeiculoConfiguracao(CONF_4X4, CONF_LABELS[1], QTDE_PNEUS[1], QTDE_PNEUS[1], new int[]{0,0,0,0})
    };

    @Override
    public int getQtdePneu() {
        return QTDE_PNEUS[getConfiguracao()];
    }
    
    @Override
    public VeiculoConfiguracao getVeiculoConfiguracaoByIndex(int index) {
        return veiculoConfiguracaoArray[index];
    }

    @Override
    public String getConfiguracaoLabel() {
        return CONF_LABELS[getConfiguracao()];
    }

    @Override
    public String[] getConfLabels() {
        return CONF_LABELS;
    }
    
}
