package org.example.passgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class FileGenerator {

    public String filename;
    public File file;

    public FileGenerator (String filename) {
        this.filename = filename;
        this.file = new File(this.filename);
    }

    public boolean CreateFile (String content) {
        try {

            FileWriter filewriter = new FileWriter(this.file);
            BufferedWriter writer = new BufferedWriter(filewriter);
            writer.write(content);
            writer.close();

            return true;
        }
        catch (IOException e) {
            System.out.println("возникла ошибка при создании файла");
            return false;
        }
    }
}
