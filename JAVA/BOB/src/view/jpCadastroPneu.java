package view;

import control.ItemControl;
import control.PneuControl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Item;
import model.Pneu;

/**
 *
 * @author mario
 */
public class jpCadastroPneu extends javax.swing.JPanel {

    private static final String ALTERAR = "Alterar";
    private static final String SALVAR = "Salvar";
    private static final String ZERO = "0";
    private static final String VAZIO = "";
    
    private final int INDEX_NOVO = 0;
    
    ArrayList<Pneu> arraylistPneu;
    List<Item> arraylistItem;
    PneuControl pctrol;
    Pneu pneu;
    ItemControl ictrol;
    Item item;
    Auxiliar aux;
    
    public jpCadastroPneu() {
        inicializar();
    }

    private void inicializar(){
        initComponents();
        arraylistPneu = new ArrayList<Pneu>();
        arraylistItem = new ArrayList<Item>();
        pctrol = new PneuControl();
        ictrol = new ItemControl();
        pneu = new Pneu();
        item = new Item();
        aux = new Auxiliar();
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                  if(e.getValueIsAdjusting())
                      return;
                 preencherSelectedTable();
            }
        });
        atualizarTela();
    }
    private void preencherSelectedTable(){
         try{
            int i = jTable.getSelectedRow();
            if(i>=0 && arraylistPneu.size()>0){
           //     preencherAvisoSelecionado(arrayList.get(arrayList.size()-i-1));
                List<Pneu> pneuList = arraylistPneu.stream()
                        .filter(pneu -> {
                            if(pneu.getId()== Integer.parseInt(""+jTable.getValueAt(i, 0))){
                                return true;
                            }
                            return false;
                        })
                        .collect(Collectors.toList());
                if(pneuList.size()>0){
                    Pneu p = pneuList.get(0);
                    preencherPneu(p);
                    
                }
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.preencherSelectedTable");
        }
    }

    private void preencherPneu(Pneu p) {
        
        try {
            List<Item> filtrados = arraylistItem.stream()
                    .filter((t) -> {
                            Item i = (Item) t;
                            if(i.getId()==p.getIdItem()){
                                return true;
                            }
                            return false;
                        })
                    .collect(Collectors.toList());
            int index = 0;
            for (int i = 0; i < arraylistItem.size(); i++) {
                if(filtrados.get(0).getId()==arraylistItem.get(i).getId()){
                    index = i+1;
                    break;
                }
            }
            cbbItensPneus.setSelectedIndex(index);
//            cbbItensPneus.setSelectedIndex(arraylistItem.indexOf(arraylistItem.stream().filter((t) -> {
//                Item i = (Item) t;
//                if(i.getId()==p.getIdItem()){
//                    return true;
//                }
//                return false;
//            }).collect(Collectors.toList()).get(0)));
            txfId.setText(p.getId()+"");
            txfMarca.setText(p.getMarca());
            txfModelo.setText(p.getModelo());
            txfFogo.setText(p.getFogo()+"");
            txfkm.setText(p.getKm()+"");
            txfkmTracao.setText(p.getKmTracao()+"");
            txfVidaUtil.setText(p.getKmTotal()+"");
            txfValor.setText(String.format("%.2f", p.getValor()));
            txfValorAtual.setText(String.format("%.2f", pctrol.getValorAtualByIdPneu(p.getId())));
            txfReformas.setText(pctrol.getQtdeReformasByIdPneu(p.getId())+"");
            txfMovimentacao.setText(pctrol.getQtdeMovimentacaoByIdPneu(p.getId())+"");
            cbbStatus.setSelectedIndex(p.getStatus());
            txaInfo.setText(p.getInfo());
            if(p.getStatus()==Pneu.RODANDO){
                lblPlaca.setText(pctrol.getPlacaRodandoByIdPneu(p.getId()));
            }else{
                lblPlaca.setText(VAZIO);
            }
            
            btnSalvar.setText(ALTERAR);
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.preencherPneu");
        }
      //  enableCampos(false);
    
    }
    
    private void enableCampos(boolean status){
        txfMarca.setEditable(status);
        txfModelo.setEditable(status);
        txfFogo.setEditable(status);
        txfkm.setEditable(status);
        txfkmTracao.setEditable(status);
        
    }
    
    public void atualizarTela(){
        limparCampos();
        preencherCBBItemPneu();
        preencherCBBStatus();
        preencherTable();
    }
    private void preencherSelectedCBBItemPneu(){
        int j = cbbItensPneus.getSelectedIndex();
        if(j>0){
            item = arraylistItem.get(j-1);
            txaInfo.setText(item.getInfo());
            txfMarca.setText(item.getMarca());
            txfModelo.setText(item.getModelo());
            txfValor.setText(item.getValor()+"");
        }else{
            limparCampos();
        }
    }
    private void preencherTable(){
        DefaultTableModel model = new DefaultTableModelNaoEditavel();
        TableRowSorter tableSorter = new TableRowSorter(model);
        model.addColumn("ID");//0
        model.addColumn("Fogo");//1
        model.addColumn("Marca");//2
        model.addColumn("Modelo");//3
        model.addColumn("Km Total");//4
        model.addColumn("Valor");//5
        model.addColumn("Custo por KM");//6
        model.addColumn("Status");//7
        model.addColumn("Reformas");//8
        model.addColumn("Movimentações");//9
        
        try{
            arraylistPneu = pctrol.getArrayListTodosPneus();
            Object[] linha;
            for(Pneu p: arraylistPneu){
                linha = new Object[10];
                linha[0]=p.getId();
                linha[1]=p.getFogo();
                linha[2]=p.getMarca();
                linha[3]=p.getModelo();
                linha[4]=p.getKmTotal();
                linha[5]= aux.StringFloatReais(p.getValor());
                linha[6]= aux.StringFloatReais(p.getCustoKm());
                linha[7]= Pneu.STATUS_PNEU[p.getStatus()];
                linha[8]= pctrol.getQtdeReformasByIdPneu(p.getId());
                linha[9]= pctrol.getQtdeMovimentacaoByIdPneu(p.getId());
                if(p.getStatus()==Pneu.RODANDO){
                    linha[7]= pctrol.getPlacaRodandoByIdPneu(p.getId());
                }
                
                model.addRow(linha);
            }
            tableSorter = new TableRowSorter(model);
         }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.preencherListView");
        }finally{
            jTable.setModel(model);
            if(arraylistPneu.size()>0){
                jTable.getSelectionModel().setSelectionInterval(0, 0);
                lblTotal.setText(arraylistPneu.size()+"");
            }
            lblTotal.setText(arraylistPneu.size()+"");
            jTable.setRowSorter(tableSorter);
            tableSorter.toggleSortOrder(7); 
        }
    }
    private void preencherCBBItemPneu(){
        try{
            cbbItensPneus.removeAllItems();
            cbbItensPneus.addItem("Novo - Não Cadastrado!");
            arraylistItem = ictrol.getArrayListItemByTipo(new int[]{Item.PNEU})
                                                    .stream()
                                                    .sorted(Comparator.comparing(Item::getMarca).thenComparing(Item::getModelo))
                                                    .collect(Collectors.toList());
            int i = 0;
            for(Item item: arraylistItem){/*.stream()
                    .sorted(Comparator.comparing(Item::getMarca).thenComparing(Item::getModelo))
                    .collect(Collectors.toList())){*/
                cbbItensPneus.addItem(String.format("%d - %s",++i, item.getMarcaModelo()));
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.preencherCBBItemPneu");
        }
    }
    private void preencherCBBStatus(){
        try{
            cbbStatus.removeAllItems();
            for(String s : Pneu.STATUS_PNEU){
                cbbStatus.addItem(s);
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.preencherCBBStatus");
        }
    }
    private void limparCampos(){
        txfId.setText(ZERO);
        txfFogo.setText(ZERO);
        txfMarca.setText(ZERO);
        txfModelo.setText(ZERO);
        txfValor.setText(ZERO);
        txfValorAtual.setText(ZERO);
        lblPlaca.setText(VAZIO);
        txaInfo.setText(ZERO);
        txfkm.setText(ZERO);
        txfkmTracao.setText(ZERO);
        txfVidaUtil.setText(ZERO);
        txfReformas.setText(ZERO);
        txfMovimentacao.setText(ZERO);
        btnSalvar.setText(SALVAR);
        if(cbbStatus.getItemCount()>0){
            cbbStatus.setSelectedIndex(Pneu.ESTOQUE);
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

        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaInfo = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txfId = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbbItensPneus = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txfFogo = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txfMarca = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txfModelo = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txfValor = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txfValorAtual = new javax.swing.JTextField();
        btnValor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txfkm = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txfkmTracao = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txfVidaUtil = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txfMovimentacao = new javax.swing.JTextField();
        btnMovimentacoes = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txfReformas = new javax.swing.JTextField();
        btnReformas = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cbbStatus = new javax.swing.JComboBox<>();
        lblPlaca = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setText("Informação");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        txaInfo.setColumns(20);
        txaInfo.setRows(5);
        jScrollPane1.setViewportView(txaInfo);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Cadastrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

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
        jScrollPane3.setViewportView(jTable);

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel17.setText("Total ");

        lblTotal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblTotal.setText("1000");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(lblTotal)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
        );

        jPanel12.setLayout(new java.awt.GridLayout(0, 1));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("ID:");

        txfId.setEditable(false);
        txfId.setText("51524");

        btnNovo.setBackground(new java.awt.Color(33, 150, 243));
        btnNovo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/maisp.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setIconTextGap(10);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setBackground(new java.awt.Color(255, 64, 129));
        btnSalvar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvarp.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setIconTextGap(10);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 593, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel13);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setText("Modelos Cadastrados");

        cbbItensPneus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbItensPneusItemStateChanged(evt);
            }
        });
        cbbItensPneus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbItensPneusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbItensPneus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbItensPneus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel11);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setText("Fogo");

        txfFogo.setText("5555");
        txfFogo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfFogoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfFogo)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 108, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfFogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Marca");

        txfMarca.setText("Mobil");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 100, Short.MAX_VALUE))
                    .addComponent(txfMarca))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel10);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Modelo");

        txfModelo.setText("SW2050");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel4.setText("Valor Original");

        txfValor.setText("20,50");
        txfValor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txfValorMousePressed(evt);
            }
        });
        txfValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfValorKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addComponent(txfValor))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel15.setText("Valor Atual");

        txfValorAtual.setEditable(false);
        txfValorAtual.setText("20,50");
        txfValorAtual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txfValorAtualMousePressed(evt);
            }
        });
        txfValorAtual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfValorAtualKeyTyped(evt);
            }
        });

        btnValor.setText("...");
        btnValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(txfValorAtual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnValor))
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfValorAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnValor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel18);

        jPanel12.add(jPanel1);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("Eixo Comum (km)");

        txfkm.setEditable(false);
        txfkm.setText("5555");
        txfkm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfkmKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addComponent(txfkm))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setText("Eixo Tração (km)");

        txfkmTracao.setEditable(false);
        txfkmTracao.setText("5555");
        txfkmTracao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfkmTracaoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addComponent(txfkmTracao))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfkmTracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setText("Km total");

        txfVidaUtil.setEditable(false);
        txfVidaUtil.setText("5555");
        txfVidaUtil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfVidaUtilKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 85, Short.MAX_VALUE))
                    .addComponent(txfVidaUtil))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfVidaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel14.setText("Movimentações");

        txfMovimentacao.setEditable(false);
        txfMovimentacao.setText("0");

        btnMovimentacoes.setText("...");
        btnMovimentacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovimentacoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(txfMovimentacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMovimentacoes)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMovimentacoes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel17);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setText("Reformas");

        txfReformas.setEditable(false);
        txfReformas.setText("0");

        btnReformas.setText("...");
        btnReformas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReformasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(txfReformas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReformas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfReformas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReformas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel16);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setText("Status");

        cbbStatus.setEnabled(false);
        cbbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbStatusItemStateChanged(evt);
            }
        });

        lblPlaca.setText("DLH-8657");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                    .addComponent(cbbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblPlaca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel15);

        jPanel12.add(jPanel3);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pneu.png"))); // NOI18N
        jLabel1.setText("Cadastro de Pneu");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        if(!jfPrincipal.isUserOperaOrAdmin()){
            aux.showMessagemSemPermissao();
            return;
        }
        String id, marca, modelo, valor,info,fogo, km, kmtracao, vida;
        try{
            id = txfId.getText();
            fogo = txfFogo.getText();
            marca = txfMarca.getText();
            modelo= txfModelo.getText();
            valor = txfValor.getText();
            info = txaInfo.getText();
            km = txfkm.getText();
            kmtracao = txfkmTracao.getText();
            vida = txfVidaUtil.getText();//continuar aqui...
            
            int idItem = 0;
            idItem = (cbbItensPneus.getSelectedIndex()<=0) ? 0 : arraylistItem.get(cbbItensPneus.getSelectedIndex()-1).getId();

            if(pctrol.salvar(id, marca, modelo, valor, info, idItem, fogo, km, kmtracao, cbbStatus.getSelectedIndex())){
                atualizarTela();
            }
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.btnSalvarActionPerformed");
        }
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void cbbItensPneusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbItensPneusItemStateChanged
       try{ if(cbbItensPneus.getSelectedIndex()>=0){
            limparCampos();
            preencherSelectedCBBItemPneu();
        }
       }catch(Exception e){
           aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.cbbItemsPneusStateChanged");
       }
    }//GEN-LAST:event_cbbItensPneusItemStateChanged

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        
        cbbItensPneus.setSelectedIndex(INDEX_NOVO);
        btnSalvar.setText(SALVAR);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void txfValorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txfValorMousePressed
        
    }//GEN-LAST:event_txfValorMousePressed

    private void txfValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfValorKeyTyped
        String caracteres="0987654321.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }

    }//GEN-LAST:event_txfValorKeyTyped

    private void txfkmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfkmKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_txfkmKeyTyped

    private void txfkmTracaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfkmTracaoKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_txfkmTracaoKeyTyped

    private void txfVidaUtilKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfVidaUtilKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_txfVidaUtilKeyTyped

    private void txfFogoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfFogoKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_txfFogoKeyTyped

    private void cbbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbStatusItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbStatusItemStateChanged

    private void txfValorAtualMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txfValorAtualMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfValorAtualMousePressed

    private void txfValorAtualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfValorAtualKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txfValorAtualKeyTyped

    private void cbbItensPneusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbItensPneusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbItensPneusActionPerformed

    private void btnReformasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReformasActionPerformed
        try {
            int id = Integer.parseInt(txfId.getText());
            if(id>0){
                Pneu pneu = pctrol.getItemByID(id);
                new jdPneuReforma(this,pneu).setVisible(true);
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.btnReformasActionPerformed");
        }
        
    }//GEN-LAST:event_btnReformasActionPerformed

    private void btnMovimentacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovimentacoesActionPerformed
         try {
            int id = Integer.parseInt(txfId.getText());
            if(id>0){
                Pneu pneu = pctrol.getItemByID(id);
                new jdPneuMovimentacao(pneu).setVisible(true);
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.btnMovimentacoesActionPerformed");
        }
    }//GEN-LAST:event_btnMovimentacoesActionPerformed

    private void btnValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValorActionPerformed
       try {
            int id = Integer.parseInt(txfId.getText());
            if(id>0){
                Pneu pneu = pctrol.getItemByID(id);
                new jdPneuValorAtual(this, pneu).setVisible(true);
            }
        } catch (Exception e) {
            aux.RegistrarLog(e.getMessage(), "jpCadastroPneu.btnMovimentacoesActionPerformed");
        }
    }//GEN-LAST:event_btnValorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMovimentacoes;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnReformas;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnValor;
    private javax.swing.JComboBox<String> cbbItensPneus;
    private javax.swing.JComboBox<String> cbbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextArea txaInfo;
    private javax.swing.JTextField txfFogo;
    private javax.swing.JTextField txfId;
    private javax.swing.JTextField txfMarca;
    private javax.swing.JTextField txfModelo;
    private javax.swing.JTextField txfMovimentacao;
    private javax.swing.JTextField txfReformas;
    private javax.swing.JTextField txfValor;
    private javax.swing.JTextField txfValorAtual;
    private javax.swing.JTextField txfVidaUtil;
    private javax.swing.JTextField txfkm;
    private javax.swing.JTextField txfkmTracao;
    // End of variables declaration//GEN-END:variables
}
