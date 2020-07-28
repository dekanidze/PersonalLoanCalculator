package com.aivarassaltanovas.paskoluskaiciuokle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Table implements ActionListener {

    private GUI gui;
    private double[] duomenys;

    public JButton atostogos;

    public DefaultTableModel model;

    DecimalFormat dec = new DecimalFormat("#0.00");

    public Table(GUI gui) {

        this.gui = gui;
    }

    final String[] STULPELIU_REIKSMES = {"Mėnuo", "Paskolos dalis", "Palūkanos", "Įmoka su palūkanomis", "Kredito likutis"};

    public void lentele(double[] duomenys) {

        this.duomenys = duomenys;
        JFrame frame = new JFrame();
        ImageIcon img = new ImageIcon(getClass().getResource("icon.ico"));
        frame.setIconImage(img.getImage());
        frame.setTitle("Paskolos išmokėjimo tvarkaraštis");
        frame.setSize(500, 510);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                gui.frame.setEnabled(true);
            }
        });

        JTable table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(STULPELIU_REIKSMES);
        table.setModel(model);

        table.setBackground(Color.white);
        // table.setForeground(Color.black);
        Font font = new Font("Arial", Font.PLAIN, 12);
        table.setFont(font);
        table.setRowHeight(20);

        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        JButton rodytiGrafika = new JButton("Rodyti grafiką");
        frame.add(rodytiGrafika);
        rodytiGrafika.addActionListener(this);
        rodytiGrafika.setActionCommand("rodytiGrafika");

        JButton intervalas = new JButton("Rodyti nuo iki");
        frame.add(intervalas);
        intervalas.addActionListener(this);
        intervalas.setActionCommand("intervalas");

        atostogos = new JButton("Atostogos");
        frame.add(atostogos);
        atostogos.addActionListener(this);
        atostogos.setActionCommand("atostogos");

        JButton issaugoti = new JButton("Išsaugoti");
        frame.add(issaugoti);
        issaugoti.addActionListener(this);
        issaugoti.setActionCommand("issaugoti");

        frame.setVisible(true);

        idetiDuomenis(duomenys, model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "rodytiGrafika":

                if (gui.linijinis.isSelected()) {

                    new Grafikas("Linijinis grafikas", duomenys, gui, this);
                }

                if (gui.anuiteto.isSelected()) {

                    new Grafikas("Anuiteto grafikas", duomenys, gui, this);
                }

                break;

            case "intervalas":

                Filtravimas filtravimas = new Filtravimas(duomenys, gui, this);
                filtravimas.gerai.setActionCommand("filtravimas");
                break;

            case "atostogos":

                filtravimas = new Filtravimas(duomenys, gui, this);
                filtravimas.gerai.setActionCommand("atostogos");
                break;

            case "issaugoti":

                Issaugoti save = new Issaugoti(this);
                save.issaugoti();
                break;

            default:
                System.exit(0);
        }
    }

    public void idetiDuomenis(double[] duomenys, DefaultTableModel model) {

        int j = 0;
        for (int i = 1; i <= gui.Menesiai; i++) {
            model.addRow(new Object[]{i, dec.format(duomenys[j]), dec.format(duomenys[j + 1]), dec.format(duomenys[j + 2]), dec.format(duomenys[j + 3])});
            j += 4;
        }
    }
}

