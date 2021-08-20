package view;

import control.ManutencaoControl;
import control.VeiculoKMControl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Manutencao;
import model.Usuario;
import model.Veiculo;

/**
 *
 * @author mario
 */
public class jpCustoManutencao extends javax.swing.JPanel {

    private Usuario usuario;
    private Veiculo veiculo;
    private Auxiliar aux;
    private ManutencaoControl mctrol;
    private ArrayList<Manutencao> arraylist;
    private VeiculoKMControl vkmCtrol;
    
    public jpCustoManutencao() {
        inicializar();
    }
    public jpCustoManutencao(Usuario usuario, Veiculo veiculo){
        inicializar();
        this.usuario = usuario;
        this.veiculo = veiculo;
        jtxfDataFim.setText(aux.getDataStringAtual());
        //jtxfDataInicio.setText("02/09/2020");
        jtxfDataInicio.setText(aux.getDataAtualMenosPeriodo(1));
        atualizarTela();
    }
    private void inicializar(){
        initComponents();
        //veiculo = new Veiculo();
        usuario = new Usuario();
        aux = new Auxiliar();
        mctrol = new ManutencaoControl();
        vkmCtrol = new VeiculoKMControl();
        arraylist = new ArrayList<Manutencao>();
        
    }
    public void atualizarTela(){
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        }).start();*/
        preencherTabela();
                preencherUltimo();
                preencherIntervaloDatas();
        
    }
    private void preencherTabela(){
        try{
            arraylist = mctrol.getArrayListManutencaoTodosByIdVeiculo(veiculo.getId());
            
            DefaultTableModel tableModel = new DefaultTableModelNaoEditavel();
                tableModel.addColumn("Nº");//1
                tableModel.addColumn("IDº Manutenção");//2
                tableModel.addColumn("Data");//3
                tableModel.addColumn("KM");//4
                tableModel.addColumn("Oficina");//5
                tableModel.addColumn("Serviço");//6
                tableModel.addColumn("Valor");//7
                
            for(int i=arraylist.size()-1; i>=0; i--){
                Object[] item = new Object[7];
                item[0]=i+1;
                item[1]=arraylist.get(i).getId();
                item[2]=aux.getDataString(arraylist.get(i).getDatamilis());
                item[3]=arraylist.get(i).getKm();
                item[4]= arraylist.get(i).getOficina();
                item[5]= arraylist.get(i).getServico();
                item[6]= aux.StringFloatReais(arraylist.get(i).getValor());
                tableModel.addRow(item);
            }
            jTable.setModel(tableModel);
            jTable.getColumnModel().getColumn(0).setMaxWidth(45);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PreencherTabela - jpCustoManutencao");
        }
    }
    private void preencherUltimo(){
        if(arraylist.size()>0){
            int i = arraylist.size()-1;
            lblOficina.setText(arraylist.get(i).getOficina());
            lblServico.setText(arraylist.get(i).getServico());
            lblData.setText(aux.getDataString(arraylist.get(i).getDatamilis()));
            lblKm.setText(arraylist.get(i).getKm()+"");
            int kmAtual = vkmCtrol.getUltimoKmByIDVeiculo(veiculo.getId());
            int diffkm = kmAtual - arraylist.get(i).getKm();
            diffkm = diffkm<=0? 1: diffkm;
            float valor = arraylist.get(i).getValor();
          //  lblCustoKmParcial.setText(aux.CustoKMString(diffkm, valor));
            lblCustoKmParcial.setText(aux.StringFloatReais(mctrol.custoKmParcial(veiculo.getId())));
            lblKmAtual.setText(kmAtual+"");
            lblValor.setText(aux.StringFloatReais(valor));
            
        }else{
            lblOficina.setText("");
            lblServico.setText("");
            lblData.setText(aux.getDataStringAtual());
            int km = vkmCtrol.getUltimoKmByIDVeiculo(veiculo.getId());
            lblKm.setText(km+"");
            lblCustoKmParcial.setText(aux.StringFloatReais(0));
            lblKmAtual.setText(km+"");
            lblValor.setText(aux.StringFloatReais(0));
        }
    }
    private void preencherIntervaloDatas(){
        ArrayList<Manutencao> arrayIntervalo;
        try{
            arrayIntervalo = mctrol.getArrayListManutencaoByIdVeiculoAndIntervaloDatas(veiculo.getId(), 
                                                                                        aux.dataStringLong(jtxfDataInicio.getText()), 
                                                                                        aux.dataStringLong(jtxfDataFim.getText())
                                                                                       );
            Manutencao mI = new Manutencao(), mF = new Manutencao();
            
            if(arrayIntervalo.size()>0){ // Manutenção Inicial e Final
                mI = arrayIntervalo.get(0);
                mF = arrayIntervalo.get(arrayIntervalo.size()-1);
            }
            int diffKm = mF.getKm() - mI.getKm(); 
            diffKm = diffKm<=0 ? 1 : diffKm; // Diferença de Km da Inicial e Final
            
            float valorTotal = 0;
            for(Manutencao m: arrayIntervalo){
                valorTotal += m.getValor()<0? 0 : m.getValor(); // Soma dos valores das manutenções
            }
            String custo = aux.CustoKMString(diffKm, valorTotal);
            
            lblKmInicio.setText(mI.getKm()+"");
            lblKmFim.setText(mF.getKm()+"");
            lblValorFim.setText(aux.StringFloatReais(valorTotal));
            lblCustoKmGeral.setText(custo);
            lblDiffKm.setText(diffKm+"");
            lbldiffData.setText(aux.diffDataString(aux.dataStringLong(jtxfDataFim.getText()), aux.dataStringLong(jtxfDataInicio.getText())));
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "preencherIntervaloDatas");
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

        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblKmAtual = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblOficina = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblServico = new javax.swing.JLabel();
        lblCustoKmParcial = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        lblKm = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblCustoKmGeral = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jtxfDataInicio = new javax.swing.JTextField();
        try{            
            javax.swing.text.MaskFormatter data =
            new javax.swing.text.MaskFormatter("##/##/####");        
            jtxfDataInicio = new javax.swing.JFormattedTextField(data);    
        }catch(Exception e){    }
        jtxfDataFim = new javax.swing.JTextField();
        try{            
            javax.swing.text.MaskFormatter data =
            new javax.swing.text.MaskFormatter("##/##/####");        
            jtxfDataFim = new javax.swing.JFormattedTextField(data);    
        }catch(Exception e){    }
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblValorFim = new javax.swing.JLabel();
        lblKmInicio = new javax.swing.JLabel();
        lblKmFim = new javax.swing.JLabel();
        lbldiffData = new javax.swing.JLabel();
        lblDiffKm = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

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
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Histórico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

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

        btnAdicionar.setBackground(new java.awt.Color(255, 64, 129));
        btnAdicionar.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/maisp.png"))); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdicionar))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(btnAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaoB.png"))); // NOI18N
        jLabel1.setText("Manutenção");
        jLabel1.setOpaque(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Última Manutenção", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Data");

        lblData.setBackground(new java.awt.Color(205, 220, 57));
        lblData.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblData.setText("30/12/2020");

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Km Atual");

        lblKmAtual.setBackground(new java.awt.Color(205, 220, 57));
        lblKmAtual.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblKmAtual.setText("150.000");

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Oficina");

        lblOficina.setBackground(new java.awt.Color(205, 220, 57));
        lblOficina.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblOficina.setText("Nome ");

        jLabel21.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Serviço");

        lblServico.setBackground(new java.awt.Color(205, 220, 57));
        lblServico.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblServico.setText("Nome ");

        lblCustoKmParcial.setBackground(new java.awt.Color(205, 220, 57));
        lblCustoKmParcial.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCustoKmParcial.setForeground(new java.awt.Color(33, 150, 243));
        lblCustoKmParcial.setText("R$ 4,21");

        jLabel22.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel22.setText("Custo Atual por KM");

        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Reais/Km");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Km da Manutenção");

        lblKm.setBackground(new java.awt.Color(205, 220, 57));
        lblKm.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblKm.setText("150.000");

        jLabel24.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Valor");

        lblValor.setBackground(new java.awt.Color(205, 220, 57));
        lblValor.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblValor.setText("R$ 150000,00");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblData)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(lblKm))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblOficina, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblKmAtual))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValor)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblCustoKmParcial, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel22))
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(lblData)
                                    .addComponent(jLabel23)
                                    .addComponent(lblKm))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(lblOficina)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(lblKmAtual))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblValor)
                                    .addComponent(jLabel24))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(lblServico)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCustoKmParcial))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Média", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(117, 117, 117))); // NOI18N

        lblCustoKmGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblCustoKmGeral.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCustoKmGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblCustoKmGeral.setText("R$ 4,21");

        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Reais/Km");

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Período");

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel17.setText("Custo por KM");

        jtxfDataInicio.setForeground(new java.awt.Color(255, 64, 129));
        jtxfDataInicio.setText("30/12/2020");
        jtxfDataInicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxfDataInicioFocusLost(evt);
            }
        });
        jtxfDataInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxfDataInicioKeyPressed(evt);
            }
        });

        jtxfDataFim.setForeground(new java.awt.Color(255, 64, 129));
        jtxfDataFim.setText("31/12/2020");
        jtxfDataFim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxfDataFimFocusLost(evt);
            }
        });
        jtxfDataFim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxfDataFimKeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel19.setText("Valor do Intervalo");

        jLabel20.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("KM do Intervalo");

        lblValorFim.setBackground(new java.awt.Color(205, 220, 57));
        lblValorFim.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblValorFim.setText("R$ 4,21");

        lblKmInicio.setBackground(new java.awt.Color(205, 220, 57));
        lblKmInicio.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblKmInicio.setText("100000");

        lblKmFim.setBackground(new java.awt.Color(205, 220, 57));
        lblKmFim.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblKmFim.setText("150000");

        lbldiffData.setForeground(new java.awt.Color(102, 102, 102));
        lbldiffData.setText("0 meses e 15 dias");

        lblDiffKm.setForeground(new java.awt.Color(102, 102, 102));
        lblDiffKm.setText("500 km");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbldiffData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtxfDataInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxfDataFim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(lblKmInicio)
                    .addComponent(lblKmFim)
                    .addComponent(lblDiffKm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblValorFim, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblCustoKmGeral, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel17))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCustoKmGeral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(20, 20, 20))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblKmInicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblKmFim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiffKm))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(5, 5, 5)
                        .addComponent(jtxfDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxfDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldiffData, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValorFim)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
       // new jdAbastecimento(this, veiculo, usuario).setVisible(true);
       if(jfPrincipal.isUserOperaOrAdmin()){
                jfCadastroManutencao jfm = new  jfCadastroManutencao(this, veiculo, usuario);
            jfm.setSize(800, 500);
           // jfm.setExtendedState(JFrame.MAXIMIZED_BOTH);
              jfm.setVisible(true);
        }else{
            aux.showMessagemSemPermissao();
        }
       
       
       
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void jtxfDataInicioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfDataInicioFocusLost
        jtxfDataInicio.setText(verificarData(jtxfDataInicio));
        preencherIntervaloDatas();
    }//GEN-LAST:event_jtxfDataInicioFocusLost

    private String verificarData(JTextField jtxfield){
        try{
            String dia = jtxfield.getText().substring(0, 2);
            String mes = jtxfield.getText().substring(3, 5);
            String ano = jtxfield.getText().substring(6);

            Calendar calendar = new GregorianCalendar();
            calendar.setLenient(false);
            calendar.set(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia));
            return aux.getDataString(calendar.getTimeInMillis());
            
        }catch(Exception e){
            aux.showMessageWarning("Informe uma data válida!", "Valor inválido");
            return aux.getDataStringAtual();
        }
    }
    
    private void jtxfDataFimFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfDataFimFocusLost
            jtxfDataFim.setText(verificarData(jtxfDataFim));
            preencherIntervaloDatas();
        
    }//GEN-LAST:event_jtxfDataFimFocusLost

    private void jtxfDataInicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxfDataInicioKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER){
            jtxfDataInicio.setText(verificarData(jtxfDataInicio));
            preencherIntervaloDatas();
        }
    }//GEN-LAST:event_jtxfDataInicioKeyPressed

    private void jtxfDataFimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxfDataFimKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER){
            jtxfDataFim.setText(verificarData(jtxfDataFim));
            preencherIntervaloDatas();
        }
    }//GEN-LAST:event_jtxfDataFimKeyPressed


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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtxfDataFim;
    private javax.swing.JTextField jtxfDataInicio;
    private javax.swing.JLabel lblCustoKmGeral;
    private javax.swing.JLabel lblCustoKmParcial;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDiffKm;
    private javax.swing.JLabel lblKm;
    private javax.swing.JLabel lblKmAtual;
    private javax.swing.JLabel lblKmFim;
    private javax.swing.JLabel lblKmInicio;
    private javax.swing.JLabel lblOficina;
    private javax.swing.JLabel lblServico;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblValorFim;
    private javax.swing.JLabel lbldiffData;
    // End of variables declaration//GEN-END:variables
}
