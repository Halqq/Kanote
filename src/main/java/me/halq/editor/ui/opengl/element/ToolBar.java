package me.halq.editor.ui.opengl.element;

import me.halq.editor.ui.opengl.element.api.Element;
import me.halq.editor.ui.opengl.element.subs.FileSub;
import org.joml.Vector4f;
import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.event.CursorEnterEvent;
import org.liquidengine.legui.style.color.ColorConstants;

/**
 * @author Halq
 * @since 11/07/2023 at 21:17
 */

public class ToolBar extends Element {

    public static Panel toolbar = new Panel(0, 0, 10000, 20000);
    public static Panel background = new Panel(0, 0, 10000, 45);

    public static boolean sub = false;

    @Override
    public void prepareComponents() {
        background.getStyle().getBackground().setColor(new Vector4f(0.14f, 0.14f, 0.14f, 1.0f));
        background.getStyle().setBorder(null);
        background.getStyle().setMargin(0f, 0f, 0f, 0f);

        toolbar.getStyle().getBackground().setColor(ColorConstants.transparent());
        toolbar.getStyle().setBorder(null);

        Button fileb = new Button("File");
        fileb.getStyle().getBackground().setColor(ColorConstants.darkGray());
        fileb.getStyle().setTextColor(ColorConstants.white());
        fileb.getStyle().setBorder(null);
        fileb.getStyle().setMargin(5f, 5f, 5f, 5f);
        fileb.setSize(150, 30);
        fileb.setPosition(0, 0);
        fileb.getHoveredStyle().getBackground().setColor(new Vector4f(0.33f, 0.33f, 0.33f, 1.0f));
        fileb.getStyle().setBorderRadius(0f);

        fileb.getListenerMap().addListener(CursorEnterEvent.class, event -> {
            if (!sub || FileSub.sub || FileSub.sub2 || FileSub.sub3 || FileSub.sub4) {
                FileSub.makeSubs();
            } else {
                FileSub.removeSubs();
            }

            sub = event.isEntered();
        });

        Button editb = new Button("Edit");
        editb.getStyle().getBackground().setColor(ColorConstants.darkGray());
        editb.getStyle().setTextColor(ColorConstants.white());
        editb.getStyle().setBorder(null);
        editb.getStyle().setMargin(5f, 5f, 5f, 5f);
        editb.setSize(150, 30);
        editb.setPosition(150, 0);
        editb.getHoveredStyle().getBackground().setColor(new Vector4f(0.33f, 0.33f, 0.33f, 1.0f));
        editb.getStyle().setBorderRadius(0f);

        toolbar.add(fileb);
        toolbar.add(editb);
    }

    @Override
    public Component getComponent() {
        return toolbar;
    }
}
