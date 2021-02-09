/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arraypanelspersonalizado;

import java.util.GregorianCalendar;

/**
 *
 * @author mario
 */
public class jpLinha extends javax.swing.JPanel {

    String item="nome", data=""; 
    int kmTroca=0, kmAtual=0, kmProxima=0;
    long datamilis=0;
    boolean feito = true;
    public jpLinha(String item, String data, int kmTroca, int kmAtual, int kmProxima, boolean feito) {
        initComponents();
        this.item = item;
        this.data = data;
        this.kmAtual = kmAtual;
        this.kmTroca = kmTroca;
        this.kmProxima = kmProxima;
        atualizarTela();
    }
    private void atualizarTela(){
        txfItem.setText(item);
        txfData.setText(data);
        barKM.setMinimum(kmTroca);
        barKM.setMaximum(kmProxima);
        barKM.setValue(kmAtual);
        barKM.setString(kmAtual+"");
        barKM.setStringPainted(true);
        ckFeito.setSelected(feito);
        validate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txfItem = new javax.swing.JTextField();
        ckFeito = new javax.swing.JCheckBox();
        barKM = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        txfData = new javax.swing.JTextField();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jLabel1.setText("Item");

        txfItem.setText("FILTRO DE AR");
        txfItem.setMinimumSize(new java.awt.Dimension(150, 20));
        txfItem.setPreferredSize(new java.awt.Dimension(150, 20));

        ckFeito.setText("Feito");

        jLabel2.setText("Data");

        txfData.setText("12/12/2020");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckFeito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(txfItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(ckFeito))
            .addComponent(barKM, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel2)
            .addComponent(txfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        validate();
        repaint();
    }//GEN-LAST:event_formComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barKM;
    private javax.swing.JCheckBox ckFeito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txfData;
    private javax.swing.JTextField txfItem;
    // End of variables declaration//GEN-END:variables
}
