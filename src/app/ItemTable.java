package app;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;

public class ItemTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ItemTable() {
		super();
		this.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Descripción", "Puntaje"
			}
		));
		this.setShowVerticalLines(false);	
		this.setFont(new Font("Arial", Font.PLAIN, 14));
		this.setAutoCreateRowSorter(true);
	}
	
	public void addRow(String description, int points){
		DefaultTableModel model = (DefaultTableModel) this.getModel();
		model.addRow(new Object[]{description, points});
	};
	
	public void deleteSelectedRow(){
	   DefaultTableModel model = (DefaultTableModel) this.getModel();
	   int[] rows = this.getSelectedRows();
	   for(int i=0;i<rows.length;i++){
	     model.removeRow(rows[i]-i);
	   }
	}
	
	public Object getValueAt(int row, int col){
		DefaultTableModel dtm = (DefaultTableModel) this.getModel();
		return dtm.getValueAt(row,col);
	}
	
	public int getRowCount(){
		DefaultTableModel dtm = (DefaultTableModel) this.getModel();
		return dtm.getRowCount();
	}
	
	public Vector<Object> colToVector(int col){
		Vector<Object> column = new Vector<Object>();
		int nRow = getRowCount();
		
		for (int i = 0 ; i < nRow ; i++){
			column.addElement(getValueAt(i,col));
	    }
		
		return column;
	}
	
	@SuppressWarnings("unchecked")
	public void sort(){
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
        list.add( new RowSorter.SortKey(1, SortOrder.ASCENDING) );
	}
	
	public void truncate(){
		DefaultTableModel dm = (DefaultTableModel)this.getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged(); 
	}
	
	public boolean isEmtpy(){
		DefaultTableModel dtm = (DefaultTableModel) this.getModel();
	    int nRow = dtm.getRowCount();
		return nRow == 0;
	}
	
	

}
