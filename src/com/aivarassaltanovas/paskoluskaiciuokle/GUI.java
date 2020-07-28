package com.aivarassaltanovas.paskoluskaiciuokle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    public JFrame frame;
    public JPanel panel;

    public JRadioButton anuiteto;
    public JRadioButton linijinis;

    private JTextField paskolosSuma;
    private JTextField paskolosTerminasMetai;
    private JTextField paskolosTerminasMenesiai;
    private JTextField metinisProcentas;

    private JLabel pranesimasBlogai;
    private JLabel blogasIvedimas;

    public double Suma;
    public int Metai;
    public int Menesiai;
    public double Procentai;
    static int index = 0; // noredamas istrinti eilute, turiu vartotojo ivestus parametrus pakeisti indeksu lenteleje

    public GUI() {
        frame = new JFrame("Paskolos skaičiuoklė"); // the frame, the window
        panel = new JPanel();
        ImageIcon img = new ImageIcon(getClass().getResource("icon.ico"));
        frame.setIconImage(img.getImage());
        frame.setSize(305, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel); // add panel o the frame
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        pranesimasBlogai = new JLabel("Prašome užpildyti visus laukus!");
        pranesimasBlogai.setBounds(55, 270, 180, 25);
        pranesimasBlogai.setForeground(Color.RED);
        panel.add(pranesimasBlogai);
        pranesimasBlogai.setVisible(false);

        blogasIvedimas = new JLabel("Neteisingai užpildyti laukeliai");
        blogasIvedimas.setBounds(60, 270, 170, 25);
        blogasIvedimas.setForeground(Color.RED);
        panel.add(blogasIvedimas);
        blogasIvedimas.setVisible(false);


        JLabel tekstasPaskolosSuma = new JLabel("Paskolos suma:");
        tekstasPaskolosSuma.setBounds(10, 20, 120, 25);
        panel.add(tekstasPaskolosSuma);

        paskolosSuma = new JTextField();
        paskolosSuma.setBounds(120, 20, 160, 25);
        panel.add(paskolosSuma);


        JLabel tekstasPaskolosTerminas = new JLabel("Paskolos terminas:");
        tekstasPaskolosTerminas.setBounds(10, 70, 120, 25);
        panel.add(tekstasPaskolosTerminas);

        JLabel tekstasPaskolosTerminasMetai = new JLabel("metai");
        tekstasPaskolosTerminasMetai.setBounds(150, 70, 35, 25);
        panel.add(tekstasPaskolosTerminasMetai);

        paskolosTerminasMetai = new JTextField();
        paskolosTerminasMetai.setBounds(120, 70, 30, 25);
        panel.add(paskolosTerminasMetai);

        JLabel tekstasPaskolosTerminasMenesiai = new JLabel("mėnesiai");
        tekstasPaskolosTerminasMenesiai.setBounds(225, 70, 50, 25);
        panel.add(tekstasPaskolosTerminasMenesiai);

        paskolosTerminasMenesiai = new JTextField();
        paskolosTerminasMenesiai.setBounds(195, 70, 30, 25);
        panel.add(paskolosTerminasMenesiai);


        JLabel tekstasMetinisProcentas = new JLabel("Metinis procentas:");
        tekstasMetinisProcentas.setBounds(10, 120, 120, 25);
        panel.add(tekstasMetinisProcentas);

        metinisProcentas = new JTextField();
        metinisProcentas.setBounds(120, 120, 160, 25);
        panel.add(metinisProcentas);


        JLabel tekstasPaskolosGrazinimoGrafikas = new JLabel("Paskolos grąžinimo grafikas:");
        tekstasPaskolosGrazinimoGrafikas.setBounds(10, 180, 165, 25);
        panel.add(tekstasPaskolosGrazinimoGrafikas);

        anuiteto = new JRadioButton("Anuiteto");
        anuiteto.setBounds(175, 170, 105, 25);
        panel.add(anuiteto);

        linijinis = new JRadioButton("Linijinis");
        linijinis.setBounds(175, 195, 105, 25);
        panel.add(linijinis);

        ButtonGroup group = new ButtonGroup();
        group.add(anuiteto);
        group.add(linijinis);


        JButton skaiciuoti = new JButton("Skaičiuoti");
        skaiciuoti.setBounds(90, 230, 105, 25);
        panel.add(skaiciuoti);
        skaiciuoti.setActionCommand("skaiciuoti");
        skaiciuoti.addActionListener(this);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("skaiciuoti")) {

            if (paskolosSuma.getText().isEmpty() || metinisProcentas.getText().isEmpty() || (!linijinis.isSelected() && !anuiteto.isSelected())) {

                pranesimasBlogai.setVisible(true);
                blogasIvedimas.setVisible(false);

            } else {

                try {
                    blogasIvedimas.setVisible(false);
                    pranesimasBlogai.setVisible(false);
                    Suma = Double.parseDouble(paskolosSuma.getText());
                    Metai = Integer.parseInt(paskolosTerminasMetai.getText());
                    Menesiai = Integer.parseInt(paskolosTerminasMenesiai.getText());
                    Procentai = Double.parseDouble(metinisProcentas.getText());

                    SkaiciuotiPalukanas skaiciuoti = new SkaiciuotiPalukanas();
                    Menesiai = Metai * 12 + Menesiai;
                    double[] duomenys = new double[Menesiai * 4];


                    if (linijinis.isSelected()) {

                        duomenys = skaiciuoti.linijinis(Suma, Menesiai, Procentai);
                    }

                    if (anuiteto.isSelected()) {

                        duomenys = skaiciuoti.anuiteto(Suma, Menesiai, Procentai);
                    }
                    frame.setEnabled(false);
                    Table lent = new Table(this);
                    index = 0;
                    lent.lentele(duomenys);


                } catch (NumberFormatException ex) {

                    pranesimasBlogai.setVisible(false);
                    blogasIvedimas.setVisible(true);
                }
            }
        }
    }
}