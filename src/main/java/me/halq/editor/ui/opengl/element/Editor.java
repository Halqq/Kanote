package me.halq.editor.ui.opengl.element;

import me.halq.editor.ui.opengl.element.api.Element;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.component.TextAreaField;
import org.liquidengine.legui.component.optional.align.VerticalAlign;
import org.liquidengine.legui.style.color.ColorConstants;

/**
 * @author Halq
 * @since 11/07/2023 at 22:03
 */

public class Editor extends Element {

    public static String text = "nop";
    public static TextAreaField textArea = new TextAreaField();
    Panel editor = new Panel(200, 60, 1100, 600);

    @Override
    public void prepareComponents(){
        editor.getStyle().getBackground().setColor(ColorConstants.darkGray());
        editor.getStyle().setBorder(null);
        textArea.setSize(1100, 600);
        textArea.setPosition(0, 0);
        textArea.getStyle().getBackground().setColor(ColorConstants.darkGray());
        textArea.getPressedStyle().getBackground().setColor(ColorConstants.darkGray());
        textArea.getHoveredStyle().getBackground().setColor(ColorConstants.darkGray());
        textArea.getFocusedStyle().getBackground().setColor(ColorConstants.darkGray());
        textArea.getStyle().setTextColor(ColorConstants.white());
        textArea.getStyle().setVerticalAlign(VerticalAlign.TOP);
        textArea.getStyle().setFontSize(20f);

        textArea.getTextState().setText("aaa");

        editor.add(textArea);
    }

    @Override
    public Component getComponent() {
        return editor;
    }

    public static void update(){
        text = textArea.getTextState().getText();
    }
}
