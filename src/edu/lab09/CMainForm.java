package edu.lab09;

import edu.shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CMainForm extends JFrame{
    private JPanel mainPanel;
    private JPanel graphicArea;

    private CDocument document;

    public CMainForm(String title) throws HeadlessException {
        super(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(true);
        this.setLocationRelativeTo(null);


        graphicArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                graphicAreaMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               graphicAreaMouseReleased();
            }
        });

        graphicArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                graphicAreaMouseDragged(e);
            }
        });
        this.document = new CDocument((CGraphicArea) graphicArea);

        this.createShapes();
    }

    private void createUIComponents() {
        graphicArea = new CGraphicArea();

    }

    private void graphicAreaMousePressed(final MouseEvent mouseEvent){
        if(mouseEvent.getButton() != MouseEvent.BUTTON1) return;
        if(this.document.selectShape(mouseEvent.getX(),mouseEvent.getY())) this.document.redraw();
    }

    private void graphicAreaMouseReleased(){
        this.document.deselectShape();
        this.document.redraw();
    }

    private void graphicAreaMouseDragged(final MouseEvent mouseEvent){
        this.document.moveSelectedTo(mouseEvent.getX(),mouseEvent.getY());
        final long time = this.document.redraw();
        if(time <= 0) return;

        this.setTitle(String.format("Kształtowniki, czas rysowania %d ms", time));
    }


    private void createShapes(){
        this.document.addShape(new CShapeCircle(200,200,Color.lightGray,Color.BLACK,70));
        this.document.addShape(new CShapeCircle(600,300,Color.YELLOW,Color.BLUE,90));

        this.document.addShape(new CPolyTriangle(500,600,Color.YELLOW,Color.BLUE,200,150));
        this.document.addShape(new CPolyRectangle(800,400,Color.GRAY,Color.DARK_GRAY,250,100));
        this.document.addShape(new CPolyAngle(900,400,Color.GRAY,Color.DARK_GRAY,250,100,20));
        this.document.addShape(new CPolyZBar(900,400,Color.GRAY,Color.DARK_GRAY,250,100,20));
        this.document.addShape(new CPolyChannelBar(900,400,Color.GRAY,Color.DARK_GRAY,250,100,20));
        this.document.addShape(new CPolyTBar(900,400,Color.GRAY,Color.DARK_GRAY,250,100,20));
        this.document.addShape(new CPolyIBeam(900,400,Color.GRAY,Color.DARK_GRAY,250,100,20));
        this.document.addShape(new CPolyPipe(900,400,Color.GRAY,Color.DARK_GRAY,250,100,50));
        this.document.addShape(new CText(900,400,Color.GRAY,"Sraka",40));

        this.document.redraw();
    }
}
