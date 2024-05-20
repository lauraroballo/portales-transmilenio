
package datos;
import javax.swing.*;

public class MatrizAdyacenciaFrame extends JFrame {
    private JTextArea textArea;

    public MatrizAdyacenciaFrame(String[][] matriz) {
        setTitle("Matriz de Adyacencia");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane);
        
        // Construir la representación de la matriz como un String
        StringBuilder sb = new StringBuilder();
        for (String[] fila : matriz) {
            for (String valor : fila) {
                sb.append(valor).append("\t");
            }
            sb.append("\n");
        }
        textArea.setText(sb.toString());
        
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }
}
