package view;

import Exception.ValorInvalidoException;
import control.DocumentoControl;
import control.VeiculoControl;
import data.VeiculoDAO;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import model.Auxiliar;
import model.Caminhao;
import model.Carro;
import model.Documento;
import model.Veiculo;
import model.VeiculoConfiguracao;

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
    jpVeiculoConfiguracao jp;
    jfPrincipal jfp;

    public jpCadastroVeiculo() {
        iniciarlizar();
    }

    //Construtor deve ser chamado para alteração de veículo
    public jpCadastroVeiculo(jfPrincipal jfp, Veiculo veiculo) {
        iniciarlizar();
        this.jfp = jfp;
        this.veiculo = veiculo;
        btnSalvar.setText("Alterar");
        preencherCampos();
    }

    //Método que irá preencher os componentes com os dados do veículo
    private void preencherCampos() {
        txfPlaca.setText(veiculo.getPlaca());//1
        txfCarroceria.setText(veiculo.getCarroceria());//2
        txfMarca.setText(veiculo.getMarca());//3
        txfAno.setText(veiculo.getAno() + "");//4
        txaInfo.setText(veiculo.getInfo());//5
        cbbTipo.setSelectedIndex(veiculo.getTipo());
        //txfTipo.setText(veiculo.getTipo());//6
        txfModelo.setText(veiculo.getModelo());//7
        cbbConfiguracao.setSelectedIndex(veiculo.getConfiguracao());//8
        cbbCombustivel.setSelectedIndex(veiculo.getCombustivel());//9
        preencherjpConfPneus(veiculo.getVeiculoConfiguracao());
        atualizarListviewDocumentos();
        enableCamposComID(true);
    }

    //Método que irá preencher o CBBconfiguração com as configurações disponíveis na classe Veiculo
    private void preencherCBBConfiguração() {
        try{
        cbbConfiguracao.removeAllItems();
        veiculo = getVeiculo();
            for (String a : getVeiculo().getConfLabels()) {
                cbbConfiguracao.addItem(a);
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCadastroVeiculo.preencherCBBConfiguração");
        }
    }

    private void preencherCBBTipo() {
        cbbTipo.removeAllItems();
        for (String tipo : Veiculo.TIPOS_STRING) {
            cbbTipo.addItem(tipo);
        }
    }

    private void preencherCBBCombustivel() {
        cbbCombustivel.removeAllItems();
        for (String a : Veiculo.COMBUSTIVEIS) {
            cbbCombustivel.addItem(a);
        }
        cbbCombustivel.setSelectedIndex(0);
    }

    //Método irá pesquisar os documentos e preencher o ListView com base no ID do Veículo
    private void atualizarListviewDocumentos() {
        try {
            DefaultListModel listModel = new DefaultListModel();
            int j = 0;
            if(veiculo!=null){
                arrayListDocs = new DocumentoControl(veiculo.getId()).getArrayListDocumentosVeiculo();
            }else{
                arrayListDocs = new ArrayList<>();
            }
            for (int i = arrayListDocs.size() - 1; i >= 0; i--) {
                String linha = "Nº " + arrayListDocs.get(i).getId();
                linha = linha + ", Registro: " + arrayListDocs.get(i).getN_registro();
                linha = linha + ", Validade: " + arrayListDocs.get(i).getDataString();
                linha = linha + ", Info: " + arrayListDocs.get(i).getInfo();
                listModel.add(j, linha);
                j++;
            }
            jListDocs.setModel(listModel);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCadastroVeiculo.atualizarListviewDocumentos");
        }
    }

    private void iniciarlizar() {
        initComponents();
        //veiculo = new Veiculo();
        aux = new Auxiliar();
        vDao = new VeiculoDAO();
        vCtrol = new VeiculoControl();
        arrayListDocs = new ArrayList<Documento>();
        jp = new jpVeiculoConfiguracao(new VeiculoConfiguracao());

        limparCampos();
        enableCamposComID(false);
        preencherCBBTipo();
        atualizarListviewDocumentos();
        preencherCBBConfiguração();
        preencherCBBCombustivel();
        

    }

    private void limparCampos() {
        txfPlaca.setText("");
        txfCarroceria.setText("");
        txfMarca.setText("");
        txfModelo.setText("");
        txaInfo.setText("");
        txfAno.setText("");
        atualizarListviewDocumentos();
    }

    private Boolean validarCampos() {//validação deveria ser feita no VeiculoControl.. verificar
        if (txfPlaca.getText().equals("")
                || txfMarca.getText().equals("")
                || txfAno.getText().equals("")
                || txfCarroceria.getText().equals("")
                || cbbCombustivel.getSelectedIndex() < 0 || cbbConfiguracao.getSelectedIndex() < 0
                || cbbTipo.getSelectedIndex() < 0) {
            aux.showMessageWarning("Existem campos inválidos", "Verique os campos");
            return false;
        }
        int index = cbbConfiguracao.getSelectedIndex();

        VeiculoConfiguracao vc = getVeiculo().getVeiculoConfiguracaoByIndex(index);
        veiculo.setConfiguracao(index);
/*
        vc.setConfiguracao(index);
        vc.setnEixos(Veiculo.QTDE_EIXOS[index]);
        vc.setnRodas(Veiculo.QTDE_PNEUS[index]);
        vc.setPosicoesTracao(jp.getRodasTracao());
        vc.setDescricao(Veiculo.CONF_LABELS[index]);
*/
        veiculo.setVeiculoConfiguracao(vc);

        return true;
    }

    private Veiculo getVeiculo() {
        Veiculo veiculo;

        switch (cbbTipo.getSelectedIndex()) {
            case Veiculo.CAMINHAO:
                veiculo = new Caminhao();
                break;
            default:
                veiculo = new Carro();
        }

        try {
            veiculo.setId(id_veiculo);
            veiculo.setMarca(txfMarca.getText());
            veiculo.setModelo(txfModelo.getText());
            String ano = txfAno.getText();
            ano = ano.equals("")? "0": ano;
            veiculo.setAno(Integer.parseInt(ano));
            veiculo.setCarroceria(txfCarroceria.getText());
            veiculo.setInfo(txaInfo.getText());
            veiculo.setCombustivel(cbbCombustivel.getSelectedIndex());
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCadastroVeiculo.getVeiculo");
        }
        return veiculo;
    }

    private void enableCamposComID(boolean status) {
        //btnAnexar.setEnabled(status);
        btnMais.setEnabled(status);
        btnMenos.setEnabled(status);
        btnDetalhes.setEnabled(status);
        //jpConfigPneus.setEnabled(status);
    }

    private void alteracaoCbbConfiguracao() {
        try {
            if (cbbConfiguracao.getSelectedIndex() == -1) {
                return;
            }
            jpVeiculoConfig.removeAll();
            jp = new jpVeiculoConfiguracao(getVeiculo().getVeiculoConfiguracaoByIndex(cbbConfiguracao.getSelectedIndex()));
            jpVeiculoConfig.add(jp);

            jpVeiculoConfig.revalidate();
            jpVeiculoConfig.repaint();
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCadastroVeiculo.alteraçãoCbbConfiguracao");
        }
        //implementar a atualização do jpanel dos pneus, 
        //recomendação utilizar o mesmo jpanel que será implementado nas atualizações de pneu
    }

    private void preencherjpConfPneus(VeiculoConfiguracao vc) {
        try {
            jpVeiculoConfig.removeAll();
            jp = new jpVeiculoConfiguracao(vc);
            jpVeiculoConfig.add(jp);
            jpVeiculoConfig.revalidate();
            jpVeiculoConfig.repaint();
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCadastroVeiculo.preencherjpConfPneus");
        }

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
        jLabel4 = new javax.swing.JLabel();
        txfCarroceria = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbbCombustivel = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaInfo = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbbConfiguracao = new javax.swing.JComboBox<>();
        jpVeiculoConfig = new javax.swing.JPanel();
        cbbTipo = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnDetalhes = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListDocs = new javax.swing.JList<>();
        btnMais = new javax.swing.JButton();
        btnMenos = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações do veículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Placa");

        txfPlaca.setText("ABC5588");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Marca");

        txfMarca.setText("Volkswagem");

        txfModelo.setText("Modelo xyz / Versão 9999");

        txfAno.setText("2000");

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Ano");

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Modelo / Versão");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Tipo / Espécie");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Carroceria");

        txfCarroceria.setText("Não se aplica");

        btnPesquisar.setBackground(new java.awt.Color(255, 255, 0));
        btnPesquisar.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
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

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Combustível");

        cbbCombustivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Informações");

        txaInfo.setColumns(20);
        txaInfo.setRows(5);
        jScrollPane2.setViewportView(txaInfo);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Configuração");

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

        jpVeiculoConfig.setBackground(new java.awt.Color(255, 255, 255));
        jpVeiculoConfig.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbbConfiguracao, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpVeiculoConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpVeiculoConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbConfiguracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(79, Short.MAX_VALUE))))
        );

        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTipoItemStateChanged(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

        btnNovo.setBackground(new java.awt.Color(33, 150, 243));
        btnNovo.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/maisp.png"))); // NOI18N
        btnNovo.setText(" Novo");
        btnNovo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNovo);

        btnSalvar.setBackground(new java.awt.Color(255, 64, 129));
        btnSalvar.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvarp.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(btnSalvar);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.add(jLabel6);

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
        jPanel4.add(btnDetalhes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(txfMarca)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 359, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txfCarroceria)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(cbbCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txfModelo))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txfAno, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel13))
                        .addGap(0, 535, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txfModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfCarroceria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Documentação do Veiculo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

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
                .addGap(0, 0, Short.MAX_VALUE))
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
        if (!txfPlaca.getText().equals("") && veiculo.getId() != 0) {
            jfCadastroDocumento jfc = new jfCadastroDocumento(veiculo.getId(), txfPlaca.getText());
            jfc.setVisible(true);
            atualizarListviewDocumentos();

        } else {
            aux.showMessageWarning("A placa não pode estar em braco", "Verifique o campo");
        }
    }//GEN-LAST:event_btnMaisActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (!jfPrincipal.isUserOperaOrAdmin()) {
            aux.showMessagemSemPermissao();
            return;
        }

        if (validarCampos()) {
            veiculo.setId(vCtrol.addVeiculo(veiculo.getId(),
                    txfPlaca.getText(),
                    txfMarca.getText(),
                    txfModelo.getText(),
                    txfAno.getText(),
                    cbbTipo.getSelectedIndex(),
                    cbbConfiguracao.getSelectedIndex(),
                    txfCarroceria.getText(),
                    txaInfo.getText(),
                    cbbCombustivel.getSelectedIndex(),
                    veiculo.getVeiculoConfiguracao()));
        }
        if (veiculo.getId() != 0) {
            enableCamposComID(true);
            btnSalvar.setText("Alterar");

        }
    }//GEN-LAST:event_btnSalvarActionPerformed
    private void btnMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosActionPerformed
        if (!jfPrincipal.isUserAdmin()) {
            aux.showMessagemSemPermissao();
            return;
        }

        int j = jListDocs.getSelectedIndex();
        if (j >= 0) {
            int i = arrayListDocs.size() - j - 1;
            if (new DocumentoControl(veiculo.getId()).excluirDocumento(arrayListDocs.get(i))) {
                atualizarListviewDocumentos();
            }
        }
    }//GEN-LAST:event_btnMenosActionPerformed
    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        if (!jfPrincipal.isUserOperaOrAdmin()) {
            aux.showMessagemSemPermissao();
            return;
        }
        btnSalvar.setText("Salvar");
        veiculo.setId(0);
        enableCamposComID(false);
        limparCampos();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        pesquisarByPlaca();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void cbbConfiguracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbConfiguracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbConfiguracaoActionPerformed

    private void cbbConfiguracaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbConfiguracaoItemStateChanged
        alteracaoCbbConfiguracao();
    }//GEN-LAST:event_cbbConfiguracaoItemStateChanged

    private void btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalhesActionPerformed
        jfp.AtualizarContentPane(new jpDetalheVeiculo(jfp, veiculo, jfp.getUsuario()));
    }//GEN-LAST:event_btnDetalhesActionPerformed

    private void cbbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTipoItemStateChanged
        preencherCBBConfiguração();
    }//GEN-LAST:event_cbbTipoItemStateChanged
    private void pesquisarByPlaca() {
        try {
            String placa = txfPlaca.getText();
            if (placa.equals("")) {
                throw new ValorInvalidoException("placa", "");
            }
            this.veiculo = vCtrol.getVeiculoByPlaca(placa);
            if (this.veiculo.getId() != 0) {
                preencherCampos();
            } else {
                aux.showMessageInformacao("Placa informada não cadastrada! Placa: " + placa, "Pesquisa");
                limparCampos();
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCadastroVeiculo.pesquisarByPlaca");
        }

    }

    //Alterar o valor da configuraçao do veículo e atualizar o jpanel com as configurações.

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalhes;
    private javax.swing.JButton btnMais;
    private javax.swing.JButton btnMenos;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbbCombustivel;
    private javax.swing.JComboBox<String> cbbConfiguracao;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jListDocs;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpVeiculoConfig;
    private javax.swing.JTextArea txaInfo;
    private javax.swing.JTextField txfAno;
    private javax.swing.JTextField txfCarroceria;
    private javax.swing.JTextField txfMarca;
    private javax.swing.JTextField txfModelo;
    private javax.swing.JTextField txfPlaca;
    // End of variables declaration//GEN-END:variables
}
