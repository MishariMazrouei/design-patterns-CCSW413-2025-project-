package Calc;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BtnMaker {
    private BtnBuilder btnBuilder;
    private BtnCloneFactory cloneFactory = new BtnCloneFactory();

    public BtnMaker(BtnBuilder btnBuilder){
        this.btnBuilder=btnBuilder;
    }

    public JButton makeBtnNButton(String text){

        this.btnBuilder.btnText(text);
        this.btnBuilder.makeBtn();

        return cloneFactory.getClone(btnBuilder.getBtn());
    }

    public JButton makeBtnOperation(String text, ActionListener btnEvent){

        this.btnBuilder.btnText(text);
        this.btnBuilder.btnActionListener(btnEvent);
        this.btnBuilder.makeBtn();

        return cloneFactory.getClone(btnBuilder.getBtn());
    }
}
