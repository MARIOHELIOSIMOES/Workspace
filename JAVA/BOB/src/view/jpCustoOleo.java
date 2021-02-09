package view;

import control.ItemControl;
import control.PedidoControl;
import control.PedidoItemControl;
import control.PedidoOleoControl;
import control.VeiculoKMControl;
import control.VeiculoPreventivaControl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Item;
import model.Pedido;
import model.PedidoItem;
import model.ProgressBarItem;
import model.Usuario;
import model.Veiculo;
import model.VeiculoOleoCheck;
import model.VeiculoPreventiva;
import model.GraficoItem;
import model.GraficoItemC;
import model.LinhaItem;

/**
 *
 * @author mario
 */
public class jpCustoOleo extends javax.swing.JPanel {

    private Veiculo veiculo;
    private float valorUltimo=0;
    private int diffKm, kmAtual;
    private Pedido ultimoPedido;
    private Usuario usuario;
    private ArrayList<Pedido> arraylistPedidos;
    private PedidoOleoControl poc;
    private PedidoItemControl pic;
    private VeiculoKMControl vkm;
    private VeiculoPreventivaControl vpc;
    Auxiliar aux;
    
    public jpCustoOleo() {
        inicializar();
    }
    public jpCustoOleo(Veiculo veiculo, Usuario usuario){
        inicializar();
        this.veiculo = veiculo;
        this.usuario = usuario;
        atualizarTela();
    }
    public void atualizarTela(){
        try{
            kmAtual = vkm.getUltimoKmByIDVeiculo(veiculo.getId());
            lblKmAtual.setText(kmAtual+"");
            preencherUltimoPedido();
            preencherTabela();
            calcularMediaGeral();
            
        }catch(Exception e){
            System.out.print("Atualizar Tela jpCustoOleo Erro:  " + e.getMessage());
        }
        
    }
    private void inicializar(){
        initComponents();
        veiculo = new Veiculo();
        usuario = new Usuario();
        arraylistPedidos = new ArrayList<Pedido>();
        pic = new PedidoItemControl();
        poc = new PedidoOleoControl();
        vpc = new VeiculoPreventivaControl();
        vkm = new VeiculoKMControl();
        aux = new Auxiliar();   

    }
    private void preencherUltimoPedido(){
        try{// preencherTableUltima(ck);
           preencherPanelItens();
        }catch(Exception e){
           aux.RegistrarLog(e.getMessage(), "jpCustoOleo.preencherUltimoPedido");
        }
    }
    private void preencherTabela(){
        try{
            arraylistPedidos = poc.getArrayListPedidoByIdVeiculo(veiculo.getId());
            
            DefaultTableModel tableModel = new DefaultTableModelNaoEditavel();
                tableModel.addColumn("Nº");//1
                tableModel.addColumn("NºPedido");//2
                tableModel.addColumn("Data");//3
                tableModel.addColumn("Informação");//6
                tableModel.addColumn("KM da Troca");//4
                tableModel.addColumn("KM Percorrido"); //5
                tableModel.addColumn("Valor Total");//7
                tableModel.addColumn("Custo R$/KM");//8
                int kmProximo = 0, kmAtual = 0, diffKm = 0;
                float custo = 0;
                kmProximo = this.kmAtual;
            for(int i=arraylistPedidos.size()-1; i>=0; i--){
                kmAtual = arraylistPedidos.get(i).getKm();
                diffKm = kmProximo - kmAtual;
                diffKm = (diffKm<=0)? 1: diffKm;
                kmProximo = kmAtual;
                custo = pic.getValorTotalByIdPedido(arraylistPedidos.get(i).getId());
                
                Object[] item = new Object[8];
                
                item[0]=i+1;
                item[1]=arraylistPedidos.get(i).getId();
                item[2]=aux.getDataString(arraylistPedidos.get(i).getDatamilis());
                item[3]=arraylistPedidos.get(i).getInfo();
                item[4]= kmAtual;
                item[5] = diffKm;
                item[6]= aux.StringFloatReais(custo);
                item[7]= aux.StringFloatReais((custo/diffKm));
                
                tableModel.addRow(item);
            }
            jTable.setModel(tableModel);
            jTable.getColumnModel().getColumn(0).setMaxWidth(50);
            //jTable.getColumnModel().getColumn(1).setMaxWidth(70);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustoOleo.preencherTabela");
        }
    }
    private void calcularMediaGeral(){
        try{
             
            lblCustoKmGeral.setText(aux.StringFloatReais(poc.getMediaCustoGeral(veiculo.getId(), 5)));
            lblCustoAtual.setText(aux.StringFloatReais(poc.getMediaCustoAtual(veiculo.getId())));
            ArrayList<GraficoItemC> arraygrafico = poc.getArrayGraficoC(veiculo.getId(), 5);
            jpGrafico.removeAll();
            jpGrafico.setLayout(new GridLayout(1,1));
            jpGrafico.add(new jpGraficoBarra("", arraygrafico));
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustoOleo.CalcularMediaGeral");
        }
    }
    private ArrayList<VeiculoPreventiva> getListaPadrao(){
        ArrayList<VeiculoPreventiva> arrayList = new ArrayList<>();
        VeiculoPreventiva vp;
        for(int i = 0; i< Item.OLEOSFILTROS.length; i++){
            vp = vpc.getVeiculoPrenventivaByIdVeiculoAndByIdTipo(veiculo.getId(), Item.OLEOSFILTROS[i]);
            arrayList.add(vp);
        }
        return arrayList;
    }
    private void preencherPanelItens(){
        try{
            jpItens.removeAll();
            // ArrayList<VeiculoPreventiva> arrayPreventiva = vpc.getArrayListPreventivasPendentesByIdVeiculoByTipo(veiculo.getId(), Item.OLEOSFILTROS);
           ArrayList<VeiculoPreventiva> arrayPreventiva = getListaPadrao();
            VeiculoPreventiva vp;
            for(int i = 0; i< arrayPreventiva.size(); i++){
                vp = arrayPreventiva.get(i);
                jpItens.add(new jpLinha(new LinhaItem(Item.TIPO_STRING[vp.getTipo()], vp.getKm(), kmAtual , vp.getKmProx(), vkm.getMediaKmPorDia(veiculo.getId()))));
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustoOleo.preencherPanelItens");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblCustoAtual = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jpItens = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jpHistorico = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblCustoKmGeral = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jpGrafico = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblKmAtual = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/oleo.png"))); // NOI18N
        jLabel1.setText("Óleo e Filtros");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações dos itens", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(117, 117, 117))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(580, 164));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel6.setText("Custo Último Pedido");

        lblCustoAtual.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCustoAtual.setForeground(new java.awt.Color(33, 150, 243));
        lblCustoAtual.setText("R$ 21,21");

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Reais/Km");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jpItens.setMinimumSize(new java.awt.Dimension(150, 30));
        jpItens.setLayout(new java.awt.GridLayout(0, 1));

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Em aberto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jpItens, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCustoAtual)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(jpItens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpHistorico.setBackground(new java.awt.Color(255, 255, 255));
        jpHistorico.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Histórico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(117, 117, 117))); // NOI18N

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
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable);

        javax.swing.GroupLayout jpHistoricoLayout = new javax.swing.GroupLayout(jpHistorico);
        jpHistorico.setLayout(jpHistoricoLayout);
        jpHistoricoLayout.setHorizontalGroup(
            jpHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jpHistoricoLayout.setVerticalGroup(
            jpHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHistoricoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Média", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(127, 127, 127))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel17.setText("Custo KM");

        lblCustoKmGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblCustoKmGeral.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCustoKmGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblCustoKmGeral.setText("R$ 4,21");

        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Reais/Km");

        jpGrafico.setBackground(new java.awt.Color(255, 255, 255));
        jpGrafico.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel11))
                            .addComponent(lblCustoKmGeral))))
                .addGap(22, 22, 22)
                .addComponent(jpGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCustoKmGeral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnNovo.setBackground(new java.awt.Color(255, 64, 129));
        btnNovo.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/maisp.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setMaximumSize(new java.awt.Dimension(105, 27));
        btnNovo.setMinimumSize(new java.awt.Dimension(105, 27));
        btnNovo.setPreferredSize(new java.awt.Dimension(105, 27));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setText("Km Atual");

        lblKmAtual.setBackground(new java.awt.Color(205, 220, 57));
        lblKmAtual.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblKmAtual.setForeground(new java.awt.Color(33, 150, 243));
        lblKmAtual.setText("Último");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblKmAtual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblKmAtual)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        new jfTrocaOleo(this, usuario, veiculo).setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if(evt.getClickCount()>1){
            new jdPedido(this, arraylistPedidos.get(arraylistPedidos.size() - 1 - jTable.getSelectedRow()), usuario).setVisible(true);
        }
    }//GEN-LAST:event_jTableMouseClicked
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNovo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel jpGrafico;
    private javax.swing.JPanel jpHistorico;
    private javax.swing.JPanel jpItens;
    private javax.swing.JLabel lblCustoAtual;
    private javax.swing.JLabel lblCustoKmGeral;
    private javax.swing.JLabel lblKmAtual;
    // End of variables declaration//GEN-END:variables
}
