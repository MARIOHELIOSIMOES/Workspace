/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.PneuControl;
import control.PneuDesgasteControl;
import control.PneuPosicaoControl;
import control.PneuReformaControl;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Pneu;
import model.Veiculo;
import model.PneuPosicao;

/**
 *
 * @author mario
 */
public class jpCustoPneu extends javax.swing.JPanel implements MouseListener {

    Auxiliar aux;
    Veiculo veiculo;
    ArrayList<Pneu> arrayListPneu;
    ArrayList<PneuPosicao> arrayListPneuPosicao;
    int posicao = 0;
    PneuPosicaoControl ppc;
    PneuReformaControl prc;
    PneuControl pc;
    PneuDesgasteControl pdc;

    public jpCustoPneu() {
        initComponents();
    }

    public jpCustoPneu(Veiculo veiculo) {
        inicializar();
        this.veiculo = veiculo;
        preencherArrays(); // !!!!!!!!!!!primeiro método a ser chamado!!!!!
        preencherTabela();
        preencherMontagemPneus();
        preencherImagem();
    }

    public void atualizarTela() {
        try {
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                  
                }
            }).start();*/
            preencherArrays();
            preencherTabela();
            preencherMontagemPneus();
            preencherImagem();
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCustoPneu.AtualizarTela");
        }

    }

    private void inicializar() {
        initComponents();
        aux = new Auxiliar();
        //veiculo = new Veiculo();
        arrayListPneu = new ArrayList<Pneu>();
        arrayListPneuPosicao = new ArrayList<PneuPosicao>();
        ppc = new PneuPosicaoControl();
        pc = new PneuControl();
        prc = new PneuReformaControl();
        pdc = new PneuDesgasteControl();
    }

    private void preencherImagem(){
        jpImagemConfi.removeAll();
        
        jpImagemConfi.add(new jpImagemConfiguracao(veiculo.getConfiguracao()));
        revalidate();
    }
        
    private void preencherArrays() {
        arrayListPneuPosicao = ppc.getArrayListVeiculoPneuByIdVeiculo(veiculo.getId());
        int[] ids = new int[arrayListPneuPosicao.size()];

        for (int i = 0; i < arrayListPneuPosicao.size(); i++) {
            ids[i] = arrayListPneuPosicao.get(i).getIdPneu();
        }

        arrayListPneu = pc.getArrayListByIds(ids);
        ordenarArrays();

    }

    private void ordenarArrays() {
        if (arrayListPneu.size() != arrayListPneuPosicao.size()) {
            System.out.println("Tamanhos de arrays diferentes!");
            return;
        }
        arrayListPneu.sort(new Comparator<Pneu>() {
            @Override
            public int compare(Pneu o1, Pneu o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        arrayListPneuPosicao.sort(new Comparator<PneuPosicao>() {
            @Override
            public int compare(PneuPosicao o1, PneuPosicao o2) {
                if (o1.getIdPneu() > o2.getIdPneu()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        );
    }

    private void preencherTabela() {
        DefaultTableModel model = new DefaultTableModelNaoEditavel();
        TableRowSorter tableSorter = new TableRowSorter(model);
        try {
            model.addColumn("Data Montagem");
            model.addColumn("Posição");
            model.addColumn("Custo Médio");
            model.addColumn("Fogo");
            model.addColumn("Marca");
            model.addColumn("Modelo");
            model.addColumn("Reformas");
            model.addColumn("Km no Veiculo");
            model.addColumn("Km do Pneu");
            //model.addColumn("Km Rodados Tração");
            //model.addColumn("Km Total");
            //model.addColumn("Custo KM");
            //model.addColumn("Situação");

            Pneu pneu;
            PneuPosicao pneuPosicao;
            int kmPercorrido = 0;
            Object[] linha = new Object[9];
            
            for (int i = 0; i < arrayListPneuPosicao.size(); i++) {
                pneu = arrayListPneu.get(i);
                pneuPosicao = arrayListPneuPosicao.get(i);
                kmPercorrido = pneu.getKmTotal()- pneuPosicao.getKm();
                
                linha[0] = aux.getDataString(pneuPosicao.getDatamilis());
                linha[1] = (pneuPosicao.getPosicao() + 1);
                linha[2] = aux.StringFloatReais(pdc.getCustoDesgasteByIdVeiculoByPosicao(veiculo.getId(), pneuPosicao.getPosicao()));
                linha[3] = pneu.getFogo();
                linha[4] = pneu.getMarca();
                linha[5] = pneu.getModelo();
                linha[6] = prc.getQtdeReformasByIdPneu(pneu.getId());
                linha[7] = kmPercorrido;
                linha[8] = pneu.getKmTotal();
                //linha[7] = arrayListPneu.get(i).getKmTracao();
                //linha[8] = arrayListPneu.get(i).getKmTotal();
                //linha[7] = aux.CustoKMString(arrayListPneu.get(i).getKmTotal(), arrayListPneu.get(i).getValor());
              //  linha[8] = "Rodando";
                model.addRow(linha);
            }
            model.setNumRows(arrayListPneu.size());
            tableSorter = new TableRowSorter(model);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCustoPneu.preencherTabela");
        } finally {
            jTable.setModel(model);

            jTable.setRowSorter(tableSorter);
            tableSorter.toggleSortOrder(1); // Esta linha seleciona a coluna padrão ordenada
        }
    }

    private void preencherMontagemPneus() {
        try {
            //preencherArrayMock();
            //BufferedImage img = ImageIO.read(new File("C:\\Projetos\\Netbeans\\SAAE MARMITEX\\src\\View\\4X2.jpg"));
            //jpMontagem.getGraphics().drawImage(img.getScaledInstance(500, 200, 0), 0, 0, null);
            
            int l = veiculo.getQtdePneu()>2? 3: 2;
            
            GridLayout glayout = new GridLayout(0, l);
            
            
            glayout.setHgap(5);
            glayout.setVgap(5);

            jpMontagem.setLayout(glayout);
            jpMontagem.removeAll();

            ArrayList<Pneu> arraypPneus = new ArrayList<>();
            Pneu p;
            for (int i = 0; i < veiculo.getQtdePneu(); i++) {
                p = new Pneu();
                arraypPneus.add(p);
                jpPneu jppneu = new jpPneu(new Pneu(), veiculo, i);
                jppneu.addMouseListener(this);
                jpMontagem.add(jppneu);
            }
            for (int i = 0; i < arrayListPneu.size(); i++) {
                posicao = arrayListPneuPosicao.get(i).getPosicao();
                posicao = (posicao > veiculo.getQtdePneu()) ? -1 : posicao;
                if (posicao == -1) {
                    continue;
                }
                // System.out.println("posicao: "+posicao);
                jpPneu jppneu = new jpPneu(arrayListPneu.get(i), veiculo, posicao);
                jppneu.addMouseListener(this);
                jpMontagem.remove(posicao);
                jpMontagem.add(jppneu, posicao);
            }
            
            
            jpMontagem.revalidate();

        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCustoPneu.preencherMontagemPneus");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jpImagemConfi = new javax.swing.JPanel();
        jpMontagem = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informações sobre os Pneus");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações sobre os pneus", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
        );

        jpImagemConfi.setBackground(new java.awt.Color(255, 255, 255));
        jpImagemConfi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuracao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jpImagemConfi.setMinimumSize(new java.awt.Dimension(0, 0));
        jpImagemConfi.setName(""); // NOI18N
        jpImagemConfi.setPreferredSize(new java.awt.Dimension(400, 200));
        jpImagemConfi.setLayout(new java.awt.GridLayout(0, 1));

        jpMontagem.setBackground(new java.awt.Color(255, 255, 255));
        jpMontagem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Montagem Atual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

        javax.swing.GroupLayout jpMontagemLayout = new javax.swing.GroupLayout(jpMontagem);
        jpMontagem.setLayout(jpMontagemLayout);
        jpMontagemLayout.setHorizontalGroup(
            jpMontagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        jpMontagemLayout.setVerticalGroup(
            jpMontagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpImagemConfi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpMontagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpImagemConfi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpMontagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel jpImagemConfi;
    private javax.swing.JPanel jpMontagem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() > 1) {
            try {
                jpPneu jp = (jpPneu) e.getComponent();
                jdTrocaPneu jdt = new jdTrocaPneu(this, veiculo, jp.getPosicao());
                // jdt.setAlwaysOnTop(true);
                jdt.setVisible(true);
            } catch (Exception ex) {
                aux.RegistrarLog(ex.getMessage(), "jpCustoPneu.mouseClicked");
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
