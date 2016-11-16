package swing;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.Controller;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JPanel panel = new JPanel();
	private JPanelMain panelMain = new JPanelMain(this);
	private JPanelAddItem panelAddItem = new JPanelAddItem(this);
	private JPanelResult panelResult = new JPanelResult(this);
	
	public void showJPanelAddItem(){
		CardLayout c = (CardLayout)(panel.getLayout());
		c.show(panel, "panelAddItem");
	}
	
	public void showJPanelMain(){
		CardLayout c = (CardLayout)(panel.getLayout());
		c.show(panel, "panelMain");
	}
	
	public void showJPanelResult(){
		panelResult.loadResult();
		CardLayout c = (CardLayout)(panel.getLayout());
		c.show(panel, "panelResult");
	}
	
	public void actionCargarDemo(){
		resetAll();
		Controller.getInstance().loadDemo();
	}
	
	public void resetAll(){
		panelMain.reset();
		panelResult.reset();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 363, 544);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBounds(0, 0, 345, 475);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		panel.add(panelMain, "panelMain");
		panel.add(panelAddItem, "panelAddItem");
		panel.add(panelResult, "panelResult");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\uf0c9");
		mnNewMenu.setFont(Controller.getInstance().getFontAwesome());
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNew = new JMenuItem("Nuevo");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetAll();
			}
		});
		mntmNew.setFont(new Font("Arial", Font.PLAIN, 12));
		mnNewMenu.add(mntmNew);
		
		JMenuItem mntmCargarDemo = new JMenuItem("Cargar Demo");
		mntmCargarDemo.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmCargarDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionCargarDemo();
			}
		});
		mnNewMenu.add(mntmCargarDemo);
		
	}
}
