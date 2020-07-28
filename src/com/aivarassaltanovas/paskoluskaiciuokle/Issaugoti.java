package com.aivarassaltanovas.paskoluskaiciuokle;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Issaugoti {

    Table table;

    public Issaugoti(Table table) {
        this.table = table;
    }

    public void issaugoti() {
        try {
            File file = new File(System.getProperty("java.Table.path"), "Duomenys.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < table.model.getRowCount(); i++) {
                for (int j = 0; j < table.model.getColumnCount(); j++) {
                    bw.write(table.model.getColumnName(j) + ": ");
                    bw.write(String.valueOf(table.model.getValueAt(i, j)));
                    bw.write("\n");
                }
                bw.write("\n");
            }
            bw.close();
            fw.close();
            JOptionPane.showMessageDialog(null, "Duomenys sėkmingai perkelti");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
