package view;

import control.PedidoOleoControl;
import control.UsuarioControl;
import control.VeiculoCombustivelControl;
import control.VeiculoKMControl;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.GraficoItemC;
import model.Usuario;
import model.Veiculo;
import model.VeiculoCombustivel;

/**
 *
 * @author mario
 */
public class jpCustoCombustivel extends javax.swing.JPanel {

    private Usuario usuario;
    private Veiculo veiculo;
    private ArrayList<VeiculoCombustivel> arraylist;
    private VeiculoCombustivelControl vcc;
    private Auxiliar aux;

    public jpCustoCombustivel() {
        inicializar();
    }
    public jpCustoCombustivel(Usuario usuario, Veiculo veiculo){
        inicializar();
        this.usuario = usuario;
        this.veiculo = veiculo;
        atualizarTela();
    }
    private void inicializar(){
        initComponents();
        arraylist = new ArrayList<VeiculoCombustivel>();
        veiculo = new Veiculo();
        usuario = new Usuario();
        vcc = new VeiculoCombustivelControl();
        aux = new Auxiliar();
        
    }
    public void atualizarTela(){
        lblKmAtual.setText(new VeiculoKMControl().getUltimoKmByIDVeiculo(veiculo.getId())+"");
        preencherTabela();
        calcularMediaGeral();
        calcularMediaUltimo();
        preencherGrafico();
    }
    private void preencherTabela(){
        try{
            arraylist = new VeiculoCombustivelControl().getArrayListByIDVeiculo(veiculo.getId());
            
            
            DefaultTableModel tableModel = new DefaultTableModelNaoEditavel();
                tableModel.addColumn("Nº");//1
                tableModel.addColumn("Data");//2
                tableModel.addColumn("Posto");//3
                tableModel.addColumn("Combustível");//4
                tableModel.addColumn("Motorista");//5
                tableModel.addColumn("KM Inicial");//6
                tableModel.addColumn("KM Percorrido");//7
                tableModel.addColumn("Litragem");//8
                tableModel.addColumn("Valor");//9
                tableModel.addColumn("Km / Litro");//10
                tableModel.addColumn("R$ / Km");//11

                Auxiliar aux = new Auxiliar();
                VeiculoCombustivelControl vcc = new VeiculoCombustivelControl();
                VeiculoKMControl vkm = new VeiculoKMControl();
                UsuarioControl uc = new UsuarioControl();
                int kmAtual = vkm.getUltimoKmByIDVeiculo(veiculo.getId());
                int kmAnterior = 0, kmPercorrido=0;
                
            for(int i=arraylist.size()-1; i>=0; i--){
                
                float media = 0, custo =0;
                
                kmAnterior = arraylist.get(i).getKm();
                media = vcc.mediaConsumo(kmAtual,kmAnterior , arraylist.get(i).getLitros());
                kmPercorrido = kmAtual - kmAnterior;
                kmAtual = kmAnterior;
                
                custo = vcc.CustoKm(arraylist.get(i).getValor(), arraylist.get(i).getLitros(), media);
                
                Object[] item = new Object[11];
                item[0]=i+1;
                item[1]=aux.getDataString(arraylist.get(i).getDataMilis());
                item[2]=arraylist.get(i).getPosto();
                item[3]=arraylist.get(i).getCombustivel();
                item[4]= uc.getNomeUsuarioById(arraylist.get(i).getIdUsuario());
                item[5]= arraylist.get(i).getKm();
                item[6]=kmPercorrido;
                item[7]=arraylist.get(i).getLitros();
                item[8]=aux.StringFloatReais(arraylist.get(i).getValor());
                item[9]=media;
                item[10]=aux.StringFloatReais(custo);
                tableModel.addRow(item);
            }
            jTable.setModel(tableModel);
            jTable.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable.getColumnModel().getColumn(1).setMaxWidth(70);
            
            
            
       
        }catch (Exception e){
            
        }
    }
    private void calcularMediaGeral(){
        float media=0, custo=0;
        media = vcc.mediaConsumoGeralByIdVeiculo(veiculo.getId(), 5);//5 é a quantidade de registros que entrará no calculo da media
        custo = vcc.custoKmGeralByIdVeiculo(veiculo.getId(), 5);
        lblConsumoGeral.setText(aux.numeroFormatadoDuasCasasString(media));
        lblCustoKmGeral.setText(aux.StringFloatReais(custo));
    }
    private void calcularMediaUltimo(){
        float media=0, custo=0;
        media = vcc.mediaConsumoUltByIdVeiculo(veiculo.getId());//5 é a quantidade de registros que entrará no calculo da media
        custo = vcc.custoKmUltByIdVeiculo(veiculo.getId());
        lblConsumoUlt.setText(aux.numeroFormatadoDuasCasasString(media));
        lblCustoKmUlt.setText(aux.StringFloatReais(custo));
    }
    private void preencherGrafico(){
        try{
            
            ArrayList<GraficoItemC> arraygrafico = vcc.getArrayGraficoC(veiculo.getId(), 5);
            jpGrafico.removeAll();
            jpGrafico.setLayout(new GridLayout(1,1));
            //jpGrafico.setLayout(new SpringLayout());
            jpGrafico.add(new jpGraficoBarra("", arraygrafico));
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustoCombustivel.preencherGrafico");
        }
        //pane.add(button, c);
            
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblKmAtual = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblConsumoUlt = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblCustoKmUlt = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblConsumoGeral = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblCustoKmGeral = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jpGrafico = new javax.swing.JPanel();

        jLabel14.setText("jLabel14");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Histórico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(117, 117, 117))); // NOI18N

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
        jScrollPane2.setViewportView(jTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abastecimentoB.png"))); // NOI18N
        jLabel1.setText("Custo com Combustível");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setText("Km Atual");

        lblKmAtual.setBackground(new java.awt.Color(205, 220, 57));
        lblKmAtual.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblKmAtual.setForeground(new java.awt.Color(33, 150, 243));
        lblKmAtual.setText("Último");

        btnAdicionar.setBackground(new java.awt.Color(255, 64, 129));
        btnAdicionar.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/maisp.png"))); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setMaximumSize(new java.awt.Dimension(115, 27));
        btnAdicionar.setMinimumSize(new java.awt.Dimension(115, 27));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(115, 27));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblKmAtual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKmAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Último Abastecimento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(117, 117, 117))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setText("Consumo");

        lblConsumoUlt.setBackground(new java.awt.Color(205, 220, 57));
        lblConsumoUlt.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblConsumoUlt.setForeground(new java.awt.Color(33, 150, 243));
        lblConsumoUlt.setText("11");

        jLabel3.setText("Km/Litro");

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel13.setText("Custo Atual por KM");

        lblCustoKmUlt.setBackground(new java.awt.Color(205, 220, 57));
        lblCustoKmUlt.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCustoKmUlt.setForeground(new java.awt.Color(33, 150, 243));
        lblCustoKmUlt.setText("R$ 3,21");

        jLabel15.setText("Reais/Km");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblConsumoUlt)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCustoKmUlt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustoKmUlt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConsumoUlt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel15))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Média", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(117, 117, 117))); // NOI18N

        lblConsumoGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblConsumoGeral.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblConsumoGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblConsumoGeral.setText("10 ");

        jLabel7.setText("Km/Litro");

        lblCustoKmGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblCustoKmGeral.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCustoKmGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblCustoKmGeral.setText("R$ 4,21");

        jLabel11.setText("Reais/Km");

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel16.setText("Consumo");

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel17.setText("Custo KM");

        jpGrafico.setBackground(new java.awt.Color(255, 255, 255));
        jpGrafico.setAutoscrolls(true);
        jpGrafico.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblConsumoGeral)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCustoKmGeral)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addComponent(jLabel17))
                .addGap(35, 35, 35)
                .addComponent(jpGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsumoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(lblCustoKmGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jpGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        new jdAbastecimento(this, veiculo, usuario).setVisible(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jpGrafico;
    private javax.swing.JLabel lblConsumoGeral;
    private javax.swing.JLabel lblConsumoUlt;
    private javax.swing.JLabel lblCustoKmGeral;
    private javax.swing.JLabel lblCustoKmUlt;
    private javax.swing.JLabel lblKmAtual;
    // End of variables declaration//GEN-END:variables
}
