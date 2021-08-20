package view;

import control.PneuControl;
import control.PneuDesgasteControl;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Pneu;
import model.PneuDesgaste;
import model.Veiculo;

/**
 *
 * @author mario
 */
public class jdHistoricoPneuPosicao extends javax.swing.JDialog {

    private static String CLASS = "jdHistoricoPneuPosicao.";
    private static String MARCA = "Marca";
    private static String MODELO = "Modelo";
    private static String FOGO = "Fogo";
    
    private static String KM_ENTRADA = "Km Entrada";
    private static String KM_SAIDA = "Km Saída";
    private static String KM_RODADO = "Km Rodado";
    
    private static String VALOR = "Valor Pneu";
    private static String DESGATE = "Desgaste";
    private static String CUSTO_KM = "Custo por Km";
    private static String DATA = "Data";
    
    Veiculo veiculo;
    int posicao;
    Auxiliar aux;
    PneuDesgasteControl pdc;
    PneuControl pc;
    ArrayList<PneuDesgaste> arrayList;
    
    PneuDesgaste pdMaior, pdMenor;
    float custoMedio = 0;
    
    public jdHistoricoPneuPosicao(Veiculo veiculo, int posicao){
        this.veiculo = veiculo;
        this.posicao = posicao;
        inicializar();
        atualizarTela();
    }
    private void atualizarTela(){
        preencherTabela();
        preencherProgressBar();
    }
    private void atualizarMaiorMenor(PneuDesgaste pd){
    
        Thread t = new Thread(() -> {
            if(pdMenor.getCustoKm()==0){
           pdMenor = pd;
       }
       if(pd.getCustoKm()> pdMaior.getCustoKm()){
           pdMaior = pd;
       }
       if(pd.getCustoKm()<pdMenor.getCustoKm()){
           pdMenor = pd;
       }
        });
       t.start();
        
       
    }
    private void inicializar(){
        super.setModal(true);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            //aux.RegistrarLog(DESGATE, FOGO);
        }
        initComponents();
        setLocationRelativeTo(null);
        aux = new Auxiliar();
        pc = new PneuControl();
        pdc = new PneuDesgasteControl();
        pdMaior = new PneuDesgaste();
        pdMenor = new PneuDesgaste();
    }
    private void preencherProgressBar(){        
        float maior =  pdMaior.getCustoKm();
        maior = maior == 0 ? 1: maior;
        
        int media = (int) ((custoMedio / maior) * 100);
        int menor = (int) ((pdMenor.getCustoKm() / maior) * 100);
        
        jpbMedia.setValue(media);
        jpbMedia.setString("Custo médio: "+aux.StringFloatReais(custoMedio));

        jpbMenor.setValue(menor);
        jpbMenor.setString("Menor custo: " +aux.StringFloatReais(pdMenor.getCustoKm()));

        jpbMaior.setValue(100);
        jpbMaior.setString("Maior custo: " +aux.StringFloatReais(pdMaior.getCustoKm()));

    }
    private void preencherTabela(){
        DefaultTableModel model = new DefaultTableModelNaoEditavel();
        model.addColumn(MARCA);
        model.addColumn(MODELO);
        model.addColumn(FOGO);
        model.addColumn(VALOR);
        model.addColumn(KM_ENTRADA);
        model.addColumn(KM_SAIDA);
        model.addColumn(KM_RODADO);
        model.addColumn(DESGATE);
        model.addColumn(CUSTO_KM);
        model.addColumn(DATA);
        
        try {
            arrayList = pdc.getArrayListDesgasteByIdVeiculoAndPosicao(veiculo.getId(), posicao);
            
            Object[] linha;
            Pneu pneu;
            
            for(PneuDesgaste pd : arrayList){
                
                linha = new Object[10];
                pneu = pc.getItemByID(pd.getIdPneu());
                
                linha[0] = pneu.getMarca();
                linha[1] = pneu.getModelo();
                linha[2]= pneu.getFogo();
                linha[3]= aux.StringFloatReais(pneu.getValor());
                linha[4] = pd.getKmEntrada();
                linha[5] = pd.getKmSaida();
                linha[6]= pd.getKmPercorrido();
                linha[7]=aux.StringFloatReais(pd.getValor());
                linha[8]= aux.CustoKMString(pd.getKmPercorrido(), pd.getValor());
                linha[9]=aux.getDataString(pd.getDatamilis());
                
                model.addRow(linha);
                atualizarMaiorMenor(pd);
                
                custoMedio+=pd.getCustoKm();
                
            }
            custoMedio= (arrayList.size()>0) ? (custoMedio / arrayList.size()) : (custoMedio / 1);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), CLASS+"preencherTabela");
        }finally{
            jTable.setModel(model);
            preencherAnalise();
        }
    }
    private void preencherAnalise(){
        Thread t = new Thread(() -> {
            
            Pneu pneuMenor = pc.getItemByID(pdMenor.getIdPneu());
            Pneu pneuMaior = pc.getItemByID(pdMaior.getIdPneu());
            
            lblFogoMenor.setText(pneuMenor.getFogo()+"");
            lblMarcaMenor.setText(pneuMenor.getMarca());
            lblModeloMenor.setText(pneuMenor.getModelo());
            lblKmMenor.setText(pdMenor.getKmPercorrido()+"");
            lblCustoKmMenor.setText(aux.CustoKMString(pdMenor.getKmPercorrido(), pdMenor.getValor()));
            lblValorMenor.setText(aux.StringFloatReais(pdMenor.getValor()));
            
            lblFogoMaior.setText(pneuMaior.getFogo()+"");
            lblMarcaMaior.setText(pneuMaior.getMarca());
            lblModeloMaior.setText(pneuMaior.getModelo());
            lblKmMaior.setText(pdMaior.getKmPercorrido()+"");
            lblCustoKmMaior.setText(aux.CustoKMString(pdMaior.getKmPercorrido(), pdMaior.getValor()));
            lblValorMaior.setText(aux.StringFloatReais(pdMaior.getValor()));
            
            lblPlaca.setText(veiculo.getPlaca());
            lblPosicao.setText((posicao+1) + "");
            lblCusto.setText(aux.StringFloatReais(custoMedio));
            
        });
        t.start();
    }
    /*
    public jdHistoricoPneuPosicao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public jdHistoricoPneuPosicao(){
        super.setModal(true);
        initComponents();
        setLocationRelativeTo(null);
        
    }*/
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblPlaca = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblPosicao = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCusto = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblFogoMenor = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMarcaMenor = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblModeloMenor = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblKmMenor = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblValorMenor = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblCustoKmMenor = new javax.swing.JLabel();
        jpProgressBar = new javax.swing.JPanel();
        jpbMaior = new javax.swing.JProgressBar();
        jpbMedia = new javax.swing.JProgressBar();
        jpbMenor = new javax.swing.JProgressBar();
        jPanel9 = new javax.swing.JPanel();
        lblFogoMaior = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblMarcaMaior = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblModeloMaior = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblKmMaior = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblValorMaior = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblCustoKmMaior = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 550));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HISTÓRICO DA POSIÇÃO");
        jLabel1.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 8), new java.awt.Color(127, 127, 127))); // NOI18N

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 8), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setText("Veiculo");
        jPanel3.add(jLabel2);

        lblPlaca.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblPlaca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlaca.setText("DLH-8657");
        jPanel3.add(lblPlaca);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 127, 127));
        jLabel4.setText("Posição");
        jPanel3.add(jLabel4);

        lblPosicao.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblPosicao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPosicao.setText("5");
        jPanel3.add(lblPosicao);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(127, 127, 127));
        jLabel6.setText("Custo Médio por KM");
        jPanel3.add(jLabel6);

        lblCusto.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblCusto.setForeground(new java.awt.Color(0, 0, 204));
        lblCusto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCusto.setText("R$ 0,010 / km");
        jPanel3.add(lblCusto);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Análise dos dados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 8), new java.awt.Color(127, 127, 127))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(0, 2));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 127, 127));
        jLabel3.setText("Fogo");
        jPanel5.add(jLabel3);

        lblFogoMenor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblFogoMenor.setForeground(new java.awt.Color(0, 153, 0));
        lblFogoMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFogoMenor.setText("500");
        jPanel5.add(lblFogoMenor);

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Marca");
        jPanel5.add(jLabel5);

        lblMarcaMenor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblMarcaMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMarcaMenor.setText("Pirelli");
        jPanel5.add(lblMarcaMenor);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(127, 127, 127));
        jLabel7.setText("Modelo");
        jPanel5.add(jLabel7);

        lblModeloMenor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblModeloMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModeloMenor.setText("A20 175/75");
        jPanel5.add(lblModeloMenor);

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(127, 127, 127));
        jLabel9.setText("Km rodado");
        jPanel5.add(jLabel9);

        lblKmMenor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblKmMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKmMenor.setText("11000");
        jPanel5.add(lblKmMenor);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(127, 127, 127));
        jLabel8.setText("Valor desgaste ");
        jPanel5.add(jLabel8);

        lblValorMenor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblValorMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorMenor.setText("R$ 120,00");
        jPanel5.add(lblValorMenor);

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(127, 127, 127));
        jLabel10.setText("Custo por km rodado");
        jPanel5.add(jLabel10);

        lblCustoKmMenor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblCustoKmMenor.setForeground(new java.awt.Color(0, 153, 0));
        lblCustoKmMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustoKmMenor.setText("R$ 0,01 / km");
        jPanel5.add(lblCustoKmMenor);

        jpProgressBar.setBackground(new java.awt.Color(255, 255, 255));
        jpProgressBar.setLayout(new java.awt.GridLayout(0, 1, 10, 10));

        jpbMaior.setBackground(new java.awt.Color(255, 255, 255));
        jpbMaior.setForeground(new java.awt.Color(255, 0, 0));
        jpbMaior.setValue(90);
        jpbMaior.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpbMaior.setName("FOGO 50"); // NOI18N
        jpbMaior.setString("Maior");
        jpbMaior.setStringPainted(true);
        jpProgressBar.add(jpbMaior);

        jpbMedia.setForeground(new java.awt.Color(0, 0, 204));
        jpbMedia.setValue(45);
        jpbMedia.setName("FOGO 50"); // NOI18N
        jpbMedia.setString("Media");
        jpbMedia.setStringPainted(true);
        jpProgressBar.add(jpbMedia);

        jpbMenor.setForeground(new java.awt.Color(0, 153, 0));
        jpbMenor.setValue(20);
        jpbMenor.setName("FOGO 50"); // NOI18N
        jpbMenor.setString("Menor");
        jpbMenor.setStringPainted(true);
        jpProgressBar.add(jpbMenor);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.GridLayout(0, 2));

        lblFogoMaior.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblFogoMaior.setForeground(new java.awt.Color(255, 0, 0));
        lblFogoMaior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFogoMaior.setText("500");
        jPanel9.add(lblFogoMaior);

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(127, 127, 127));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Fogo");
        jPanel9.add(jLabel12);

        lblMarcaMaior.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblMarcaMaior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMarcaMaior.setText("Pirelli");
        jPanel9.add(lblMarcaMaior);

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(127, 127, 127));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Marca");
        jPanel9.add(jLabel13);

        lblModeloMaior.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblModeloMaior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModeloMaior.setText("A20 175/75");
        jPanel9.add(lblModeloMaior);

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(127, 127, 127));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Modelo");
        jPanel9.add(jLabel14);

        lblKmMaior.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblKmMaior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKmMaior.setText("11000");
        jPanel9.add(lblKmMaior);

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(127, 127, 127));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Km rodado");
        jPanel9.add(jLabel15);

        lblValorMaior.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblValorMaior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorMaior.setText("R$ 120,00");
        jPanel9.add(lblValorMaior);

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(127, 127, 127));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Valor desgaste ");
        jPanel9.add(jLabel16);

        lblCustoKmMaior.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblCustoKmMaior.setForeground(new java.awt.Color(255, 0, 0));
        lblCustoKmMaior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustoKmMaior.setText("R$ 0,01 / km");
        jPanel9.add(lblCustoKmMaior);

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(127, 127, 127));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Custo por km rodado");
        jPanel9.add(jLabel17);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpProgressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel jpProgressBar;
    private javax.swing.JProgressBar jpbMaior;
    private javax.swing.JProgressBar jpbMedia;
    private javax.swing.JProgressBar jpbMenor;
    private javax.swing.JLabel lblCusto;
    private javax.swing.JLabel lblCustoKmMaior;
    private javax.swing.JLabel lblCustoKmMenor;
    private javax.swing.JLabel lblFogoMaior;
    private javax.swing.JLabel lblFogoMenor;
    private javax.swing.JLabel lblKmMaior;
    private javax.swing.JLabel lblKmMenor;
    private javax.swing.JLabel lblMarcaMaior;
    private javax.swing.JLabel lblMarcaMenor;
    private javax.swing.JLabel lblModeloMaior;
    private javax.swing.JLabel lblModeloMenor;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblPosicao;
    private javax.swing.JLabel lblValorMaior;
    private javax.swing.JLabel lblValorMenor;
    // End of variables declaration//GEN-END:variables
}
