package graph;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class TrigonometricGraph extends JFrame {
    // ширина і висота вікна програми
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    public JLabel label;

    public TrigonometricGraph() {
        // налаштування вікна
        setTitle("Графік функції");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // налаштування підпису
        label = new JLabel("Графік тригонометричної функції y = (sin(x)*sin(2x))/2");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0, 10, WIDTH, 30); // позиціонування та розмір підпису

        add(label);

        // створюємо панель з графіком
        GraphPanel graphPanel = new GraphPanel();
        // додаємо панель на вікно
        add(graphPanel);
    }

}

class GraphPanel extends JPanel {
    private static final int POINTS = 1000; // кількість точок
    // масштаб
    private static final double SCALE_X = 60.0;
    private static final double SCALE_Y = 100.0;
    private static final int PADDING = 50; // відступ

    // перевизначаємо метод paintComponent для малювання графіку
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // очищаємо вікно
        // визначаємо розмір області для малювання
        int width = getWidth() - 2 * PADDING;
        int height = getHeight() - 2 * PADDING;
        // малюємо прямокутник, у якому буде знаходитись графік
        g.drawRect(PADDING, PADDING, width, height);

        // створюємо об'єкт Graphics2D для малювання графіку
        Graphics2D g2d = (Graphics2D) g;
        // встановлюємо область, в якій можна малювати графік.
        g2d.setClip(PADDING, PADDING, width, height);
        g2d.setColor(java.awt.Color.BLUE);

        // обчислюємо координати центру панелі
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // малюємо вісі OX та OY
        g2d.drawLine(PADDING, centerY, getWidth() - PADDING, centerY);
        g2d.drawLine(centerX, PADDING, centerX, getHeight() - PADDING);

        Path2D path = new Path2D.Double(); // створення шляху, що буде лінією графіку

        // обчислюємо точок графіку та додаємо їх до шляху (тобто створюємо лінію графіку)
        for (int i = 0; i < POINTS; i++) {
            // обчислення координат точки
            double x = (i - POINTS / 2) / SCALE_X;
            double y = function(x);

            // вираховуємо координати відносто області малювання
            int pixelX = (int) (centerX + x * SCALE_X);
            int pixelY = (int) (centerY - y * SCALE_Y);

            /* якщо це перша точка (i == 0), то використовується метод moveTo
            для переміщення поточної точки без з'єднання лінією з попередньою */
            if (i == 0) {
                path.moveTo(pixelX, pixelY);
            } /* в іншому випадку, використовується метод lineTo
            для з'єднання попередньої точки з поточною */
            else {
                path.lineTo(pixelX, pixelY);
            }
        }

        // відображаємо графік
        g2d.draw(path);
    }

    // Функція: y = (sin(x)*sin(2x))/2
    private double function(double x) {
        return (Math.sin(x)*Math.sin(2*x))/2 ;
    }
}

