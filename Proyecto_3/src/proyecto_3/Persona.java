/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_3;

import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class Persona {
    String nombre;
    ArrayList<Persona> amigos = new ArrayList();

    public Persona(String nombre,Persona amigo) {
        this.nombre = nombre;
        amigos.add(amigo);
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }
    

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Persona> getAmigos() {
        return amigos;
    }
    public void NuevoAmigo(Persona amigo){
        amigos.add(amigo);
    }
    public void MisAmigos(){
        System.out.print(nombre + " Tiene de amigo/a a: ");
        for (int i = 0; i < amigos.size(); i++) {
            if (i != amigos.size()-1) {
                System.out.print(amigos.get(i).getNombre() + ",");
            }else{
                System.out.print(amigos.get(i).getNombre());
            }
        }
        System.out.println("");
    }
}
