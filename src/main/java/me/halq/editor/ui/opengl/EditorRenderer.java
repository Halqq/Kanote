package me.halq.editor.ui.opengl;

import me.halq.editor.ui.opengl.element.Editor;
import me.halq.editor.ui.opengl.element.api.ElementManager;
import me.halq.editor.ui.opengl.element.subs.FileSub;
import org.joml.Vector2i;
import org.liquidengine.legui.animation.AnimatorProvider;
import org.liquidengine.legui.component.Frame;
import org.liquidengine.legui.listener.processor.EventProcessorProvider;
import org.liquidengine.legui.system.context.CallbackKeeper;
import org.liquidengine.legui.system.context.Context;
import org.liquidengine.legui.system.context.DefaultCallbackKeeper;
import org.liquidengine.legui.system.handler.processor.SystemEventProcessor;
import org.liquidengine.legui.system.handler.processor.SystemEventProcessorImpl;
import org.liquidengine.legui.system.layout.LayoutManager;
import org.liquidengine.legui.system.renderer.Renderer;
import org.liquidengine.legui.system.renderer.nvg.NvgRenderer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWWindowCloseCallbackI;
import org.lwjgl.opengl.GL;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * @author Halq
 * @since 11/07/2023 at 18:39
 */

public class EditorRenderer {

    private static final DecimalFormat fpsFormat = new DecimalFormat("00");
    public static int WIDTH;
    public static int HEIGHT;
    public static double fps;
    private static double lastTime;
    private static int frames;
    private static volatile boolean running = false;

    public static void openGui() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int WIDTH = (int) screenSize.getWidth();
        int HEIGHT = (int) screenSize.getHeight();
        if (!GLFW.glfwInit()) {
            throw new RuntimeException("Can't initialize GLFW");
        }

        long window = glfwCreateWindow(WIDTH, HEIGHT, "KaNote", NULL, NULL);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwSwapInterval(0);

        glfwShowWindow(window);

        GL.createCapabilities();

        new ElementManager();

        Frame frame = new Frame(WIDTH, HEIGHT);
        createGuiElements(frame);

        Context context = new Context(window);

        CallbackKeeper keeper = new DefaultCallbackKeeper();

        CallbackKeeper.registerCallbacks(window, keeper);

        GLFWKeyCallbackI glfwKeyCallbackI = (w1, key, code, action, mods) -> running = !(key == GLFW_KEY_ESCAPE && action != GLFW_RELEASE);
        GLFWWindowCloseCallbackI glfwWindowCloseCallbackI = w -> running = false;

        keeper.getChainKeyCallback().add(glfwKeyCallbackI);
        keeper.getChainWindowCloseCallback().add(glfwWindowCloseCallbackI);

        SystemEventProcessor systemEventProcessor = new SystemEventProcessorImpl();
        SystemEventProcessor.addDefaultCallbacks(keeper, systemEventProcessor);

        Renderer renderer = new NvgRenderer();

        running = true;

        renderer.initialize();

        while (running) {
            context.updateGlfwWindow();
            Vector2i windowSize = context.getFramebufferSize();

            glViewport(0, 0, windowSize.x, windowSize.y);
            glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);

            double currentTime = glfwGetTime();
            double delta = currentTime - lastTime;
            frames++;
            if (delta >= 1.0) {
                fps = frames / delta;
                System.out.println("FPS: " + fps);
                frames = 0;
                lastTime = currentTime;

                String formattedFps = fpsFormat.format(fps);
                FrameBuilder.updateFpsLabel(formattedFps);

            }

            if (FileSub.fileName != null) {
                String f;
                if (FileSub.fileName.contains("\\")) {
                    f = FileSub.fileName.substring(FileSub.fileName.lastIndexOf("\\") + 1);
                } else {
                    f = FileSub.fileName;
                }
                glfwSetWindowTitle(window, "KaNote - Editing: " + f);
            }

            Editor.update();
            renderer.render(frame, context);

            glPushMatrix();
            glColor3f(1.0f, 0.0f, 0.0f);
            glBegin(GL_QUADS);
            glVertex2f(-50.0f, -50.0f);
            glVertex2f(50.0f, -50.0f);
            glVertex2f(50.0f, 50.0f);
            glVertex2f(-50.0f, 50.0f);
            glEnd();
            glPopMatrix();

            glfwPollEvents();
            glfwSwapBuffers(window);

            systemEventProcessor.processEvents(frame, context);

            EventProcessorProvider.getInstance().processEvents();

            LayoutManager.getInstance().layout(frame);

            AnimatorProvider.getAnimator().runAnimations();
        }

        renderer.destroy();

        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private static void createGuiElements(Frame frame) {
        new FrameBuilder(frame);
    }
}