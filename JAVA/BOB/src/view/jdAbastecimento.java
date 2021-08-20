/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.UsuarioControl;
import control.VeiculoCombustivelControl;
import control.VeiculoKMControl;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Usuario;
import model.Veiculo;
import model.VeiculoCombustivel;

/**
 *
 * @author mario
 */
public class jdAbastecimento extends javax.swing.JDialog {

    Veiculo veiculo;
    Usuario usuario;
    ArrayList<VeiculoCombustivel> arraylist;
    jpCustoCombustivel jpRaiz;
    Auxiliar aux;
    VeiculoKMControl vkmCtrol;
    VeiculoCombustivelControl vcCtrol;
    
    public jdAbastecimento() {
        inicializar();
    }
    public jdAbastecimento(jpCustoCombustivel jpRaiz, Veiculo veiculo, Usuario usuario){
        super.setModal(true);
        inicializar();
        this.veiculo = veiculo;
        this.usuario = usuario;
        this.jpRaiz = jpRaiz;
        txfPlaca.setText(veiculo.getPlaca());
        txfUsuario.setText(usuario.getNome());
        txfKmAtual.setText(vkmCtrol.getUltimoKmByIDVeiculo(veiculo.getId())+"");
        txfCombustivel.setText(Veiculo.COMBUSTIVEIS[veiculo.getCombustivel()]);
        limparCampos();
    }
    private void inicializar(){
        initComponents();
        arraylist = new ArrayList<VeiculoCombustivel>();
        //veiculo = new Veiculo();
        usuario = new Usuario();
        vkmCtrol = new VeiculoKMControl();
        vcCtrol = new VeiculoCombustivelControl();
        aux = new Auxiliar();
        jTable.getSelectionModel().addListSelectionListener((e) -> {
           preencherSelectedTable();
        });
      // txfKmAtual = new JFormattedTextField(NumberFormat.getNumberInstance());
    }
    private void limparCampos(){
       // txfCombustivel.setText("");
        //txfPlaca.setText("");
        //txfUsuario.setText("");
        txfPosto.setText("");
        txfId.setText("0");
        txfLitragem.setText("");
        txfValor.setText("");
        txfKm.setText("");
        txfData.setText(aux.getDataStringAtual());
        preencherTabela();
    }
    private void preencherSelectedTable(){
        try{
           VeiculoCombustivel vc;
            
            int j = arraylist.size() - jTable.getSelectedRow()-1;

            if(j>=0 && jTable.getSelectedRow()>=0){
                
                vc = arraylist.get(j);
                btnSalvar.setText("Alterar");
                
                txfCombustivel.setText(vc.getCombustivel());
               // txfPlaca.setText(ve);
                txfUsuario.setText(vc.getMotorista());
                txfPosto.setText(vc.getPosto());
                txfId.setText(vc.getId()+"");
                txfLitragem.setText(""+vc.getLitros());
                //txfValor.setText(aux.StringFloatReais(arraylist.get(i).getValor()));
                txfValor.setText(vc.getValor()+"");
                txfKm.setText(""+vc.getKm());
                txfData.setText(aux.getDataString(vc.getDataMilis()));   
                btnExcluir.setEnabled(true);
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jdAbastecimento.preencherSelectedTable");
        }
    }
    
    private void preencherTabela(){
        DefaultTableModel model = new DefaultTableModelNaoEditavel();
        try{
            model.addColumn("Nº");//1
            model.addColumn("Data");//2
            model.addColumn("Usuário");//3
            model.addColumn("Posto");//4
            model.addColumn("Combustível");//5
            model.addColumn("Litros");//6
            model.addColumn("Valor");//7
            model.addColumn("Km");//8
            model.addColumn("Motorista");//9
            
            arraylist = vcCtrol.getArrayListByIDVeiculo(veiculo.getId());

            Object[] linha;
            UsuarioControl uc = new UsuarioControl();
            for(int i=arraylist.size()-1; i>=0; i--){

                linha = new Object[9];
                linha[0]=(i+1);
                linha[1]=aux.getDataString(arraylist.get(i).getDataMilis());
                linha[2]=uc.getNomeUsuarioById(arraylist.get(i).getIdUsuario());
                linha[3]=arraylist.get(i).getPosto();
                linha[4]=arraylist.get(i).getCombustivel();
                linha[5]=arraylist.get(i).getLitros();
                linha[6]= arraylist.get(i).getValor();
                linha[7]=arraylist.get(i).getKm();
                linha[8]=arraylist.get(i).getMotorista();

                model.addRow(linha);
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jdAbastecimento.preencherTabela");
        }finally{
            jTable.setModel(model);
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
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txfId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txfPlaca = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txfKmAtual = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txfUsuario = new javax.swing.JTextField();
        txfData = new javax.swing.JTextField();
        try{            
            javax.swing.text.MaskFormatter data =
            new javax.swing.text.MaskFormatter("##/##/####");        
            txfData = new javax.swing.JFormattedTextField(data);    
        }catch(Exception e){    }
        txfPosto = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txfCombustivel = new javax.swing.JTextField();
        txfLitragem = new javax.swing.JTextField();
        txfValor = new javax.swing.JTextField();
        txfKm = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abastecimentoB.png"))); // NOI18N
        jLabel1.setText("Abastecimento");
        jLabel1.setOpaque(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("ID");
        jPanel2.add(jLabel6);

        txfId.setEditable(false);
        txfId.setBackground(new java.awt.Color(255, 255, 255));
        txfId.setText("15");
        jPanel2.add(txfId);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Placa do Veículo");
        jPanel2.add(jLabel8);

        txfPlaca.setEditable(false);
        txfPlaca.setBackground(new java.awt.Color(255, 255, 255));
        txfPlaca.setText("DLH-8657");
        jPanel2.add(txfPlaca);

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("KM Atual");
        jPanel2.add(jLabel13);

        txfKmAtual.setEditable(false);
        txfKmAtual.setBackground(new java.awt.Color(255, 255, 255));
        txfKmAtual.setText("DLH-8657");
        jPanel2.add(txfKmAtual);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(2, 3, 10, 0));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Usuário/Motorista");
        jPanel3.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Data");
        jPanel3.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Posto de Combustível");
        jPanel3.add(jLabel4);

        txfUsuario.setText("Mario");
        jPanel3.add(txfUsuario);

        txfData.setText("20/12/2020");
        txfData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfDataFocusLost(evt);
            }
        });
        jPanel3.add(txfData);

        txfPosto.setText("Posto RVM Mogi Guaçu");
        jPanel3.add(txfPosto);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(2, 4, 10, 0));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Combustível");
        jPanel4.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Litragem");
        jPanel4.add(jLabel10);

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Valor");
        jPanel4.add(jLabel11);

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("KM do Veiculo");
        jPanel4.add(jLabel12);

        txfCombustivel.setText("Gasolina");
        jPanel4.add(txfCombustivel);

        txfLitragem.setText("50");
        txfLitragem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfLitragemFocusLost(evt);
            }
        });
        txfLitragem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfLitragemKeyTyped(evt);
            }
        });
        jPanel4.add(txfLitragem);

        txfValor.setText("150,50");
        txfValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfValorFocusLost(evt);
            }
        });
        txfValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfValorKeyTyped(evt);
            }
        });
        jPanel4.add(txfValor);

        txfKm.setText("150,50");
        txfKm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfKmFocusLost(evt);
            }
        });
        txfKm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfKmKeyTyped(evt);
            }
        });
        jPanel4.add(txfKm);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

        btnNovo.setBackground(new java.awt.Color(33, 150, 243));
        btnNovo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/maisp.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setMaximumSize(new java.awt.Dimension(105, 27));
        btnNovo.setMinimumSize(new java.awt.Dimension(105, 27));
        btnNovo.setPreferredSize(new java.awt.Dimension(105, 27));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNovo);

        btnSalvar.setBackground(new java.awt.Color(255, 64, 129));
        btnSalvar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvarp.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setMaximumSize(new java.awt.Dimension(105, 27));
        btnSalvar.setMinimumSize(new java.awt.Dimension(105, 27));
        btnSalvar.setPreferredSize(new java.awt.Dimension(105, 27));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel5.add(btnSalvar);
        jPanel5.add(jLabel5);

        btnExcluir.setBackground(new java.awt.Color(255, 0, 0));
        btnExcluir.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/freiop.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.setMaximumSize(new java.awt.Dimension(105, 27));
        btnExcluir.setMinimumSize(new java.awt.Dimension(105, 27));
        btnExcluir.setPreferredSize(new java.awt.Dimension(105, 27));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel5.add(btnExcluir);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros de Abastecimento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

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
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        txfId.setText(""+0);
        btnSalvar.setText("Salvar");
        btnExcluir.setEnabled(false);
        limparCampos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(vcCtrol.addVeiculoCombustivel(txfId.getText(), veiculo.getId(), usuario.getId(), 
                                                     txfKm.getText(), txfPosto.getText(), txfCombustivel.getText(), 
                                                     txfLitragem.getText(), txfValor.getText(), txfData.getText(), txfUsuario.getText())){
                
        
          limparCampos();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        jpRaiz.atualizarTela();
    }//GEN-LAST:event_formWindowClosed

    private void txfDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txfDataFocusLost
        if(!aux.validarData(txfData.getText())){
            txfData.setText(aux.getDataStringAtual());
            aux.showMessageDataInvalida();
        }   
    }//GEN-LAST:event_txfDataFocusLost

    private void txfValorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txfValorFocusLost
       try{
           float valor = new Auxiliar().StringToFloat(txfValor.getText());
           txfValor.setText(valor+"");
       }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Informe um valor numérico válido", "Valor inválido",JOptionPane.WARNING_MESSAGE);
       }
    }//GEN-LAST:event_txfValorFocusLost

    private void txfLitragemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txfLitragemFocusLost
        try{
           float valor = new Auxiliar().StringToFloat(txfLitragem.getText());
           txfLitragem.setText(valor+"");
       }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Informe um valor numérico válido", "Valor inválido",JOptionPane.WARNING_MESSAGE);
       }       
    }//GEN-LAST:event_txfLitragemFocusLost

    private void txfKmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txfKmFocusLost
        try{
            int valor = Integer.parseInt(txfKm.getText());
       }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Informe um valor numérico válido", "Valor inválido",JOptionPane.WARNING_MESSAGE);
            txfKm.setText("");
       }
    }//GEN-LAST:event_txfKmFocusLost

    private void txfLitragemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfLitragemKeyTyped
        String caracteres="0987654321.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_txfLitragemKeyTyped

    private void txfKmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfKmKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_txfKmKeyTyped

    private void txfValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfValorKeyTyped
        String caracteres="0987654321.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_txfValorKeyTyped

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if(!jfPrincipal.isUserAdmin()){
            aux.showMessagemSemPermissao();
            return;
        } 
        if(vcCtrol.excluirById(txfId.getText())){
            btnExcluir.setEnabled(false);
            preencherTabela();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField txfCombustivel;
    private javax.swing.JTextField txfData;
    private javax.swing.JTextField txfId;
    private javax.swing.JTextField txfKm;
    private javax.swing.JTextField txfKmAtual;
    private javax.swing.JTextField txfLitragem;
    private javax.swing.JTextField txfPlaca;
    private javax.swing.JTextField txfPosto;
    private javax.swing.JTextField txfUsuario;
    private javax.swing.JTextField txfValor;
    // End of variables declaration//GEN-END:variables
}
