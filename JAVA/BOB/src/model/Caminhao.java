package model;

public class Caminhao extends Veiculo{
    
    public Caminhao(){
        super();
        setTipo(CAMINHAO);
        setConfiguracao(CONF_4X2);
        setVeiculoConfiguracao(veiculoConfiguracaoArray[CONF_4X2]);
    }
    private static int CONF_4X2 = 0, CONF_6X2 = 1, CONF_6X4 = 2, CONF_8X4=3;
    public static final String[] CONF_LABELS={"4x2","6x2", "6x4", "8x4"};
    public static final int[] QTDE_PNEUS = {6, 10, 10, 14};
    public static final int[] QTDE_EIXOS = {2, 3, 3, 4};
    public static VeiculoConfiguracao[] veiculoConfiguracaoArray = {
        new VeiculoConfiguracao(CONF_4X2, CONF_LABELS[0], QTDE_PNEUS[0], QTDE_EIXOS[0], new int[]{0}),
        new VeiculoConfiguracao(CONF_6X2, CONF_LABELS[1], QTDE_PNEUS[1], QTDE_PNEUS[1], new int[]{0}),
        new VeiculoConfiguracao(CONF_4X2, CONF_LABELS[2], QTDE_PNEUS[2], QTDE_EIXOS[2], new int[]{0}),
        new VeiculoConfiguracao(CONF_6X2, CONF_LABELS[3], QTDE_PNEUS[3], QTDE_PNEUS[3], new int[]{0})
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
