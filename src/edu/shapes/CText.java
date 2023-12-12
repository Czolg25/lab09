package edu.shapes;

import java.awt.*;
import java.util.Objects;

public class CText implements IShape{
    protected  int x0;
    protected int y0;
    protected int offsetX;
    protected int offsetY;
    protected Color fontColor;
    protected String string;
    protected int fontSize;

    public CText(int x0,int y0,Color fontColor, String string, int fontSize) {
        this.x0 = x0;
        this.y0 = y0;
        this.fontColor = fontColor;
        this.string = string;
        this.fontSize = fontSize;
    }

    protected boolean isPointInside(int x, int y){

        return false;
    }
    protected void drawShape(Graphics2D graphics2D){
        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, this.fontSize));
        graphics2D.setColor(this.fontColor);
        graphics2D.drawString(this.string,this.x0,this.y0);
    };

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
        if (o == null || getClass() != o.getClass()) return false;
        CText cText = (CText) o;
        return x0 == cText.x0 && y0 == cText.y0 && offsetX == cText.offsetX && offsetY == cText.offsetY && fontSize == cText.fontSize && Objects.equals(fontColor, cText.fontColor) && Objects.equals(string, cText.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x0, y0, offsetX, offsetY, fontColor, string, fontSize);
    }
}
