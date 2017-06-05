import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Quadro extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LinkedList<ArrayList<Point>> ps;
	int graphs;
	private JLabel scoreLabel;
	

	public Quadro() {
		ps = new LinkedList<ArrayList<Point>>();
		ps.add(new ArrayList<Point>());
		graphs = 0;
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(scoreLabel.getFont().deriveFont(28.0f));
		this.add(scoreLabel,BorderLayout.NORTH);
		this.setBorder(BorderFactory.createLineBorder(getForeground(), 2));
	}

	public void setScore(double score){
		DecimalFormat df = new DecimalFormat("#.00");
		scoreLabel.setText("Score: " + df.format(score));
		repaint();
	}
	
	public void addTruckImage(Truck truck) {
		for (int i = 0; i < Truck.nPontos; i++)
			ps.get(graphs).add(
					new Point(truck.getPoint(i).x, truck.getPoint(i).y));
		ps.add(new ArrayList<Point>());
		graphs++;
		this.repaint();
	}

	public void clear() {
		ps.clear();
		ps.add(new ArrayList<Point>());
		ps.add(new ArrayList<Point>());
		ps.add(new ArrayList<Point>());

		ps.get(0).add(new Point(this.getWidth()/2-20,this.getHeight()-20));
		ps.get(0).add(new Point(this.getWidth()/2-20,this.getHeight()));
		
		ps.get(1).add(new Point(this.getWidth()/2+20,this.getHeight()-20));
		ps.get(1).add(new Point(this.getWidth()/2+20,this.getHeight()));
		
		graphs = 2;
		this.repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Point p, p2;
		for (int j = 0; j < graphs + 1; j++)
			for (int i = 0; i < (ps.get(j).size() - 1); i++) {
				p = ps.get(j).get(i);
				p2 = ps.get(j).get(i + 1);
				g.drawLine(p.x, p.y, p2.x, p2.y);
			}

	}

}
