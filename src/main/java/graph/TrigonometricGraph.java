package graph;
import javax.swing.*;
import java.awt.*;

public class TrigonometricGraph extends JFrame {
    // Розміри вікна
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    // Підпис для графіку
    public JLabel label;

    public TrigonometricGraph() {
        // Налаштування вікна
        setTitle("Графік функції");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Налаштування підпису
        label = new JLabel("Графік тригонометричної функції y = (sin(x)*sin(2x))/2");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0, 10, WIDTH, 30);

        // Додавання підпису до вікна
        add(label);

        // Створення панелі з графіком
        GraphPanel graphPanel = new GraphPanel();
        // Додавання панелі до вікна
        add(graphPanel);
    }

    // Метод main для запуску програми
    public static void main(String[] args) {
        // Створення та відображення вікна у потоці з подіями Swing
        SwingUtilities.invokeLater(() -> new TrigonometricGraph().setVisible(true));
    }
}

// Клас для малювання графіку
class GraphPanel extends JPanel {
    private static final int POINTS = 1000; // Кількість точок
    // Масштаб для вісі x і y
    private static final double SCALE_X = 60.0;
    private static final double SCALE_Y = 100.0;
    private static final int PADDING = 50; // Відступ для побудови графіку

    // Перевизначений метод малювання компонента
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        // Встановлення розміру області для малювання
        int width = getWidth() - 2 * PADDING;
        int height = getHeight() - 2 * PADDING;
        // Малювання прямокутника, який буде областю графіку
        g.drawRect(PADDING, PADDING, width, height);

        // Конвертуємо базовий клас Graphics у Graphics2D для отримання доступу до додаткових функцій малювання
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
        // Встановлюємо обмеження області малювання, щоб графік не виходив за межі панелі
        g2d.setClip(PADDING, PADDING, width, height);
        // Встановлюємо синій колір для графіку
        g2d.setColor(java.awt.Color.BLUE);

        // Обчислення центру панелі для побудови графіку
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Відображення осей OX та OY
        g2d.drawLine(PADDING, centerY, getWidth() - PADDING, centerY);
        g2d.drawLine(centerX, PADDING, centerX, getHeight() - PADDING);

        java.awt.geom.Path2D path = new java.awt.geom.Path2D.Double(); // Створення об'єкту для шляху

        // Обчислення точок графіку та додавання до шляху
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

        // Відображення графіку
        g2d.draw(path);
    }

    // Функція: y = (sin(x)*sin(2x))/2
    private double function(double x) {
        return (Math.sin(x)*Math.sin(2*x))/2 ;
    }
}

