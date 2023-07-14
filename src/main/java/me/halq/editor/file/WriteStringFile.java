package me.halq.editor.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Halq
 * @since 13/07/2023 at 03:02
 */

public class WriteStringFile {

    public static void writeOnFile(String file, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.close();
    }
}
