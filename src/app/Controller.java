package app;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//esta clase me permite guardar todas las variables del la aplicacion
public class Controller {

	private static Controller instance = null;
	private Font fontAwesome = null;
	private ItemTable itemsTable = null;
	private ItemTable resultsTable = null;

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
	
	
		
    public int calc(int capacity){
    	
    	DefaultTableModel dtm = (DefaultTableModel) itemsTable.getModel();
	    int nRow = dtm.getRowCount();
	    Vector<Tuple<Integer,Integer>> sequence = new Vector<Tuple<Integer,Integer>>();
	    
	    sequence.add(new Tuple<Integer, Integer>(0, 0));
	    for (int i = 0 ; i < nRow ; i++){
	    	sequence.add(new Tuple<Integer, Integer>((Integer)dtm.getValueAt(i,1), (Integer)dtm.getValueAt(i,1)));
	    } 	
    	
    	return mochila(sequence, capacity);
    }
        
	private int mochila(Vector<Tuple<Integer,Integer>> sequence, int capacity){
		int matrix[][] = new int[sequence.size()+1][capacity+1];
		
		for(int i=1;i<=capacity;i++){
			matrix[0][i] = 0;
		}
		
		for(int j=0;j<=sequence.size();j++){
			matrix[j][0] = 0;
		}
		
		for(int j=1;j<sequence.size();j++){
			for(int i=1;i<=capacity;i++){
				if(i-sequence.get(j).weight <0){
					matrix[j][i] = matrix[j-1][i];
				}else{					
					matrix[j][i] = Math.max(sequence.get(j).gain + matrix[j-1][i-sequence.get(j).weight], matrix[j-1][i]);
				}
			}
		}
		
		return matrix[sequence.size()-1][capacity];
	}
	
}
