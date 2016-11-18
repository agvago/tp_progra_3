package app;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.swing.JTextField;

//esta clase me permite guardar todas las variables del la aplicacion
public class Controller {

	private static Controller instance = null;
	private Font fontAwesome = null;
	private ItemTable itemsTable = null;
	private ItemTable resultsTable = null;
	private int knapsackMaxGain = 0;

	private Controller() {
		super();
	}
	
	public static Controller getInstance() {
		if (instance == null)
			instance = new Controller();
		
		return instance;
	}
	
	public Font getFontAwesome(){
		if (fontAwesome == null){
			InputStream is = this.getClass().getResourceAsStream("/fontawesome-webfont.ttf");
			try {
				Font font = Font.createFont(Font.TRUETYPE_FONT, is);
				font = font.deriveFont(Font.PLAIN, 24f);
				fontAwesome = font;
			} catch (FontFormatException e) {
				fontAwesome = null;
			} catch (IOException e) {
				fontAwesome = null;
			}
		}
		return fontAwesome;
	}
	
	public ItemTable getItemsTable(){
		if(itemsTable==null){
			itemsTable = new ItemTable();			
		}
		return itemsTable;
	}	
	
	public ItemTable getResultTable(){
		if(resultsTable==null){
			resultsTable = new ItemTable();
		}
		return resultsTable;
	}	
	
	public void loadDemo(int demoIndex){
		
		switch (demoIndex) {
		case 2:
			getItemsTable().addRow("1", 100);
			getItemsTable().addRow("2", 200);
			getItemsTable().addRow("3", 50);
			getItemsTable().addRow("4", 25);
			getItemsTable().addRow("5", 10);
			getItemsTable().addRow("6", 5);
			getItemsTable().addRow("7", 1000);
			getItemsTable().addRow("8", 650);
			getItemsTable().addRow("9", 450);
			getItemsTable().addRow("10", 550);
			getItemsTable().addRow("11", 36);
			getItemsTable().addRow("12", 28);
			break;
		default:
			getItemsTable().addRow("Reloj", 50);
			getItemsTable().addRow("Mouse", 38);
			getItemsTable().addRow("Vasos", 65);
			getItemsTable().addRow("Secador de Pelo", 85);
			break;
		}		
	}
	
	public static Boolean txtFieldIsInt(JTextField field, boolean required){
		 try{
			if(required && field.getText().isEmpty()){
				return false;
			}else if(field.getText().isEmpty()){
				return true;
			}
		    Integer.parseInt(field.getText());
		    return true;
		}catch (NumberFormatException ex) {
			return false;
		}
	}
	
	public static Boolean txtFieldIsRequired(JTextField field){
		if(field.getText().isEmpty())
			return false;
		
		return true;
	}
		
    public void calc(int capacity){
    	
    	//obtengo los elementos de la tabla de items
    	Vector<Object> points = this.itemsTable.colToVector(1);
    	Vector<Object> items = this.itemsTable.colToVector(0);
	    
	    Vector<Tuple<Integer,Integer>> sequence = new Vector<Tuple<Integer,Integer>>();
	    for (int i = 0 ; i < points.size() ; i++){
	    	sequence.add(new Tuple<Integer, Integer>((Integer)points.elementAt(i),(Integer)points.elementAt(i)));
	    }   	
    	
    	Knapsack k = new Knapsack(sequence, capacity);
    	this.knapsackMaxGain = k.run();
    	
 
    	Vector<Integer> indexes = k.resultIndexes();
    	
    	if(!this.resultsTable.isEmtpy())
    		this.resultsTable.truncate();
    
    	for (Integer i : indexes) {
			this.resultsTable.addRow((String)items.elementAt(i-1), (Integer)points.elementAt(i-1));
		}

    }
    
    public int getMaxGain(){
    	return this.knapsackMaxGain;
    }
}
