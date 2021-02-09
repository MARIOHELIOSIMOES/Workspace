package view;

import control.ManutencaoControl;
import control.PedidoOleoControl;
import control.VeiculoCombustivelControl;
import control.VeiculoControl;
import control.VeiculoKMControl;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Usuario;
import model.Veiculo;
import model.GraficoItem;

/**
 *
 * @author mario
 */
public class jpCustosGlobais extends javax.swing.JPanel {

    ArrayList<Veiculo> arraylist;
    ArrayList<CustosVeiculo> arraylistCustos;
    
    VeiculoKMControl vkmCtrol;
    ManutencaoControl manCtrol;
    PedidoOleoControl pOleoCtrol;
    VeiculoControl veiCtrol;
    VeiculoCombustivelControl vcombCtrol;
    
    Veiculo veiculoMenor, veiculoMaior;
    CustosVeiculo custoMenor, custoMaior;
    
    Auxiliar aux;
    jfPrincipal jfp;
    Usuario usuario;
    
    public jpCustosGlobais(jfPrincipal jfp, Usuario usuario) {
        this.jfp = jfp;
        this.usuario = usuario;
        inicializar();
    }
    private void inicializar(){
        initComponents();
        arraylist = new ArrayList<Veiculo>();
        arraylistCustos = new ArrayList<CustosVeiculo>();
        veiculoMenor = new Veiculo();
        veiculoMaior = new Veiculo();
        vkmCtrol = new VeiculoKMControl();
        manCtrol = new ManutencaoControl();
        pOleoCtrol = new PedidoOleoControl();
        veiCtrol = new VeiculoControl();
        vcombCtrol = new VeiculoCombustivelControl();
        custoMaior = new CustosVeiculo();
        custoMenor = new CustosVeiculo();
        aux = new Auxiliar();
        
        preencherTabela();
    }
    private void preencherTabela(){
        DefaultTableModel tableModel = new DefaultTableModelNaoEditavel();
        
        try{
            tableModel.addColumn("Nº");
            tableModel.addColumn("Placa");
            tableModel.addColumn("Marca");
            tableModel.addColumn("Modelo");
            tableModel.addColumn("Ano");
            tableModel.addColumn("Manutenção (Reais/KM)");
            tableModel.addColumn("Combustível (Reais/KM)");
            tableModel.addColumn("Óleo (Reais/KM)");
            tableModel.addColumn("Total (Reais/KM)");
            arraylist = veiCtrol.getArrayListTodosVeiculos();
            arraylistCustos = new ArrayList<CustosVeiculo>();
            CustosVeiculo custoVeiculo;
            if(arraylist.size()>0){
                for(int i = arraylist.size() -1; i>=0; i--){
                    custoVeiculo = new CustosVeiculo();
                    custoVeiculo.setIdVeiculo(arraylist.get(i).getId());
                    custoVeiculo.setCombustivel(vcombCtrol.custoKmGeralByIdVeiculo(arraylist.get(i).getId(), 5));
                    custoVeiculo.setOleo(pOleoCtrol.getMediaCustoGeral(arraylist.get(i).getId(), 5));
                    custoVeiculo.setManutencao(manCtrol.custoKmParcial(arraylist.get(i).getId()));
                    arraylistCustos.add(custoVeiculo);
                    if(custoVeiculo.getCustosGerais()>custoMaior.getCustosGerais()){
                        veiculoMaior = arraylist.get(i);
                        custoMaior = custoVeiculo;
                    }
                    if(custoVeiculo.getCustosGerais()<=custoMenor.getCustosGerais() || veiculoMenor.getId()==0){
                        veiculoMenor = arraylist.get(i);
                        custoMenor = custoVeiculo;
                    }
                }
                Object[] row;
                for(int i = arraylist.size() -1; i>=0; i--){
                    row = new Object[9];
                    row[0]= i+1;
                    row[1] = arraylist.get(i).getPlaca();
                    row[2] = arraylist.get(i).getMarca();
                    row[3] = arraylist.get(i).getModelo();
                    row[4] = arraylist.get(i).getAno();
                    row[5] = aux.StringFloatReais(arraylistCustos.get(arraylistCustos.size() - 1 - i).getManutencao());
                    row[6] = aux.StringFloatReais(arraylistCustos.get(arraylistCustos.size() - 1 - i).getCombustivel());
                    row[7] = aux.StringFloatReais(arraylistCustos.get(arraylistCustos.size() - 1 - i).getOleo());
                    row[8] = aux.StringFloatReais(arraylistCustos.get(arraylistCustos.size() - 1 - i).getCustosGerais());
                    tableModel.addRow(row);
                }
            }
        }catch(Exception e){
            
        }finally{
            jTable.setModel(tableModel);
            preencherDetalhesFrota();
        }
        
    }
    private void preencherDetalhesFrota(){
        float custoGeralCombustivel = 0, custoGeralOleo = 0, custoGeralManutencao=0, custoGeralTotal = 0;
        try{
            for(int i = 0; i< arraylistCustos.size(); i++){
                custoGeralCombustivel += arraylistCustos.get(i).getCombustivel();
                custoGeralOleo+=arraylistCustos.get(i).getOleo();
                custoGeralManutencao+= arraylistCustos.get(i).getManutencao();
                custoGeralTotal += arraylistCustos.get(i).getCustosGerais();
            }
            int tamanho = arraylist.size();
            tamanho = (tamanho>0) ? tamanho: 1;
            
            lblCombustivelGeralAcu.setText(aux.StringFloatReais(custoGeralCombustivel));
            lblOleoGeralAcu.setText(aux.StringFloatReais(custoGeralOleo));
            lblManutencaoGeralAcu.setText(aux.StringFloatReais(custoGeralManutencao));
            lblTotalGeralAcu.setText(aux.StringFloatReais(custoGeralTotal));
            
            custoGeralCombustivel = custoGeralCombustivel / tamanho;
            custoGeralManutencao = custoGeralManutencao / tamanho;
            custoGeralOleo = custoGeralOleo / tamanho;
            custoGeralTotal = custoGeralTotal / tamanho;
            
            lblCombustivelGeral.setText(aux.StringFloatReais(custoGeralCombustivel));
            lblOleoGeral.setText(aux.StringFloatReais(custoGeralOleo));
            lblManutencaoGeral.setText(aux.StringFloatReais(custoGeralManutencao));
            lblTotalGeral.setText(aux.StringFloatReais(custoGeralTotal));
            
            ArrayList<GraficoItem> arraygrafico = new ArrayList<GraficoItem>();
            GraficoItem gi = new GraficoItem();
            gi.setNome("Combustivel");
            gi.setValor((double)custoGeralCombustivel);
            arraygrafico.add(gi);
            gi = new GraficoItem();
            gi.setNome("Oleo");
            gi.setValor((double)custoGeralOleo);
            arraygrafico.add(gi);
            gi = new GraficoItem();
            gi.setNome("Manutenção");
            gi.setValor((double)custoGeralManutencao);
            //jpGraficoGeral.setLayout(new GridLayout(1, 1));
            arraygrafico.add(gi);
            jpGraficoGeral.add(new jpGraficoPizza("Custo Médio", arraygrafico));
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustosGlobais.preencherDetalhesFrota");
        }finally{
            
            lblNVeiculos.setText(arraylist.size()+"");
            preencherMaior();
            preencherMenor();
            
        }
        
        
    }
    private void preencherMaior(){
        try{
            lblAnoMaior.setText(veiculoMaior.getAno()+"");
            lblPlacaMaior.setText(veiculoMaior.getPlaca());
            lblMarcaMaior.setText(veiculoMaior.getMarca());
            lblModeloMaior.setText(veiculoMaior.getModelo());
            lblCombustivelMaior.setText(aux.StringFloatReais(custoMaior.getCombustivel()));
            lblOleoMaior.setText(aux.StringFloatReais(custoMaior.getOleo()));
            lblManutencaoMaior.setText(aux.StringFloatReais(custoMaior.getManutencao()));
            lblTotalMaior.setText(aux.StringFloatReais(custoMaior.getCustosGerais()));
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustosGlobais. preencherMaior");
        }
        
    }
    private void preencherMenor(){
        try{
            lblAnoMenor.setText(veiculoMenor.getAno()+"");
            lblPlacaMenor.setText(veiculoMenor.getPlaca());
            lblMarcaMenor.setText(veiculoMenor.getMarca());
            lblModeloMenor.setText(veiculoMenor.getModelo());
            lblCombustivelMenor.setText(aux.StringFloatReais(custoMenor.getCombustivel()));
            lblOleoMenor.setText(aux.StringFloatReais(custoMenor.getOleo()));
            lblManutencaoMenor.setText(aux.StringFloatReais(custoMenor.getManutencao()));
            lblTotalMenor.setText(aux.StringFloatReais(custoMenor.getCustosGerais()));
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustosGlobais. preencherMenor");
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        lblNVeiculos = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblCombustivelGeral = new javax.swing.JLabel();
        lblOleoGeral = new javax.swing.JLabel();
        lblManutencaoGeral = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        lblTotalGeral = new javax.swing.JLabel();
        lblCombustivelGeralAcu = new javax.swing.JLabel();
        lblOleoGeralAcu = new javax.swing.JLabel();
        lblManutencaoGeralAcu = new javax.swing.JLabel();
        lblTotalGeralAcu = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jpGraficoGeral = new javax.swing.JPanel();
        jSeparator13 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblPlacaMenor = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblMarcaMenor = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblModeloMenor = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblAnoMenor = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblCombustivelMenor = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        lblOleoMenor = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        lblManutencaoMenor = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        lblTotalMenor = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblPlacaMaior = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblMarcaMaior = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblModeloMaior = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblAnoMaior = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblManutencaoMaior = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        lblOleoMaior = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        lblCombustivelMaior = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        lblTotalMaior = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/analiseB.png"))); // NOI18N
        jLabel1.setText("Análise Global da Frota");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações Gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

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
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalhes da Frota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel22.setText("Tamanho da Frota");

        lblNVeiculos.setBackground(new java.awt.Color(205, 220, 57));
        lblNVeiculos.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        lblNVeiculos.setForeground(new java.awt.Color(33, 150, 243));
        lblNVeiculos.setText("100");

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(33, 150, 243));
        jLabel23.setText("Custo Médio");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel24.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abastecimentop.png"))); // NOI18N
        jLabel24.setText("Combustível");

        jLabel25.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/oleop.png"))); // NOI18N
        jLabel25.setText("Óleo e Filtro");

        jLabel26.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel26.setText("Manutenções");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblCombustivelGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblCombustivelGeral.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCombustivelGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblCombustivelGeral.setText("100");

        lblOleoGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblOleoGeral.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblOleoGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblOleoGeral.setText("100");

        lblManutencaoGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoGeral.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblManutencaoGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblManutencaoGeral.setText("100");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel35.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel35.setText("Total");

        lblTotalGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblTotalGeral.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblTotalGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblTotalGeral.setText("100");

        lblCombustivelGeralAcu.setBackground(new java.awt.Color(205, 220, 57));
        lblCombustivelGeralAcu.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCombustivelGeralAcu.setForeground(new java.awt.Color(255, 64, 129));
        lblCombustivelGeralAcu.setText("100");

        lblOleoGeralAcu.setBackground(new java.awt.Color(205, 220, 57));
        lblOleoGeralAcu.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblOleoGeralAcu.setForeground(new java.awt.Color(255, 64, 129));
        lblOleoGeralAcu.setText("100");

        lblManutencaoGeralAcu.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoGeralAcu.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblManutencaoGeralAcu.setForeground(new java.awt.Color(255, 64, 129));
        lblManutencaoGeralAcu.setText("100");

        lblTotalGeralAcu.setBackground(new java.awt.Color(205, 220, 57));
        lblTotalGeralAcu.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblTotalGeralAcu.setForeground(new java.awt.Color(255, 64, 129));
        lblTotalGeralAcu.setText("100");

        jLabel36.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 64, 129));
        jLabel36.setText("Total Acumulado");

        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Reais/Km");

        jpGraficoGeral.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoGeral.setLayout(new java.awt.GridLayout());

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNVeiculos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel36)
                    .addComponent(jLabel17))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCombustivelGeralAcu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCombustivelGeral, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(lblOleoGeral)
                    .addComponent(lblOleoGeralAcu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26)
                        .addComponent(lblManutencaoGeralAcu, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(lblManutencaoGeral))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalGeralAcu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel35)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalGeral))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpGraficoGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalGeral)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalGeralAcu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCombustivelGeral)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addGap(6, 6, 6))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblOleoGeral))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblManutencaoGeral)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCombustivelGeralAcu)
                                    .addComponent(lblOleoGeralAcu)
                                    .addComponent(lblManutencaoGeralAcu))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNVeiculos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator13, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menores Custos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel8.setText("Placa");

        lblPlacaMenor.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblPlacaMenor.setText("DLH8657");

        jLabel10.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel10.setText("Marca");

        lblMarcaMenor.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblMarcaMenor.setText("Volkswagem");

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel11.setText("Modelo");

        lblModeloMenor.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblModeloMenor.setText("GOL G4");

        jLabel12.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel12.setText("Ano");

        lblAnoMenor.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblAnoMenor.setText("2015");

        jLabel27.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abastecimentop.png"))); // NOI18N
        jLabel27.setText("Combustível");

        lblCombustivelMenor.setBackground(new java.awt.Color(205, 220, 57));
        lblCombustivelMenor.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCombustivelMenor.setForeground(new java.awt.Color(33, 150, 243));
        lblCombustivelMenor.setText("100");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel28.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/oleop.png"))); // NOI18N
        jLabel28.setText("Óleo e Filtro");

        lblOleoMenor.setBackground(new java.awt.Color(205, 220, 57));
        lblOleoMenor.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblOleoMenor.setForeground(new java.awt.Color(33, 150, 243));
        lblOleoMenor.setText("100");

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel29.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel29.setText("Manutenções");

        lblManutencaoMenor.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoMenor.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblManutencaoMenor.setForeground(new java.awt.Color(33, 150, 243));
        lblManutencaoMenor.setText("100");

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel33.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel33.setText("Total");

        lblTotalMenor.setBackground(new java.awt.Color(205, 220, 57));
        lblTotalMenor.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblTotalMenor.setForeground(new java.awt.Color(255, 64, 129));
        lblTotalMenor.setText("100");

        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Reais/Km");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPlacaMenor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMarcaMenor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblModeloMenor)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(lblAnoMenor))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblCombustivelMenor)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblOleoMenor)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblManutencaoMenor)))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblTotalMenor)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel18)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblPlacaMenor)
                    .addComponent(jLabel10)
                    .addComponent(lblMarcaMenor)
                    .addComponent(jLabel11)
                    .addComponent(lblModeloMenor)
                    .addComponent(jLabel12)
                    .addComponent(lblAnoMenor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblOleoMenor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblManutencaoMenor)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblCombustivelMenor)))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTotalMenor)
                                    .addComponent(jLabel18))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maiores Custos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(127, 127, 127));

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel9.setText("Placa");

        lblPlacaMaior.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblPlacaMaior.setText("DLH8657");

        jLabel13.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel13.setText("Marca");

        lblMarcaMaior.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblMarcaMaior.setText("Volkswagem");

        jLabel14.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel14.setText("Modelo");

        lblModeloMaior.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblModeloMaior.setText("GOL G4");

        jLabel16.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel16.setText("Ano");

        lblAnoMaior.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblAnoMaior.setText("2015");

        jLabel30.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel30.setText("Manutenções");

        lblManutencaoMaior.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoMaior.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblManutencaoMaior.setForeground(new java.awt.Color(255, 64, 129));
        lblManutencaoMaior.setText("100");

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblOleoMaior.setBackground(new java.awt.Color(205, 220, 57));
        lblOleoMaior.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblOleoMaior.setForeground(new java.awt.Color(255, 64, 129));
        lblOleoMaior.setText("100");

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/oleop.png"))); // NOI18N
        jLabel31.setText("Óleo e Filtro");

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblCombustivelMaior.setBackground(new java.awt.Color(205, 220, 57));
        lblCombustivelMaior.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCombustivelMaior.setForeground(new java.awt.Color(255, 64, 129));
        lblCombustivelMaior.setText("100");

        jLabel32.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abastecimentop.png"))); // NOI18N
        jLabel32.setText("Combustível");

        lblTotalMaior.setBackground(new java.awt.Color(205, 220, 57));
        lblTotalMaior.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblTotalMaior.setForeground(new java.awt.Color(33, 150, 243));
        lblTotalMaior.setText("100");

        jLabel34.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel34.setText("Total");

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Reais/Km");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(lblPlacaMaior)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(lblMarcaMaior)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(lblModeloMaior)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(lblAnoMaior))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator9)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addComponent(lblCombustivelMaior)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel31)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(lblOleoMaior)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(16, 16, 16)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(lblManutencaoMaior))
                                .addComponent(jLabel30))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel34)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(lblTotalMaior)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel19)))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblPlacaMaior)
                    .addComponent(jLabel13)
                    .addComponent(lblMarcaMaior)
                    .addComponent(jLabel14)
                    .addComponent(lblModeloMaior)
                    .addComponent(jLabel16)
                    .addComponent(lblAnoMaior))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblOleoMaior))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblCombustivelMaior))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblManutencaoMaior))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTotalMaior)
                                    .addComponent(jLabel19)))
                            .addComponent(jSeparator7)
                            .addComponent(jSeparator11)
                            .addComponent(jSeparator8))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if(evt.getClickCount()>1){
            try{
                if(arraylist.size()>0 && jTable.getSelectedRow()>=0){
                    jfp.AtualizarContentPane(new jpDetalheVeiculo(jfp, arraylist.get(arraylist.size() - jTable.getSelectedRow()-1), usuario));
                }
            }catch(Exception e){
                aux.RegistrarLog(e.getMessage(), "jpCustosGlobais.jTableMouseClicked");
            }
        }
    }//GEN-LAST:event_jTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel jpGraficoGeral;
    private javax.swing.JLabel lblAnoMaior;
    private javax.swing.JLabel lblAnoMenor;
    private javax.swing.JLabel lblCombustivelGeral;
    private javax.swing.JLabel lblCombustivelGeralAcu;
    private javax.swing.JLabel lblCombustivelMaior;
    private javax.swing.JLabel lblCombustivelMenor;
    private javax.swing.JLabel lblManutencaoGeral;
    private javax.swing.JLabel lblManutencaoGeralAcu;
    private javax.swing.JLabel lblManutencaoMaior;
    private javax.swing.JLabel lblManutencaoMenor;
    private javax.swing.JLabel lblMarcaMaior;
    private javax.swing.JLabel lblMarcaMenor;
    private javax.swing.JLabel lblModeloMaior;
    private javax.swing.JLabel lblModeloMenor;
    private javax.swing.JLabel lblNVeiculos;
    private javax.swing.JLabel lblOleoGeral;
    private javax.swing.JLabel lblOleoGeralAcu;
    private javax.swing.JLabel lblOleoMaior;
    private javax.swing.JLabel lblOleoMenor;
    private javax.swing.JLabel lblPlacaMaior;
    private javax.swing.JLabel lblPlacaMenor;
    private javax.swing.JLabel lblTotalGeral;
    private javax.swing.JLabel lblTotalGeralAcu;
    private javax.swing.JLabel lblTotalMaior;
    private javax.swing.JLabel lblTotalMenor;
    // End of variables declaration//GEN-END:variables
    
}
class CustosVeiculo{

    public float getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(float combustivel) {
        this.combustivel = combustivel;
    }

    public float getOleo() {
        return oleo;
    }

    public void setOleo(float oleo) {
        this.oleo = oleo;
    }

    public float getManutencao() {
        return manutencao;
    }

    public void setManutencao(float manutencao) {
        this.manutencao = manutencao;
    }
    private float combustivel =0;
    private float oleo = 0;
    private float manutencao =0;
    private int idVeiculo = 0;
    public  CustosVeiculo(){
        
    }
    public CustosVeiculo(int idVeiculo, float combustivel, float oleo, float manutencao){
        setIdVeiculo(idVeiculo);
        setCombustivel(combustivel);
        setOleo(oleo);
        setManutencao(manutencao);
    }
    public float getCustosGerais(){
        return getCombustivel()+getOleo();
    }
        
    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
}
