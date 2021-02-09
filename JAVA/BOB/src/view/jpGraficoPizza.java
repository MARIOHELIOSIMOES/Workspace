/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import model.Auxiliar;
import model.GraficoItem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author mario
 */
public class jpGraficoPizza extends javax.swing.JPanel {

    public jpGraficoPizza() {
        initComponents();
    }
    
    Auxiliar aux;
    ArrayList<GraficoItem> arraylist;
    String nomeGrafico;
    
    public jpGraficoPizza(String nomeGrafico, ArrayList<GraficoItem> arraylist) {
        initComponents();
        aux = new Auxiliar();
        this.arraylist = arraylist;
        this.nomeGrafico = nomeGrafico;
        carregarDados();
    }
    public void setTamanho(int width, int height){
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
    }
    private DefaultPieDataset getDataset(){
         DefaultPieDataset pieDataset = new DefaultPieDataset();//</code>
           try{
                //Adiciona os dados ao dataSet deve somar um total de 100%
                for(GraficoItem item : arraylist){
                    pieDataset.setValue(item.getNome(), item.getValor());
                }
           } catch(Exception e){
               aux.RegistrarLog(e.getMessage(), "jfGraficoPizza.getDataset");
           }
           return pieDataset;
    }
    private void carregarDados(){
            try{
                JFreeChart grafico = ChartFactory.createPieChart(
                nomeGrafico, 
                getDataset(), 
                true, //Para mostrar ou não a legenda
                true, //Para mostrar ou não os tooltips
                false);
                grafico.setBackgroundPaint(Color.WHITE);
                ChartPanel chartP = new ChartPanel( grafico );
                setLayout(new GridLayout(1, 1));
                add(chartP);
                validate();
                repaint();
            }catch(Exception e){
                aux.RegistrarLog(e.getMessage(), "jfGraficoPizza.carregarDados");
            }
           
    }
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
