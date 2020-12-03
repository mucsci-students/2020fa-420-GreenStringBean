package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.Polygon;

import javax.swing.JPanel;

import model.Relationship.relationshipType;

public class RelationArrow extends JPanel 
{
    private static final long serialVersionUID = 5153326128524986518L;
    final static float THICKNESS = 2;
    final static float DASH_ARRAY[] = {10};
    final static BasicStroke SOLID_STROKE = new BasicStroke(THICKNESS);
    final static BasicStroke DASHED_STROKE = new BasicStroke(THICKNESS, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, DASH_ARRAY, 0);

    final static int CAP_MAJOR_LENGTH = 16;
    final static int CAP_MINOR_LENGTH = 8;

    private JPanel panelFrom;
    private JPanel panelTo;
    private Point from;
    private Point to;
    private relationshipType type;
    private boolean isHorizontal;
    private boolean isReverse;
    private boolean isLoop;
    private boolean isDark;

    /**
     * Constructor for creating a relationship arrow
     * @param panelFrom panel from
     * @param panelTo   panel to
     * @param type      relationship type
     */
    public RelationArrow(JPanel panelFrom, JPanel panelTo, relationshipType type, boolean isDark)
    {
        this.panelFrom = panelFrom;
        this.panelTo = panelTo;
        this.type = type;
        this.isDark = isDark;

        isLoop = (panelFrom == panelTo);

        from = new Point();
        to = new Point();

        calculateEndpoints();
    }

    public void setDark(boolean isDark)
    {
        this.isDark = isDark;
    }

    /**
     * Get the endpoints from both class panels 
     */
    private void calculateEndpoints()
    {        
        if (isLoop)
        {
            from.x = panelFrom.getX() + panelFrom.getWidth();
            from.y = panelFrom.getY() + (panelFrom.getHeight() / 2);
            to.x = panelFrom.getX() + (panelFrom.getWidth() / 2);
            to.y = panelFrom.getY();
            return;
        }

        int xDist = panelTo.getX() - panelFrom.getX();
        int yDist = panelTo.getY() - panelFrom.getY();

        if(Math.abs(xDist) > Math.abs(yDist))
        {
            //Arrow mostly horizontal
            isHorizontal = true;
            if(xDist > 0)
            {
                //Left to right
                isReverse = false;
                from.x = panelFrom.getX() + panelFrom.getWidth();
                to.x = panelTo.getX();
            }
            else
            {
                //Right to left
                isReverse = true;
                from.x = panelFrom.getX();
                to.x = panelTo.getX() + panelTo.getWidth();

            }
            from.y = panelFrom.getY() + (panelFrom.getHeight() / 2);
            to.y = panelTo.getY() + (panelTo.getHeight() / 2);
        }
        else
        {
            //Arrow mostly vertical
            isHorizontal = false;
            if(yDist > 0)
            {
                //Top to bottom 
                isReverse = false;
                from.y = panelFrom.getY() + panelFrom.getHeight();
                to.y = panelTo.getY();
            }
            else
            {
                //Bottom to top
                isReverse = true;
                from.y = panelFrom.getY();
                to.y = panelTo.getY() + panelTo.getHeight();
            }
            from.x = panelFrom.getX() + (panelFrom.getWidth() / 2);
            to.x = panelTo.getX() + (panelTo.getWidth() / 2);
        }
    }

    /**
     * Makes the relationship arrow 
     * @param g the graphics to draw the arrow
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        calculateEndpoints();

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(isDark ? Color.WHITE : Color.BLACK);
        if (type == relationshipType.REALIZATION)
        {
            g2d.setStroke(DASHED_STROKE);
        }
        else
        {
            g2d.setStroke(SOLID_STROKE);
        }

        boolean isDiamond = (type == relationshipType.AGGREGATION || type == relationshipType.COMPOSITION);
        int capSize = isDiamond ? CAP_MAJOR_LENGTH * 2 : CAP_MAJOR_LENGTH;
        boolean shouldDrawCap;

        if (isLoop)
        {
            int offsetX = from.x + panelFrom.getWidth() / 2;
            int offsetY = to.y - panelFrom.getHeight() / 2;
            g2d.draw(new Line2D.Float(from.x, from.y, offsetX, from.y));
            g2d.draw(new Line2D.Float(offsetX, from.y, offsetX, offsetY));
            g2d.draw(new Line2D.Float(offsetX, offsetY, to.x, offsetY));
            g2d.draw(new Line2D.Float(to.x, offsetY, to.x, to.y));
            shouldDrawCap = (Math.abs(to.y - offsetY) > capSize);
        }
        else
        {
            if (isHorizontal)
            {
                int midwayX = (from.x + to.x) / 2;
                g2d.draw(new Line2D.Float(from.x, from.y, midwayX, from.y));
                g2d.draw(new Line2D.Float(midwayX, from.y, midwayX, to.y));
                g2d.draw(new Line2D.Float(midwayX, to.y, to.x, to.y));
                shouldDrawCap = (Math.abs(to.x - midwayX) > capSize);
            }
            else
            {
                int midwayY = (from.y + to.y) / 2;
                g2d.draw(new Line2D.Float(from.x, from.y, from.x, midwayY));
                g2d.draw(new Line2D.Float(from.x, midwayY, to.x, midwayY));
                g2d.draw(new Line2D.Float(to.x, midwayY, to.x, to.y));
                shouldDrawCap = (Math.abs(to.y - midwayY) > capSize);
            }
        }

        if (shouldDrawCap && !isInverse())
        {
            g2d.setStroke(SOLID_STROKE);
            drawCap(g2d, isDiamond);
        }
    }

    /**
     * Check if the endpoints are in the opposite of the expected positions
     * (If they are, the cap will not be drawn)
     * @return true if the arrow is inverse, false otherwise
     */
    private boolean isInverse()
    {
        if (isLoop)
        {
            return false;
        }
        if (isHorizontal)
        {
            if (isReverse)
            {
                return from.x < to.x;
            }
            else
            {
                return from.x > to.x;
            }
        }
        else
        {
            if (isReverse)
            {
                return from.y < to.y;
            }
            else
            {
                return from.y > to.y;
            }
        }
    }

    /**
     * Makes the end shape of a relationship arrow
     * @param g2d       the graphics to draw the cap     
     * @param isDiamond whether the cap is a diamond or triangle
     */
    private void drawCap(Graphics2D g2d, boolean isDiamond)
    {
        Polygon cap = new Polygon();
        cap.addPoint(to.x, to.y);
        if (isHorizontal)
        {
            if (isReverse)
            {
                cap.addPoint(to.x + CAP_MAJOR_LENGTH, to.y - CAP_MINOR_LENGTH);
                if (isDiamond)
                {
                    cap.addPoint(to.x + (CAP_MAJOR_LENGTH * 2), to.y);
                }
                cap.addPoint(to.x + CAP_MAJOR_LENGTH, to.y + CAP_MINOR_LENGTH);
            }
            else
            {
                cap.addPoint(to.x - CAP_MAJOR_LENGTH, to.y - CAP_MINOR_LENGTH);
                if (isDiamond)
                {
                    cap.addPoint(to.x - (CAP_MAJOR_LENGTH * 2), to.y);
                }
                cap.addPoint(to.x - CAP_MAJOR_LENGTH, to.y + CAP_MINOR_LENGTH);
            }
        }
        else
        {
            if (isReverse)
            {
                cap.addPoint(to.x + CAP_MINOR_LENGTH, to.y + CAP_MAJOR_LENGTH);
                if (isDiamond)
                {
                    cap.addPoint(to.x, to.y + (CAP_MAJOR_LENGTH * 2));
                }
                cap.addPoint(to.x - CAP_MINOR_LENGTH, to.y + CAP_MAJOR_LENGTH);
            }
            else
            {
                cap.addPoint(to.x + CAP_MINOR_LENGTH, to.y - CAP_MAJOR_LENGTH);
                if (isDiamond)
                {
                    cap.addPoint(to.x, to.y - (CAP_MAJOR_LENGTH * 2));
                }
                cap.addPoint(to.x - CAP_MINOR_LENGTH, to.y - CAP_MAJOR_LENGTH);
            }
        }
        g2d.draw(cap);
        if (type != relationshipType.COMPOSITION)
        {
            g2d.setColor(isDark ? Color.DARK_GRAY : Color.WHITE);
        }
        g2d.fill(cap);
    }

    /**
     * Repaints the graphic 
     * @param g is the graphic being used
     */
    protected void repaint(Graphics g)
    {
        paintComponent(g);
    }
}
