package me.halq.editor.ui.opengl.element.api;

import me.halq.editor.ui.opengl.element.Editor;
import me.halq.editor.ui.opengl.element.ToolBar;
import me.halq.editor.ui.opengl.element.subs.FileSub;
import org.liquidengine.legui.component.Component;

import java.util.ArrayList;

/**
 * @author Halq
 * @since 13/07/2023 at 22:30
 */

public class ElementManager {

    static ArrayList<Element> components = new ArrayList<>();

    public ElementManager() {
        addElement(new FileSub());
        addElement(new Editor());
        addElement(new ToolBar());
    }

    public void addElement(Element element) {
        components.add(element);
    }

    public static void prepareComponents() {
        for (Element element : components) {
            element.prepareComponents();
        }
    }

    public static Component getComponents() {
        Component c = null;
        for (Element element : components) {
            c = element.getComponent();
        }
        return c;
    }
}
