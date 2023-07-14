package me.halq.editor.ui.opengl;

import me.halq.editor.ui.opengl.element.api.ElementManager;
import org.joml.Vector4f;
import org.liquidengine.legui.component.Frame;
import org.liquidengine.legui.component.Label;

import static me.halq.editor.ui.opengl.EditorRenderer.fps;
import static me.halq.editor.ui.opengl.element.ToolBar.background;

/**
 * @author Halq
 * @since 11/07/2023 at 21:26
 */

public class FrameBuilder {

    public static Frame frame;
    private static Label fpsLabel;

    public FrameBuilder(Frame frame) {
        FrameBuilder.frame = frame;

        ElementManager.prepareComponents();
        frame.getContainer().getStyle().getBackground().setColor(new Vector4f(0.27f, 0.27f, 0.27f, 1.0f));

        frame.getContainer().add(background);
        frame.getContainer().add(ElementManager.getComponents());

        fpsLabel = new Label("FPS: " + fps);
        fpsLabel.setPosition(0, 150);
        fpsLabel.getStyle().setTextColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
        frame.getContainer().add(fpsLabel);
    }

    public static void updateFpsLabel(String fps) {
        fpsLabel.getTextState().setText("FPS: " + fps);
    }
}
