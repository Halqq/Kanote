package me.halq.editor.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Halq
 * @since 13/07/2023 at 03:12
 */

public class GetStringFile {

    public static String getFileText(String file){
        try {
            ArrayList<String> lines = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            br.close();
            String[] linesArray = lines.toArray(new String[lines.size()]);
            String text = "";
            for (int i = 0; i < linesArray.length; i++) {
                text += linesArray[i] + "\n";
            }
            return text;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
