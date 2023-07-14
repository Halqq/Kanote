package me.halq.editor.ui.opengl.element.subs;

import me.halq.editor.file.GetStringFile;
import me.halq.editor.file.SaveFile;
import me.halq.editor.ui.opengl.element.Editor;
import me.halq.editor.ui.opengl.element.api.Element;
import me.halq.editor.ui.opengl.element.ToolBar;
import org.joml.Vector4f;
import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.event.CursorEnterEvent;
import org.liquidengine.legui.event.MouseClickEvent;
import org.liquidengine.legui.style.color.ColorConstants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import static org.liquidengine.legui.event.MouseClickEvent.MouseClickAction.CLICK;

/**
 * @author Halq
 * @since 12/07/2023 at 23:12
 */

public class FileSub extends Element {

    public static boolean sub = false;
    public static boolean sub2 = false;
    public static boolean sub3 = false;
    public static boolean sub4 = false;
    public static String fileName;
    static Button openB = new Button("Open");
    static Button saveB = new Button("Save");
    static Button createb = new Button("Create");
    static Button closeb = new Button("Close");
    static String saveFile;

    public static void makeSubs() {
        ToolBar.toolbar.add(openB);
        ToolBar.toolbar.add(saveB);
        ToolBar.toolbar.add(createb);
        ToolBar.toolbar.add(closeb);
    }

    public static void removeSubs() {
        ToolBar.toolbar.remove(openB);
        ToolBar.toolbar.remove(saveB);
        ToolBar.toolbar.remove(createb);
        ToolBar.toolbar.remove(closeb);
    }

    @Override
    public void prepareComponents() {
        openB.getStyle().getBackground().setColor(ColorConstants.darkGray());
        openB.getHoveredStyle().getBackground().setColor(new Vector4f(0.33f, 0.33f, 0.33f, 1.0f));
        openB.getStyle().setTextColor(ColorConstants.white());
        openB.getStyle().setBorder(null);
        openB.getStyle().setMargin(5f, 5f, 5f, 5f);
        openB.setSize(125, 30);
        openB.setPosition(0, 30);

        saveB.getStyle().getBackground().setColor(ColorConstants.darkGray());
        saveB.getHoveredStyle().getBackground().setColor(new Vector4f(0.33f, 0.33f, 0.33f, 1.0f));
        saveB.getStyle().setTextColor(ColorConstants.white());
        saveB.getStyle().setBorder(null);
        saveB.getStyle().setMargin(5f, 5f, 5f, 5f);
        saveB.setSize(125, 30);
        saveB.setPosition(0, 60);

        createb.getStyle().getBackground().setColor(ColorConstants.darkGray());
        createb.getHoveredStyle().getBackground().setColor(new Vector4f(0.33f, 0.33f, 0.33f, 1.0f));
        createb.getStyle().setTextColor(ColorConstants.white());
        createb.getStyle().setBorder(null);
        createb.getStyle().setMargin(5f, 5f, 5f, 5f);
        createb.setSize(125, 30);
        createb.setPosition(0, 90);

        closeb.getStyle().getBackground().setColor(ColorConstants.darkGray());
        closeb.getHoveredStyle().getBackground().setColor(new Vector4f(0.33f, 0.33f, 0.33f, 1.0f));
        closeb.getStyle().setTextColor(ColorConstants.white());
        closeb.getStyle().setBorder(null);
        closeb.getStyle().setMargin(5f, 5f, 5f, 5f);
        closeb.setSize(125, 30);
        closeb.setPosition(0, 120);

        openB.getListenerMap().addListener(CursorEnterEvent.class, event -> {
            if (!event.isEntered()) {
                sub = false;
                if (!sub2 && !sub3 && !sub4 && !ToolBar.sub) {
                    removeSubs();
                }
            } else {
                sub = true;
            }
        });

        saveB.getListenerMap().addListener(CursorEnterEvent.class, event -> {
            if (!event.isEntered()) {
                sub2 = false;
                if (!sub3 && !sub4 && !sub) {
                    removeSubs();
                }
            } else {
                sub2 = true;
            }
        });

        createb.getListenerMap().addListener(CursorEnterEvent.class, event -> {
            if (!event.isEntered()) {
                sub3 = false;
                if (!sub2 && !sub4 && !sub) {
                    removeSubs();
                }
            } else {
                sub3 = true;
            }
        });

        //meu deus nao sei como essa gambiarra funcionou

        closeb.getListenerMap().addListener(CursorEnterEvent.class, event -> {
            if (!event.isEntered()) {
                sub4 = false;
                if (!sub2 && !sub3 && !sub) {
                    removeSubs();
                }
            } else {
                sub4 = true;
            }
        });

        openB.getListenerMap().addListener(MouseClickEvent.class, event -> {
            if (event.getAction().equals(CLICK)) {
                JFrame frame = new JFrame("Open");
                frame.setAlwaysOnTop(true);
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Texto", "txt");
                chooser.setFileFilter(filter);
                chooser.showOpenDialog(frame);
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                if (chooser.getSelectedFile() != null) {
                    fileName = chooser.getSelectedFile().getAbsolutePath();
                    Editor.textArea.getTextState().setText(GetStringFile.getFileText(fileName));
                }
            }
        });

        saveB.getListenerMap().addListener(MouseClickEvent.class, event -> {
            if (event.getAction().equals(CLICK)) {
                if (saveFile == null) {
                    JFrame frame = new JFrame();
                    frame.setAlwaysOnTop(true);
                    JOptionPane.showMessageDialog(frame, "Selecione ou crie um arquivo primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JFrame frame = new JFrame("Save");
                    frame.setAlwaysOnTop(true);
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Texto", "txt");
                    chooser.setFileFilter(filter);
                    if (fileName != null) {
                        chooser.setSelectedFile(new File(fileName));
                    }

                    chooser.showSaveDialog(frame);
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                    if (chooser.getSelectedFile() != null) {
                        saveFile = chooser.getSelectedFile().getAbsolutePath();
                        SaveFile.saveFile(saveFile, Editor.text);
                    }
                }
            }
        });

        createb.getListenerMap().addListener(MouseClickEvent.class, event -> {
            System.out.println("Create event work");
        });

        closeb.getListenerMap().addListener(MouseClickEvent.class, event -> {
            System.exit(0);
        });

    }
}
