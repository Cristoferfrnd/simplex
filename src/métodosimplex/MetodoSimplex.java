/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package métodosimplex;

/**
 *
 * @author Cristofer Pujota
 */
public class MetodoSimplex {

    public  void imprimirTabla(double[][] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {
                System.out.print(tabla[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public double[][] agVarOlg(double[][] tabla, int nRes, int nVar, double[] fun) {
        double res[][] = new double[tabla.length + 1][(tabla[0].length) + nRes];
        int aux = nVar;
        for (int i = 0; i < tabla.length; i++) {
            res[i][res[0].length - 1] = tabla[i][(tabla[0].length) - 1];
        }
        for (int i = 0; i < tabla.length; i++) {
            res[i][aux] = 1;
            aux = aux + 1;
        }

        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length - 1; j++) {
                res[i][j] = tabla[i][j];
            }
        }

        for (int i = 0; i < tabla[0].length - 1; i++) {
            res[res.length - 1][i] = -fun[i];
        }
        return res;
    }

    public double[][] genProxT(double[][] tabla) {
        int colP = 0, filaP = 0;
        double eleP = 0;
        double[][] tablaSig = new double[tabla.length][tabla[0].length];
        
        colP = obtColP(tabla);
        filaP = obtfilaP(tabla,colP);
       eleP = tabla[filaP][colP];

        System.out.println(filaP + " , " + colP);
        System.out.println(eleP);

        for (int i = 0; i < tabla[0].length; i++) {
            tablaSig[filaP][i] = tabla[filaP][i] / eleP;
        }

        for (int i = 0; i < tabla.length; i++) {
            if (i != filaP) {
                for (int j = 0; j < tabla[0].length; j++) {
                    tablaSig[i][j] = tabla[i][j] - (tabla[i][colP] * tablaSig[filaP][j]);
                }
            }

        }
        return tablaSig;
    }

    public boolean verificarSol(double[] fun) {
        boolean res = true;
        for (int i = 0; i < fun.length - 1; i++) {
            if (fun[i] < 0) {
                res = false;
            }
        }
        return res;
    }
    
    public int obtColP(double[][] tabla){
        double aux = 100000;
        int colP=0;
         for (int i = 0; i < tabla[0].length; i++) {
            if (tabla[tabla.length - 1][i] < aux) {
                colP = i;
                aux = tabla[tabla.length - 1][i];

            }
        }
         return colP;
    }

    private int obtfilaP(double[][] tabla,int colP) {
        int filaP=0;
        double aux = 100000;
        double eleCP,eleP;

        for (int i = 0; i < tabla.length; i++) {
            eleCP = tabla[i][colP];
            if ((tabla[i][tabla[0].length - 1] / eleCP) < aux && (tabla[i][tabla[0].length - 1] / eleCP) > 0) {
                eleP = eleCP;
                filaP = i;
                aux = (tabla[i][tabla[0].length - 1] / eleCP);
            }
        }
        return filaP;
    }
   

}
