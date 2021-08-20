package view;

import control.PneuControl;
import control.PneuDesgasteControl;
import control.PneuPosicaoControl;
import control.VeiculoControl;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Pneu;
import model.PneuDesgaste;
import model.PneuPosicao;

/**
 *
 * @author mario
 */
public class jdPneuMovimentacao extends javax.swing.JDialog {

    Pneu pneu;
    Auxiliar aux;
    PneuPosicaoControl ppc;
    VeiculoControl vc;
    List<PneuDesgaste> arraylist;
    PneuDesgasteControl pdc;
    PneuControl pc;

    public jdPneuMovimentacao(Pneu pneu) {
        inicializar();
        this.pneu = pneu;
        preencherPanelInfos();
        preencherPanelAtual();
        preencherTabela();
    }

    private void inicializar() {
        initComponents();
        setModal(true);
        aux = new Auxiliar();
        vc = new VeiculoControl();
        ppc = new PneuPosicaoControl();
        arraylist = new ArrayList<>();
        pdc = new PneuDesgasteControl();

    }

    private void preencherPanelInfos() {
        jpInfo.removeAll();
        jpInfo.add(new jpInfoPneu(pneu));
    }
    private void preencherTabela(){
        DefaultTableModel model = new DefaultTableModelNaoEditavel();
        model.addColumn("ID");
        model.addColumn("Data");
        model.addColumn("Veiculo");
        model.addColumn("KM Entrada");
        model.addColumn("KM Saída");
        model.addColumn("KM Percorrido");
        model.addColumn("Valor desgastado");
        model.addColumn("Custo do KM");//8
        
        TableRowSorter tableSorter = new TableRowSorter(model);
        float custoKmMedio=0, custoKmMaior=0, custoKmMenor=0, custoKmAtual = 0, custoDesgaste=0;
        
        try{
            arraylist = pdc.getArrayListDesgasteByIdPneu(pneu.getId());
            
            for(PneuDesgaste pd: arraylist){
                Object[] linha = new Object[8];
                linha[0] = pd.getId();
                linha[1]= aux.getDataString(pd.getDatamilis());
                linha[2]= vc.getPlacaByIdVeiculo(pd.getIdVeiculo());
                linha[3]= pd.getKmEntrada();
                linha[4]= pd.getKmSaida();
                linha[5]= pd.getKmPercorrido();
                linha[6]= aux.StringFloatReais(pd.getValor());
                linha[7]= aux.CustoKMString(pd.getKmPercorrido(), pd.getValor());
                
                custoKmAtual = aux.CustoKmFloat(pd.getKmPercorrido(), pd.getValor());
                custoKmMedio+=custoKmAtual;
                custoDesgaste+=pd.getValor();
                
                if(custoKmAtual>=custoKmMaior){
                    custoKmMaior = custoKmAtual;
                }
                if(custoKmAtual<=custoKmMenor||custoKmMenor==0){
                    custoKmMenor = custoKmAtual;
                }
                
                model.addRow(linha);
                
            }
            tableSorter = new TableRowSorter(model);
            int qtde = arraylist.size();
            qtde = arraylist.size()>0? qtde : 1;
            custoKmMedio = custoKmMedio/qtde;
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jdPneuMovimentacao.preencherTabela");
        }finally{
            jTable.setModel(model);
            jTable.setRowSorter(tableSorter);
            
            lblCustoKmMaior.setText(aux.StringFloatReais(custoKmMaior));
            lblCustoKmMedio.setText(aux.StringFloatReais(custoKmMedio));
            lblCustoKmMenor.setText(aux.StringFloatReais(custoKmMenor));
            lblTotalDesgaste.setText(aux.StringFloatReais(custoDesgaste));
            
            
            //tableSorter.toggleSortOrder(2); 
        }
        
    }
    private void preencherPanelAtual() {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                   
                    PneuPosicao pneuPosicao = ppc.getVeiculoPneuByIdPneu(pneu.getId());
                    pc = new PneuControl();
                    if(pneu.getStatus()==Pneu.RODANDO){
                        lblData.setText(aux.getDataString(pneuPosicao.getDatamilis()));
                        lblKmEntrada.setText(pneuPosicao.getKm() + "");
                        lblPosicao.setText((pneuPosicao.getPosicao()+1) + "");
                        lblPlaca.setText(vc.getVeiculoById(pneuPosicao.getIdVeiculo()).getPlaca());
                    }else{
                        lblData.setText(aux.getDataStringAtual());
                        lblKmEntrada.setText("");
                        lblPosicao.setText("");
                        lblPlaca.setText("");

                    }
                    
                    lblStatus.setText(pneu.getStatusString());
                    lblCustoKm.setText(aux.CustoKMString(pneu.getKmTotal(), pc.getValorAtualByIdPneu(pneu.getId())));
                    
                }
            });
            t.start();

        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jdPneuMovimentacao.preencherPanelAtual");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jpInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblPlaca = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblKmEntrada = new javax.swing.JLabel();
        lblPosicao = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblTotalDesgaste = new javax.swing.JLabel();
        lblCustoKm = new javax.swing.JLabel();
        lblCustoKmMedio = new javax.swing.JLabel();
        lblCustoKmMenor = new javax.swing.JLabel();
        lblCustoKmMaior = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pneu.png"))); // NOI18N
        jLabel3.setText("Histórico de Movimentação do Pneu");
        jLabel3.setOpaque(true);

        jpInfo.setBackground(new java.awt.Color(153, 153, 153));
        jpInfo.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setText("Informações do Pneu = jpInfoPneu");
        jpInfo.add(jLabel1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status Atual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(2, 5, 15, 10));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Status");
        jPanel1.add(jLabel2);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Placa");
        jPanel1.add(jLabel4);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Data Entrada");
        jPanel1.add(jLabel6);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Km Entrada");
        jPanel1.add(jLabel8);

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Posicao");
        jPanel1.add(jLabel10);

        lblStatus.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Rodando");
        jPanel1.add(lblStatus);

        lblPlaca.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblPlaca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlaca.setText("DLH8657");
        jPanel1.add(lblPlaca);

        lblData.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblData.setText("19/05/2021");
        jPanel1.add(lblData);

        lblKmEntrada.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblKmEntrada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKmEntrada.setText("12500");
        jPanel1.add(lblKmEntrada);

        lblPosicao.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblPosicao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPosicao.setText("2");
        jPanel1.add(lblPosicao);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Custos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total desgate");
        jPanel3.add(jLabel5);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Custo KM ");
        jPanel3.add(jLabel7);

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Custo Km Rodado Médio");
        jPanel3.add(jLabel13);

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Menor Desgaste KM");
        jPanel3.add(jLabel15);

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Maior  Desgaste KM");
        jPanel3.add(jLabel11);

        lblTotalDesgaste.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblTotalDesgaste.setForeground(new java.awt.Color(0, 102, 255));
        lblTotalDesgaste.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalDesgaste.setText("R$ 1200");
        jPanel3.add(lblTotalDesgaste);

        lblCustoKm.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCustoKm.setForeground(new java.awt.Color(0, 102, 255));
        lblCustoKm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustoKm.setText("R$ 1200");
        jPanel3.add(lblCustoKm);

        lblCustoKmMedio.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCustoKmMedio.setForeground(new java.awt.Color(0, 102, 255));
        lblCustoKmMedio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustoKmMedio.setText("R$ 1200");
        jPanel3.add(lblCustoKmMedio);

        lblCustoKmMenor.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCustoKmMenor.setForeground(new java.awt.Color(0, 102, 255));
        lblCustoKmMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustoKmMenor.setText("R$ 1200");
        jPanel3.add(lblCustoKmMenor);

        lblCustoKmMaior.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCustoKmMaior.setForeground(new java.awt.Color(0, 102, 255));
        lblCustoKmMaior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustoKmMaior.setText("R$ 1200");
        jPanel3.add(lblCustoKmMaior);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jpInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jpInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(536, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel jpInfo;
    private javax.swing.JLabel lblCustoKm;
    private javax.swing.JLabel lblCustoKmMaior;
    private javax.swing.JLabel lblCustoKmMedio;
    private javax.swing.JLabel lblCustoKmMenor;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblKmEntrada;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblPosicao;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTotalDesgaste;
    // End of variables declaration//GEN-END:variables

}
