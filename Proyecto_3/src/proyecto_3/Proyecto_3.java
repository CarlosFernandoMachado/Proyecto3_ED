/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author carlos
 */
public class Proyecto_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        ArrayList<Persona> personas = leerarchivo();
        
    }

    public static ArrayList<Persona> leerarchivo() {
        ArrayList<Persona> personas = new ArrayList();
        String cadena;
        FileReader fr = null;
        BufferedReader br = null;
        Persona persona1, persona2;
        boolean validar = true;
        try {
            fr = new FileReader("amigos.txt");
            br = new BufferedReader(fr);
            while ((cadena = br.readLine()) != null) {
                validar = true;
                String temp[] = cadena.split(",");
                if (personas.isEmpty()) {
                    persona1 = new Persona(temp[1]);
                    persona2 = new Persona(temp[0], persona1);
                    personas.add(persona2);
                    personas.add(persona1);
                } else {
                    for (int i = 0; i < personas.size(); i++) {
                        if (personas.get(i).getNombre().equals(temp[0])) {
                            for (int j = 0; j < personas.size(); j++) {
                                if (personas.get(j).getNombre().equals(temp[1])) {
                                    personas.get(i).NuevoAmigo(personas.get(j));
                                    validar = false;
                                    break;
                                }
                            }
                            if (!validar) {
                                break;
                            } else {
                                persona1 = new Persona(temp[1]);
                                personas.get(i).NuevoAmigo(persona1);
                                personas.add(persona1);
                                validar = false;
                                break;
                            }
                        }
                    }
                    if (validar) {
                        persona1 = new Persona(temp[0]);
                        for (int i = 0; i < personas.size(); i++) {
                            if (personas.get(i).getNombre().equals(temp[1])) {
                                validar = false;
                                persona1.NuevoAmigo(personas.get(i));
                                break;
                            }
                        }
                        if (validar) {
                            persona2 = new Persona(temp[1]);
                            persona1.NuevoAmigo(persona2);
                            personas.add(persona2);
                        }
                        personas.add(persona1);
                    }
                }
            }
        } catch (Exception e) {
        }
        try {
            br.close();
        } catch (Exception e) {
        }
        return personas;
    }
}
