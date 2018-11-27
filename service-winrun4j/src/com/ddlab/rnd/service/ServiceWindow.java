package com.ddlab.rnd.service;

import java.awt.EventQueue;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

public class ServiceWindow 
{
	private JFrame frame;
	private ImageIcon img = null;
	private TrayIcon trayIcon = null;
	private SystemTray tray = null;
	private PopupMenu popup = null;
	private MenuItem showItem = null;
	private MenuItem exitItem = null;
	private JLabel lblImage = null;

	private String configImgPath = "images/configuration-icon16.png";
	private String buttonStartImgPath = "images/Alarm-Tick-icon24.png";
	private String buttonStopImgPath = "images/Alarm-Error-icon24.png";
	private String startImg = "images/Check-icon48.png";
	private String stopImg = "images/Sign-Error-icon48.png";
	private JLabel statusLbl = null;
	private JButton btnStartService = null;
	private JButton btnStopService = null;
	
	protected static Logger logger = Logger.getLogger(ServiceWindow.class);

	public ServiceWindow() 
	{
		logger.debug("Window started ...");
		initializeUI();
		createTray();
		addListeners();
	}
	
	public void open()
	{
		frame.setVisible(true);
	}
	
	private void createTray()
	{
		try 
		{
			img = new ImageIcon(configImgPath);
			trayIcon = new TrayIcon(img.getImage());
			tray = SystemTray.getSystemTray();
			popup = new PopupMenu();
			showItem = new MenuItem("Show");
			exitItem = new MenuItem("Exit");
			popup.add(exitItem);
			popup.add(showItem);
			trayIcon.setPopupMenu(popup);
			tray = SystemTray.getSystemTray();
			
			tray.add(trayIcon);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	private void addListeners()
	{
		logger.debug("All listeners started ...");
		try 
		{
			frame.addWindowListener ( new WindowAdapter()  
			{  
				public void windowIconified(WindowEvent e)  
				{  
					System.out.println("Iconified ...");
					frame.setVisible(false);
				}  
				@Override
				public void windowClosing(WindowEvent e) 
				{
					tray.remove(trayIcon);
					super.windowClosing(e);
				}
			});  
			
			btnStartService.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e)
				{
					statusLbl.setText("Service started.");
					lblImage.setIcon(new ImageIcon(startImg));
					AppConstants.RUN_FLAG = true;
					Thread th = new Thread( new MyThread() );
					th.start();
				}
			});
			
			btnStopService.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					statusLbl.setText("Service stopped.");
					lblImage.setIcon(new ImageIcon(stopImg));
					AppConstants.RUN_FLAG = false;
				}
			});
			showItem.addActionListener( new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					frame.setVisible(true);
					frame.setExtendedState(JFrame.NORMAL);
				}
			});
			exitItem.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					tray.remove(trayIcon);
					System.exit(0);
				}
			});
			trayIcon.addMouseListener( new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) {
					if( e.getClickCount() == 2 )
					{
						frame.setVisible(true);
						frame.setExtendedState(JFrame.NORMAL);
					}
				} 
			});

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error(e);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeUI() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Service Run Dialog");
		btnStartService = new JButton("Start Service");
		btnStartService.setHorizontalAlignment(SwingConstants.LEFT);
		btnStartService.setIcon(new ImageIcon(buttonStartImgPath));
		btnStartService.setBounds(20, 188, 136, 37);
		frame.getContentPane().add(btnStartService);

		btnStopService = new JButton("Stop Service");
		btnStopService.setHorizontalAlignment(SwingConstants.LEFT);
		btnStopService.setIcon(new ImageIcon(buttonStopImgPath));
		btnStopService.setBounds(215, 188, 136, 37);
		frame.getContentPane().add(btnStopService);

		lblImage = new JLabel("");
		lblImage.setBounds(20, 11, 91, 48);
		frame.getContentPane().add(lblImage);

		statusLbl = new JLabel("");
		statusLbl.setHorizontalAlignment(SwingConstants.CENTER);
		statusLbl.setBounds(139, 17, 163, 37);
		frame.getContentPane().add(statusLbl);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		logger.debug("Started the application ...");
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ServiceWindow window = new ServiceWindow();
					window.frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
