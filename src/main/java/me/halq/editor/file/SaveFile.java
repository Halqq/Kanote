package me.halq.editor.file;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Halq
 * @since 13/07/2023 at 02:47
 */

public class SaveFile {

    public static void saveFile(String file, String text) {
        File f = new File(file);
        if (f.exists()) {
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);

            // Exibir a caixa de diálogo com a pergunta ao usuário
            int resposta = JOptionPane.showOptionDialog(
                    frame,
                    "O arquivo já existe. Deseja sobrescrevê-lo?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Sim", "Não"},
                    JOptionPane.YES_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                try {
                    if (f.exists()) {
                        WriteStringFile.writeOnFile(file, text);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Faça a ação quando o usuário optar por não sobrescrever o arquivo aqui
                System.out.println("Não será feita a sobrescrita do arquivo.");
            }
        }
    }
}
