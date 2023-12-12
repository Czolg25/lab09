package edu.shapes;

import java.awt.*;

public class CShapeCircle extends CShape{
    protected int r;

    public CShapeCircle(int x0, int y0, Color fillColor, Color borderColor, int r) {
        super(x0, y0, fillColor, borderColor);
        this.r = r;
    }

    @Override
    protected boolean isPointInside(int x, int y) {
        return Math.sqrt( Math.pow(x - this.x0,2) + Math.pow(y-this.y0,2)) <= this.r;
    }

    @Override
    protected void drawShape(Graphics2D graphics2D) {
        graphics2D.setColor(this.fillColor);
        graphics2D.fillOval(this.x0-this.r,this.y0-this.r,this.r*2,this.r*2);
        graphics2D.setColor(this.borderColor);
        graphics2D.drawOval(this.x0-this.r,this.y0-this.r,this.r*2,this.r*2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CShapeCircle that)) return false;
        if (!super.equals(o)) return false;

        return r == that.r;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + r;
        return result;
    }
}
