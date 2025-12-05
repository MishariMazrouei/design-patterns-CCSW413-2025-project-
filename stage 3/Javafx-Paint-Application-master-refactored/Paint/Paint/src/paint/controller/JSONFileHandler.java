/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import paint.model.Shape;
import java.util.ArrayList;
/**
 *
 * @author Asus
 */
public class JSONFileHandler extends FileHandler{

    @Override
    protected boolean canHandleSave(String path) {
        return path.toLowerCase().endsWith("json");
    }

    @Override
    protected boolean canHandleLoad(String path) {
        return path.toLowerCase().endsWith(".json");
    }

   @Override
    protected boolean doSave(String path, ArrayList<Shape> shapes) {
        // TODO: implement real JSON later (Gson or Jackson)
        return false;  // unsupported for now
    }

    @Override
    protected boolean doLoad(String path, DrawingEngine engine) {
        // TODO: implement real JSON later
        return false;
    }
    
}
