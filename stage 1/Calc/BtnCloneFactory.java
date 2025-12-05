package Calc;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
public class BtnCloneFactory {
    
     public JButton getClone(JButton src) {

        if (src == null) {
            throw new IllegalArgumentException("Source JButton cannot be null");
        }

        // 1. Create new button with same text and icon
        JButton clone;
        String text = src.getText();
        Icon icon = src.getIcon();
        if (text != null && icon != null) {
            clone = new JButton(text, icon);
        } else if (text != null) {
            clone = new JButton(text);
        } else if (icon != null) {
            clone = new JButton(icon);
        } else {
            clone = new JButton();
        }

        // 2. Copy all visual properties
        clone.setFont(src.getFont());
        clone.setForeground(src.getForeground());
        clone.setBackground(src.getBackground());
        clone.setOpaque(src.isOpaque());
        clone.setBorderPainted(src.isBorderPainted());

        // Deep clone border if it implements Cloneable
        Border border = src.getBorder();
        if (border instanceof Cloneable) {
            try {
                clone.setBorder((Border) ((Cloneable) border).clone());
            } catch (Exception e) {
                clone.setBorder(border); // fallback to shallow
            }
        } else {
            clone.setBorder(border);
        }

        clone.setMargin(src.getMargin());
        clone.setAlignmentX(src.getAlignmentX());
        clone.setAlignmentY(src.getAlignmentY());

        // 3. Copy all icon variants
        clone.setRolloverIcon(src.getRolloverIcon());
        clone.setPressedIcon(src.getPressedIcon());
        clone.setDisabledIcon(src.getDisabledIcon());
        clone.setSelectedIcon(src.getSelectedIcon());
        clone.setRolloverSelectedIcon(src.getRolloverSelectedIcon());

        // 4. Copy size constraints
        clone.setPreferredSize(src.getPreferredSize());
        clone.setMinimumSize(src.getMinimumSize());
        clone.setMaximumSize(src.getMaximumSize());

        // 5. Copy state
        clone.setEnabled(src.isEnabled());
        clone.setFocusable(src.isFocusable());
        clone.setRequestFocusEnabled(src.isRequestFocusEnabled());

        // 6. Copy tooltip
        clone.setToolTipText(src.getToolTipText());

        // 7. Copy Action (usually immutable)
        clone.setAction(src.getAction());

        // 8. Deep copy ButtonModel state with new model
        ButtonModel srcModel = src.getModel();
        ButtonModel cloneModel = new DefaultButtonModel();
        clone.setModel(cloneModel);
        cloneModel.setArmed(srcModel.isArmed());
        cloneModel.setEnabled(srcModel.isEnabled());
        cloneModel.setPressed(srcModel.isPressed());
        cloneModel.setRollover(srcModel.isRollover());
        cloneModel.setSelected(srcModel.isSelected());

        // 9. Re-register all ActionListeners
        for (ActionListener listener : src.getActionListeners()) {
            clone.addActionListener(listener);
        }

        return clone;

    }

}
