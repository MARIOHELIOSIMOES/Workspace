package View;

import Control.UsuarioControl;

import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txfServidor;
    private JTextField txfUsuario;
    private JTextField txfSenha;

    public Login() {
        setContentPane(contentPane);
        //setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void onOK() {
        // add your code here
        int resultado = new UsuarioControl().Login(txfUsuario.getText(), txfSenha.getText());
        switch (resultado){
            case -1:
                JOptionPane.showMessageDialog(null,"Usuário Inválido","Login Falhou!",JOptionPane.ERROR_MESSAGE);
                break;
            case 0:
                JOptionPane.showMessageDialog(null,"Usuário ou Senha Inválido","Login Falhou!",JOptionPane.ERROR_MESSAGE);
                break;
            case 1:
                {
                    JOptionPane.showMessageDialog(null,txfUsuario.getText()+", Bem Vindo!","Login com sucesso!",JOptionPane.INFORMATION_MESSAGE);
                    new Principal().setVisible(true);
                    dispose();
                    break;
                }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Login dialog = new Login();
        dialog.pack();
        dialog.setVisible(true);

    }

}
