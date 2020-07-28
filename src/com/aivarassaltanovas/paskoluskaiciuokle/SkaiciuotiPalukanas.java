package com.aivarassaltanovas.paskoluskaiciuokle;


public class SkaiciuotiPalukanas {

    double[] duomenys;
    double paskolosDalis;
    double palukanos;
    double imoka;

    public double[] linijinis(double Suma, int Menesiai, double Procentai) {

        int j = 0;

        paskolosDalis = Suma / Menesiai;
        duomenys = new double[Menesiai * 5];

        for (int i = 1; i <= Menesiai; i++) {
            palukanos = Suma * (Procentai / 100 / 12);
            imoka = paskolosDalis + palukanos;
            Suma = Suma - paskolosDalis;

            duomenys[j] = paskolosDalis;
            duomenys[j + 1] = palukanos;
            duomenys[j + 2] = imoka;
            duomenys[j + 3] = Suma;
            j += 4;
        }

        return duomenys;
    }

    public double[] anuiteto(double Suma, int Menesiai, double Procentai) {

        int j = 0;

        palukanos = Procentai / 12 / 100;
        double koeficientas = (palukanos * Math.pow((1 + palukanos), Menesiai)) / (Math.pow((1 + palukanos), Menesiai) - 1);
        imoka = koeficientas * Suma;

        duomenys = new double[Menesiai * 5];

        for (int i = 1; i <= Menesiai; i++) {
            palukanos = Suma * (Procentai / 100 / 12);
            paskolosDalis = imoka - palukanos;
            Suma = Suma - paskolosDalis;

            duomenys[j] = paskolosDalis;
            duomenys[j + 1] = palukanos;
            duomenys[j + 2] = imoka;
            duomenys[j + 3] = Suma;
            j += 4;
        }
        return duomenys;
    }
}
