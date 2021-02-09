package view;

import Exception.ValorInvalidoException;
import control.DocumentoControl;
import control.VeiculoControl;
import data.VeiculoDAO;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import model.Auxiliar;
import model.Documento;
import model.Veiculo;

/**
 *
 * @author mario
 */
public class jpCadastroVeiculo extends javax.swing.JPanel {

    private static int id_veiculo = 100;//variável provisória para id do veículo;
    Veiculo veiculo;
    ArrayList<Documento> arrayListDocs;
    Auxiliar aux;
    VeiculoDAO vDao;
    DocumentoControl docCtrol;
    VeiculoControl vCtrol;
    
    
    public jpCadastroVeiculo() {
       iniciarlizar();
    }
    
    //Construtor deve ser chamado para alteração de veículo
    public jpCadastroVeiculo (Veiculo veiculo){
        iniciarlizar();
        this.veiculo = veiculo;
        btnSalvar.setText("Alterar");
        preencherCampos();
    }
    //Método que irá preencher os componentes com os dados do veículo
    private void preencherCampos(){
        txfPlaca.setText(veiculo.getPlaca());//1
        txfCarroceria.setText(veiculo.getCarroceria());//2
        txfMarca.setText(veiculo.getMarca());//3
        txfAno.setText(veiculo.getAno()+"");//4
        txaInfo.setText(veiculo.getInfo());//5
        txfTipo.setText(veiculo.getTipo());//6
        txfModelo.setText(veiculo.getModelo());//7
        cbbConfiguracao.setSelectedIndex(veiculo.getConfiguracao());//8
        cbbCombustivel.setSelectedIndex(veiculo.getCombustivel());//9
        atualizarListviewDocumentos();
        enableCamposComID(true);
    }
    //Método que irá preencher o CBBconfiguração com as configurações disponíveis na classe Veiculo
    private void preencherCBBConfiguração(){
        cbbConfiguracao.removeAllItems();
        for(String a: Veiculo.confLabels){
            cbbConfiguracao.addItem(a);
        }
    }
    private void preencherCBBCombustivel(){
        cbbCombustivel.removeAllItems();
        for(String a: Veiculo.COMBUSTIVEIS){
            cbbCombustivel.addItem(a);
        }
        cbbCombustivel.setSelectedIndex(0);
    }
    
    //Método irá pesquisar os documentos e preencher o ListView com base no ID do Veículo
    private void atualizarListviewDocumentos(){
        try{
            DefaultListModel listModel = new DefaultListModel();
            int j=0;
            arrayListDocs = new DocumentoControl(veiculo.getId()).getArrayListDocumentosVeiculo();

            for(int i=arrayListDocs.size()-1; i>=0; i--){
                String linha = "Nº " + arrayListDocs.get(i).getId();
                linha = linha + ", Registro: " + arrayListDocs.get(i).getN_registro();
                linha = linha +", Validade: " + arrayListDocs.get(i).getDataString();
                linha = linha +", Info: " + arrayListDocs.get(i).getInfo();
                listModel.add(j,linha);
                j++;
            }
            jListDocs.setModel(listModel);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCadastroVeiculo.atualizarListviewDocumentos");
        }
    }
    
    private void iniciarlizar(){
        initComponents();
        veiculo = new Veiculo();
        aux = new Auxiliar();
        vDao = new VeiculoDAO();
        vCtrol = new VeiculoControl();
        arrayListDocs = new ArrayList<Documento>();
        
        limparCampos();
        enableCamposComID(false);
        atualizarListviewDocumentos();
        preencherCBBConfiguração();
        preencherCBBCombustivel();
    }
    private void limparCampos(){
        txfPlaca.setText("");
        txfCarroceria.setText("");
        txfMarca.setText("");
        txfModelo.setText("");
        txaInfo.setText("");
        txfTipo.setText("");
        txfAno.setText("");
        atualizarListviewDocumentos();
    }
    private Boolean validarCampos(){//validação deveria ser feita no VeiculoControl.. verificar
        if(txfPlaca.getText().equals("")||txfMarca.getText().equals("")||txfAno.getText().equals("")||txfAno.getText().equals("")||txfCarroceria.getText().equals("")||cbbCombustivel.getSelectedIndex()<0||cbbConfiguracao.getSelectedIndex()<0){
            aux.showMessageWarning("Existem campos inválidos", "Verique os campos");
            return false;
        }
        return true;
    }
    private Veiculo getVeiculo(){
        Veiculo veiculo = new Veiculo();
        try{
        veiculo.setId(id_veiculo);
        veiculo.setMarca(txfMarca.getText());
        veiculo.setModelo(txfAno.getText());
        veiculo.setTipo(txfTipo.getText());
        veiculo.setAno(Integer.parseInt(txfAno.getText()));
        veiculo.setCarroceria(txfCarroceria.getText());
        veiculo.setInfo(txaInfo.getText());
        veiculo.setCombustivel(cbbCombustivel.getSelectedIndex());
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCadastroVeiculo.getVeiculo");
        }
        return veiculo;
    }
    private void enableCamposComID(boolean status){
        //btnAnexar.setEnabled(status);
        btnMais.setEnabled(status);
        btnMenos.setEnabled(status);
        //jpConfigPneus.setEnabled(status);
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txfPlaca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txfMarca = new javax.swing.JTextField();
        txfModelo = new javax.swing.JTextField();
        txfAno = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txfTipo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txfCarroceria = new javax.swing.JTextField();
        cbbConfiguracao = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbbCombustivel = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaInfo = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListDocs = new javax.swing.JList<>();
        btnMais = new javax.swing.JButton();
        btnMenos = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações do veículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel1.setText("Placa");

        txfPlaca.setText("ABC5588");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel2.setText("Marca");

        txfMarca.setText("Volkswagem");

        txfModelo.setText("Modelo xyz / Versão 9999");

        txfAno.setText("2000");

        jLabel15.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel15.setText("Ano");

        jLabel14.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel14.setText("Modelo / Versão");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel3.setText("Tipo / Espécie");

        txfTipo.setText("Caminhão/Carro");

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel4.setText("Carroceria");

        txfCarroceria.setText("Não se aplica");

        cbbConfiguracao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbConfiguracao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbConfiguracaoItemStateChanged(evt);
            }
        });
        cbbConfiguracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbConfiguracaoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel5.setText("Configuração");

        btnPesquisar.setBackground(new java.awt.Color(255, 255, 0));
        btnPesquisar.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pesquisar.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setMaximumSize(new java.awt.Dimension(117, 25));
        btnPesquisar.setMinimumSize(new java.awt.Dimension(117, 25));
        btnPesquisar.setPreferredSize(new java.awt.Dimension(117, 25));
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel12.setText("Combustível");

        cbbCombustivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbCombustivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCombustivelItemStateChanged(evt);
            }
        });
        cbbCombustivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCombustivelActionPerformed(evt);
            }
        });

        btnSalvar.setBackground(new java.awt.Color(255, 64, 129));
        btnSalvar.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvarp.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setBackground(new java.awt.Color(33, 150, 243));
        btnNovo.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/maisp.png"))); // NOI18N
        btnNovo.setText(" Novo");
        btnNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel13.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel13.setText("Informações");

        txaInfo.setColumns(20);
        txaInfo.setRows(5);
        jScrollPane2.setViewportView(txaInfo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txfMarca)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txfModelo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(txfAno, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(txfTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                                                .addGap(18, 18, 18))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cbbCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(163, 163, 163)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txfCarroceria)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addComponent(cbbConfiguracao, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel4))
                                                .addGap(0, 28, Short.MAX_VALUE))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addContainerGap())))
                    .addComponent(jScrollPane2)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfCarroceria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbConfiguracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Documentação do Veiculo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N

        jListDocs.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListDocs);

        btnMais.setBackground(new java.awt.Color(0, 204, 0));
        btnMais.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        btnMais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/maisp.png"))); // NOI18N
        btnMais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        btnMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaisActionPerformed(evt);
            }
        });

        btnMenos.setBackground(new java.awt.Color(255, 51, 0));
        btnMenos.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        btnMenos.setText("-");
        btnMenos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)));
        btnMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnMais, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 69, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaisActionPerformed
        if(!txfPlaca.getText().equals("")&&veiculo.getId()!=0){
            jfCadastroDocumento jfc = new jfCadastroDocumento(veiculo.getId(), txfPlaca.getText());
            jfc.setVisible(true);
            atualizarListviewDocumentos();
            
        }else{
            aux.showMessageWarning("A placa não pode estar em braco", "Verifique o campo");
        }
    }//GEN-LAST:event_btnMaisActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(validarCampos()){
            veiculo.setId(vCtrol.addVeiculo(veiculo.getId(),txfPlaca.getText(), txfMarca.getText(),txfModelo.getText(),txfAno.getText(),txfTipo.getText(),cbbConfiguracao.getSelectedIndex(),txfCarroceria.getText(),txaInfo.getText(), cbbCombustivel.getSelectedIndex()));
        }
        if(veiculo.getId()!=0){
            enableCamposComID(true);
            btnSalvar.setText("Alterar");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosActionPerformed
        int j = jListDocs.getSelectedIndex();
        if(j>=0){
            int i = arrayListDocs.size()-j-1;
            if(new DocumentoControl(veiculo.getId()).excluirDocumento(arrayListDocs.get(i))){
                atualizarListviewDocumentos();
            }
        }
    }//GEN-LAST:event_btnMenosActionPerformed
 
    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        btnSalvar.setText("Salvar");
        veiculo.setId(0);
        enableCamposComID(false);
        limparCampos();
        
    }//GEN-LAST:event_btnNovoActionPerformed

    private void cbbCombustivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCombustivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCombustivelActionPerformed

    private void cbbCombustivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCombustivelItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCombustivelItemStateChanged

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        pesquisarByPlaca();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void cbbConfiguracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbConfiguracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbConfiguracaoActionPerformed

    private void cbbConfiguracaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbConfiguracaoItemStateChanged
        alteracaoCbbConfiguracao();
    }//GEN-LAST:event_cbbConfiguracaoItemStateChanged
    private void pesquisarByPlaca(){
        try{
            String placa = txfPlaca.getText();
            if(placa.equals("")){
                throw new ValorInvalidoException("placa", "");
            }
            this.veiculo = vCtrol.getVeiculoByPlaca(placa);
            if(this.veiculo.getId()!=0){
                preencherCampos();
            }else{
                aux.showMessageInformacao("Placa informada não cadastrada! Placa: "+placa, "Pesquisa");
                limparCampos();
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCadastroVeiculo.pesquisarByPlaca");
        }
        
    }

    //Alterar o valor da configuraçao do veículo e atualizar o jpanel com as configurações.
    private void alteracaoCbbConfiguracao(){
        veiculo.setConfiguracao(cbbConfiguracao.getSelectedIndex());
        //implementar a atualização do jpanel dos pneus, 
        //recomendação utilizar o mesmo jpanel que será implementado nas atualizações de pneu
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMais;
    private javax.swing.JButton btnMenos;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbbCombustivel;
    private javax.swing.JComboBox<String> cbbConfiguracao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jListDocs;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea txaInfo;
    private javax.swing.JTextField txfAno;
    private javax.swing.JTextField txfCarroceria;
    private javax.swing.JTextField txfMarca;
    private javax.swing.JTextField txfModelo;
    private javax.swing.JTextField txfPlaca;
    private javax.swing.JTextField txfTipo;
    // End of variables declaration//GEN-END:variables
}
