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
public class AddShapeCommand implements Command{

    private final DrawingEngine engine;
    private final Shape shape;
    
    public AddShapeCommand(DrawingEngine engine,Shape shape){
        this.engine = engine;
        this.shape = shape;
    }
    
    @Override
    public void execute() {
            engine.addShape(shape);
    }

    @Override
    public void undo() {
            engine.removeShape(shape);
    }
    
}
