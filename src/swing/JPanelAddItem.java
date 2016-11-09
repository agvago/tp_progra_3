package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import app.Controller;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPanelAddItem extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private MainWindow parentWindow;
	private JTextField txtItem;
	private JTextField txtPuntaje;
	private JLabel lblBack;
	private JLabel lblAdd;
	
	public boolean addItem(){
		
		if(!Controller.txtFieldIsRequired(txtItem)){
			txtItem.setBorder(new MatteBorder(2, 2, 2, 2, Color.RED));
			return false;
		}
		txtItem.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));

		if(!Controller.txtFieldIsInt(txtPuntaje, true)){
			txtPuntaje.setBorder(new MatteBorder(2, 2, 2, 2, Color.RED));
			return false;
		}
		txtPuntaje.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		
		Controller.getInstance().getItemsTable().addRow(txtItem.getText(), Integer.parseInt(txtPuntaje.getText()));	
		Controller.getInstance().getItemsTable().sort();
		
		return true;
	}
	
	public void reset(){
		txtItem.setText("item");
		txtItem.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		txtPuntaje.setText("puntaje");
		txtPuntaje.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
	}
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ })
	public JPanelAddItem(MainWindow parentWindow) {
		this.parentWindow = parentWindow;
		
		setBackground(Color.WHITE);					
		setBounds(0, 0, 347, 475);
		setLayout(null);
		
		txtItem = new JTextField();
		txtItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtItem.selectAll();
			}
		});
		txtItem.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		txtItem.setForeground(Color.GRAY);
		txtItem.setFont(new Font("Arial", Font.PLAIN, 44));
		txtItem.setHorizontalAlignment(SwingConstants.CENTER);
		txtItem.setText("item");
		txtItem.setBounds(0, 39, 347, 79);
		add(txtItem);
		txtItem.setColumns(10);
		
		txtPuntaje = new JTextField();
		txtPuntaje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPuntaje.selectAll();
			}
		});
		txtPuntaje.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		txtPuntaje.setText("puntaje");
		txtPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuntaje.setForeground(Color.GRAY);
		txtPuntaje.setFont(new Font("Arial", Font.PLAIN, 44));
		txtPuntaje.setColumns(10);
		txtPuntaje.setBounds(0, 155, 347, 79);
		add(txtPuntaje);
		
		lblBack = new JLabel("volver");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				parentWindow.showJPanelMain();
				reset();
			}
		});
		lblBack.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBack.setBounds(0, 410, 174, 65);
		add(lblBack);
		
		lblAdd = new JLabel("agregar");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(addItem()){
					parentWindow.showJPanelMain();
					reset();
				}
			}
		});
		lblAdd.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial", Font.PLAIN, 20));
		lblAdd.setBounds(173, 410, 174, 65);
		add(lblAdd);
	}
}
