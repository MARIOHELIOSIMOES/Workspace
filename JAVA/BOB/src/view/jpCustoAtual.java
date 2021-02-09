package view;

import control.ManutencaoControl;
import control.PedidoOleoControl;
import control.VeiculoCombustivelControl;
import control.VeiculoKMControl;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Auxiliar;
import model.GraficoItem;
import model.Pedido;
import model.PedidoItem;
import model.Veiculo;

/**
 *
 * @author mario
 */
public class jpCustoAtual extends javax.swing.JPanel {

    Veiculo veiculo;
    Auxiliar aux;
    
    public jpCustoAtual() {
        inicializar();
    }
    public jpCustoAtual(Veiculo veiculo){
        inicializar();
        this.veiculo = veiculo;
        atualizarCustos();
    }
    public void atualizarCustos(){
        
        limparCampos();
        lblCombustivel.setText(aux.StringFloatReais(custoCombustivel()));
        lblFiltro.setText(aux.StringFloatReais(custoFiltro()));
        lblFreio.setText(aux.StringFloatReais(custoFreio()));
        lblGeral.setText(aux.StringFloatReais(custoGeral()));
        lblManutencao.setText(aux.StringFloatReais(custoManutencao()));
        lblOleo.setText(aux.StringFloatReais(custoOleo()));
        lblPneu.setText(aux.StringFloatReais(custoPneu()));
        preencherGrafico();

    }
    private void limparCampos(){
        lblCombustivel.setText("");
        lblFiltro.setText("");
        lblFreio.setText("");
        lblGeral.setText("");
        lblManutencao.setText("");
        lblOleo.setText("");
        lblPneu.setText("");
    }
    private void preencherGrafico(){
        try{
            ArrayList<GraficoItem> arraygrafico = new ArrayList<GraficoItem>();
            GraficoItem gi = new GraficoItem();
            gi.setNome("Combustivel");
            gi.setValor((double)custoCombustivel());
            arraygrafico.add(gi);
            gi = new GraficoItem();
            gi.setNome("Oleo");
            gi.setValor((double)custoOleo());
            arraygrafico.add(gi);
            gi = new GraficoItem();
            gi.setNome("Manutenção");
            gi.setValor((double)custoManutencao());
            arraygrafico.add(gi);
            jpGrafico.setLayout(new GridLayout(1, 1));
            jpGrafico.add(new jpGraficoPizza("", arraygrafico));
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustoAtual.preencherGrafico");
        }
    }
    private float custoCombustivel(){
        float custo = 0;
        custo = new VeiculoCombustivelControl().custoKmGeralByIdVeiculo(veiculo.getId(),5);
        
        return custo;
    }
    private float custoFiltro(){
        float custo = 0;
        //custo = new VeiculoCombustivelControl().custoKmUltByIdVeiculo(veiculo.getId());
        
        return custo;
    }
    private float custoFreio(){
        float custo = 0;
       // custo = new VeiculoCombustivelControl().custoKmUltByIdVeiculo(veiculo.getId());
        
        return custo;
    }
    private float custoGeral(){
        float custo = 0, c1=0, c2=0, c3=0,c4=0, c5=0, c6=0;
        c1 = custoCombustivel();
        c2 = custoFiltro();
        c3 = custoFreio();
        //c4 = custoManutencao();
        c5 = custoOleo();
        c6 = custoPneu();
        custo = (c1+c2+c3+c4+c5+c6);
        return custo;
    }
    private float custoManutencao(){
        float custo = 0;
            custo = new ManutencaoControl().custoKmParcial(veiculo.getId());
        
        return custo;
    }
    private float custoOleo(){
        float custo = 0;
            try{
                custo = new PedidoOleoControl().getMediaCustoGeral(veiculo.getId(), 5);
            }catch(Exception e){
                
            }
        return custo;
    }
    private float custoPneu(){
        float custo = 0;
        //custo = new VeiculoCombustivelControl().custoKmUltByIdVeiculo(veiculo.getId());
        return custo;
    }
    private void inicializar(){
        initComponents();
        aux = new Auxiliar();
        limparCampos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblGeral = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCombustivel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblPneu = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblFreio = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblOleo = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblFiltro = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblManutencao = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jpGrafico = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/analiseB.png"))); // NOI18N
        jLabel1.setText("Análise de Custo");
        jLabel1.setOpaque(true);

        lblGeral.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblGeral.setText("R$ 2121,21");

        jLabel3.setText("Reais/Km");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setText("Custo Atual");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abastecimentop.png"))); // NOI18N
        jLabel5.setText("Combustível");

        jLabel6.setText("Reais/Km");

        lblCombustivel.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCombustivel.setForeground(new java.awt.Color(33, 150, 243));
        lblCombustivel.setText("R$ 2121,21");

        jLabel8.setText("Reais/Km");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pneup.png"))); // NOI18N
        jLabel9.setText("Pneu");

        lblPneu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblPneu.setForeground(new java.awt.Color(33, 150, 243));
        lblPneu.setText("R$ 2121,21");

        jLabel11.setText("Reais/Km");

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/freiop.png"))); // NOI18N
        jLabel12.setText("Freio");

        lblFreio.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblFreio.setForeground(new java.awt.Color(33, 150, 243));
        lblFreio.setText("R$ 2121,21");

        jLabel14.setText("Reais/Km");

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/oleop.png"))); // NOI18N
        jLabel15.setText("Óleo");

        lblOleo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblOleo.setForeground(new java.awt.Color(33, 150, 243));
        lblOleo.setText("R$ 2121,21");

        jLabel17.setText("Reais/Km");

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/filtrop.png"))); // NOI18N
        jLabel18.setText("Filtro");

        lblFiltro.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblFiltro.setForeground(new java.awt.Color(33, 150, 243));
        lblFiltro.setText("R$ 2121,21");

        jLabel20.setText("Reais/Km");

        jLabel21.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel21.setText("Manutenção");

        lblManutencao.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblManutencao.setForeground(new java.awt.Color(33, 150, 243));
        lblManutencao.setText("R$ 2121,21");

        jpGrafico.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpGraficoLayout = new javax.swing.GroupLayout(jpGrafico);
        jpGrafico.setLayout(jpGraficoLayout);
        jpGraficoLayout.setHorizontalGroup(
            jpGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );
        jpGraficoLayout.setVerticalGroup(
            jpGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCombustivel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel6)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel14))
                                    .addComponent(lblOleo)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel8))
                                    .addComponent(lblPneu))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel11))
                                    .addComponent(lblFreio))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel20))
                                    .addComponent(lblManutencao)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel17))
                                    .addComponent(lblFiltro)))))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jpGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(lblGeral)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel8))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPneu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFreio, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOleo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblManutencao, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(0, 206, Short.MAX_VALUE))
                                    .addComponent(jpGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        atualizarCustos();
    }//GEN-LAST:event_formFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel jpGrafico;
    private javax.swing.JLabel lblCombustivel;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblFreio;
    private javax.swing.JLabel lblGeral;
    private javax.swing.JLabel lblManutencao;
    private javax.swing.JLabel lblOleo;
    private javax.swing.JLabel lblPneu;
    // End of variables declaration//GEN-END:variables
}
