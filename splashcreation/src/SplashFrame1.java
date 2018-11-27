import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SplashFrame1 extends JFrame 
{
	public SplashFrame1()
	{
		setBounds(100, 100, 671, 354);
		ImageIcon imh = new ImageIcon("images/img1.jpg");
        setSize(imh.getIconWidth(), imh.getIconHeight());
        
        JPanel panel = new JPanel()
		{
			public void paintComponent(Graphics g) 
            {
                Image img = new ImageIcon("images/img1.jpg").getImage();
                Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
                setPreferredSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            } 
		};
		panel.setBounds(0, 0, imh.getIconWidth(), imh.getIconHeight());
		add(panel);
		setUndecorated(true);
	}
	
	public static void main(String[] args) throws Exception
	{
		SplashFrame1 frame = new SplashFrame1();
		frame.setVisible(true);
		
		Thread.sleep(5000);
		
		frame.setVisible(false);
		
		frame.dispose();
	}
}
