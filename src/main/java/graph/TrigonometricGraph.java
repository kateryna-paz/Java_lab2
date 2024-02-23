package graph;
import javax.swing.*;
import java.awt.*;

public class TrigonometricGraph extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    public JLabel label;

    public TrigonometricGraph() {
        setTitle("Графік функції");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // налаштування підпису
        label = new JLabel("Графік тригонометричної функції y = (sin(x)*sin(2x))/2");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0, 10, WIDTH, 30);

        add(label);

        // створення панелі з графіком
        GraphPanel graphPanel = new GraphPanel();
        add(graphPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrigonometricGraph().setVisible(true));
    }
}

class GraphPanel extends JPanel {
    private static final int POINTS = 1000; // кількість точок
    // масштаб
    private static final double SCALE_X = 60.0;
    private static final double SCALE_Y = 100.0;
    private static final int PADDING = 50; // відступ

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        // встановлення розміру області для малювання
        int width = getWidth() - 2 * PADDING;
        int height = getHeight() - 2 * PADDING;
        g.drawRect(PADDING, PADDING, width, height);

        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
        g2d.setClip(PADDING, PADDING, width, height);
        g2d.setColor(java.awt.Color.BLUE);

        // обчислення центру панелі для графіку
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Вісі OX та OY
        g2d.drawLine(PADDING, centerY, getWidth() - PADDING, centerY);
        g2d.drawLine(centerX, PADDING, centerX, getHeight() - PADDING);

        java.awt.geom.Path2D path = new java.awt.geom.Path2D.Double(); // створення шляху

        // обчислення точок графіку та додавання до шляху
        for (int i = 0; i < POINTS; i++) {
            double x = (i - POINTS / 2) / SCALE_X;
            double y = function(x);

            int pixelX = (int) (centerX + x * SCALE_X);
            int pixelY = (int) (centerY - y * SCALE_Y);

            if (i == 0) {
                path.moveTo(pixelX, pixelY);
            } else {
                path.lineTo(pixelX, pixelY);
            }
        }

        // відображення графіку
        g2d.draw(path);
    }

    // Функція: y = (sin(x)*sin(2x))/2
    private double function(double x) {
        return (Math.sin(x)*Math.sin(2*x))/2 ;
    }
}

