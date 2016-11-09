package swing;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import app.Controller;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

public class JPanelResult extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPuntaje;
	@SuppressWarnings("unused")
	private MainWindow parentWindow;
		
	
	public void reset(){
		Controller.getInstance().getResultTable().truncate();
	}
	
	public boolean calc(){
		
		if(!Controller.txtFieldIsInt(txtPuntaje, true)){
			txtPuntaje.setBorder(new MatteBorder(2, 2, 2, 2, Color.RED));
			return false;
		}
		txtPuntaje.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		
		return true;
	}
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ })
	public JPanelResult(MainWindow parentWindow) {
		this.parentWindow = parentWindow;
		
		setBackground(Color.WHITE);				
		setBounds(0, 0, 347, 475);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 60, 347, 350);
		add(scrollPane);
		
		txtPuntaje = new JTextField();
		txtPuntaje.setEditable(false);
		txtPuntaje.setBorder(new MatteBorder(2, 2, 1, 2, (Color) new Color(0, 0, 255)));
		txtPuntaje.setForeground(Color.GRAY);
		txtPuntaje.setFont(new Font("Arial", Font.PLAIN, 44));
		txtPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuntaje.setText("puntaje");
		txtPuntaje.setBounds(0, 0, 347, 60);
		add(txtPuntaje);
		txtPuntaje.setColumns(10);
		
		scrollPane.setViewportView(Controller.getInstance().getResultTable());
		
		JLabel labelBack = new JLabel("volver");
		labelBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentWindow.showJPanelMain();
			}
		});
		labelBack.setBorder(new MatteBorder(1, 2, 2, 2, (Color) new Color(0, 0, 255)));
		labelBack.setBackground(SystemColor.activeCaptionText);
		labelBack.setHorizontalAlignment(SwingConstants.CENTER);
		labelBack.setFont(new Font("Arial", Font.PLAIN, 24));
		labelBack.setBounds(0, 410, 347, 65);
		add(labelBack);
	}
}
