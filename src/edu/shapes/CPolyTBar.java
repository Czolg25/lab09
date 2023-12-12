package edu.shapes;

import java.awt.*;

public class CPolyTBar extends CPoly{
    protected int a;
    protected int h;
    protected int g;

    public CPolyTBar(int x0, int y0, Color fillColor, Color borderColor, int a, int h, int g) {
        super(x0, y0, fillColor, borderColor, 8);
        this.a = a;
        this.g = g;
        this.h = h;
    }

    @Override
    protected void updateCoordinates() {
        this.px[0] = this.x0;
        this.py[0] = this.y0;

        this.px[1] = this.x0+this.g;
        this.py[1] = this.y0;

        this.px[2] = this.x0+this.g;
        this.py[2] = this.y0-this.h+this.g;

        this.px[3] = this.x0+(this.a/2);
        this.py[3] = this.y0-this.h+this.g;

        this.px[4] = this.x0+(this.a/2);
        this.py[4] = this.y0-this.h;

        this.px[5] = this.x0-(this.a/2);
        this.py[5] = this.y0-this.h;

        this.px[6] = this.x0-(this.a/2);
        this.py[6] = this.y0-this.h+this.g;

        this.px[7] = this.x0;
        this.py[7] = this.y0-this.h+this.g;
    }
}
