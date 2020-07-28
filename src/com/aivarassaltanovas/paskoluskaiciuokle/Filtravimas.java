package com.aivarassaltanovas.paskoluskaiciuokle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Filtravimas implements ActionListener {

    GUI gui;
    Table table;
    private double[] duomenys;
    private JFrame frame;

    private JTextField iki;
    private JTextField nuo;
    private JLabel blogasIvedimas;
    public JButton gerai;

    public Filtravimas(double[] duomenys, GUI gui, Table table) {

        this.duomenys = duomenys;
        this.gui = gui;
        this.table = table;

        frame = new JFrame(); // the frame, the window
        JPanel panel = new JPanel();

        ImageIcon img = new ImageIcon(getClass().getResource("icon.ico"));
        frame.setIconImage(img.getImage());
        frame.setSize(185, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(panel); // add panel on the frame
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        blogasIvedimas = new JLabel("Neteisinga įvestis");
        blogasIvedimas.setBounds(35, 125, 110, 25);
        blogasIvedimas.setForeground(Color.RED);
        panel.add(blogasIvedimas);
        blogasIvedimas.setVisible(false);


        JLabel tekstasNuo = new JLabel("Nuo:");
        tekstasNuo.setBounds(40, 20, 30, 25);
        panel.add(tekstasNuo);

        nuo = new JTextField();
        nuo.setBounds(70, 20, 50, 25);
        panel.add(nuo);

        JLabel tekstasIki = new JLabel("Iki:");
        tekstasIki.setBounds(40, 50, 30, 25);
        panel.add(tekstasIki);

        iki = new JTextField();
        iki.setBounds(70, 50, 50, 25);
        panel.add(iki);

        gerai = new JButton("Gerai");
        gerai.setBounds(55, 90, 70, 25);
        panel.add(gerai);
        gerai.addActionListener(this);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (nuo.getText().isEmpty() || iki.getText().isEmpty()) {

            blogasIvedimas.setVisible(true);

        } else {

            try {

                int Nuo = Integer.parseInt(nuo.getText());
                int Iki = Integer.parseInt(iki.getText());

                if (Nuo > Iki || table.model.getRowCount() < Iki - GUI.index || Nuo - GUI.index <= 0)
                    blogasIvedimas.setVisible(true);

                else {

                    Nuo -= (GUI.index);
                    Iki -= (GUI.index);


                    if (e.getActionCommand().equals("filtravimas"))
                        filtruoti(Nuo, Iki);


                    if (e.getActionCommand().equals("atostogos")) {
                        frame.setVisible(false);

                        for (int i = 0; i < table.model.getRowCount(); i++)
                            table.model.setValueAt(table.dec.format(duomenys[i * 4 + 2]), i, 3);

                        for (int i = Nuo - 1; i < Iki; i++)
                            table.model.setValueAt(table.dec.format(duomenys[i * 4 + 1]), i, 3);
                    }

                    frame.setVisible(false);
                }

            } catch (NumberFormatException ex) {

                blogasIvedimas.setVisible(true);
            }
        }
    }

    public void filtruoti(int Nuo, int Iki) {
        frame.setVisible(false);

        for (int i = 0; i < gui.Menesiai; i++) {
            if (Nuo > i + 1 || Iki <= i) {
                if (Nuo > i) GUI.index++;
                table.model.removeRow(i);

                for (int j = i * 4; j < (gui.Menesiai - 1) * 4; j += 4) {

                    duomenys[j] = duomenys[j + 4];
                    duomenys[j + 1] = duomenys[j + 5];
                    duomenys[j + 2] = duomenys[j + 6];
                    duomenys[j + 3] = duomenys[j + 7];
                }

                i--;
                gui.Menesiai--;
                Nuo--;
                Iki--;
            }
        }
    }
}
