package swing;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BorderFactory;
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

public class JPanelMain extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPuntaje;
	@SuppressWarnings("unused")
	private MainWindow parentWindow;
		
	
	public void reset(){
		txtPuntaje.setText("puntaje");
		txtPuntaje.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		Controller.getInstance().getItemsTable().truncate();
	}
	
	public boolean calc(){
		
		if(!Controller.txtFieldIsInt(txtPuntaje, true)){
			txtPuntaje.setBorder(new MatteBorder(2, 2, 2, 2, Color.RED));
			return false;
		}
		txtPuntaje.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		
		if(Controller.getInstance().getItemsTable().isEmtpy())
			return false;
		
		Controller.getInstance().calc(Integer.parseInt(txtPuntaje.getText()));
		
		return true;
	}
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ })
	public JPanelMain(MainWindow parentWindow) {
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
		txtPuntaje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPuntaje.selectAll();
			}
		});
		txtPuntaje.setBorder(new MatteBorder(2, 2, 1, 2, (Color) new Color(0, 0, 255)));
		txtPuntaje.setForeground(Color.GRAY);
		txtPuntaje.setFont(new Font("Arial", Font.PLAIN, 44));
		txtPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuntaje.setText("puntaje");
		txtPuntaje.setBounds(0, 0, 347, 60);
		add(txtPuntaje);
		txtPuntaje.setColumns(10);
		
		scrollPane.setViewportView(Controller.getInstance().getItemsTable());
		
		JLabel lblRemove = new JLabel("\uf014");
		lblRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().getItemsTable().deleteSelectedRow();
			}
		});
		lblRemove.setBorder(new MatteBorder(1, 1, 2, 2, (Color) new Color(0, 0, 255)));
		lblRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemove.setFont(Controller.getInstance().getFontAwesome());
		lblRemove.setBounds(285, 410, 62, 65);
		add(lblRemove);
		
		JLabel labelAdd = new JLabel("+");
		labelAdd.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 255)));
		labelAdd.setHorizontalAlignment(SwingConstants.CENTER);
		labelAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				parentWindow.showJPanelAddItem();
			}
		});
		labelAdd.setFont(new Font("FontAwesome", Font.PLAIN, 24));
		labelAdd.setBounds(223, 410, 62, 65);
		add(labelAdd);
		
		JLabel labelCalc = new JLabel("calcular");
		labelCalc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(calc()){
					parentWindow.showJPanelResult();
				}
			}
		});
		labelCalc.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.BLUE));
		labelCalc.setBackground(SystemColor.activeCaptionText);
		labelCalc.setHorizontalAlignment(SwingConstants.CENTER);
		labelCalc.setFont(new Font("Arial", Font.PLAIN, 24));
		labelCalc.setBounds(0, 410, 223, 65);
		add(labelCalc);
	}
}
