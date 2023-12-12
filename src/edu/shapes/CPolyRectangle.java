package edu.shapes;

import java.awt.*;

public class CPolyRectangle extends CPoly{
    protected int a;
    protected int b;

    public CPolyRectangle(int x0, int y0, Color fillColor, Color borderColor, int a, int b) {
        super(x0, y0, fillColor, borderColor, 4);
        this.a = a;
        this.b = b;
    }

    @Override
    protected void updateCoordinates() {
        this.px[0] = this.x0;
        this.py[0] = this.y0;

        this.px[1] = this.x0+this.a;
        this.py[1] = this.y0;

        this.px[2] = this.x0+this.a;
        this.py[2] = this.y0-this.b;

        this.px[3] = this.x0;
        this.py[3] = this.y0-this.b;
    }
}
