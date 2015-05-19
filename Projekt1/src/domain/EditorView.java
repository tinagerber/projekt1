package domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import domain.EditorHandler.State;


public class EditorView<V, E> extends JPanel {
	
	private EditorHandler<V,E> editorHandler;
	private ImageIcon edgeIcon;
	private ImageIcon dedgeIcon;
	JButton edgeButton;
	
	public EditorView(EditorHandler<V,E> handler) {
		
		this.editorHandler=handler;
		setLayout(new BorderLayout());
		constructPanelComponents();
	}
	
	/*
	 * Constructs a panel with buttons for selecting, deleting
	 * or creating vertices or edges
	 */
	private void constructPanelComponents() {
		
		ImageIcon selectIcon = new ImageIcon("Images/selection2.png");
		ImageIcon deleteIcon = new ImageIcon("Images/delete1.png");
		ImageIcon vertexIcon = new ImageIcon("Images/vertex.png");
		edgeIcon = new ImageIcon("Images/edge.png");
		dedgeIcon = new ImageIcon("Images/dedge.png");
		
		JButton selectionButton = new JButton(selectIcon);
		JButton deleteButton = new JButton(deleteIcon);
		JButton vertexButton = new JButton(vertexIcon);
		edgeButton = new JButton(edgeIcon);
		
		//Selects different vertices and edges individually
		selectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               editorHandler.setState(State.SELECT);
            }
         });
		
		//Deletes a selected vertex or edge
		deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               editorHandler.deleteDecorable();
               editorHandler.setGraphSaved(false);
            }
         });
		
		//Creates a new vertex
		vertexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               editorHandler.setState(State.VERTEX);
               editorHandler.setGraphSaved(false);
            }
         });
		
		//Creates a new edge
		edgeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               editorHandler.setState(State.EDGE);
               editorHandler.setGraphSaved(false);
            }
         });
		
		JPanel toolPanel = new JPanel(new FlowLayout());

		toolPanel.add(selectionButton);
		toolPanel.add(deleteButton);
		toolPanel.add(vertexButton);
		toolPanel.add(edgeButton);
		
		add(toolPanel, BorderLayout.NORTH);
	}
	
	/*
	 * Changes the edge button whether
	 * the graph is directed or not 
	 */
	public void changeDirectionEdgeButton(boolean directed) {
		if (directed) edgeButton.setIcon(dedgeIcon);
		else edgeButton.setIcon(edgeIcon);
	}
}
