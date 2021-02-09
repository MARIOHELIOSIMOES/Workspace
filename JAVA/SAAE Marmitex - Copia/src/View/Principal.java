package View;

import Control.PedidoControl;
import Dao.RestauranteDAO;
import Model.Pedido;
import Model.Restaurante;
import com.mysql.fabric.xmlrpc.base.Value;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Principal extends JFrame {
    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;
    private JTextField usuárioLogadoTextField;
    private JButton novoButton;
    private JButton imprimirButton;
    private JButton salvarButton1;
    private JComboBox cbbRestaurante;
    private JTextField tfQtde;
    private JTextArea taDescricao;
    private JLabel lblTelefone;
    private JLabel lblEndereco;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTable table1;
    private JButton button1;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextArea textArea1;
    private JTable table2;

    ArrayList<Restaurante> restauranteArrayList;

    public Principal(){
        iniciar();

        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirButton.setEnabled(true);
                salvarButton1.setEnabled(true);
                tfQtde.setText("");
                taDescricao.setText("");
                PreencherCbbRestaurante();
            }
        });

        cbbRestaurante.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CbbSelecionado();
            }
        });
        cbbRestaurante.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                CbbSelecionado();
            }
        });
        salvarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pedido pedido = new Pedido();
                pedido.setData(Calendar.getInstance().getTime().toString());
                pedido.setDescricao(taDescricao.getText().toString());
                pedido.setIdRestaurante(restauranteArrayList.get(cbbRestaurante.getSelectedIndex()).getId());
                pedido.setIdUsuario(1);
                PedidoControl pedidoControl = new PedidoControl();
                pedidoControl.Inserir(pedido);
            }
        });
    }

    public void iniciar(){

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        PreencherCbbRestaurante();

    }
    private void CbbSelecionado(){
        int i = cbbRestaurante.getSelectedIndex()+0;
        lblEndereco.setText("Endereço: "+restauranteArrayList.get(i).getEndereco());
        lblTelefone.setText("Telefone: "+restauranteArrayList.get(i).getTelefone());
    }
    private void PreencherCbbRestaurante(){
        cbbRestaurante.removeAll();
        restauranteArrayList = new RestauranteDAO().PesquisarTodos();
        for(int i = 0; i<restauranteArrayList.size(); i++){
            cbbRestaurante.addItem(restauranteArrayList.get(i).getNome());
        }
        if(restauranteArrayList.size()>0)
            cbbRestaurante.setSelectedIndex(0);
    }

}
