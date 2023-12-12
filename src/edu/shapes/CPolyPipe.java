package edu.shapes;

import edu.shapes.objects.PipePoints;

import java.awt.*;

public class CPolyPipe extends CPoly{
    protected int a;
    protected int b;
    protected int g;

    public CPolyPipe(int x0, int y0, Color fillColor, Color borderColor, int a, int b, int g) {
        super(x0, y0, fillColor, borderColor, 8);
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
        this.py[2] = this.y0-this.b;

        this.px[3] = this.x0;
        this.py[3] = this.y0-this.b;

        //mid

        this.px[4] = this.x0+(this.a-this.g)/2;
        this.py[4] = this.y0-(this.b-this.g)/2;

        this.px[5] = this.x0+(this.a-this.g)/2;
        this.py[5] = this.y0-(this.b-this.g)/2-this.g;

        this.px[6] = this.x0+(this.a-this.g)/2+this.g;
        this.py[6] = this.y0-(this.b-this.g)/2-this.g;

        this.px[7] = this.x0+(this.a-this.g)/2+this.g;
        this.py[7] = this.y0-(this.b-this.g)/2;
    }

    @Override
    protected boolean isPointInside(int x, int y) {
        updateCoordinates();

        final PipePoints pipePoints = getPipePoints();
        final int size = pipePoints.pointsX1().length;

        Polygon bigPolygon = new Polygon(pipePoints.pointsX1(),pipePoints.pointsY1(),size);
        Polygon smallPolygon = new Polygon(pipePoints.pointsX2(),pipePoints.pointsY2(),size);
        return bigPolygon.contains(x, y) && !smallPolygon.contains(x, y);
    }

    @Override
    protected void drawShape(Graphics2D graphics2D) {
        updateCoordinates();

        final PipePoints pipePoints = getPipePoints();
        final int size = pipePoints.pointsX1().length;


        graphics2D.setColor(this.fillColor);
        graphics2D.fillPolygon(pipePoints.pointsX1(),pipePoints.pointsY1(),size);
        graphics2D.setColor(this.borderColor);
        graphics2D.drawPolygon(pipePoints.pointsX1(),pipePoints.pointsY1(),size);
        graphics2D.setColor(this.borderColor);
        graphics2D.drawPolygon(pipePoints.pointsX2(),pipePoints.pointsY2(),size);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillPolygon(pipePoints.pointsX2(),pipePoints.pointsY2(),size);
    }

    private PipePoints getPipePoints(){
        final int size = this.pointCount/2;
        final int[] pointsX1 = new int[size];
        final int[] pointsY1 = new int[size];
        final int[] pointsX2 = new int[size];
        final int[] pointsY2 = new int[size];

        for (int i = 0; i < this.px.length; i++) {
            if(i<size){
                pointsX1[i] = this.px[i];
                pointsY1[i] = this.py[i];
            }else {
                pointsX2[i-size] = this.px[i];
                pointsY2[i-size] = this.py[i];
            }
        }

        return new PipePoints(pointsX1, pointsY1, pointsX2, pointsY2);
    }
}
