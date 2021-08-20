package view;

import control.PneuControl;
import control.VeiculoControl;
import control.PneuPosicaoControl;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Pneu;
import model.Veiculo;
import model.PneuPosicao;

/**
 *
 * @author mario
 */
public class jdTrocaPneu extends javax.swing.JDialog {

    Auxiliar aux;
    Veiculo veiculo;
    int posicao = -1;
    ArrayList<Pneu> arraylistPneu;
    ArrayList<PneuPosicao> arraylistVpc;
    PneuControl pc;
    PneuPosicaoControl vpc;
    jpCustoPneu jpPai;
    Pneu pneuAtual, pneuNovo;
    VeiculoControl vc;

    public jdTrocaPneu(jpCustoPneu jpPai, Veiculo veiculo, int posicao) {
        setModal(true);
        inicializar();
        this.veiculo = veiculo;
        this.posicao = posicao;
        this.jpPai = jpPai;
        preencherDadosVeiculo();
        atualizarTela();
    }

    private void inicializar() {
        initComponents();
        setLocationRelativeTo(null);
        aux = new Auxiliar();
        arraylistPneu = new ArrayList<Pneu>();
        arraylistVpc = new ArrayList<PneuPosicao>();
        pc = new PneuControl();
        vpc = new PneuPosicaoControl();
        pneuAtual = new Pneu();
        vc = new VeiculoControl();
    }

    public void atualizarTela() {
        preencherAtual();
        preencherTabelaPneus();
    }

    private void preencherAtual() {
        PneuPosicao vp = vpc.getVeiculoPneuByIdVeiculoAndPosicao(veiculo.getId(), posicao);
        pneuAtual = vp.getIdPneu() == 0 ? new Pneu() : pc.getItemByID(vp.getIdPneu());
        jpInfoPneu.removeAll();
        jpInfoPneu.add(new jpPneu(pneuAtual, veiculo, posicao));
        jpInfoPneu.revalidate();
    }

    private void preencherDadosVeiculo() {
        try {
            lblPlaca.setText(veiculo.getPlaca());
            lblConfi.setText(Veiculo.CONF_LABELS[veiculo.getConfiguracao()]);
            lblPosicao.setText((posicao + 1) + "");
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jdTrocaPneu.preencherDAdosVeiculo");
        }
    }

    private void preencherTabelaPneus() {
        Thread t = new Thread(() -> {
            DefaultTableModel model = new DefaultTableModelNaoEditavel();
            try {
                model.addColumn("Fogo");
                model.addColumn("Marca");
                model.addColumn("Modelo");
                model.addColumn("Reformas");
                model.addColumn("Km Total");
                model.addColumn("Valor");
                model.addColumn("Localização");

                preencherArrays();
                Object[] linha = new Object[7];
                for (int i = 0; i < arraylistPneu.size(); i++) {
                    Pneu p = arraylistPneu.get(i);
                    PneuPosicao vp = vpc.getVeiculoPneuByIdPneu(p.getId());

                    linha[0] = p.getFogo();
                    linha[1] = p.getMarca();
                    linha[2] = p.getModelo();
                    linha[3] = pc.getQtdeReformasByIdPneu(p.getId());
                    linha[4] = p.getKmTotal();
                    linha[5] = aux.StringFloatReais(p.getValor());
                    linha[6] = vp.getIdVeiculo() == 0 ? "Estoque" : vc.getVeiculoById(vp.getIdVeiculo()).getPlaca();

                    model.addRow(linha);
                }
            } catch (Exception e) {
                aux.RegistrarLog(e.getMessage(), "jdTrocaPneu.preencherTabelaPneus");
            } finally {
                jTable.setModel(model);
            }
        });
        t.start();

    }

    private void preencherArrays() {
        try {
            arraylistPneu.clear();
            if (ckEstoque.isSelected()) {
                arraylistPneu.addAll(pc.getArrayListPneusEstoque());
                //arraylistPneu = pc.getArrayListPneusEstoque();
            }
            if (ckRodando.isSelected()) {
                //arraylistPneu = pc.getArrayListPneusRodando();
                arraylistPneu.addAll(pc.getArrayListPneusRodando());
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jdTrocaPneu.preencherArrays");
        }
    }

    private void preencherSelectedTable() {
        try {
            int i = jTable.getSelectedRow();
            jpInfoPneu.removeAll();
            pneuNovo = arraylistPneu.get(i);
            if(pneuNovo.getStatus()==Pneu.ESTOQUE){
                jpInfoPneu.add(new jpPneu(pneuNovo, veiculo, posicao));
            }else{
                jpInfoPneu.add(new jpPneu(pneuNovo, veiculo, posicao, true));
            }
            jpInfoPneu.revalidate();

            // pack();
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jdTrocaPneu.preencherSelectedTable");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblPosicao = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblConfi = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblPlaca = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        ckEstoque = new javax.swing.JCheckBox();
        ckRodando = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jpInfoPneu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleção de Pneu");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSalvar.setBackground(new java.awt.Color(0, 102, 255));
        btnSalvar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvarp.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Posição");
        jPanel2.add(jLabel4);

        lblPosicao.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lblPosicao.setForeground(new java.awt.Color(255, 0, 0));
        lblPosicao.setText("4");
        jPanel2.add(lblPosicao);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Configuração");
        jPanel2.add(jLabel3);

        lblConfi.setText("6 X 2");
        jPanel2.add(lblConfi);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Placa");
        jPanel2.add(jLabel2);

        lblPlaca.setText("DLH 8657");
        jPanel2.add(lblPlaca);

        btnExcluir.setBackground(new java.awt.Color(255, 0, 0));
        btnExcluir.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/freiop.png"))); // NOI18N
        btnExcluir.setText("Remover");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pneus", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(464, 250));

        ckEstoque.setSelected(true);
        ckEstoque.setText("Estoque");
        ckEstoque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckEstoqueItemStateChanged(evt);
            }
        });

        ckRodando.setSelected(true);
        ckRodando.setText("Rodando");
        ckRodando.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckRodandoItemStateChanged(evt);
            }
        });

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
        jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(ckEstoque)
                .addGap(18, 18, 18)
                .addComponent(ckRodando)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckEstoque)
                    .addComponent(ckRodando))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
        );

        jpInfoPneu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecionado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jpInfoPneu.setPreferredSize(new java.awt.Dimension(0, 100));
        jpInfoPneu.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpInfoPneu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpInfoPneu, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void efetivarTrocaPneu(int operacao, float valor) {
        if (!jfPrincipal.isUserOperaOrAdmin()) {
            aux.showMessagemSemPermissao();
            return;
        }
        if (vpc.Salvar(veiculo.getId(), pneuAtual, pneuNovo, posicao, operacao, valor)) {
            aux.showMessageInformacao("Salvo com sucesso!", "Movimentação de Pneu");
            atualizarTela();
            jpPai.atualizarTela();
        } else {
            aux.showMessageInformacao("Falha ao Salvar!!", "Movimentação de Pneu");
        }

    }
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(pneuNovo.getStatus()==Pneu.ESTOQUE){
            if (pneuAtual.getId() != 0) {
                new jdPneuDestino(this, pneuAtual).setVisible(true);
            } else {
                efetivarTrocaPneu(Pneu.ESTOQUE, 0);
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        jpPai.atualizarTela();
    }//GEN-LAST:event_formWindowClosed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        preencherSelectedTable();
    }//GEN-LAST:event_jTableMouseClicked

    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyReleased
        preencherSelectedTable();
    }//GEN-LAST:event_jTableKeyReleased

    private void ckEstoqueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckEstoqueItemStateChanged
        preencherTabelaPneus();
    }//GEN-LAST:event_ckEstoqueItemStateChanged

    private void ckRodandoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckRodandoItemStateChanged
        preencherTabelaPneus();
    }//GEN-LAST:event_ckRodandoItemStateChanged

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        pneuNovo = new Pneu();
        if (pneuAtual.getId() != 0) {
            new jdPneuDestino(this, pneuAtual).setVisible(true);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox ckEstoque;
    private javax.swing.JCheckBox ckRodando;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel jpInfoPneu;
    private javax.swing.JLabel lblConfi;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblPosicao;
    // End of variables declaration//GEN-END:variables

}
