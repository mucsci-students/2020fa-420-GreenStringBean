package view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import model.Relationship.relationshipType;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

public class RelationArrow extends JPanel {
    private Point from;
    private Point to;
    private relationshipType type;

    public RelationArrow(Point from, Point to, relationshipType type)
    {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        System.out.println("Got to paint in RelationArrow()");

        //Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(from.x, from.y, to.x, to.y);
    }

}
