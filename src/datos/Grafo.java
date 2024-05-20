package datos;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Nodo {
    String nombre;
    int x, y;

    public Nodo(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }
}

class Arista {
    Nodo origen, destino;
    double peso;

    public Arista(Nodo origen, Nodo destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
}

public class Grafo extends JPanel {
    private List<Nodo> nodos;
    private List<Arista> aristas;

    public Grafo() {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarNodo(String nombre, int x, int y) {
        nodos.add(new Nodo(nombre, x, y));
        repaint();
    }

    public void agregarArista(String nombreOrigen, String nombreDestino, double peso) {
        Nodo origen = encontrarNodo(nombreOrigen);
        Nodo destino = encontrarNodo(nombreDestino);
        if (origen != null && destino != null) {
            aristas.add(new Arista(origen, destino, peso));
            repaint();
        }
    }

    private Nodo encontrarNodo(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.nombre.equals(nombre)) {
                return nodo;
            }
        }
        return null;
    }

    public void mostrarMatrizAdyacencia() {
      
        int n = nodos.size();
        double[][] matriz = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (Arista arista : aristas) {
            int i = nodos.indexOf(arista.origen);
            int j = nodos.indexOf(arista.destino);
            matriz[i][j] = arista.peso;
        }

        // Convertir matriz a String
        String[][] matrizStr = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == Double.POSITIVE_INFINITY) {
                    matrizStr[i][j] = "0";
                } else {
                    matrizStr[i][j] = String.valueOf(matriz[i][j]);
                }
            }
        }

        new MatrizAdyacenciaFrame(matrizStr);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        // Dibujar aristas
        for (Arista arista : aristas) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine(arista.origen.x, arista.origen.y, arista.destino.x, arista.destino.y);
            // Dibujar peso de la arista
            int midX = (arista.origen.x + arista.destino.x) / 2;
            int midY = (arista.origen.y + arista.destino.y) / 2;
            g2d.drawString(String.valueOf(arista.peso), midX, midY);
        }

        // Dibujar nodos
        for (Nodo nodo : nodos) {
            g2d.setColor(Color.RED);
            g2d.fillOval(nodo.x - 10, nodo.y - 10, 20, 20);
            g2d.setColor(Color.BLACK);
            g2d.drawString(nodo.nombre, nodo.x - 20, nodo.y - 15);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Grafo de Bogotá");
        Grafo grafo = new Grafo();

       
        grafo.agregarNodo("Portal norte", 100, 250);  
        grafo.agregarNodo("Portal suba", 190, 600);   
        grafo.agregarNodo("Portal 80", 400, 700);
        grafo.agregarNodo("Portal dorado", 700, 780);
        grafo.agregarNodo("Portal Américas", 1000, 700);
        grafo.agregarNodo("Portal sur", 1080, 610); 
        grafo.agregarNodo("Portal tunal", 1300, 500);
        grafo.agregarNodo("Portal Usme", 1400, 400);
        grafo.agregarNodo("Calle 100", 400, 250);   
        grafo.agregarNodo("Calle 63", 600, 250);  
        grafo.agregarNodo("Av Jimenez", 900, 250);
        grafo.agregarNodo("Ricaurte", 950, 450);
        grafo.agregarNodo("Bicentenario", 960, 100);
        grafo.agregarNodo("Santa Lucia", 1200, 400);
        grafo.agregarNodo("Portal 20 de julio", 1200, 100);
        grafo.agregarNodo("Ruta umb", 580, 95);
        grafo.agregarNodo("Universidad manuela beltran", 540, 25);
        grafo.agregarNodo("Escuela militar", 530, 500);
        
        
        
        grafo.agregarArista("Portal 80", "Escuela militar", 7.8);
        
        grafo.agregarArista("Escuela militar", "Calle 100", 6.3);
        
        grafo.agregarArista("Portal norte", "Calle 100", 11.3); 
        
        grafo.agregarArista("Escuela militar", "Portal suba", 10.6);

        grafo.agregarArista("Calle 100", "Av Jimenez", 9.9);

        grafo.agregarArista("Av Jimenez", "Portal dorado", 11.4);
        grafo.agregarArista("Escuela militar", "Ricaurte", 9.2);
        grafo.agregarArista("Ricaurte", "Portal Américas", 11.3);

        grafo.agregarArista("Ricaurte", "Portal sur", 9.3);

        grafo.agregarArista("Santa Lucia", "Portal tunal", 1.9);
 
        grafo.agregarArista("Santa Lucia", "Portal Usme", 10.1);

        grafo.agregarArista("Av Jimenez", "Santa Lucia", 10.0);

        grafo.agregarArista("Ricaurte", "Bicentenario", 3.3);

        grafo.agregarArista("Bicentenario", "Portal 20 de julio", 4.6);

        grafo.agregarArista("Calle 100", "Calle 63", 4.4);
 
        grafo.agregarArista("Calle 63", "Ruta umb", 2.7);

        grafo.agregarArista("Ruta umb", "Universidad manuela beltran", 1.1);



   grafo.mostrarMatrizAdyacencia();

    frame.setLayout(new BorderLayout());
    frame.add(grafo, BorderLayout.CENTER);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    
    
}
}