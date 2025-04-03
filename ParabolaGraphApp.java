// ParabolaGraphApp.java
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class ParabolaGraphApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Parabola Graph: y = x^2/2 - 2");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            JPanel graphPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    int width = getWidth();
                    int height = getHeight();
                    int axisPadding = 50;
                    double scale = 50.0;

                    // Draw Axes
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(axisPadding, height / 2, width - axisPadding, height / 2);
                    g2d.drawLine(width / 2, axisPadding, width / 2, height - axisPadding);

                    g2d.drawString("X", width - axisPadding - 20, height / 2 + 15);
                    g2d.drawString("Y", width / 2 - 15, axisPadding + 20);

                    for (int i = -5; i <= 5; i++) {
                        int xTick = width / 2 + (int) (i * scale);
                        g2d.drawLine(xTick, height / 2 - 5, xTick, height / 2 + 5);

                        int yTick = height / 2 - (int) (i * scale);
                        g2d.drawLine(width / 2 - 5, yTick, width / 2 + 5, yTick);
                    }

                    // Draw Parabola
                    g2d.setColor(Color.BLUE);
                    g2d.setStroke(new BasicStroke(3));

                    Path2D parabola = new Path2D.Double();
                    boolean firstPoint = true;

                    for (double x = -10; x <= 10; x += 0.01) {
                        double y = (x * x) / 2 - 2;
                        double screenX = width / 2 + x * scale;
                        double screenY = height / 2 - y * scale;

                        if (firstPoint) {
                            parabola.moveTo(screenX, screenY);
                            firstPoint = false;
                        } else {
                            parabola.lineTo(screenX, screenY);
                        }
                    }

                    g2d.draw(parabola);
                }
            };

            frame.setContentPane(graphPanel);
            frame.setVisible(true);
        });
    }
}