import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class frame1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1 frame = new frame1();
					
					frame.setUndecorated(true);
					
//					 Image im = Toolkit.getDefaultToolkit().getImage("images/img1.jpg");
//					 frame.setIconImage(im);
//					 frame.setSize(100, 100);
					
					
					frame.setVisible(true);
					
//					Thread.sleep(3000);
					
//					frame.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frame1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 354);
		
//		contentPane = new JPanel();
//		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
		
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
		
//		panelBgImg.setBounds(0, 0, imh.getIconWidth(), imh.getIconHeight());
//		panel.setBounds(21, 11, 612, 294);
		
		panel.setBounds(0, 0, imh.getIconWidth(), imh.getIconHeight());
//		contentPane.add(panel);
		
		this.add(panel);
		
	}
	
//	@Override
//	public void paintComponents(Graphics g) 
//	{
//		System.out.println("Cleed.........");
//		Image img = new ImageIcon("images/img1.jpg").getImage();
//        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
////        setPreferredSize(size);
////        setMinimumSize(size);
////        setMaximumSize(size);
////        setSize(size);
////        setLayout(null);
//        g.drawImage(img, 0, 0, null);
//	}
}
