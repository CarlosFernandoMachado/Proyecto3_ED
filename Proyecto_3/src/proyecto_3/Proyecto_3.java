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
import org.graphstream.algorithm.AStar;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.MultiGraph;

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
        boolean validar = false;
        String nombre1 = "", nombre2 = "";
        Graph graph = new MultiGraph("Amigos");
        for (int i = 0; i < personas.size(); i++) {
            graph.addNode(personas.get(i).getNombre());
        }
        for (int i = 0; i < personas.size(); i++) {
            for (int j = 0; j < personas.get(i).getAmigos().size(); j++) {
                graph.addEdge(personas.get(i).getNombre() + personas.get(i).getAmigos().get(j).getNombre(), personas.get(i).getNombre(), personas.get(i).getAmigos().get(j).getNombre());
            }
        }
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
        int opcion = 0;
        System.out.println("Se recomienda al usuario que al momento de visualizar la grafica se mantenga la pestaña abierta\nYa que esta se va ir actualizando con lo que vaya haciendo\npuede poner el programa a un lado\n y la grafica al otro");
        while (opcion != 4) {
            System.out.print("1) Visualizar grafo\n2) Ver si dos personas son amigos\n3) Conectar a otra persona mediante amigos\n4) Salir\nOpcion: ");
            opcion = sc.nextInt();
            if (opcion == 1) {
                graph.display();
            } else if (opcion == 2) {
                while (!validar) {
                    System.out.print("Ingrese el nombre de la primera persona: ");
                    nombre1 = sc.next();
                    for (int i = 0; i < personas.size(); i++) {
                        if (personas.get(i).getNombre().equals(nombre1)) {
                            validar = true;
                            break;
                        }
                    }
                }
                validar = false;
                while (!validar) {
                    System.out.print("Ingrese el nombre de la segunda persona: ");
                    nombre2 = sc.next();
                    for (int i = 0; i < personas.size(); i++) {
                        if (personas.get(i).getNombre().equals(nombre2)) {
                            validar = true;
                            break;
                        }
                    }
                }
                validar = false;
                if (graph.getNode(nombre1).hasEdgeBetween(nombre2)) {
                    System.out.println(nombre1 + " y " + nombre2 + " son amigos!");
                } else {
                    System.out.println(nombre1 + " y " + nombre2 + " no son amigos!");
                }
            } else if (opcion == 3) {
                while (!validar) {
                    System.out.print("Ingrese el nombre de la primera persona: ");
                    nombre1 = sc.next();
                    for (int i = 0; i < personas.size(); i++) {
                        if (personas.get(i).getNombre().equals(nombre1)) {
                            validar = true;
                            break;
                        }
                    }
                }
                validar = false;
                while (!validar) {
                    System.out.print("Ingrese el nombre de la segunda persona: ");
                    nombre2 = sc.next();
                    for (int i = 0; i < personas.size(); i++) {
                        if (personas.get(i).getNombre().equals(nombre2)) {
                            validar = true;
                            break;
                        }
                    }
                }
                validar = false;
                AStar astar = new AStar(graph);
                astar.compute(nombre1, nombre2);
                Path path = astar.getShortestPath();
                if (path != null) {
                    System.out.println(path);
                    System.out.println("Desea presentarlos?si/no");
                    String ans = sc.next();
                    if (ans.equals("si") || ans.equals("Si") || ans.equals("sI") || ans.equals("SI")) {
                        graph.addEdge(nombre1 + nombre2, nombre1, nombre2);
                    }
                } else {
                    System.out.println(nombre1 + " y " + nombre2 + " no se pueden llegar a conocer ya que no tienen amigos en comun");
                }
            } else {
                System.out.println("para salir cierre la pestaña de la grafica porfavor");
            }
        }
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
