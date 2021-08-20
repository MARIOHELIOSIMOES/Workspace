/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.DocumentoControl;
import control.UsuarioControl;
import control.VeiculoControl;
import control.VeiculoKMControl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Auxiliar;
import model.DefaultTableModelNaoEditavel;
import model.Usuario;
import model.Veiculo;
import model.VeiculoKM;

/**
 *
 * @author mario
 */
public class jpKM extends javax.swing.JPanel {

    Veiculo veiculo;
    Usuario usuario;
    ArrayList<VeiculoKM> arraylist;
    Auxiliar aux;
    VeiculoKMControl vkmCtrol;
    UsuarioControl uc;
    
    public jpKM() {
        inicializar();
    }
    public jpKM(Veiculo veiculo, Usuario usuario){
        this.veiculo = veiculo;
        this.usuario = usuario;
        inicializar();
        
    }
    private void inicializar(){
        initComponents();
        arraylist = new ArrayList<VeiculoKM>();
        aux = new Auxiliar();
        vkmCtrol = new VeiculoKMControl();
        uc = new UsuarioControl();
        limparCampos();
        preencherTabela();
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                 preencherSelectedTable();
            }
        });
    }
    private void preencherSelectedTable(){
        int j = jTable.getSelectedRow();
        if(j>=0){
            int i = arraylist.size()-j-1;
            lblDetalheData.setText(new Auxiliar(arraylist.get(i).getDataMilis()).getDataString());
            lblDetalheKm.setText(arraylist.get(i).getValor()+"");
            lblDetalheUsuario.setText(new UsuarioControl().getNomeUsuarioById(arraylist.get(i).getIdUsuario()));
            txfId.setText(arraylist.get(i).getId()+"");
        }
    }
    
    private void limparCampos(){
        txfKmAtual.setText("");
        txfUltKM.setText("");
      // lblDataAtual.setText("");
        txfDataAtual.setText(aux.getDataStringAtual());
        lblDetalheData.setText("");
        lblDetalheKm.setText("");
        lblDetalheUsuario.setText("");
        preencherTabela();
    }
    
    private void preencherTabela(){
        DefaultTableModel model = new DefaultTableModelNaoEditavel();
        try{
            model.addColumn("Nº");
            model.addColumn("Data");
            model.addColumn("KM");
            model.addColumn("Usuário");
            int j=0;
            
            arraylist = vkmCtrol.getArrayListVeiculoKM(veiculo.getId());
            if(arraylist.size()>0){
                txfUltKM.setText(arraylist.get(arraylist.size()-1).getValor()+"");
                
                Object[] linha = new Object[4];
                for(int i=arraylist.size()-1; i>=0; i--){
                    linha[0] = (i+1);
                    linha[1] = aux.getDataString(arraylist.get(i).getDataMilis());
                    linha[2] = arraylist.get(i).getValor();
                    linha[3] = uc.getNomeUsuarioById(arraylist.get(i).getIdUsuario());
                    model.addRow(linha);
                }
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jpKm.preencherTabela");
        }finally{
            jTable.setModel(model);
            jTable.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable.getColumnModel().getColumn(1).setMaxWidth(80);
            jTable.getColumnModel().getColumn(2).setMaxWidth(70);
        
            if(arraylist.size()>0){
                 jTable.addRowSelectionInterval(0, 0);
                preencherSelectedTable();
            }
        }
        
        
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txfId = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        lblDetalheData = new javax.swing.JLabel();
        lblDetalheKm = new javax.swing.JLabel();
        lblDetalheUsuario = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txfUltKM = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txfKmAtual = new javax.swing.JTextField();
        txfDataAtual = new javax.swing.JTextField();
        try{            
            javax.swing.text.MaskFormatter data =
            new javax.swing.text.MaskFormatter("##/##/####");        
            txfDataAtual = new javax.swing.JFormattedTextField(data);    
        }catch(Exception e){    }
        jPanel5 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/quilometragemB.png"))); // NOI18N
        jLabel1.setText("Atualização da Quilometragem");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalhes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(2, 4, 10, 1));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Data");
        jPanel1.add(jLabel6);

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Km");
        jPanel1.add(jLabel10);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Fonte geradora");
        jPanel1.add(jLabel8);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setText("ID");

        txfId.setEditable(false);
        txfId.setText("1520");

        btnExcluir.setBackground(new java.awt.Color(255, 64, 129));
        btnExcluir.setFont(new java.awt.Font("Comic Sans MS", 3, 12)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/freiop.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcluir)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);

        lblDetalheData.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        lblDetalheData.setForeground(new java.awt.Color(127, 127, 127));
        lblDetalheData.setText("07/08/2020");
        jPanel1.add(lblDetalheData);

        lblDetalheKm.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        lblDetalheKm.setForeground(new java.awt.Color(127, 127, 127));
        lblDetalheKm.setText("100.000");
        jPanel1.add(lblDetalheKm);

        lblDetalheUsuario.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        lblDetalheUsuario.setForeground(new java.awt.Color(127, 127, 127));
        lblDetalheUsuario.setText("usuario xyz");
        jPanel1.add(lblDetalheUsuario);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Histórico de Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

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
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );

        jPanel4.setLayout(new java.awt.GridLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel7.setText("Último KM");

        txfUltKM.setEditable(false);
        txfUltKM.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txfUltKM.setForeground(new java.awt.Color(33, 150, 243));
        txfUltKM.setText("111.111");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txfUltKM, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfUltKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel7);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel4.setText("Novo Km");

        txfKmAtual.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txfKmAtual.setForeground(new java.awt.Color(33, 150, 243));
        txfKmAtual.setText("111.111");

        txfDataAtual.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txfDataAtual.setForeground(new java.awt.Color(255, 64, 129));
        txfDataAtual.setText("30/12/2020");
        txfDataAtual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfDataAtualFocusLost(evt);
            }
        });
        txfDataAtual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txfDataAtualKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txfDataAtual)
                    .addComponent(txfKmAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfKmAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel6);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnSalvar.setBackground(new java.awt.Color(33, 150, 243));
        btnSalvar.setFont(new java.awt.Font("Comic Sans MS", 3, 12)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvarp.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvar)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(jfPrincipal.isUserOperaOrAdmin()){
            if(new VeiculoKMControl().addVeiculoKM(veiculo.getId(), usuario.getId(), txfDataAtual.getText(), txfKmAtual.getText())){
                limparCampos();
            }
        }else{
            aux.showMessagemSemPermissao();
        }           
    }//GEN-LAST:event_btnSalvarActionPerformed

    private String verificarData(String jtxfield){
        try{
            String dia = jtxfield.substring(0, 2);
            String mes = jtxfield.substring(3, 5);
            String ano = jtxfield.substring(6);

            Calendar calendar = new GregorianCalendar();
            calendar.setLenient(false);
            calendar.set(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia));
            return aux.getDataString(calendar.getTimeInMillis());
            
        }catch(Exception e){
            aux.showMessageWarning("Informe uma data válida!", "Valor inválido");
            return aux.getDataStringAtual();
        }
    }
    private void txfDataAtualFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txfDataAtualFocusLost
        txfDataAtual.setText(verificarData(txfDataAtual.getText()));             
    }//GEN-LAST:event_txfDataAtualFocusLost

    private void txfDataAtualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfDataAtualKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER){
                    txfDataAtual.setText(verificarData(txfDataAtual.getText()));
        }        
    }//GEN-LAST:event_txfDataAtualKeyPressed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        preencherSelectedTable();
    }//GEN-LAST:event_jTableMouseClicked

    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyReleased
        preencherSelectedTable();
    }//GEN-LAST:event_jTableKeyReleased

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
       
        if(!jfPrincipal.isUserAdmin()){
            aux.showMessagemSemPermissao();
            return;
        }           
      
        try{
           String id = aux.InputTextIDExclusao();
           if(id.equalsIgnoreCase(txfId.getText())){
               vkmCtrol.excluirById(arraylist.get(arraylist.size() - 1 -jTable.getSelectedRow()));
           }
       }catch(Exception e){
           aux.RegistrarLog(e.getMessage(),"jpKM.btnExcluirActionPerformed");
       }finally{
           preencherTabela();
       }
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel lblDetalheData;
    private javax.swing.JLabel lblDetalheKm;
    private javax.swing.JLabel lblDetalheUsuario;
    private javax.swing.JTextField txfDataAtual;
    private javax.swing.JTextField txfId;
    private javax.swing.JTextField txfKmAtual;
    private javax.swing.JTextField txfUltKM;
    // End of variables declaration//GEN-END:variables
}
