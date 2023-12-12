package edu.shapes;

import java.awt.*;
import java.util.Objects;

public abstract class CShape implements IShape {
    protected  int x0;
    protected int y0;
    protected int offsetX;
    protected int offsetY;
    protected Color fillColor;
    protected Color borderColor;

    public CShape(int x0, int y0, Color fillColor, Color borderColor) {
        this.x0 = x0;
        this.y0 = y0;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    protected abstract boolean isPointInside(int x,int y);
    protected abstract void drawShape(Graphics2D graphics2D);

    @Override
    public void moveBy(int dx, int dy) {
        this.x0 += dx-this.offsetX;
        this.y0 += dy-this.offsetY;
    }

    @Override
    public void moveTo(int x, int y) {
        this.x0 = x-this.offsetX;
        this.y0 = y-this.offsetY;
    }

    @Override
    public boolean select(int x, int y) {
        boolean isPointInside = isPointInside(x,y);

        if(isPointInside){
            this.offsetX = x-this.x0;
            this.offsetY = y-this.y0;
        }else {
            this.offsetX = 0;
            this.offsetY = 0;
        }

        return isPointInside;
    }

    @Override
    public void draw(Graphics graphics, boolean selected) {
        if(graphics instanceof Graphics2D graphics2D){
            graphics2D.setStroke(chooseBasicStroke(selected));
            drawShape(graphics2D);
        }
    }

    private BasicStroke chooseBasicStroke(boolean selected){
        if(selected) return new BasicStroke(5);
        return new BasicStroke(2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CShape cShape)) return false;

        if (x0 != cShape.x0) return false;
        if (y0 != cShape.y0) return false;
        if (offsetX != cShape.offsetX) return false;
        if (offsetY != cShape.offsetY) return false;
        if (!Objects.equals(fillColor, cShape.fillColor)) return false;
        return Objects.equals(borderColor, cShape.borderColor);
    }

    @Override
    public int hashCode() {
        int result = x0;
        result = 31 * result + y0;
        result = 31 * result + offsetX;
        result = 31 * result + offsetY;
        result = 31 * result + (fillColor != null ? fillColor.hashCode() : 0);
        result = 31 * result + (borderColor != null ? borderColor.hashCode() : 0);
        return result;
    }
}
