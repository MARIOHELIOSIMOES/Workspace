package view;

import control.AvisoControl;
import control.VeiculoControl;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
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
    AvisoControl ac;
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
        //this.veiculo = new Veiculo();
        arraylist = new ArrayList<Veiculo>();
        aux = new Auxiliar();
        ac = new AvisoControl();
        limparCampos();
        addListeners();
        
        atualizarTela();
       // atualizarTamanhoBotoes();
        
    }
    public void atualizarTela(){
        atualizarAvisos();
        preencherTable();
    }
    private void atualizarAvisos(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lblNAvisos.setText(ac.getNAtivos()+"");
            }
        });
        t.start();
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
        int j = -1;
        try{
            j = arraylist.size() - jTable.getSelectedRow()-1;
            
            if(arraylist.size()>0 && j>=0){
                lblAno.setText(""+arraylist.get(j).getAno());
                lblMarca.setText(arraylist.get(j).getMarca());
                lblCarroceria.setText(arraylist.get(j).getCarroceria());
                lblModelo.setText(arraylist.get(j).getModelo());
                lblConfiguracao.setText(arraylist.get(j).getConfiguracaoLabel());
                lblInfo.setText(arraylist.get(j).getInfo());
                lblPlaca.setText(arraylist.get(j).getPlaca());
                lblTipo.setText(arraylist.get(j).getTipoLabel());
                //atualizarTamanhoBotoes();
            }
        }catch(Exception e){
           aux.showMessageWarning(e.getMessage()+"\n J = {" + j + "} e arraylist.size = {"+arraylist.size()+"}", "PreencherSelectedTable");
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
            linha = linha +", Tipo: " + Veiculo.TIPOS_STRING[arraylist.get(i).getTipo()];
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
            item[2]=Veiculo.TIPOS_STRING[arraylist.get(i).getTipo()];
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnDetalhes = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblPlaca = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblAno = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblCarroceria = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblConfiguracao = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
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
        btnCustos = new javax.swing.JLabel();
        btnEstoque = new javax.swing.JLabel();
        btnManutencao = new javax.swing.JLabel();
        btnOutros = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNAvisos = new javax.swing.JLabel();
        btnAvisos = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações da frota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 10, 5));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Placa");
        jPanel5.add(jLabel1);

        lblPlaca.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblPlaca.setText("DLH8657");
        jPanel5.add(lblPlaca);

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Tipo");
        jPanel5.add(jLabel9);

        lblTipo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblTipo.setText("Carro");
        jPanel5.add(lblTipo);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Marca");
        jPanel5.add(jLabel3);

        lblMarca.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMarca.setText("Volkswagem");
        jPanel5.add(lblMarca);

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Modelo");
        jPanel5.add(jLabel5);

        lblModelo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblModelo.setText("GOL G4");
        jPanel5.add(lblModelo);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Ano");
        jPanel5.add(jLabel7);

        lblAno.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblAno.setText("2015");
        jPanel5.add(lblAno);

        jPanel4.add(jPanel5);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridLayout());

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Carroceria");
        jPanel7.add(jLabel11);

        lblCarroceria.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCarroceria.setText("Não se aplica");
        jPanel7.add(lblCarroceria);

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Configuração");
        jPanel7.add(jLabel14);

        lblConfiguracao.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblConfiguracao.setText("4x2");
        jPanel7.add(lblConfiguracao);

        jPanel4.add(jPanel7);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblInfo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblInfo.setText("Cor vermelho");

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Informação");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInfo)
                .addContainerGap(898, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfo)
                    .addComponent(jLabel15)))
        );

        jPanel4.add(jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar)
                    .addComponent(btnDetalhes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Próximas manutenções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
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
        chkOleo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        chkOleo.setForeground(new java.awt.Color(51, 51, 51));
        chkOleo.setText("Óleo e Filtro");
        chkOleo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkOleoItemStateChanged(evt);
            }
        });

        chkPneu.setBackground(new java.awt.Color(255, 255, 255));
        chkPneu.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        chkPneu.setForeground(new java.awt.Color(51, 51, 51));
        chkPneu.setText("Pneu");
        chkPneu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkPneuItemStateChanged(evt);
            }
        });

        chkFreio.setBackground(new java.awt.Color(255, 255, 255));
        chkFreio.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        chkFreio.setForeground(new java.awt.Color(51, 51, 51));
        chkFreio.setText("Freio");
        chkFreio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFreioItemStateChanged(evt);
            }
        });

        chkOutras.setBackground(new java.awt.Color(255, 255, 255));
        chkOutras.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        chkOutras.setForeground(new java.awt.Color(51, 51, 51));
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
        );

        jpNotificacoes.setPreferredSize(new java.awt.Dimension(735, 80));
        jpNotificacoes.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jpNotificacoesComponentResized(evt);
            }
        });
        jpNotificacoes.setLayout(new java.awt.GridLayout(1, 0));

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
        jpNotificacoes.add(btnCustos);

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
        jpNotificacoes.add(btnEstoque);

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
        jpNotificacoes.add(btnManutencao);

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
        jpNotificacoes.add(btnOutros);

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new java.awt.BorderLayout());

        lblNAvisos.setBackground(new java.awt.Color(204, 204, 204));
        lblNAvisos.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblNAvisos.setForeground(new java.awt.Color(255, 0, 0));
        lblNAvisos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNAvisos.setText("100");
        lblNAvisos.setOpaque(true);
        jPanel2.add(lblNAvisos, java.awt.BorderLayout.PAGE_END);

        btnAvisos.setBackground(new java.awt.Color(204, 204, 204));
        btnAvisos.setFont(new java.awt.Font("Verdana", 3, 11)); // NOI18N
        btnAvisos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAvisos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tarefaspendentes.png"))); // NOI18N
        btnAvisos.setText("Avisos");
        btnAvisos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAvisos.setOpaque(true);
        btnAvisos.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel2.add(btnAvisos, java.awt.BorderLayout.CENTER);

        jpNotificacoes.add(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpNotificacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jpNotificacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
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
            jfp.AtualizarContentPane(new jpCadastroVeiculo(jfp, arraylist.get(arraylist.size() - jTable.getSelectedRow()-1)));
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

    
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        //atualizarTamanhoBotoes();
    }//GEN-LAST:event_formComponentResized

    private void jpNotificacoesComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpNotificacoesComponentResized
        //atualizarTamanhoBotoes();
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

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        jfp.AtualizarContentPane(new jpAvisosGlobais());
    }//GEN-LAST:event_jPanel2MouseClicked
    

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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
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
    private javax.swing.JLabel lblNAvisos;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txfDias;
    // End of variables declaration//GEN-END:variables
}
