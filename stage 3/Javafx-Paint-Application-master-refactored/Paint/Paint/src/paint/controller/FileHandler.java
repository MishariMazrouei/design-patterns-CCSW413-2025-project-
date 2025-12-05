/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import paint.model.Shape;
import java.util.ArrayList;
/**
 *
 * @author Asus
 */
public abstract class FileHandler {
    
    protected FileHandler next;
    
    public void setNext(FileHandler next){
        this.next = next;
    }
    
    public boolean save(String path,ArrayList<Shape> shapes){
        if(canHandleSave(path)){
            return doSave(path, shapes);
        }else if(next != null){
            return next.save(path, shapes);
        }
        
        return false;
    }
    
    public boolean load(String path,DrawingEngine engine){
        if(canHandleLoad(path)){
            return doLoad(path, engine);
        }else if(next != null){
            return next.load(path, engine);
        }
        
        return false;
    }
    
    protected abstract boolean canHandleSave(String path);
    protected abstract boolean canHandleLoad(String path);
    protected abstract boolean doSave(String path,ArrayList<Shape> shapes);
    protected abstract boolean doLoad(String path,DrawingEngine engine);
    
}
