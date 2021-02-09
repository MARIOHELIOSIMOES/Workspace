
package jfreechart;

import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.layout.CenterLayout;


public class JfreeChart extends JFrame{
    JPanel panel;
    public JfreeChart(){
        //setContentPane(new Dado());
        panel = new JPanel();
        JButton button = new JButton("Ação");
        /*button.addActionListener(this);
        ScrollPane scroll = new ScrollPane();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.add(button);
        panel.add(button);
        
        scroll.add(panel);
        //scroll.add(new jpTeste());
        */
        //panel.add(new JFGraficoPizza().getContentPane());
        //setContentPane(new JFGraficoPizza().getContentPane());
        setContentPane(new JFGraficoBarra().getContentPane());
        //setContentPane(scroll);
        //setContentPane(new jpGridBagLayout());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(800, 500);
        this.pack();
       
    }
    
    public static void main(String[] args) {
        new JfreeChart().setVisible(true);
        
        
    }

}
