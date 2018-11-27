import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
/*
 * Created on Mar 24, 2008
 *
 */

/**
 * @author Anand
 */

public class JBackGroundImageDemo extends JFrame
{
    Container con = null;
    JPanel panelBgImg;
    
    public JBackGroundImageDemo()
    {
        setTitle("JBackGroundImageDemo");
        con = getContentPane();
        
        con.setLayout(null);
        ImageIcon imh = new ImageIcon("images/img1.jpg");
        setSize(imh.getIconWidth(), imh.getIconHeight());
        setUndecorated(true);
        panelBgImg = new JPanel()
        {
            public void paintComponent(Graphics g) 
            {
                Image img = new ImageIcon("images/img1.jpg").getImage();
                Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            } 
        };
        
        con.add(panelBgImg);
        panelBgImg.setBounds(0, 0, imh.getIconWidth(), imh.getIconHeight());
        
//        GridBagLayout layout = new GridBagLayout();
//        
//        JPanel panelContent = new JPanel(layout);
//        GridBagConstraints gc = new GridBagConstraints();
//
//        gc.insets = new Insets(3, 3, 3, 3);
//        gc.gridx = 1;
//        gc.gridy = 1;
        
//        JLabel label = new JLabel("UserName: ", JLabel.LEFT);                        
//        panelContent.add(label, gc);
//        
//        gc.gridx = 2;
//        gc.gridy = 1;
        
//        JTextField txtName = new JTextField(10);
//        panelContent.add(txtName, gc);
//        
//        gc.insets = new Insets(3, 3, 3, 3);
//        gc.gridx = 1;
//        gc.gridy = 2;
//        gc.gridwidth = 2;

//        JButton btn = new JButton("Login");
//        panelContent.add(btn, gc);
//        panelContent.setBackground(Color.GRAY);
//        panelContent.setBorder(new LineBorder(Color.WHITE));
//        
//        panelBgImg.add(panelContent);
//        
//        panelBgImg.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 200));
//        
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) 
    {
        new JBackGroundImageDemo().setVisible(true);
    }
}   
	