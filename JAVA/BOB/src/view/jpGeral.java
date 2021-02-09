package view;

import control.VeiculoControl;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Usuario;
import model.Veiculo;

/**
 *
 * @author mario
 */
public class jpGeral extends javax.swing.JPanel {

    Usuario usuario;
    Veiculo veiculo;
    ArrayList<Veiculo> arraylist;
    jfPrincipal jfp;
    Auxiliar aux;
    public jpGeral() {
        inicializar();
    }
    public jpGeral(jfPrincipal jfp, Usuario usuario){
        inicializar();
        this.jfp = jfp;
        this.usuario = usuario;
    }
    private void inicializar(){
        initComponents();
        this.usuario = new Usuario();
        this.veiculo = new Veiculo();
        arraylist = new ArrayList<Veiculo>();
        aux = new Auxiliar();
        limparCampos();
        preencherTable();
        addListeners();
        atualizarTamanhoBotoes();
        
    }
    private void addListeners(){
        jTable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                preencherSelectedTable();
            }

            @Override
            public void keyPressed(KeyEvent e) {
              preencherSelectedTable();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                preencherSelectedTable();
            }
        });
        
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                  if(e.getValueIsAdjusting())
                      return;
                  
                  preencherListView();
            }
        });
    }
    private void preencherSelectedTable(){
        try{
            int j = arraylist.size() - jTable.getSelectedRow()-1;

            if(arraylist.size()>0){
                lblAno.setText(""+arraylist.get(j).getAno());
                lblMarca.setText(arraylist.get(j).getMarca());
                lblCarroceria.setText(arraylist.get(j).getCarroceria());
                lblModelo.setText(arraylist.get(j).getModelo());
                lblConfiguracao.setText(Veiculo.confLabels[arraylist.get(j).getConfiguracao()]);
                lblInfo.setText(arraylist.get(j).getInfo());
                lblPlaca.setText(arraylist.get(j).getPlaca());
                lblTipo.setText(arraylist.get(j).getTipo());
                //atualizarTamanhoBotoes();
            }
        }catch(Exception e){
           aux.showMessageWarning(e.getMessage(), "PreencherSelectedTable");
        }
        
    }
    private void preencherSelectedListview(){
      /*  int j = jList.getSelectedIndex();
        if(j>=0){
            int i = arraylist.size()-j-1;
            lblDetalheData.setText(new Auxiliar(arraylist.get(i).getDataMilis()).getDataString());
            lblDetalheKm.setText(arraylist.get(i).getValor()+"");
            lblDetalheUsuario.setText(new UsuarioControl().getNomeUsuarioById(arraylist.get(i).getIdUsuario()));
        
        }*/
    }
    private void limparCampos(){
        lblAno.setText("");
        lblMarca.setText("");
        lblCarroceria.setText("");
        lblModelo.setText("");
        lblConfiguracao.setText("");
        lblInfo.setText("");
        lblPlaca.setText("");
        lblTipo.setText("");
        
        preencherListView();
    } 
    private void preencherListView(){
        DefaultListModel listModel = new DefaultListModel();
        int j=0;
        arraylist = new VeiculoControl().getArrayListTodosVeiculos();
        for(int i=arraylist.size()-1; i>=0; i--){
            String linha = "Nº " + (i+1);
            linha = linha + ", Placa: " + arraylist.get(i).getPlaca();
            linha = linha +", Tipo: " + arraylist.get(i).getTipo();
            listModel.add(j,linha);
            j++;
        }
        jList.setModel(listModel);
        if(arraylist.size()>0){
            jList.setSelectedIndex(0);
        }
    }
    private void preencherTable(){
        //DefaultListModel listModel = new DefaultListModel();
        DefaultTableModel tableModel = new DefaultTableModelNaoEditavel();
        
        tableModel.addColumn("Nº");
        tableModel.addColumn("Placa");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Marca");
        tableModel.addColumn("Modelo");
        tableModel.addColumn("Ano");

        arraylist = new VeiculoControl().getArrayListTodosVeiculos();
        for(int i=arraylist.size()-1; i>=0; i--){
            Object[] item = new Object[6];
            item[0]=i+1;
            item[1]=arraylist.get(i).getPlaca();
            item[2]=arraylist.get(i).getTipo();
            item[3]=arraylist.get(i).getMarca();
            item[4]=arraylist.get(i).getModelo();
            item[5]=arraylist.get(i).getAno();
            tableModel.addRow(item);
            
        }
        jTable.setModel(tableModel);
        jTable.getColumnModel().getColumn(0).setMaxWidth(30);
        jTable.getColumnModel().getColumn(5).setMaxWidth(60);
        if(arraylist.size()>0){
            jTable.addRowSelectionInterval(0, 0);
            preencherSelectedTable();
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        lblPlaca = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblAno = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblCarroceria = new javax.swing.JLabel();
        lblConfiguracao = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        btnDetalhes = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txfDias = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        chkOleo = new javax.swing.JCheckBox();
        chkPneu = new javax.swing.JCheckBox();
        chkFreio = new javax.swing.JCheckBox();
        chkOutras = new javax.swing.JCheckBox();
        jpNotificacoes = new javax.swing.JPanel();
        btnEstoque = new javax.swing.JLabel();
        btnManutencao = new javax.swing.JLabel();
        btnOutros = new javax.swing.JLabel();
        btnCustos = new javax.swing.JLabel();
        btnAvisos = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações da frota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel1.setText("Placa");

        jTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
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
        jTable.setEditingColumn(0);
        jTable.setEditingRow(0);
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable.setShowGrid(true);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        lblPlaca.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblPlaca.setText("DLH8657");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel3.setText("Marca");

        lblMarca.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblMarca.setText("Volkswagem");

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel5.setText("Modelo");

        lblModelo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblModelo.setText("GOL G4");

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel7.setText("Ano");

        lblAno.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblAno.setText("2015");

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel9.setText("Tipo");

        lblTipo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblTipo.setText("Carro");

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel11.setText("Carroceria");

        lblCarroceria.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblCarroceria.setText("Não se aplica");

        lblConfiguracao.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblConfiguracao.setText("4x2");

        jLabel14.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel14.setText("Configuração");

        jLabel15.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel15.setText("Informação");

        lblInfo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblInfo.setText("Cor vermelho");

        btnAlterar.setBackground(new java.awt.Color(255, 64, 129));
        btnAlterar.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pesquisar.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnDetalhes.setBackground(new java.awt.Color(33, 150, 243));
        btnDetalhes.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnDetalhes.setForeground(new java.awt.Color(255, 255, 255));
        btnDetalhes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/analisep.png"))); // NOI18N
        btnDetalhes.setText("Detalhes");
        btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalhesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCarroceria)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblConfiguracao)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblInfo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPlaca)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTipo)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMarca)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblModelo)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAno)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAlterar)
                        .addComponent(btnDetalhes))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblPlaca)
                            .addComponent(jLabel9)
                            .addComponent(lblTipo)
                            .addComponent(jLabel3)
                            .addComponent(lblMarca)
                            .addComponent(jLabel5)
                            .addComponent(lblModelo)
                            .addComponent(jLabel7)
                            .addComponent(lblAno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lblCarroceria)
                            .addComponent(jLabel14)
                            .addComponent(lblConfiguracao)
                            .addComponent(jLabel15)
                            .addComponent(lblInfo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Próximas manutenções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel17.setText("Intervalo");

        txfDias.setText("30");

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        jLabel18.setText("dias");

        jList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList.setPreferredSize(null);
        jScrollPane2.setViewportView(jList);

        chkOleo.setBackground(new java.awt.Color(255, 255, 255));
        chkOleo.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        chkOleo.setText("Óleo e Filtro");
        chkOleo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkOleoItemStateChanged(evt);
            }
        });

        chkPneu.setBackground(new java.awt.Color(255, 255, 255));
        chkPneu.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        chkPneu.setText("Pneu");
        chkPneu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkPneuItemStateChanged(evt);
            }
        });

        chkFreio.setBackground(new java.awt.Color(255, 255, 255));
        chkFreio.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        chkFreio.setText("Freio");
        chkFreio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFreioItemStateChanged(evt);
            }
        });

        chkOutras.setBackground(new java.awt.Color(255, 255, 255));
        chkOutras.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        chkOutras.setText("Outras");
        chkOutras.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkOutrasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(txfDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(chkOleo)
                .addGap(18, 18, 18)
                .addComponent(chkFreio)
                .addGap(18, 18, 18)
                .addComponent(chkPneu)
                .addGap(18, 18, 18)
                .addComponent(chkOutras)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txfDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(chkOleo)
                    .addComponent(chkFreio)
                    .addComponent(chkPneu)
                    .addComponent(chkOutras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
        );

        jpNotificacoes.setPreferredSize(new java.awt.Dimension(735, 80));
        jpNotificacoes.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jpNotificacoesComponentResized(evt);
            }
        });

        btnEstoque.setBackground(new java.awt.Color(51, 51, 51));
        btnEstoque.setFont(new java.awt.Font("Verdana", 3, 11)); // NOI18N
        btnEstoque.setForeground(new java.awt.Color(255, 255, 255));
        btnEstoque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/quilometragemB.png"))); // NOI18N
        btnEstoque.setText("Estoque");
        btnEstoque.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEstoque.setOpaque(true);
        btnEstoque.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstoqueMouseClicked(evt);
            }
        });

        btnManutencao.setBackground(new java.awt.Color(102, 102, 102));
        btnManutencao.setFont(new java.awt.Font("Verdana", 3, 11)); // NOI18N
        btnManutencao.setForeground(new java.awt.Color(255, 255, 255));
        btnManutencao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnManutencao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaoB.png"))); // NOI18N
        btnManutencao.setText("Manutenção");
        btnManutencao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnManutencao.setOpaque(true);
        btnManutencao.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnManutencao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnManutencaoMouseClicked(evt);
            }
        });

        btnOutros.setBackground(new java.awt.Color(153, 153, 153));
        btnOutros.setFont(new java.awt.Font("Verdana", 3, 11)); // NOI18N
        btnOutros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnOutros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/oleo.png"))); // NOI18N
        btnOutros.setText("Outros");
        btnOutros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOutros.setOpaque(true);
        btnOutros.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnOutros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOutrosMouseClicked(evt);
            }
        });

        btnCustos.setBackground(new java.awt.Color(0, 0, 0));
        btnCustos.setFont(new java.awt.Font("Verdana", 3, 11)); // NOI18N
        btnCustos.setForeground(new java.awt.Color(255, 255, 255));
        btnCustos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCustos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/analiseB.png"))); // NOI18N
        btnCustos.setText("Análise Global");
        btnCustos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCustos.setOpaque(true);
        btnCustos.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnCustos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCustosMouseClicked(evt);
            }
        });

        btnAvisos.setBackground(new java.awt.Color(204, 204, 204));
        btnAvisos.setFont(new java.awt.Font("Verdana", 3, 11)); // NOI18N
        btnAvisos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAvisos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tarefaspendentes.png"))); // NOI18N
        btnAvisos.setText("Avisos");
        btnAvisos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAvisos.setOpaque(true);
        btnAvisos.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnAvisos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAvisosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpNotificacoesLayout = new javax.swing.GroupLayout(jpNotificacoes);
        jpNotificacoes.setLayout(jpNotificacoesLayout);
        jpNotificacoesLayout.setHorizontalGroup(
            jpNotificacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNotificacoesLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(btnCustos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(btnEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(94, 94, 94)
                .addComponent(btnManutencao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(67, 67, 67)
                .addComponent(btnOutros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(67, 67, 67)
                .addComponent(btnAvisos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(135, 135, 135))
        );
        jpNotificacoesLayout.setVerticalGroup(
            jpNotificacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNotificacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpNotificacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCustos, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(btnManutencao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEstoque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOutros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAvisos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpNotificacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jpNotificacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
     //   new Auxiliar().showMessage("Linha: "+jTable.getSelectedRow()+"; Coluna: "+jTable.getSelectedColumn(), "Mouse Clicked", JOptionPane.WARNING_MESSAGE);
     preencherSelectedTable();
    }//GEN-LAST:event_jTableMouseClicked

    private void btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalhesActionPerformed
        if(arraylist.size()>0 && jTable.getSelectedRow()>=0){
            jfp.AtualizarContentPane(new jpDetalheVeiculo(jfp, arraylist.get(arraylist.size() - jTable.getSelectedRow()-1), usuario));
        }
    }//GEN-LAST:event_btnDetalhesActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if(arraylist.size()>0){
            jfp.AtualizarContentPane(new jpCadastroVeiculo(arraylist.get(arraylist.size() - jTable.getSelectedRow()-1)));
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void chkOutrasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkOutrasItemStateChanged
        preencherListView();
    }//GEN-LAST:event_chkOutrasItemStateChanged

    private void chkPneuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkPneuItemStateChanged
        preencherListView();
    }//GEN-LAST:event_chkPneuItemStateChanged

    private void chkOleoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkOleoItemStateChanged
        preencherListView();
    }//GEN-LAST:event_chkOleoItemStateChanged

    private void chkFreioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFreioItemStateChanged
        preencherListView();
    }//GEN-LAST:event_chkFreioItemStateChanged

    private void atualizarTamanhoBotoes(){
        int width = this.getWidth();
        int height = 85;
        
        
        jpNotificacoes.setSize(width, height);
        jpNotificacoes.setPreferredSize(new Dimension(width, height));
        jpNotificacoes.setMaximumSize(new Dimension(width, height));
        jpNotificacoes.setMinimumSize(new Dimension(width, height));
        width = (width / 5);
        
        int x, y;
        x = btnCustos.getX();
        y = btnCustos.getY();
        
        btnCustos.setLocation(x, y);
        x +=width;
        
        btnEstoque.setLocation(x, y);
        x +=width;
        
        btnManutencao.setLocation(x, y);
        x +=width;
        btnOutros.setLocation(x, y);
        x +=width;
        
        btnAvisos.setLocation(x, y);
        
        definirTamanho(btnEstoque, width, height);
        definirTamanho(btnAvisos, width, height);
        definirTamanho(btnManutencao, width, height);
        definirTamanho(btnCustos, width, height);
        definirTamanho(btnOutros, width, height);
        /*btnEstoque.setSize(width, height);
        btnEstoque.setPreferredSize(new Dimension(width, height));
        
        btnAvisos.setSize(width, height);
        btnAvisos.setPreferredSize(new Dimension(width, height));
        
        btnManutencao.setSize(width, height);
        btnManutencao.setPreferredSize(new Dimension(width, height));
        
        btnCustos.setSize(width, height);
        btnCustos.setPreferredSize(new Dimension(width, height));
        btnOutros.setSize(width, height);
        btnOutros.setPreferredSize(new Dimension(width, height));
*/
    }
    private void definirTamanho(JLabel label, int width, int height){
        label.setSize(width, height);
        label.setPreferredSize(new Dimension(width, height));
        label.setMaximumSize(new Dimension(width, height));
        label.setMinimumSize(new Dimension(width, height));
    }
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        atualizarTamanhoBotoes();
    }//GEN-LAST:event_formComponentResized

    private void jpNotificacoesComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpNotificacoesComponentResized
        atualizarTamanhoBotoes();
    }//GEN-LAST:event_jpNotificacoesComponentResized

    private void btnCustosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCustosMouseClicked
        jfp.AtualizarContentPane(new jpCustosGlobais(jfp,usuario));
    }//GEN-LAST:event_btnCustosMouseClicked

    private void btnEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstoqueMouseClicked
        aux.showMessageInformacao("Ainda não disponível", "Estoque");
    }//GEN-LAST:event_btnEstoqueMouseClicked

    private void btnManutencaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManutencaoMouseClicked
        aux.showMessageInformacao("Ainda não disponível", "Manutenção");        // TODO add your handling code here:
    }//GEN-LAST:event_btnManutencaoMouseClicked

    private void btnOutrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOutrosMouseClicked
        aux.showMessageInformacao("Ainda não disponível", "Outros");
    }//GEN-LAST:event_btnOutrosMouseClicked

    private void btnAvisosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvisosMouseClicked
        aux.showMessageInformacao("Ainda não disponível", "Avisos");
    }//GEN-LAST:event_btnAvisosMouseClicked
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JLabel btnAvisos;
    private javax.swing.JLabel btnCustos;
    private javax.swing.JButton btnDetalhes;
    private javax.swing.JLabel btnEstoque;
    private javax.swing.JLabel btnManutencao;
    private javax.swing.JLabel btnOutros;
    private javax.swing.JCheckBox chkFreio;
    private javax.swing.JCheckBox chkOleo;
    private javax.swing.JCheckBox chkOutras;
    private javax.swing.JCheckBox chkPneu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel jpNotificacoes;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblCarroceria;
    private javax.swing.JLabel lblConfiguracao;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txfDias;
    // End of variables declaration//GEN-END:variables
}
