package Threads.teste;

import Threads.BackgroundCalculator;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FibonacciNumbers extends JFrame{

    private final JPanel workedJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
    private final JTextField txfNumber = new JTextField();
    private final JButton btnGo = new JButton("Go");
    private final JLabel lblFibonacci = new JLabel();
    
    private final JPanel eventThreadJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
    private long n1 = 0, n2 = 0;
    private int count = 1;
    
    private final JLabel nJLabel = new JLabel("Fibonnacci of 1: ");
    private final JLabel nFibonacciJLabel = new JLabel(String.valueOf(n2));
    
    private final JButton nextNumberJButton = new JButton("Next number");


    public FibonacciNumbers(){
        super("Fibonacci Numbers");
        setLayout(new GridLayout(2, 1, 10, 10));
        
        workedJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "With SwingWorker"));
        workedJPanel.add(new JLabel("Get Fibonacci of: "));
        workedJPanel.add(txfNumber);
        btnGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int n=1;
               try{
                   n = Integer.parseInt(txfNumber.getText());
               }catch(NumberFormatException ex){
                   nFibonacciJLabel.setText("Enter an integer");
               }
               nFibonacciJLabel.setText("Calculating ...");
               
                BackgroundCalculator task = new BackgroundCalculator(n, nFibonacciJLabel);
                task.execute();
               
            }
            
        });
        
        workedJPanel.add(btnGo);
        workedJPanel.add(nFibonacciJLabel);
        
        eventThreadJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Without SwingWorker"));
        eventThreadJPanel.add(nJLabel);
        eventThreadJPanel.add(nFibonacciJLabel);
        nextNumberJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long temp = n1+n2; 
                n1 = n2;
                n2 = temp;
                ++count;
                nJLabel.setText("Fibonnacci of "+count+": ");
                nFibonacciJLabel.setText(String.valueOf(n2));
                
                
            }
        });
        eventThreadJPanel.add(nextNumberJButton);
        add(workedJPanel);
        add(eventThreadJPanel);
        setSize(275,200);
        setVisible(true);
        
        
    }
    public static void main(String[] args) {
        FibonacciNumbers application = new FibonacciNumbers();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
}
