package view;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.VeiculoConfiguracao;

/**
 *
 * @author mario
 */
public class jpVeiculoConfiguracao extends javax.swing.JPanel {

    VeiculoConfiguracao veiculoConfiguracao;
    ArrayList<JCheckBox> arrayCk;
    ArrayList<Integer>arrayTracao;
    boolean[] rodasTracao;
    
    public int [] getRodasTracao(){
        arrayTracao = new ArrayList<Integer>();
        
        for(int i = 0; i< arrayCk.size(); i++){
            if(arrayCk.get(i).isSelected()){
                arrayTracao.add(i);
            }
        }
        int[] rodas = new int[arrayTracao.size()];
        int p = 0;
        for(int j: arrayTracao){
            rodas[p]=j;
            p++;
        }
        return rodas;
    }
    public jpVeiculoConfiguracao(VeiculoConfiguracao vc) {
        inicializar();
        this.veiculoConfiguracao = vc;
        preencherTracao();
        atualizarTela();
    }
    private void preencherTracao(){
        rodasTracao = new boolean[veiculoConfiguracao.getnRodas()];
        int p = 0;
        for(int i: veiculoConfiguracao.getPosicoesTracao()){
            if(i>=veiculoConfiguracao.getnRodas()){
                continue; //Se o índice for maior que a quantidade de rodas deverá ignorar
            }
            rodasTracao[i] = true;
        }
    }
    private void inicializar(){
        initComponents();
        veiculoConfiguracao = new VeiculoConfiguracao();
        arrayCk = new ArrayList<>();
    }
    private void atualizarTela(){
        
        JPanel roda;
        ArrayList<JPanel> arrayRodas = new ArrayList<>();
        
        JLabel lblPosicao;
        JCheckBox ck;
        
        for(int i = 0; i < veiculoConfiguracao.getnRodas(); i++){ //Preenche os swings com os rodas
            lblPosicao = new JLabel();
            lblPosicao.setText(" "+(i+1));
            lblPosicao.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
            lblPosicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pneu.png"))); // NOI18N
            
            ck = new JCheckBox("Roda de Tracao", rodasTracao[i]);
            
            roda = new JPanel(new GridLayout(2, 1));
            roda.setBackground(Color.WHITE);
            roda.add(lblPosicao);
            roda.add(ck);
            
            arrayCk.add(ck);
            arrayRodas.add(roda);
        }
        
        jpCoringa.removeAll();
        jpCoringa.setLayout(new GridLayout(2, veiculoConfiguracao.getnEixos()));
        for(JPanel panel: arrayRodas){
            jpCoringa.add(panel);
        }
        jpCoringa.revalidate();
        jpCoringa.repaint();
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jpCoringa = new javax.swing.JPanel();
        lblRoda = new javax.swing.JLabel();
        ckRoda = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout(2, 2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Traseira");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel1, java.awt.BorderLayout.LINE_END);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Direita");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Frente");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel3, java.awt.BorderLayout.LINE_START);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Esquerda");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel4, java.awt.BorderLayout.PAGE_END);

        jpCoringa.setBackground(new java.awt.Color(255, 255, 255));
        jpCoringa.setLayout(new java.awt.GridLayout(2, 0));

        lblRoda.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblRoda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pneu.png"))); // NOI18N
        lblRoda.setText("1");
        jpCoringa.add(lblRoda);

        ckRoda.setBackground(new java.awt.Color(255, 255, 255));
        ckRoda.setText("Roda de Tração");
        jpCoringa.add(ckRoda);

        add(jpCoringa, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ckRoda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jpCoringa;
    private javax.swing.JLabel lblRoda;
    // End of variables declaration//GEN-END:variables
}
