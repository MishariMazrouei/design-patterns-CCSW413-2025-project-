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
public class XMLFileHandler extends FileHandler{
    
    

    @Override
    protected boolean canHandleSave(String path) {
        return path.toLowerCase().endsWith(".xml");
        }

    @Override
    protected boolean canHandleLoad(String path) {
        return path.toLowerCase().endsWith(".xml");
    }

    @Override
    protected boolean doSave(String path, ArrayList<Shape> shapes) {
        SaveToXML saver = new SaveToXML(path, shapes);
        return saver.checkSuccess();
    }

    @Override
    protected boolean doLoad(String path, DrawingEngine engine) {
        try{
           LoadFromXML loader = new LoadFromXML(path);
        if (loader.checkSuccess()) {
            FXMLDocumentController.shapeList = loader.getList();  // direct static access
            engine.refresh(null);  // will use CanvasBox (fixed below)
            return true;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return false;
    }
    
}
