package domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import examples.Decorable;
import examples.Graph;


public class GraphView<V,E> extends JPanel {

	private GraphComponent<V,E> comp;
	private Handler<V,E> handler;
	private JCheckBoxMenuItem name, string, weight;

	public GraphView(ActionListener renameListener){

		comp = new GraphComponent<V, E>(this, renameListener);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(comp);

		//Changes the background of main area
		scrollPane.getViewport().setBackground(Color.white);

		this.setLayout(new BorderLayout());
		this.constructComponents();
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void setHandler(Handler<V,E> handler) {
		this.handler = handler;
	}

	public void paintGraph(Graph<V,E> currentGraph) {

		comp.setGraph(currentGraph);
	}

	public void insertEdge(Point p1, Point p2) {

		comp.insertEdge(p1, p2);
	}

	public void mouseDown(Decorable d, Point p){
		handler.mouseDown(d, p);
	}
	public void mouseUp(Decorable d, Point p){
		handler.mouseUp(d, p);
	}
	public void mouseDrag(Decorable d, Point p){
		handler.mouseDrag(d, p);
	}

	public void deleteEdge() {

		comp.deleteEdge();
	}

	public void setFlag(Attribut attr, boolean selected) {
		comp.setFlag(attr, selected);

	}

	/*
	 * Constructs the menu for handling the attributes of vertices and edges
	 */
	private void constructComponents() {

		JSlider slider = new JSlider(1,20);
		slider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				comp.setZoomSize(slider.getValue());
			}
		});
		
		FlowLayout fl = new FlowLayout();
		JPanel p = new JPanel(fl);
		
		ImageIcon pzoomIcon = new ImageIcon("Images/pzoom.png");
		ImageIcon mzoomIcon = new ImageIcon("Images/mzoom.png");
		
		p.add(new JLabel(mzoomIcon));
		p.add(slider);
		p.add(new JLabel(pzoomIcon));
		
		add(p, BorderLayout.SOUTH);
	}

	public JCheckBoxMenuItem getNameItem(){
		name = new JCheckBoxMenuItem("name");
		name.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				comp.setFlag(Attribut.name, name.isSelected());
				comp.setPopupCheckBox(Attribut.name, name.isSelected());
			}
		});
		return name;
	}
	
	public JCheckBoxMenuItem getWeightItem(){
		weight = new JCheckBoxMenuItem("weight");
		weight.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				comp.setFlag(Attribut.weight, weight.isSelected());
				comp.setPopupCheckBox(Attribut.weight, weight.isSelected());
			}
		});
		return weight;
	}
	
	public JCheckBoxMenuItem getStringItem(){
		string = new JCheckBoxMenuItem("string");
		string.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				comp.setFlag(Attribut.string, string.isSelected());
				comp.setPopupCheckBox(Attribut.string, string.isSelected());
			}
		});
		return string;
	}

	public void setMenuCheckBox(Attribut attr, boolean selected) {
		switch (attr){
		case name:
			name.setState(selected);
			break;
		case weight:
			weight.setState(selected);
			break;
		case string:
			string.setState(selected);
			break;
		}
		
	}


}
