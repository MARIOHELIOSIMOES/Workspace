package jfreechart;

import java.awt.Color;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;



public class JFGraficoBarra extends javax.swing.JFrame {

    public JFGraficoBarra() {
        initComponents();
        carregarDados();
    }
    private static CategoryDataset getDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(7445, "JFreeSVG", "Warm-up");
        //dataset.addValue(24448, "Batik", "Warm-up");
        dataset.addValue(4297, "JFreeSVG", "Test");
        //dataset.addValue(21022, "Batik", "Test");
        dataset.addValue(7445, "JFreeSVG", "Categoria");
        //dataset.addValue(24448, "Batik", "Categoria");
        dataset.addValue(4297, "JFreeSVG", "B");
        //dataset.addValue(21022, "Batik", "B");
        return dataset;
    }
    private void carregarDados(){
       //Cria um dataSet para inserir os dados que serão passados para a criação do gráfico tipo Pie
            //DefaultPieDataset pieDataset = new DefaultPieDataset();//</code>
            
            try{
            //Adiciona os dados ao dataSet deve somar um total de 100%
            /*pieDataset.setValue(("Oleo"),7);
            pieDataset.setValue(("Manutencao"),5);
            pieDataset.setValue(("Combustivel"),4);
            pieDataset.setValue(("Outros"),6);
            */

            //Cria um objeto JFreeChart passando os seguintes parametros
            JFreeChart grafico = ChartFactory.createBarChart("title", "categoryAxisLabel", "valueAxisLabel", getDataset());
            grafico.setBackgroundPaint(new ChartColor(255, 255, 255));

            ChartPanel chartP = new ChartPanel( grafico );
            
            
            this.setContentPane(chartP);
            //getRootPane().setContentPane(chartP);
            //this.add(chartP, JLayeredPane.FRAME_CONTENT_LAYER);
            }catch(Exception e){
                
            }
            
            this.pack();
    }
    

    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
       this.pack();
    }//GEN-LAST:event_formComponentResized

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFGraficoBarra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
