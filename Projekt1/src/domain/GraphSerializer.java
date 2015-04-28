package domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import examples.Graph;

/*
 * Serializes and deserializes graphs permanently and temporally
 * for saving graphs and animating algorithms
 */
public class GraphSerializer<V,E> {

	private ArrayList<byte[]> editorGraph;
	private ArrayList<byte[]> algoGraph;
	private int editorIndex, algoIndex;
	
	public GraphSerializer(){
		editorGraph=new ArrayList<byte[]>();
		algoGraph= new ArrayList<byte[]>();
	}
	
	//------------------------------------------------------------------------------------//
	// Methods for storing graphs permanently
	//------------------------------------------------------------------------------------//
	
	/*
	 * Saves a graph permanently as a file by serializing
	 */
	public void saveGraph(String name, Graph graph) throws IOException {
		String filename = "GraphFiles/" + name + ".ser";
		ObjectOutputStream oos = null;
		try {			
			oos = new ObjectOutputStream(new FileOutputStream(new File(filename)));	
		    oos.writeObject(graph); 
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			oos.close();
		}
	}
	
	/*
	 * Opens a saved graph from a file by deserializing
	 */
	public Graph openGraph(String name) throws IOException {
		String filename = "GraphFiles/" + name + ".ser";
		Graph graph = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filename));
			 graph = (Graph<V,E>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ois.close();
		}
		return graph;
	}

	//------------------------------------------------------------------------------------//
	// Methods for coping graph temporarily
	//------------------------------------------------------------------------------------//
	
	/*
	 * Makes a copy of a graph while drawing a graph
	 * respectively in editor mode
	 */
	public void saveEditorGraph(Graph<V, E> g) throws IOException{
		this.serializeGraph(g, editorGraph);
	}
	
	/*
	 * Makes a copy of a graph while animating an algorithm
	 * respectively in algorithm mode
	 */
	public void saveAlgoGraph(Graph<V, E> g) throws IOException{
		this.serializeGraph(g, algoGraph);
	}
	
	/*
	 * Makes a temporary copy of a graph by serializing
	 */
	private void serializeGraph(Graph<V, E> g, ArrayList<byte[]> list) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {			
			oos = new ObjectOutputStream(bos);
			oos.writeObject(g);
			list.add( bos.toByteArray());
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			oos.close();
		}
	}
	
	/*
	 * Returns an Arraylist of Graphs deserialized from serialized list of graphs
	 */
	// Has to be tested if it works!!!!
	public ArrayList<Graph> getAlgoGraphs() throws IOException, ClassNotFoundException {
		
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(algoGraph.get(0)));
		ArrayList<Graph> algoGraphs = new ArrayList<>();
		
		
		for (int i = 0; i < algoGraph.size(); i++) {
		
			ois.read(algoGraph.get(i));
			
			try {			
				
				algoGraphs.add((Graph) ois.readObject());
				System.out.println("Graph " + i + " name " + algoGraphs.get(i));
			  	
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				ois.close();
			}
		}
		
		return algoGraphs;
	}

	//------------------------------------------------------------------------------------//
	// Methods for undoing and redoing actions and animating algorithms
	//------------------------------------------------------------------------------------//
	
	public void undoEditor(){

	}

	public void redoEditor(){

	}

	public void stepBefore(){

	}

	public void nextStep(){

	}

}
