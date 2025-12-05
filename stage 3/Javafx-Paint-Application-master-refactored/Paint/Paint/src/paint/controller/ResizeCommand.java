/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import paint.model.Shape;
/**
 *
 * @author Asus
 */
public class ResizeCommand implements Command{
    private final DrawingEngine engine;
    private final Shape oldShape;
    private final Shape newShape;

    public ResizeCommand(DrawingEngine engine, Shape oldShape, Shape newShape) {
        this.engine = engine;
        this.oldShape = oldShape;
        this.newShape = newShape;
    }

    @Override
    public void execute() {
        engine.updateShape(oldShape, newShape);
    }

    @Override
    public void undo() {
        engine.updateShape(newShape, oldShape);
    }
}
