package view;

import control.PneuControl;
import control.PneuReformaControl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;

import model.Pneu;
import model.PneuReforma;

/**
 *
 * @author mario
 */
public class jdPneuReforma extends javax.swing.JDialog {

    Pneu pneu;
    Auxiliar aux;
    ArrayList<PneuReforma> arrayList;
    private static final String ORIGEM = "jpPneuReforma";
    private static final String EMPTY = "";
    private static final String SALVAR = "Salvar";
    private static final String ALTERAR = "Alterar";
    
    PneuReformaControl pc;
    jpCadastroPneu jpPai;
    public jdPneuReforma() {
        inicializar();
    }
    public jdPneuReforma(jpCadastroPneu jpPai, Pneu pneu){
        inicializar();
        this.pneu = pneu;
        this.jpPai = jpPai;
        preencherInfoPneu();
        atualizarTela();
    }
    private void atualizarTela(){
        limparCampos();
        preencherTable();
    }
    private void preencherInfoPneu(){
        lblFogo.setText(pneu.getFogo()+"");
        lblMarcaModelo.setText(pneu.getMarcaModelo());
        lblKm.setText(pneu.getKmTotal()+"");
        lblId.setText(pneu.getId()+"");
        lblValor.setText(aux.StringFloatReais(pneu.getValor()));
        lblStatus.setText(Pneu.STATUS_PNEU[pneu.getStatus()]);
        
    }
    private void inicializar(){
        super.setModal(true);
        initComponents();
        arrayList = new ArrayList<PneuReforma>();
        pneu = new Pneu();
        aux = new Auxiliar();
        pc = new PneuReformaControl();
        jTable.getSelectionModel().addListSelectionListener((e)->{
            preencherSelectedTable();
        });
        txfData.setText(aux.getDataStringAtual());
    }
    private void preencherSelectedTable() {
        try{
            int i = jTable.getSelectedRow();
            if(i>=0){
                PneuReforma pr = arrayList.get(i);
                lblIdReforma.setText(pr.getId()+"");
                txfCusto.setText(pr.getValor()+"");
                txfData.setText(aux.getDataString(pr.getDatamilis()));
                txfInfo.setText(pr.getInfo());
                txfOficina.setText(pr.getOficina());
                txfKm.setText(pr.getKm()+"");
                
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), ORIGEM+".preencherSelectedTable");
            limparCampos();
        }finally{
            btnSalvar.setText(ALTERAR);
        }
    }
    private void preencherTable(){
        DefaultTableModel model = new DefaultTableModelNaoEditavel();
        model.addColumn("ID");
        model.addColumn("Data");
        model.addColumn("Km");
        model.addColumn("Valor");
        model.addColumn("Oficina");
        model.addColumn("Info");
        try {
            arrayList = pc.getArrayListReformasByIdPneu(pneu.getId());
            for(PneuReforma pc: arrayList){
                Object[] linha = new Object[6];
                linha[0]=pc.getId();
                linha[1]=aux.getDataString(pc.getDatamilis());
                linha[2]=pc.getKm();
                linha[3]= aux.StringFloatReais(pc.getValor());
                linha[4]= pc.getOficina();
                linha[5]=pc.getInfo();
                model.addRow(linha);
            }
            
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), ORIGEM+".preencherTable");
        }
        finally{
            jTable.setModel(model);
        }
    }
    private void limparCampos() {
        lblIdReforma.setText("0");
        txfCusto.setText(EMPTY);
        txfData.setText(EMPTY);
        txfInfo.setText(EMPTY);
        txfKm.setText(EMPTY);
        txfOficina.setText(EMPTY);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblFogo = new javax.swing.JLabel();
        lblMarcaModelo = new javax.swing.JLabel();
        lblKm = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txfOficina = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txfInfo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblIdReforma = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txfKm = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txfData = new javax.swing.JTextField();
        try{            
            javax.swing.text.MaskFormatter data =
            new javax.swing.text.MaskFormatter("##/##/####");        
            txfData = new javax.swing.JFormattedTextField(data);    
        }catch(Exception e){    }
        jLabel15 = new javax.swing.JLabel();
        txfCusto = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.white);
        setModal(true);
        setPreferredSize(new java.awt.Dimension(800, 557));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reforma de Pneu");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reformas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

        jScrollPane3.setPreferredSize(new java.awt.Dimension(800, 402));

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
        jTable.setShowGrid(false);
        jScrollPane3.setViewportView(jTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações do Pneu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 90));
        jPanel3.setLayout(new java.awt.GridLayout(2, 6, 10, 10));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("ID Pneu");
        jPanel3.add(jLabel6);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Fogo");
        jPanel3.add(jLabel8);

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Marca / Modelo");
        jPanel3.add(jLabel9);

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Km Total ");
        jPanel3.add(jLabel13);

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Valor Atual");
        jPanel3.add(jLabel11);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Status");
        jPanel3.add(jLabel3);

        lblId.setText("15");
        jPanel3.add(lblId);

        lblFogo.setText("351");
        jPanel3.add(lblFogo);

        lblMarcaModelo.setText("PIRELLI - R13 70/175");
        jPanel3.add(lblMarcaModelo);

        lblKm.setText("15542");
        jPanel3.add(lblKm);

        lblValor.setText("154,00");
        jPanel3.add(lblValor);

        lblStatus.setText("Estoque");
        jPanel3.add(lblStatus);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Reforma", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel4.setMinimumSize(new java.awt.Dimension(522, 162));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Oficina");

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Informação");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("ID");

        lblIdReforma.setText("150");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Km");

        txfKm.setMaximumSize(new java.awt.Dimension(10, 1000));

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Data");

        txfData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfDataFocusLost(evt);
            }
        });
        txfData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfDataActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel15.setText("Valor");

        btnExcluir.setBackground(new java.awt.Color(255, 0, 0));
        btnExcluir.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/freiop.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setMaximumSize(new java.awt.Dimension(97, 27));
        btnExcluir.setMinimumSize(new java.awt.Dimension(97, 27));
        btnExcluir.setPreferredSize(new java.awt.Dimension(127, 27));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txfInfo)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdReforma)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txfKm, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txfData, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txfCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 75, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(47, 47, 47)
                        .addComponent(txfOficina))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblIdReforma)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txfOficina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txfInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txfKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txfCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNovo.setBackground(new java.awt.Color(33, 150, 243));
        btnNovo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/maisp.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setMaximumSize(new java.awt.Dimension(97, 27));
        btnNovo.setMinimumSize(new java.awt.Dimension(97, 27));
        btnNovo.setPreferredSize(new java.awt.Dimension(97, 27));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setBackground(new java.awt.Color(255, 64, 129));
        btnSalvar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvarp.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        jpPai.atualizarTela();
    }//GEN-LAST:event_formWindowClosed

    private void txfDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfDataActionPerformed
    }//GEN-LAST:event_txfDataActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
        btnSalvar.setText(SALVAR);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if(pc.salvar(lblIdReforma.getText(), pneu.getId(), txfCusto.getText(), txfData.getText(), txfKm.getText(), txfOficina.getText(), txfInfo.getText())){
                atualizarTela();
                btnNovoActionPerformed(evt);
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), ORIGEM+".btnSalvarActionPerformed");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if(jfPrincipal.isUserAdmin()){
            try{
                if(lblIdReforma.getText().equalsIgnoreCase("0")){
                    return;
                }
                String id = aux.InputTextIDExclusao();
                if(id.trim().equalsIgnoreCase(lblIdReforma.getText())){
                    if(pc.excluir(lblIdReforma.getText())){
                        atualizarTela();
                    }
                }
            }catch(Exception e){
                aux.RegistrarLog(e.getMessage(), "jdPneuReforma.btnExcluirAction");
            }
           
        }else{
            aux.showMessagemSemPermissao();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txfDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txfDataFocusLost
            if(!aux.validarData(txfData.getText())){
                txfData.setText(aux.getDataStringAtual());
                aux.showMessageDataInvalida();
            }   
    }//GEN-LAST:event_txfDataFocusLost
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel lblFogo;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdReforma;
    private javax.swing.JLabel lblKm;
    private javax.swing.JLabel lblMarcaModelo;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblValor;
    private javax.swing.JTextField txfCusto;
    private javax.swing.JTextField txfData;
    private javax.swing.JTextField txfInfo;
    private javax.swing.JTextField txfKm;
    private javax.swing.JTextField txfOficina;
    // End of variables declaration//GEN-END:variables

   
}
