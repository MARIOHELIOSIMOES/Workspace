/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arraypanelspersonalizado;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mario
 */
public class jpConfPneus extends javax.swing.JPanel {

    /**
     * Creates new form jpConfPneus
     */
    public jpConfPneus() {
        initComponents();
        carregarPneus(6);
    }
    private void carregarPneus(int nP){
        setLayout(new GridLayout(2, nP/2));
        for(int i = 0; i< nP; i++){
            //add(new jpPneu(i), 1);
            add(new JLabel(""+i));
            System.out.println("i: "+ i);
            System.out.println("Componente: "+ getComponentCount());//
        }
        
        System.out.println("Componente Antes remover: "+ getComponentCount());//
        remove(3);
        System.out.println("Componente Apos remover: "+ getComponentCount());//
        add(new jpPneu(3), 3);
        System.out.println("Componente Apos adicionar: "+ getComponentCount());//
        remove(4);
        add(new jpPneu(4), 4);
        remove(5);
        add(new jpPneu(5), 5);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout(2, 0));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}