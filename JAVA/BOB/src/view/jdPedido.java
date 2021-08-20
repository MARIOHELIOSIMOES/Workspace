package view;

import control.ItemControl;
import control.PedidoControl;
import control.PedidoItemControl;
import control.VeiculoControl;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import model.Auxiliar;
import model.Item;
import model.Pedido;
import model.PedidoItem;
import model.Usuario;
import model.Veiculo;

/**
 *
 * @author mario
 */
public class jdPedido extends javax.swing.JDialog {

    Veiculo veiculo;
    Usuario usuario;
    Pedido pedido;
    ArrayList<PedidoItem> arraylist;
    jpCustoOleo jpRaiz;
    Auxiliar aux;
    
    public jdPedido() {
        inicializar();
    }
    public jdPedido(jpCustoOleo jpRaiz, Pedido pedido, Usuario usuario){
        super.setModal(true);
        inicializar();
        this.pedido = pedido;
        this.usuario = usuario;
        this.jpRaiz = jpRaiz;
        atualizarTela();
    }
    private void inicializar(){
        initComponents();
        arraylist = new ArrayList<PedidoItem>();
        //veiculo = new Veiculo();
        usuario = new Usuario();
        aux = new Auxiliar();
    }
    private void atualizarTela(){
        preencherListViewPedidoItem();
        txfId.setText(pedido.getId()+"");
        txfPlaca.setText(new VeiculoControl().getVeiculoById(pedido.getId_veiculo()).getPlaca());
        txfData.setText(aux.getDataString(pedido.getDatamilis()));
        txfKm.setText(pedido.getKm()+"");
        txaInfo.setText(pedido.getInfo());
        txfValor.setText(aux.StringFloatReais(new PedidoItemControl().getValorTotalByIdPedido(pedido.getId())));
        
    }
    private void preencherListViewPedidoItem(){
        DefaultListModel listModel = new DefaultListModel();
        ItemControl ic = new ItemControl();
        Item item = new Item();
        int j=0;
        arraylist = pedido.getArraylist();
        for(int i=0; i<arraylist.size(); i++){
            item = ic.getItemByID(arraylist.get(i).getIdItem());
            String linha = "Nº " + (i+1);
            linha+=") " + Item.TIPO_STRING[item.getTipo()];
            linha+=": " + item.getMarcaModelo();
            linha+="; Quantidade: " + arraylist.get(i).getQuantidade();
            linha+="; Total: R$ " + aux.StringFloatReais(arraylist.get(i).getValorTotal());
            listModel.add(j,linha);
            j++;
        }
        jList.setModel(listModel);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txfId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        txfPlaca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txfData = new javax.swing.JTextField();
        try{            
            javax.swing.text.MaskFormatter data =
            new javax.swing.text.MaskFormatter("##/##/####");        
            txfData = new javax.swing.JFormattedTextField(data);    
        }catch(Exception e){    }
        jLabel9 = new javax.swing.JLabel();
        txfValor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        jLabel13 = new javax.swing.JLabel();
        txfKm = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaInfo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Detalhes do Pedido");
        jLabel1.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("ID");

        txfId.setEditable(false);
        txfId.setText("15");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Data");

        btnExcluir.setBackground(new java.awt.Color(255, 64, 129));
        btnExcluir.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setMaximumSize(new java.awt.Dimension(97, 27));
        btnExcluir.setMinimumSize(new java.awt.Dimension(97, 27));
        btnExcluir.setPreferredSize(new java.awt.Dimension(97, 27));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        txfPlaca.setEditable(false);
        txfPlaca.setText("DLH-8657");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Placa do Veículo");

        txfData.setEditable(false);
        txfData.setText("20/12/2020");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Informação");

        txfValor.setEditable(false);
        txfValor.setText("150,50");

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Valor");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Itens", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), new java.awt.Color(127, 127, 127))); // NOI18N

        jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("KM ");

        txfKm.setEditable(false);
        txfKm.setText("DLH-8657");

        txaInfo.setEditable(false);
        txaInfo.setColumns(20);
        txaInfo.setRows(5);
        jScrollPane1.setViewportView(txaInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txfKm, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txfData, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txfKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11)
                    .addComponent(txfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if(!jfPrincipal.isUserAdmin()){
            aux.showMessagemSemPermissao();
            return;
        } 
        try{
            String id = aux.InputText("Informe o ID do pedido para confirmar a exclusão!");
            if(id.trim().equalsIgnoreCase(txfId.getText().trim())){
                new PedidoControl().Excluir(pedido);
                this.dispose();
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "jdPedido.btnExcluir");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        jpRaiz.atualizarTela();
    }//GEN-LAST:event_formWindowClosed
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txaInfo;
    private javax.swing.JTextField txfData;
    private javax.swing.JTextField txfId;
    private javax.swing.JTextField txfKm;
    private javax.swing.JTextField txfPlaca;
    private javax.swing.JTextField txfValor;
    // End of variables declaration//GEN-END:variables
}
