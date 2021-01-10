/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package métodosimplex;

import java.util.Scanner;

/**
 *
 * @author Cristofer Pujota
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetodoSimplex mt = new MetodoSimplex();
        Scanner sc = new Scanner(System.in);
        

        // <-- variables 
        boolean solOpt = false;
        int nRes = 3;
        int nVar = 4;
        double[] fun = {1, 4, 1, 2};  //new double[nVar]; // función objetivo

        double[][] tabla = {
            {1, 0, 1, 0, 5},
            {2, 1, 0, 1, 16},
            {0, 1, 4, 1, 6}
        }; //= new double[nRes][nVar + 1]; // Matriz de ecuaciones
        mt.imprimirTabla(tabla);
        tabla = mt.agVarOlg(tabla, nRes, nVar, fun);
        mt.imprimirTabla(tabla);

        while (!solOpt) {
            tabla = mt.genProxT(tabla);
            solOpt = mt.verificarSol(tabla[tabla.length - 1]);
            mt.imprimirTabla(tabla);
            System.out.println("");
        }

        System.out.println("solución optima: z = " + tabla[tabla.length - 1][tabla[0].length - 1]);

    }



}
