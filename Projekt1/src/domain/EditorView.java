package domain;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import domain.EditorHandler.State;




public class EditorView<V, E> extends JPanel {
	
	private EditorHandler<V,E> handler;
	public EditorView(EditorHandler<V,E> handler) {
		this.handler=handler;
		setLayout(new BorderLayout());
		constructPanelComponents();
	}

	private void constructPanelComponents() {
		
		JButton selectionButton = new JButton("Select");
		JButton deleteButton = new JButton("Delete");
		JButton vertexButton = new JButton("Vertex");
		JButton edgeButton = new JButton("Edge");
		JTextField attributText = new JTextField(15);
		JButton editAttributButton=new JButton("Edit");
		
		editAttributButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				handler.changeAttribut(attributText.getText());
			}
		});
		selectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               handler.setState(State.SELECT);
            }
         });
		
		deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               handler.deleteDecorable();
            }
         });
		
		vertexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               handler.setState(State.VERTEX);
            }
         });
		
		edgeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               handler.setState(State.EDGE);
            }
         });
		
		
		
		JPanel toolPanel = new JPanel(new FlowLayout());
		
		toolPanel.add(attributText);
		toolPanel.add(editAttributButton);
		toolPanel.add(selectionButton);
		toolPanel.add(deleteButton);
		toolPanel.add(vertexButton);
		toolPanel.add(edgeButton);
		
		
		add(toolPanel, BorderLayout.NORTH);
	}
}
