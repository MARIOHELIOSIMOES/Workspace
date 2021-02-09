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
import model.Auxiliar;
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
        limparCampos();
        preencherListView();
    }
    private void preencherSelectedListview(){
        int j = jList.getSelectedIndex();
        if(j>=0){
            int i = arraylist.size()-j-1;
            lblDetalheData.setText(new Auxiliar(arraylist.get(i).getDataMilis()).getDataString());
            lblDetalheKm.setText(arraylist.get(i).getValor()+"");
            lblDetalheUsuario.setText(new UsuarioControl().getNomeUsuarioById(arraylist.get(i).getIdUsuario()));
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
        preencherListView();
    }
    
    private void preencherListView(){
        DefaultListModel listModel = new DefaultListModel();
        int j=0;
        arraylist = new VeiculoKMControl().getArrayListVeiculoKM(veiculo.getId());
        if(arraylist.size()>0){
            txfUltKM.setText(arraylist.get(arraylist.size()-1).getValor()+"");
        }
        for(int i=arraylist.size()-1; i>=0; i--){
            String linha = "Nº " + (i+1);
            linha = linha + ", Data: " + aux.getDataString(arraylist.get(i).getDataMilis());
            linha = linha +", KM: " + arraylist.get(i).getValor();
            linha = linha +", Fonte Geradora: " + new UsuarioControl().getNomeUsuarioById(arraylist.get(i).getIdUsuario());
            listModel.add(j,linha);
            j++;
        }
        jList.setModel(listModel);
        if(arraylist.size()>0){
            jList.setSelectedIndex(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txfKmAtual = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txfUltKM = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblDetalheData = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblDetalheUsuario = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblDetalheKm = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        txfDataAtual = new javax.swing.JTextField();
        try{            
            javax.swing.text.MaskFormatter data =
            new javax.swing.text.MaskFormatter("##/##/####");        
            txfDataAtual = new javax.swing.JFormattedTextField(data);    
        }catch(Exception e){    }

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/quilometragemB.png"))); // NOI18N
        jLabel1.setText("Atualização da Quilometragem");
        jLabel1.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jLabel4.setText("Novo Km");

        txfKmAtual.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txfKmAtual.setForeground(new java.awt.Color(33, 150, 243));
        txfKmAtual.setText("111.111");

        btnSalvar.setBackground(new java.awt.Color(255, 64, 129));
        btnSalvar.setFont(new java.awt.Font("Comic Sans MS", 3, 12)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvarp.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jLabel7.setText("Último KM");

        txfUltKM.setEditable(false);
        txfUltKM.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txfUltKM.setForeground(new java.awt.Color(33, 150, 243));
        txfUltKM.setText("111.111");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalhes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(117, 117, 117))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setText("Data");

        lblDetalheData.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        lblDetalheData.setForeground(new java.awt.Color(33, 150, 243));
        lblDetalheData.setText("07/08/2020");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel8.setText("Fonte geradora");

        lblDetalheUsuario.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        lblDetalheUsuario.setForeground(new java.awt.Color(33, 150, 243));
        lblDetalheUsuario.setText("usuario xyz");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel10.setText("Km");

        lblDetalheKm.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        lblDetalheKm.setForeground(new java.awt.Color(33, 150, 243));
        lblDetalheKm.setText("100.000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(lblDetalheUsuario))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lblDetalheData))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDetalheKm))
                    .addComponent(jLabel10))
                .addContainerGap(488, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDetalheKm))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblDetalheData))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblDetalheUsuario))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Histórico de Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(117, 117, 117))); // NOI18N

        jList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txfUltKM, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txfDataAtual)
                    .addComponent(txfKmAtual, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfKmAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfUltKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(new VeiculoKMControl().addVeiculoKM(veiculo.getId(), usuario.getId(), txfDataAtual.getText(), txfKmAtual.getText())){
            limparCampos();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListValueChanged
        preencherSelectedListview();
    }//GEN-LAST:event_jListValueChanged

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDetalheData;
    private javax.swing.JLabel lblDetalheKm;
    private javax.swing.JLabel lblDetalheUsuario;
    private javax.swing.JTextField txfDataAtual;
    private javax.swing.JTextField txfKmAtual;
    private javax.swing.JTextField txfUltKM;
    // End of variables declaration//GEN-END:variables
}
