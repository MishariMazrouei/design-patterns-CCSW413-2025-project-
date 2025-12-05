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
public class RemoveShapeCommand implements Command{
        
        private final DrawingEngine engine;
        private final Shape shape;
        
        public RemoveShapeCommand(DrawingEngine engine,Shape shape){
            this.engine = engine;
            this.shape = shape;
        }

        @Override
        public void execute() {
            engine.removeShape(shape);
        }

        @Override
        public void undo() {
            engine.addShape(shape);
        }
    
}
