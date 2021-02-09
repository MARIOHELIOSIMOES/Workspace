package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import model.Auxiliar;
import model.GraficoItemC;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author mario
 */
public class jpGraficoBarra2 extends javax.swing.JFrame {

    Auxiliar aux;
    ArrayList<GraficoItemC> arraylist;
    String nomeGrafico;

    public jpGraficoBarra2(String nomeGrafico, ArrayList<GraficoItemC> arraylist) {
        initComponents();
        aux = new Auxiliar();
        this.arraylist = arraylist;
        this.nomeGrafico = nomeGrafico;
        carregarDados();
    }

    public void setTamanho(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
    }

    private CategoryDataset getDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            //Adiciona os dados ao dataSet deve somar um total de 100%
            for (GraficoItemC gi : arraylist) {
                dataset.addValue(gi.getValor(), gi.getCat1(), gi.getNome());
                dataset.addValue(gi.getValor2(), gi.getCat2(), gi.getNome());
                dataset.addValue(gi.getValor3(), gi.getCat3(), gi.getNome());
            }

        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jfGraficoBarra.getDataset");
        }
        return dataset;
    }

    private void carregarDados() {
        try {
            // JFreeChart grafico = ChartFactory.createBarChart(nomeGrafico, "Registros", "Custo/Km", getDataset());
            JFreeChart grafico = ChartFactory.createBarChart3D(nomeGrafico, "", "", getDataset());
            grafico.setBackgroundPaint(Color.WHITE);
            grafico.setBorderPaint(Color.BLACK);
            ChartPanel chartP = new ChartPanel(grafico);
            jpraiz.add(chartP);
            //this.setContentPane(chartP);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jfGraficoPizza.carregarDados");
        }
        // this.pack();
    }

    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpraiz = new javax.swing.JPanel();

        jpraiz.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jpraiz.setPreferredSize(new java.awt.Dimension(150, 100));
        jpraiz.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jpraizComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jpraizLayout = new javax.swing.GroupLayout(jpraiz);
        jpraiz.setLayout(jpraizLayout);
        jpraizLayout.setHorizontalGroup(
            jpraizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jpraizLayout.setVerticalGroup(
            jpraizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpraiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpraiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jpraizComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpraizComponentResized
        //  this.pack();
    }//GEN-LAST:event_jpraizComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpraiz;
    // End of variables declaration//GEN-END:variables
}
