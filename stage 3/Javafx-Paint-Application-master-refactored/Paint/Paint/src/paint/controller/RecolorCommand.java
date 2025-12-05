/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import javafx.scene.paint.Color;
import paint.model.Shape;
/**
 *
 * @author Asus
 */
public class RecolorCommand implements Command{
    private final DrawingEngine engine;
    private final Shape shape;
    private final Color oldColor;
    private final Color newColor;

    public RecolorCommand(DrawingEngine engine, Shape shape, Color newColor) {
        this.engine = engine;
        this.shape = shape;
        this.oldColor = shape.getFillColor();
        this.newColor = newColor;
    }

    @Override
    public void execute() {
        shape.setFillColor(newColor);
        engine.refresh(null);
    }

    @Override
    public void undo() {
        shape.setFillColor(oldColor);
        engine.refresh(null);
    }
    
}
