/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.PedidoControl;
import Dao.RestauranteDAO;
import Model.Pedido;
import Model.Restaurante;
import Model.Usuario;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class jpRequisicao extends javax.swing.JPanel {

    ArrayList<Restaurante> restauranteArrayList;
    Usuario usuario;
    
    public jpRequisicao() {
        inicializar();
    }
    public jpRequisicao(Usuario u){
        inicializar();
        this.usuario = u;
        txfUserLogado.setText(usuario.getNome());
    }
    private void inicializar(){
        initComponents();
        restauranteArrayList = new ArrayList<Restaurante>();
        usuario = new Usuario();
        limparCampos();
        enableComponentes(false);
        String data = getFormatoData(Calendar.getInstance().getTimeInMillis());
        txfDataAtual.setText(data);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txfUserLogado = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txfDataAtual = new javax.swing.JTextField();
        
 
        jLabel2 = new javax.swing.JLabel();
        cbbRestaurante = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txfTelefone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txfEndereco = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txfQuantidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescricao = new javax.swing.JTextArea();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();

        jLabel1.setText("Usuário");

        txfUserLogado.setEditable(false);
        txfUserLogado.setText("User 1");

        txfDataAtual.setEditable(false);
        txfDataAtual.setText("27/06/2020");

        jLabel2.setText("Restaurante");

        cbbRestaurante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbRestaurante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbRestauranteItemStateChanged(evt);
            }
        });
        cbbRestaurante.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbbRestaurantePropertyChange(evt);
            }
        });

        jLabel3.setText("Telefone");

        txfTelefone.setEditable(false);
        txfTelefone.setText("99999-9999 3333-3333");

        jLabel4.setText("Endereço: ");

        txfEndereco.setEditable(false);
        txfEndereco.setText("Endereço do restaurante");

        jLabel5.setText("Quantidade");

        txfQuantidade.setText("555");

        jLabel6.setText("Descrição");

        txaDescricao.setColumns(20);
        txaDescricao.setRows(5);
        jScrollPane1.setViewportView(txaDescricao);

        btnSalvar.setBackground(new java.awt.Color(204, 255, 204));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/baseline_done_outline_black_18dp.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setBackground(new java.awt.Color(204, 204, 255));
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/baseline_add_task_black_18dp.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txfUserLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txfEndereco))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbbRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txfTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfUserLogado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txfDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvar)
                            .addComponent(btnNovo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 919, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private String getFormatoData(Long TimeMilis){
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(TimeMilis);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH)+1;
        int ano = c.get(Calendar.YEAR);
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);
        
        String data = (dia<10) ? "0"+dia+"/" : dia + "/";
        data += (mes<10)?"0"+mes+"/": mes+"/";
        data += c.get(Calendar.YEAR)+" ";
        data += (hora<10)?"0"+hora : hora;
        data +=":";
        data += (minuto<10)?"0"+minuto : minuto;
        return data;
    }
    private void cbbRestauranteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbRestauranteItemStateChanged
        CbbSelecionado();
    }//GEN-LAST:event_cbbRestauranteItemStateChanged

    private void cbbRestaurantePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbbRestaurantePropertyChange

    }//GEN-LAST:event_cbbRestaurantePropertyChange

    private void pedidoGeradoSucesso(){
        try{
            enableComponentes(false);
            limparCampos();
            int i = 0;
            i = JOptionPane.showConfirmDialog(null, "Gostaria de imprimir a requisição agora?","Impressão de Requisição", JOptionPane.YES_NO_OPTION);
            if(i==JOptionPane.OK_OPTION){
                ImprimirUltimoPedido();
            }
        }catch (Exception e){
            
        }
    }
    private void ImprimirUltimoPedido(){
        new PedidoControl().ImprimirUltimoPedido();
    }
    private void enableComponentes(Boolean status){
        cbbRestaurante.setEnabled(status);
        btnNovo.setEnabled(!status);
        btnSalvar.setEnabled(status);
        txaDescricao.setEnabled(status);
        txfEndereco.setEnabled(status);
        txfTelefone.setEnabled(status);
        txfQuantidade.setEnabled(status);
    }
    private void limparCampos(){
        txaDescricao.setText("");
        txfEndereco.setText("");
        txfTelefone.setText("");
        txfQuantidade.setText("");
        PreencherCbbRestaurante();
    }
    private int checarCampos(){
        if(txaDescricao.getText().equals("")||txfQuantidade.getText().equals("")||cbbRestaurante.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Verifique os campos!", "Informações Faltando!", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        return 1;
    }
    private void CbbSelecionado(){
           int i = cbbRestaurante.getSelectedIndex()-1;
           if(i>=0){
            txfEndereco.setText(restauranteArrayList.get(i).getEndereco());
            txfTelefone.setText(restauranteArrayList.get(i).getTelefone());
           }
    }
    private void PreencherCbbRestaurante(){
        restauranteArrayList = new RestauranteDAO().PesquisarTodos();
        cbbRestaurante.removeAllItems();
        cbbRestaurante.addItem("");
        
        for(int i = 0; i<restauranteArrayList.size(); i++){
            cbbRestaurante.addItem(restauranteArrayList.get(i).getNome());
        }
        if(restauranteArrayList.size()>0)
            cbbRestaurante.setSelectedIndex(0);
    }
    
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try{
            if(checarCampos()==1){
                Pedido pedido = new Pedido();
                pedido.setTimeMili(Calendar.getInstance().getTimeInMillis());
                pedido.setQuantidade(Integer.parseInt(txfQuantidade.getText()));
                pedido.setDescricao(txaDescricao.getText().toString().trim());
                pedido.setIdRestaurante(restauranteArrayList.get(cbbRestaurante.getSelectedIndex()-1).getId());
                pedido.setIdUsuario(usuario.getId());//modificar
                PedidoControl pedidoControl = new PedidoControl();
                pedidoControl.Inserir(pedido);
                pedidoGeradoSucesso();
            }
        }catch (Exception e){

        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void mensagem(String mensagem){
        JOptionPane.showMessageDialog(null,mensagem,"Informação",JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        enableComponentes(true);
        limparCampos();
    }//GEN-LAST:event_btnNovoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbbRestaurante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea txaDescricao;
    private javax.swing.JTextField txfDataAtual;
    private javax.swing.JTextField txfEndereco;
    private javax.swing.JTextField txfQuantidade;
    private javax.swing.JTextField txfTelefone;
    private javax.swing.JTextField txfUserLogado;
    // End of variables declaration//GEN-END:variables
}
