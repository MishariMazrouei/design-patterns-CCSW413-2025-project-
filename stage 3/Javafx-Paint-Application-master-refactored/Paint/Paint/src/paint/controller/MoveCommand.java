/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import javafx.geometry.Point2D;
import paint.model.Shape;
/**
 *
 * @author Asus
 */
public class MoveCommand implements Command{
    private final DrawingEngine engine;
    private final Shape shape;
    private final Point2D oldPosition;
    private final Point2D newPosition;
    
    public MoveCommand(DrawingEngine engine, Shape shape, Point2D newPosition) {
        this.engine = engine;
        this.shape = shape;
        this.oldPosition = shape.getTopLeft();
        this.newPosition = newPosition;
    }

    @Override
    public void execute() {
        shape.setTopLeft(newPosition);
        engine.refresh(null); // canvas will be passed or stored    }
    }
    
    @Override
    public void undo() {
        shape.setTopLeft(oldPosition);
        engine.refresh(null);
    }
}

