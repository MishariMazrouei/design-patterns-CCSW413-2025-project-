package Calc;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionListener;

public class BtnOperation implements BtnBuilder {

    private String text = "";
    private JButton btn;
    private ActionListener btnEvent;

    public BtnOperation() {

        btn = new JButton();
    }

    @Override
    public void btnBackground() {
        btn.setBackground(new Color(21, 20, 22));
    }

    @Override
    public void btnFont() {
        btn.setFont(new Font("Century Gothic", 1, 18));
    }

    @Override
    public void btnForeground() {

        btn.setForeground(new Color(255, 255, 255));
    }

    @Override
    public void btnBorder() {

        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(41, 39, 44)));
    }

    @Override
    public void btnCursor() {

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void btnFocusPainted() {

        btn.setFocusPainted(false);
    }

    @Override
    public void btnIconTextGap() {

        btn.setIconTextGap(1);
        ;
    }

    @Override
    public void btnMargin() {

        btn.setMargin(new Insets(0, 0, 0, 0));
    }

    @Override
    public void btnPreferredSize() {

        btn.setPreferredSize(new Dimension(70, 70));
    }

    @Override
    public void btnText(String text) {

        this.text = text;
    }

    @Override
    public void btnActionListener(ActionListener btnEvent) {

        this.btnEvent = btnEvent;
    }

    @Override
    public void makeBtn() {
        this.btnBackground();
        this.btnBorder();
        this.btnCursor();
        this.btnFocusPainted();
        this.btnFont();
        this.btnForeground();
        this.btnIconTextGap();
        this.btnMargin();
        this.btnPreferredSize();
        btn.setText(text);
        if (btnEvent != null) { // Add this guard
            btn.addActionListener(btnEvent);
        }
    }

    @Override
    public JButton getBtn() {

        return btn;
    }

}