package Calc;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public interface BtnBuilder {

    public void makeBtn();
    public void btnBackground();
    public void btnFont();
    public void btnForeground();
    public void btnBorder();
    public void btnCursor();
    public void btnFocusPainted();
    public void btnIconTextGap();
    public void btnMargin();
    public void btnPreferredSize();
    public void btnText(String text);
    public void btnActionListener(ActionListener btnEvent);
    public JButton getBtn();
    
}