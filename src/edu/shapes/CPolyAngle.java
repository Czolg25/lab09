package edu.shapes;

import java.awt.*;

public class CPolyAngle extends CPoly{
    protected int a;
    protected int b;
    protected int g;

    public CPolyAngle(int x0, int y0, Color fillColor, Color borderColor, int a, int b,int g) {
        super(x0, y0, fillColor, borderColor, 6);
        this.a = a;
        this.b = b;
        this.g = g;
    }

    @Override
    protected void updateCoordinates() {
        this.px[0] = this.x0;
        this.py[0] = this.y0;

        this.px[1] = this.x0+this.a;
        this.py[1] = this.y0;

        this.px[2] = this.x0+this.a;
        this.py[2] = this.y0-this.g;

        this.px[3] = this.x0+this.g;
        this.py[3] = this.y0-this.g;

        this.px[4] = this.x0+this.g;
        this.py[4] = this.y0-this.b;

        this.px[5] = this.x0;
        this.py[5] = this.y0-this.b;
    }
}
