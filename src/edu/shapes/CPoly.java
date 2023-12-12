package edu.shapes;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public abstract class CPoly extends CShape{
    protected int[] px;
    protected int[] py;
    protected int pointCount;

    public CPoly(int x0, int y0, Color fillColor, Color borderColor, int pointCount) {
        super(x0, y0, fillColor, borderColor);
        this.pointCount = pointCount;
        this.px = new int[pointCount];
        this.py = new int[pointCount];
    }

    protected abstract void updateCoordinates();

    @Override
    protected boolean isPointInside(int x, int y) {
        updateCoordinates();

        Polygon polygon = new Polygon(this.px,this.py,this.pointCount);
        return polygon.contains(x, y);
    }

    @Override
    protected void drawShape(Graphics2D graphics2D) {
        updateCoordinates();

        graphics2D.setColor(this.fillColor);
        graphics2D.fillPolygon(this.px,this.py,this.pointCount);
        graphics2D.setColor(this.borderColor);
        graphics2D.drawPolygon(this.px,this.py,this.pointCount);
    }

    @Override
    public void moveTo(int x, int y) {
        updateCoordinates();
        super.moveTo(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CPoly cPoly = (CPoly) o;
        return pointCount == cPoly.pointCount && Arrays.equals(px, cPoly.px) && Arrays.equals(py, cPoly.py);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), pointCount);
        result = 31 * result + Arrays.hashCode(px);
        result = 31 * result + Arrays.hashCode(py);
        return result;
    }
}
