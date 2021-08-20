package view;

import control.PneuControl;
import model.Auxiliar;
import model.Pneu;

/**
 *
 * @author mario
 */
public class jdPneuDestino extends javax.swing.JDialog {

    Pneu pneu;
    jdTrocaPneu parent;
    Auxiliar aux;
    
    private static String ESTOQUE = "Valor estimado de desgaste do pneu para calcular o custo";
    private static String SUCATA = "Custo do pneu não sofrerá abatimento de valor";
    private static String VENDA = "Valor obtido com a venda do pneu";
    private static String ZERO = "0";
    private int operacao = Pneu.ESTOQUE;
    
    
    public jdPneuDestino() {
        inicializar();
    }
    public jdPneuDestino(jdTrocaPneu parent, Pneu pneu){
        inicializar();
        this.parent = parent;
        this.pneu = pneu;
        preencherValoresSlider();
       
    }

    private void atualizarRadiosButton(){
        if(rbtnEstoque.isSelected()){
            setLblInfo(ESTOQUE, true);
            operacao = Pneu.ESTOQUE;
        }else if(rbtnSucata.isSelected()){
            setLblInfo(SUCATA, false);
            operacao = Pneu.SUCATA;
            txfValor.setText(ZERO);
        }else{
            setLblInfo(VENDA, true);
            operacao = Pneu.VENDIDO;
        }
    }
    private void setLblInfo(String msg, boolean enable){
        lblInfo.setText(msg);
        txfValor.setEnabled(enable);
    }
    
    private void inicializar(){
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        aux = new Auxiliar();
        sldValor.addChangeListener((e) -> {
           atualizartxfValor();
        });
        setLblInfo(ESTOQUE, true);
        
    }
    private void preencherValoresSlider(){
        PneuControl pc = new PneuControl();
        float custoAtual = pc.getValorAtualByIdPneu(pneu.getId());
        sldValor.setMaximum((int) custoAtual);
        sldValor.setValue(0);
        sldValor.setMajorTickSpacing((int) custoAtual / 5);
        sldValor.revalidate();
    }
    private void atualizartxfValor(){
        txfValor.setText(sldValor.getValue()+"");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgDestinos = new javax.swing.ButtonGroup();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        lblInfo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jpBase = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        rbtnEstoque = new javax.swing.JRadioButton();
        rbtnSucata = new javax.swing.JRadioButton();
        rbtnVenda = new javax.swing.JRadioButton();
        sldValor = new javax.swing.JSlider();
        txfValor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setModal(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        btnCancelar.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(51, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        lblInfo.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(51, 51, 51));
        lblInfo.setText("Valor estimado de desgaste do pneu");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Qual será a destinação do pneu atual? ");
        jLabel1.setOpaque(true);

        jpBase.setBackground(new java.awt.Color(255, 255, 255));
        jpBase.setLayout(new java.awt.GridLayout(0, 1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        rbtnEstoque.setBackground(new java.awt.Color(255, 255, 255));
        rbgDestinos.add(rbtnEstoque);
        rbtnEstoque.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        rbtnEstoque.setSelected(true);
        rbtnEstoque.setText("Estoque");
        rbtnEstoque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbtnEstoque.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rbtnEstoque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtnEstoqueItemStateChanged(evt);
            }
        });
        jPanel1.add(rbtnEstoque);

        rbtnSucata.setBackground(new java.awt.Color(255, 255, 255));
        rbgDestinos.add(rbtnSucata);
        rbtnSucata.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        rbtnSucata.setText("Sucata");
        rbtnSucata.setEnabled(false);
        rbtnSucata.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbtnSucata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rbtnSucata.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtnSucataItemStateChanged(evt);
            }
        });
        jPanel1.add(rbtnSucata);

        rbtnVenda.setBackground(new java.awt.Color(255, 255, 255));
        rbgDestinos.add(rbtnVenda);
        rbtnVenda.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        rbtnVenda.setText("Venda");
        rbtnVenda.setEnabled(false);
        rbtnVenda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbtnVenda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rbtnVenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtnVendaItemStateChanged(evt);
            }
        });
        jPanel1.add(rbtnVenda);

        jpBase.add(jPanel1);

        sldValor.setBackground(new java.awt.Color(255, 255, 255));
        sldValor.setMajorTickSpacing(100);
        sldValor.setMinorTickSpacing(10);
        sldValor.setPaintLabels(true);
        sldValor.setPaintTicks(true);
        sldValor.setToolTipText("");
        sldValor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sldValor.setName("slider valores"); // NOI18N
        sldValor.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                sldValorCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jpBase.add(sldValor);

        txfValor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txfValor.setText("R$ 150,00");
        jpBase.add(txfValor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblInfo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jpBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnConfirmar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sldValorCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_sldValorCaretPositionChanged
        atualizartxfValor();
    }//GEN-LAST:event_sldValorCaretPositionChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        atualizarParent();
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        
        parent.efetivarTrocaPneu(operacao, Float.parseFloat(txfValor.getText()));
        dispose();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void rbtnEstoqueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtnEstoqueItemStateChanged
        atualizarRadiosButton();
    }//GEN-LAST:event_rbtnEstoqueItemStateChanged

    private void rbtnSucataItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtnSucataItemStateChanged
        atualizarRadiosButton();        
    }//GEN-LAST:event_rbtnSucataItemStateChanged

    private void rbtnVendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtnVendaItemStateChanged
        atualizarRadiosButton();
    }//GEN-LAST:event_rbtnVendaItemStateChanged

    private void atualizarParent(){
        parent.atualizarTela();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpBase;
    private javax.swing.JLabel lblInfo;
    private javax.swing.ButtonGroup rbgDestinos;
    private javax.swing.JRadioButton rbtnEstoque;
    private javax.swing.JRadioButton rbtnSucata;
    private javax.swing.JRadioButton rbtnVenda;
    private javax.swing.JSlider sldValor;
    private javax.swing.JTextField txfValor;
    // End of variables declaration//GEN-END:variables
}
