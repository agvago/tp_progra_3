package app;
import java.util.Vector;

public class Knapsack {
	
	private int itemsQty = 0; 		//cantidad de items que se pueden agregar en la mochila
	private int capacity = 0;      	// capacidad de la mochila
	private int matrix[][];    //Matriz para almacenar los resultados que después vamos a elegir la mejor solucion
	private boolean itemMatrix[][];
	private Vector<Tuple<Integer,Integer>> sequence = null;
		
	public Knapsack(Vector<Tuple<Integer,Integer>> sequence, int capacity){
		
		//inicializacion
		this.sequence = sequence;
		this.itemsQty = this.sequence.size();
		this.capacity = capacity;
		this.matrix = new int[this.itemsQty+1][capacity+1];			 //Matriz para el cálculo por dinámica
		this.itemMatrix = new boolean[this.itemsQty+1][capacity+1]; //Matriz para marcar los itmes que puedo llevar (true=llevo / false=no llevo)
		
		for(int c=0;c<=capacity;c++){
			matrix[0][c] = 0;
		}
		
		for(int q=0;q<=sequence.size();q++){
			matrix[q][0] = 0;
		}
		
	}
		
	//creado por agustin bajo
	public int run()
	{		
		//Se llena la matriz por dinamica
		for(int q = 1; q <= this.itemsQty; q++){
			
			for(int c = 0; c <= this.capacity; c++){
				
				if((this.sequence.get(q-1).weight <= c) && 
						(this.sequence.get(q-1).gain+this.matrix[q-1][c-this.sequence.get(q-1).weight] > this.matrix[q-1][c])){
					
					this.matrix[q][c] = this.sequence.get(q-1).gain + this.matrix[q-1][c-this.sequence.get(q-1).weight];
					this.itemMatrix[q][c] = true;
					
				}
				else{
					
					this.matrix[q][c] = this.matrix[q-1][c];
					this.itemMatrix[q][c] = false;
					
				}
				
			}
				
		}
		
		return this.matrix[this.itemsQty][this.capacity];
	}
	
	public Vector<Integer> resultIndexes(){
		Vector<Integer> results = new Vector<Integer>();
		
		int c = this.capacity;
		
		for(int q = this.itemsQty; q >= 1; q--){
			if(this.itemMatrix[q][c] == true){
				results.add(q);
				c -= this.sequence.get(q-1).weight;
			}
		}
		
		return results;
	}
}



