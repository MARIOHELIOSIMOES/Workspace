package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import model.Auxiliar;
import model.GraficoItem;
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
public class jpGraficoLinha extends javax.swing.JPanel {

    /**
     * Creates new form jpGraficoBarra
     */
    public jpGraficoLinha() {
        initComponents();
    }
    Auxiliar aux;
    ArrayList<GraficoItem> arraylist;
    String nomeGrafico;

    public jpGraficoLinha(String nomeGrafico, ArrayList<GraficoItem> arraylist) {
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
            for (GraficoItem gi : arraylist) {
                //dataset.addValue(gi.getValor(), gi.getCat1(), gi.getNome());
                //dataset.addValue(gi.getValor2(), gi.getCat2(), gi.getNome());
                dataset.addValue(gi.getValor(), "Km por Litro" , gi.getNome());
            }

        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jfGraficoLInha.getDataset");
        }
        return dataset;
    }

    private void carregarDados() {
        try {
            // JFreeChart grafico = ChartFactory.createBarChart(nomeGrafico, "Registros", "Custo/Km", getDataset());
            JFreeChart grafico = ChartFactory.createLineChart(nomeGrafico, "", "", getDataset());
            //JFreeChart grafico = ChartFactory.createBarChart3D(nomeGrafico, "", "", getDataset());
            grafico.setBackgroundPaint(Color.WHITE);
            grafico.setBorderPaint(Color.BLACK);
            ChartPanel chartP = new ChartPanel(grafico);
            //jpraiz.add(chartP);
            setLayout(new GridLayout(0, 1));
            //setLayout(new FlowLayout());
            add(chartP);
            validate();
            repaint();
            //this.setContentPane(chartP);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jfGraficoLinha.carregarDados");
        }
        // this.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        validate();
        repaint();
    }//GEN-LAST:event_formComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
