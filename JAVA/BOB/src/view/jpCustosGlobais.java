package view;

import control.ManutencaoControl;
import control.PedidoOleoControl;
import control.VeiculoCombustivelControl;
import control.VeiculoControl;
import control.VeiculoKMControl;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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

    private static final String OP_TODOS = "Todos";
    ArrayList<Veiculo> arraylist, arrayListFiltrado, arrayListFiltradoMarca;
    ArrayList<CustosVeiculo> arraylistCustos;
    int qtdeFiltrado = 0;
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
    
    ArrayList<String> arrayListMarcas, arrayListModelos;
    
    
    public jpCustosGlobais(jfPrincipal jfp, Usuario usuario) {
        this.jfp = jfp;
        this.usuario = usuario;
        inicializar();
    }
    private void inicializar(){
        initComponents();
        arraylist = new ArrayList<Veiculo>();
        arrayListFiltrado = new ArrayList<Veiculo>();
        arrayListFiltradoMarca = new ArrayList<Veiculo>();
        arraylistCustos = new ArrayList<CustosVeiculo>();
        //veiculoMenor = new Veiculo();
        //veiculoMaior = new Veiculo();
        vkmCtrol = new VeiculoKMControl();
        manCtrol = new ManutencaoControl();
        pOleoCtrol = new PedidoOleoControl();
        veiCtrol = new VeiculoControl();
        vcombCtrol = new VeiculoCombustivelControl();
        custoMaior = new CustosVeiculo();
        custoMenor = new CustosVeiculo();
        aux = new Auxiliar();
        arrayListMarcas = new ArrayList<String>();
        arrayListModelos = new ArrayList<String>();
       
        preecherArrayList();
    }
    private void preecherArrayList(){
        arraylist = veiCtrol.getArrayListTodosVeiculos();
        preencherCbbTipo();
    }
    private void preencherTabela(){
        DefaultTableModel model = new DefaultTableModelNaoEditavel();
        TableRowSorter tableSorter = new TableRowSorter(model);
        
        try{
            model.addColumn("Nº"); //1 
            model.addColumn("Placa");//2 
            model.addColumn("Tipo");//3
            model.addColumn("Marca"); //4
            model.addColumn("Modelo"); // 5
            model.addColumn("Ano"); //6
            model.addColumn("Combustível (R$/KM)"); //7
            model.addColumn("Óleo (R$/KM)"); //8
            model.addColumn("Total (R$/KM)"); //9
            model.addColumn("Manutenção (R$/KM)");//10
            model.addColumn("Total + Manutenção (R$/KM)"); //11
            
            //arrayListFiltrado = veiCtrol.getArrayListTodosVeiculos();
            
            arraylistCustos = new ArrayList<CustosVeiculo>();
            CustosVeiculo custoVeiculo;
            
            custoMaior = new CustosVeiculo();
            custoMenor = new CustosVeiculo();
            //veiculoMaior = new Veiculo();
            //veiculoMenor = new Veiculo();
            qtdeFiltrado = 0;
            if(arrayListFiltrado.size()>0){
                for(int i = arrayListFiltrado.size() -1; i>=0; i--){
                    Veiculo v = arrayListFiltrado.get(i);
                    if(!testarCondicoesFiltro(v)){
                        continue;
                    }
                    custoVeiculo = new CustosVeiculo();
                    custoVeiculo.setIdVeiculo(arrayListFiltrado.get(i).getId());
                    custoVeiculo.setCombustivel(vcombCtrol.custoKmGeralByIdVeiculo(arrayListFiltrado.get(i).getId(), 5));
                    custoVeiculo.setOleo(pOleoCtrol.getMediaCustoGeral(arrayListFiltrado.get(i).getId(), 5));
                    custoVeiculo.setManutencao(manCtrol.custoKmParcial(arrayListFiltrado.get(i).getId()));
                    arraylistCustos.add(custoVeiculo);
                    if(custoVeiculo.getCustosGerais()>custoMaior.getCustosGerais()){
                        veiculoMaior = arrayListFiltrado.get(i);
                        custoMaior = custoVeiculo;
                    }
                    if(custoVeiculo.getCustosGerais()<=custoMenor.getCustosGerais() || veiculoMenor.getId()==0){
                        veiculoMenor = arrayListFiltrado.get(i);
                        custoMenor = custoVeiculo;
                    }
                    qtdeFiltrado++;
                    
                    Object[] row;
                     row = new Object[11];
                     row[0]= qtdeFiltrado;
                     row[1] = v.getPlaca();
                     row[2] = Veiculo.TIPOS_STRING[v.getTipo()];
                     row[3] = v.getMarca();
                     row[4] = v.getModelo();
                     row[5] = v.getAno();
                     row[6] = aux.StringFloatReais(custoVeiculo.getCombustivel());
                     row[7] = aux.StringFloatReais(custoVeiculo.getOleo());
                     row[8] = aux.StringFloatReais(custoVeiculo.getCustosGerais());
                     row[9] = aux.StringFloatReais(custoVeiculo.getManutencao());
                     row[10] = aux.StringFloatReais(custoVeiculo.getManutencao() + custoVeiculo.getCustosGerais() );

                     model.addRow(row);
                    
                }
                /*Object[] row;
                for(int i = arrayListFiltrado.size() -1; i>=0; i--){
                    row = new Object[11];
                    row[0]= i+1;
                    row[1] = arrayListFiltrado.get(i).getPlaca();
                    row[2] = Veiculo.TIPOS_STRING[arrayListFiltrado.get(i).getTipo()];
                    row[3] = arrayListFiltrado.get(i).getMarca();
                    row[4] = arrayListFiltrado.get(i).getModelo();
                    row[5] = arrayListFiltrado.get(i).getAno();
                    row[6] = aux.StringFloatReais(arraylistCustos.get(arraylistCustos.size() - 1 - i).getCombustivel());
                    row[7] = aux.StringFloatReais(arraylistCustos.get(arraylistCustos.size() - 1 - i).getOleo());
                    row[8] = aux.StringFloatReais(arraylistCustos.get(arraylistCustos.size() - 1 - i).getCustosGerais());
                    row[9] = aux.StringFloatReais(arraylistCustos.get(arraylistCustos.size() - 1 - i).getManutencao());
                    row[10] = aux.StringFloatReais(arraylistCustos.get(arraylistCustos.size() - 1 - i).getManutencao() + arraylistCustos.get(arraylistCustos.size() - 1 - i).getCustosGerais() );
                    
                    tableModel.addRow(row);
                }*/
            }
             tableSorter = new TableRowSorter(model);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustosGlobais.preencherTabela");
        }finally{
            jTable.setModel(model);
            jTable.getColumnModel().getColumn(0).setMaxWidth(40);
            jTable.getColumnModel().getColumn(1).setMaxWidth(70);
            jTable.getColumnModel().getColumn(2).setMaxWidth(70);
            jTable.getColumnModel().getColumn(5).setMaxWidth(50);
            jTable.setRowSorter(tableSorter);
            tableSorter.toggleSortOrder(2); // Esta linha seleciona a coluna padrão ordenada
            preencherDetalhesFrota();
        }
        
    }
    private boolean testarCondicoesFiltro(Veiculo v){
        boolean isAnoIni=false, isAnoFim=false, isModelo=false;
        int anoIni = 0, anoFim = 0;
        try{
                anoIni = (txfAnoInicio.getText().isBlank())? 0 :Integer.parseInt(txfAnoInicio.getText());
                anoFim = (txfAnoFim.getText().isBlank())? 0 :Integer.parseInt(txfAnoFim.getText());
                isAnoIni = (anoIni>0)? true: false;
                isAnoFim = (anoFim>0)? true: false;
            
                    if(isAnoIni){
                        if(v.getAno()< anoIni){
                            return false;
                        }
                    }
                    if(isAnoFim){
                        if(v.getAno()> anoFim){
                            return false;
                        }
                    }
                    if(cbbModelo.getSelectedIndex()>0){
                        if(!arrayListModelos.get(cbbModelo.getSelectedIndex()-1).equalsIgnoreCase(v.getModelo())){
                            return false;
                        }
                    }
                    return true;
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustosGlobais.testarCondicoesFiltro");
            return false;
        }
    }
    private void preencherDetalhesFrota(){
        float custoGeralCombustivel = 0, custoGeralOleo = 0, custoGeralManutencao=0, custoGeralTotal = 0, custoGeralTotalManutencao = 0;
        try{
            for(int i = 0; i< arraylistCustos.size(); i++){
                custoGeralCombustivel += arraylistCustos.get(i).getCombustivel();
                custoGeralOleo+=arraylistCustos.get(i).getOleo();
                custoGeralManutencao+= arraylistCustos.get(i).getManutencao();
                custoGeralTotal += arraylistCustos.get(i).getCustosGerais();
                custoGeralTotalManutencao +=arraylistCustos.get(i).getCustosGeraisMaisManutencao();
            }
            //int tamanho = arraylist.size();
            int tamanho = qtdeFiltrado;
            tamanho = (tamanho>0) ? tamanho: 1;
            
            lblCombustivelGeralAcu.setText(aux.StringFloatReais(custoGeralCombustivel));
            lblOleoGeralAcu.setText(aux.StringFloatReais(custoGeralOleo));
            lblManutencaoGeralAcu.setText(aux.StringFloatReais(custoGeralManutencao));
            lblTotalGeralAcu.setText(aux.StringFloatReais(custoGeralTotal));
            lblManutencaoTotalGeralAcu.setText(aux.StringFloatReais(custoGeralTotalManutencao));
            
            custoGeralCombustivel = custoGeralCombustivel / tamanho;
            custoGeralManutencao = custoGeralManutencao / tamanho;
            custoGeralOleo = custoGeralOleo / tamanho;
            custoGeralTotal = custoGeralTotal / tamanho;
            custoGeralTotalManutencao = custoGeralTotalManutencao / tamanho;
            
            lblCombustivelGeral.setText(aux.StringFloatReais(custoGeralCombustivel));
            lblOleoGeral.setText(aux.StringFloatReais(custoGeralOleo));
            lblManutencaoGeral.setText(aux.StringFloatReais(custoGeralManutencao));
            lblTotalGeral.setText(aux.StringFloatReais(custoGeralTotal));
            lblManutencaoTotalGeral.setText(aux.StringFloatReais(custoGeralTotalManutencao));
            
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
            jpGraficoGeral.removeAll();
            jpGraficoGeral.add(new jpGraficoPizza("Custo Médio", arraygrafico));
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustosGlobais.preencherDetalhesFrota");
        }finally{
            
            lblNVeiculosTotal.setText(arraylist.size()+"");
            lblNVeiculos.setText(qtdeFiltrado+"");
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
            lblManutencaoTotalMaior.setText(aux.StringFloatReais(custoMaior.getCustosGeraisMaisManutencao()));
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
            lblManutencaoTotalMenor.setText(aux.StringFloatReais(custoMenor.getCustosGeraisMaisManutencao()));
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustosGlobais. preencherMenor");
        }
    }
    private void preencherCbbTipo(){
        cbbTipo.removeAllItems();
        cbbTipo.addItem(OP_TODOS);
        for(String tipo: Veiculo.TIPOS_STRING){
            cbbTipo.addItem(tipo);
        }
    }
    private void preencherCBBMarca(){
        try{
            arrayListFiltradoMarca.clear();
            arrayListMarcas.clear();
            cbbMarca.removeAllItems();
            cbbMarca.addItem(OP_TODOS);
            for(Veiculo v: arraylist){
                if(cbbTipo.getSelectedIndex()==0){
                    arrayListFiltradoMarca.add(v);
                    if(!arrayListMarcas.contains(v.getMarca())){
                        arrayListMarcas.add(v.getMarca());
                        cbbMarca.addItem(v.getMarca());
                    }
                    continue;
                }
                if(v.getTipo()==cbbTipo.getSelectedIndex()-1){
                    arrayListFiltradoMarca.add(v);
                    if(!arrayListMarcas.contains(v.getMarca())){
                        arrayListMarcas.add(v.getMarca());
                        cbbMarca.addItem(v.getMarca());
                    }
                }
            }
            preencherCBBModelo();
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustosGlobais.preencherCBBMarca");
        }
    }
    private void preencherCBBModelo() {
        try{
            arrayListFiltrado.clear();
            cbbModelo.removeAllItems();
            cbbModelo.addItem(OP_TODOS);
            arrayListModelos.clear();
            for(Veiculo v: arrayListFiltradoMarca){
                if(cbbMarca.getSelectedIndex()==0){
                    arrayListFiltrado.add(v);
                    if(!arrayListModelos.contains(v.getModelo())){
                        arrayListModelos.add(v.getModelo());
                        cbbModelo.addItem(v.getModelo());
                    }
                }else if(v.getMarca().equalsIgnoreCase(arrayListMarcas.get(cbbMarca.getSelectedIndex()-1))){
                    arrayListFiltrado.add(v);
                    if(!arrayListModelos.contains(v.getModelo())){
                        arrayListModelos.add(v.getModelo());
                        cbbModelo.addItem(v.getModelo());
                    }
                }
            }
            preencherTabela();
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpCustosGlobais.preencherCBBModelo");
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
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        lblNVeiculos = new javax.swing.JLabel();
        lblNVeiculosTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblCombustivelGeral = new javax.swing.JLabel();
        lblOleoGeral = new javax.swing.JLabel();
        lblTotalGeral = new javax.swing.JLabel();
        lblManutencaoGeral = new javax.swing.JLabel();
        lblManutencaoTotalGeral = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblCombustivelGeralAcu = new javax.swing.JLabel();
        lblOleoGeralAcu = new javax.swing.JLabel();
        lblTotalGeralAcu = new javax.swing.JLabel();
        lblManutencaoGeralAcu = new javax.swing.JLabel();
        lblManutencaoTotalGeralAcu = new javax.swing.JLabel();
        jpGraficoGeral = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblCombustivelMenor = new javax.swing.JLabel();
        lblOleoMenor = new javax.swing.JLabel();
        lblTotalMenor = new javax.swing.JLabel();
        lblManutencaoMenor = new javax.swing.JLabel();
        lblManutencaoTotalMenor = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblPlacaMenor = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblMarcaMenor = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblModeloMenor = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblAnoMenor = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblPlacaMaior = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblMarcaMaior = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblModeloMaior = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblAnoMaior = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblCombustivelMaior = new javax.swing.JLabel();
        lblOleoMaior = new javax.swing.JLabel();
        lblTotalMaior = new javax.swing.JLabel();
        lblManutencaoMaior = new javax.swing.JLabel();
        lblManutencaoTotalMaior = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cbbTipo = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbbMarca = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cbbModelo = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        txfAnoInicio = new javax.swing.JTextField();
        txfAnoFim = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/analiseB.png"))); // NOI18N
        jLabel1.setText("Análise Global da Frota");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações Gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

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
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalhes da Frota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel2.setMinimumSize(new java.awt.Dimension(1118, 125));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Consulta");

        lblNVeiculos.setBackground(new java.awt.Color(205, 220, 57));
        lblNVeiculos.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        lblNVeiculos.setForeground(new java.awt.Color(0, 153, 0));
        lblNVeiculos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNVeiculos.setText("100");

        lblNVeiculosTotal.setBackground(new java.awt.Color(205, 220, 57));
        lblNVeiculosTotal.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNVeiculosTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNVeiculosTotal.setText("100");
        lblNVeiculosTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("de");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNVeiculosTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22))
                    .addComponent(lblNVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNVeiculos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNVeiculosTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(3, 6));

        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Reais/Km");
        jPanel5.add(jLabel17);

        jLabel24.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abastecimentop.png"))); // NOI18N
        jLabel24.setText("Combustível");
        jPanel5.add(jLabel24);

        jLabel25.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/oleop.png"))); // NOI18N
        jLabel25.setText("Óleo e Filtro");
        jPanel5.add(jLabel25);

        jLabel35.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel35.setText("Total");
        jPanel5.add(jLabel35);

        jLabel26.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel26.setText("Manutenções");
        jPanel5.add(jLabel26);

        jLabel37.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel37.setText("Total + Manutenção");
        jPanel5.add(jLabel37);

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(33, 150, 243));
        jLabel23.setText("Custo Médio");
        jPanel5.add(jLabel23);

        lblCombustivelGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblCombustivelGeral.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCombustivelGeral.setForeground(new java.awt.Color(127, 127, 127));
        lblCombustivelGeral.setText("100");
        jPanel5.add(lblCombustivelGeral);

        lblOleoGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblOleoGeral.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblOleoGeral.setForeground(new java.awt.Color(127, 127, 127));
        lblOleoGeral.setText("100");
        jPanel5.add(lblOleoGeral);

        lblTotalGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblTotalGeral.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblTotalGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblTotalGeral.setText("100");
        jPanel5.add(lblTotalGeral);

        lblManutencaoGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoGeral.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblManutencaoGeral.setForeground(new java.awt.Color(127, 127, 127));
        lblManutencaoGeral.setText("100");
        jPanel5.add(lblManutencaoGeral);

        lblManutencaoTotalGeral.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoTotalGeral.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblManutencaoTotalGeral.setForeground(new java.awt.Color(33, 150, 243));
        lblManutencaoTotalGeral.setText("100");
        jPanel5.add(lblManutencaoTotalGeral);

        jLabel36.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 64, 129));
        jLabel36.setText("Total Acumulado");
        jPanel5.add(jLabel36);

        lblCombustivelGeralAcu.setBackground(new java.awt.Color(205, 220, 57));
        lblCombustivelGeralAcu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCombustivelGeralAcu.setForeground(new java.awt.Color(127, 127, 127));
        lblCombustivelGeralAcu.setText("100");
        jPanel5.add(lblCombustivelGeralAcu);

        lblOleoGeralAcu.setBackground(new java.awt.Color(205, 220, 57));
        lblOleoGeralAcu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblOleoGeralAcu.setForeground(new java.awt.Color(127, 127, 127));
        lblOleoGeralAcu.setText("100");
        jPanel5.add(lblOleoGeralAcu);

        lblTotalGeralAcu.setBackground(new java.awt.Color(205, 220, 57));
        lblTotalGeralAcu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblTotalGeralAcu.setForeground(new java.awt.Color(255, 64, 129));
        lblTotalGeralAcu.setText("100");
        jPanel5.add(lblTotalGeralAcu);

        lblManutencaoGeralAcu.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoGeralAcu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblManutencaoGeralAcu.setForeground(new java.awt.Color(127, 127, 127));
        lblManutencaoGeralAcu.setText("100");
        jPanel5.add(lblManutencaoGeralAcu);

        lblManutencaoTotalGeralAcu.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoTotalGeralAcu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblManutencaoTotalGeralAcu.setForeground(new java.awt.Color(255, 64, 129));
        lblManutencaoTotalGeralAcu.setText("100");
        jPanel5.add(lblManutencaoTotalGeralAcu);

        jpGraficoGeral.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoGeral.setLayout(new java.awt.GridLayout(1, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpGraficoGeral, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jpGraficoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menores Custos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridLayout(2, 5));

        jLabel27.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abastecimentop.png"))); // NOI18N
        jLabel27.setText("Combustível");
        jPanel6.add(jLabel27);

        jLabel28.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/oleop.png"))); // NOI18N
        jLabel28.setText("Óleo e Filtro");
        jPanel6.add(jLabel28);

        jLabel33.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel33.setText("Total");
        jPanel6.add(jLabel33);

        jLabel29.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel29.setText("Manutenções");
        jPanel6.add(jLabel29);

        jLabel38.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel38.setText("Total + Manutenções");
        jPanel6.add(jLabel38);

        lblCombustivelMenor.setBackground(new java.awt.Color(205, 220, 57));
        lblCombustivelMenor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblCombustivelMenor.setForeground(new java.awt.Color(127, 127, 127));
        lblCombustivelMenor.setText("100");
        jPanel6.add(lblCombustivelMenor);

        lblOleoMenor.setBackground(new java.awt.Color(205, 220, 57));
        lblOleoMenor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblOleoMenor.setForeground(new java.awt.Color(127, 127, 127));
        lblOleoMenor.setText("100");
        jPanel6.add(lblOleoMenor);

        lblTotalMenor.setBackground(new java.awt.Color(205, 220, 57));
        lblTotalMenor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblTotalMenor.setForeground(new java.awt.Color(33, 150, 243));
        lblTotalMenor.setText("100");
        jPanel6.add(lblTotalMenor);

        lblManutencaoMenor.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoMenor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblManutencaoMenor.setForeground(new java.awt.Color(127, 127, 127));
        lblManutencaoMenor.setText("100");
        jPanel6.add(lblManutencaoMenor);

        lblManutencaoTotalMenor.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoTotalMenor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblManutencaoTotalMenor.setForeground(new java.awt.Color(33, 150, 243));
        lblManutencaoTotalMenor.setText("100");
        jPanel6.add(lblManutencaoTotalMenor);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Placa");
        jPanel7.add(jLabel8);

        lblPlacaMenor.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        lblPlacaMenor.setText("DLH8657");
        jPanel7.add(lblPlacaMenor);

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Marca");
        jPanel7.add(jLabel10);

        lblMarcaMenor.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        lblMarcaMenor.setText("Volkswagem");
        jPanel7.add(lblMarcaMenor);

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Modelo");
        jPanel7.add(jLabel11);

        lblModeloMenor.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        lblModeloMenor.setText("GOL G4");
        jPanel7.add(lblModeloMenor);

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Ano");
        jPanel7.add(jLabel12);

        lblAnoMenor.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        lblAnoMenor.setText("2015");
        jPanel7.add(lblAnoMenor);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maiores Custos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(127, 127, 127));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Placa");
        jPanel8.add(jLabel9);

        lblPlacaMaior.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        lblPlacaMaior.setText("DLH8657");
        jPanel8.add(lblPlacaMaior);

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Marca");
        jPanel8.add(jLabel13);

        lblMarcaMaior.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        lblMarcaMaior.setText("Volkswagem");
        jPanel8.add(lblMarcaMaior);

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Modelo");
        jPanel8.add(jLabel14);

        lblModeloMaior.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        lblModeloMaior.setText("GOL G4");
        jPanel8.add(lblModeloMaior);

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Ano");
        jPanel8.add(jLabel16);

        lblAnoMaior.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        lblAnoMaior.setText("2015");
        jPanel8.add(lblAnoMaior);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.GridLayout(2, 5));

        jLabel32.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abastecimentop.png"))); // NOI18N
        jLabel32.setText("Combustível");
        jPanel9.add(jLabel32);

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/oleop.png"))); // NOI18N
        jLabel31.setText("Óleo e Filtro");
        jPanel9.add(jLabel31);

        jLabel34.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dinheirop.png"))); // NOI18N
        jLabel34.setText("Total");
        jPanel9.add(jLabel34);

        jLabel30.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel30.setText("Manutenções");
        jPanel9.add(jLabel30);

        jLabel39.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manutencaop.png"))); // NOI18N
        jLabel39.setText("Total + Manutenções");
        jPanel9.add(jLabel39);

        lblCombustivelMaior.setBackground(new java.awt.Color(205, 220, 57));
        lblCombustivelMaior.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblCombustivelMaior.setForeground(new java.awt.Color(127, 127, 127));
        lblCombustivelMaior.setText("100");
        jPanel9.add(lblCombustivelMaior);

        lblOleoMaior.setBackground(new java.awt.Color(205, 220, 57));
        lblOleoMaior.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblOleoMaior.setForeground(new java.awt.Color(127, 127, 127));
        lblOleoMaior.setText("100");
        jPanel9.add(lblOleoMaior);

        lblTotalMaior.setBackground(new java.awt.Color(205, 220, 57));
        lblTotalMaior.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblTotalMaior.setForeground(new java.awt.Color(255, 64, 129));
        lblTotalMaior.setText("100");
        jPanel9.add(lblTotalMaior);

        lblManutencaoMaior.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoMaior.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblManutencaoMaior.setForeground(new java.awt.Color(127, 127, 127));
        lblManutencaoMaior.setText("100");
        jPanel9.add(lblManutencaoMaior);

        lblManutencaoTotalMaior.setBackground(new java.awt.Color(205, 220, 57));
        lblManutencaoTotalMaior.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblManutencaoTotalMaior.setForeground(new java.awt.Color(255, 64, 129));
        lblManutencaoTotalMaior.setText("100");
        jPanel9.add(lblManutencaoTotalMaior);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator9)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel11.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tipo");
        jPanel11.add(jLabel15);

        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTipoItemStateChanged(evt);
            }
        });
        jPanel11.add(cbbTipo);

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Marca");
        jPanel11.add(jLabel18);

        cbbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMarcaItemStateChanged(evt);
            }
        });
        jPanel11.add(cbbMarca);

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Modelo");
        jPanel11.add(jLabel19);

        cbbModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbModelo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbModeloItemStateChanged(evt);
            }
        });
        jPanel11.add(cbbModelo);

        jLabel20.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Ano");
        jPanel11.add(jLabel20);

        txfAnoInicio.setToolTipText("Inicial");
        txfAnoInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txfAnoInicioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfAnoInicioKeyTyped(evt);
            }
        });
        jPanel11.add(txfAnoInicio);

        txfAnoFim.setToolTipText("Final");
        txfAnoFim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txfAnoFimKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfAnoFimKeyTyped(evt);
            }
        });
        jPanel11.add(txfAnoFim);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void cbbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTipoItemStateChanged
        if(cbbTipo.getSelectedIndex()>=0){
            preencherCBBMarca();
        }
    }//GEN-LAST:event_cbbTipoItemStateChanged

    private void cbbMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMarcaItemStateChanged
        preencherCBBModelo();
    }//GEN-LAST:event_cbbMarcaItemStateChanged

    private void cbbModeloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbModeloItemStateChanged
        preencherTabela();
    }//GEN-LAST:event_cbbModeloItemStateChanged

    private void txfAnoInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfAnoInicioKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
        
    }//GEN-LAST:event_txfAnoInicioKeyTyped

    private void txfAnoFimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfAnoFimKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
        
    }//GEN-LAST:event_txfAnoFimKeyTyped

    private void txfAnoFimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfAnoFimKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER){
            preencherTabela();
        } 
    }//GEN-LAST:event_txfAnoFimKeyPressed

    private void txfAnoInicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfAnoInicioKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER){
            preencherTabela();
        }
    }//GEN-LAST:event_txfAnoInicioKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbMarca;
    private javax.swing.JComboBox<String> cbbModelo;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
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
    private javax.swing.JLabel lblManutencaoTotalGeral;
    private javax.swing.JLabel lblManutencaoTotalGeralAcu;
    private javax.swing.JLabel lblManutencaoTotalMaior;
    private javax.swing.JLabel lblManutencaoTotalMenor;
    private javax.swing.JLabel lblMarcaMaior;
    private javax.swing.JLabel lblMarcaMenor;
    private javax.swing.JLabel lblModeloMaior;
    private javax.swing.JLabel lblModeloMenor;
    private javax.swing.JLabel lblNVeiculos;
    private javax.swing.JLabel lblNVeiculosTotal;
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
    private javax.swing.JTextField txfAnoFim;
    private javax.swing.JTextField txfAnoInicio;
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
        setIdVeiculo(0);
        setCombustivel(0);
        setOleo(0);
        setManutencao(0);
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
    public float getCustosGeraisMaisManutencao(){
        return getCustosGerais()+getManutencao();
    }
        
    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
}
