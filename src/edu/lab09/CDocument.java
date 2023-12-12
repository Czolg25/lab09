package edu.lab09;

import edu.shapes.IShape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CDocument {
    private List<IShape> shapes;
    private BufferedImage image;
    private CGraphicArea graphicArea;
    private IShape selectedShape;

    public CDocument(CGraphicArea graphicArea){
        this.graphicArea = graphicArea;
        this.image = new BufferedImage(graphicArea.getWidth(),graphicArea.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        this.shapes = new ArrayList<>();
        this.selectedShape = null;
    }

    public void clear(){
        this.shapes.clear();
        clearImage();
        repaintImage();
    }

    public boolean selectShape(int x,int y){
        ListIterator<IShape> iterator = this.shapes.listIterator(this.shapes.size());
        while (iterator.hasPrevious()) {
            IShape shape = iterator.previous();
            if(shape.select(x, y)){
                this.selectedShape = shape;
                return true;
            }
        }

        return false;
    }

    public long redraw(){
        long time = System.currentTimeMillis();

        clearImage();
        Graphics graphics = this.image.getGraphics();

        if(graphics instanceof Graphics2D graphics2D){
            graphics2D.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            for (IShape shape : this.shapes) {
                if(!shape.equals(this.selectedShape)) shape.draw(graphics2D,false);
            }

            if(this.selectedShape != null){
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
                this.selectedShape.draw(graphics2D,true);
            }
        }

        repaintImage();

        return System.currentTimeMillis() - time;
    }

    public void deselectShape(){
        this.selectedShape = null;
    }

    public void addShape(IShape shape){
        this.shapes.add(shape);
    }

    public void moveSelectedTo(int x,int y){
        if(this.selectedShape == null) return;

        this.selectedShape.moveTo(x,y);
    }

    private void repaintImage(){
        this.graphicArea.assignDrawing(this.image);
        this.graphicArea.repaint();
    }

    private void clearImage(){
        Graphics graphics = this.image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,this.image.getWidth(),this.image.getHeight());
    }


}
