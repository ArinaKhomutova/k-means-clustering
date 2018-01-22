import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Program {
    private MyFrame myFrame;
    private DrawPanel drawPanel;
    private JPanel jPanel;
    private boolean isDrawing = true;
    private int countOfClusters;
    private KMeans kMeans = new KMeans();


    public Program() {
        myFrame = new MyFrame("Кластеризация по улучшенному методу k-средних");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(650, 360);
        myFrame.setVisible(true);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        JLabel label = new JLabel("Количество кластеров");
        label.setLocation(5, 5);
        label.setSize(140, 20);
        jPanel.add(label);
        final JTextField countOfClustersField = new JFormattedTextField();
        countOfClustersField.setLocation(155, 5);
        countOfClustersField.setSize(40, 20);
        jPanel.add(countOfClustersField);

        drawPanel = new DrawPanel();
        drawPanel.setSize(1, 1);


        JButton clusterButton = new JButton("Кластер");
        clusterButton.setLocation(355, 5);
        clusterButton.setSize(120, 20);
        jPanel.add(clusterButton);
        clusterButton.addActionListener(actionEvent -> {
            try {
                countOfClusters = Integer.parseInt(countOfClustersField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Введите количество кластеров",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            if (countOfClusters > kMeans.getQuantityPoints()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Количество кластеров превышает количество точек.",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            buttonCluster(countOfClusters);

            isDrawing = false;
        });

        JButton cleanButton = new JButton("Очистить");
        cleanButton.setLocation(485, 5);
        cleanButton.setSize(120, 20);
        jPanel.add(cleanButton);
        cleanButton.addActionListener(actionEvent -> {
            drawPanel.repaint();
            isDrawing = true;
            kMeans.clearKMeans();
        });
        myFrame.setContentPane(jPanel);

        drawPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isDrawing) {
                        CustomPoint customPoint = new CustomPoint(e.getX(), e.getY());
                        drawPoint(customPoint, drawPanel.getGraphics(), Color.black);
                        kMeans.addPoint(customPoint);
                    }
                }
            });
        jPanel.add(drawPanel);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Program::new);
    }

    private void buttonCluster(int quantity) {
        kMeans.start(quantity);

        Graphics graphics = drawPanel.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());

        for (Cluster cluster : kMeans.getClusters()) {
            drawPoint(cluster.getCurrentCenter(), graphics, Color.green);
            drawPoints(cluster.getClusterPoints(), graphics, Color.black);
        }
    }

    private void drawPoints(List<CustomPoint> points, Graphics graphics, Color color) {
        for (CustomPoint point : points) {
            drawPoint(point, graphics, color);
        }
    }

    private void drawPoint(CustomPoint customPoint, Graphics graphics, Color color) {
        int circleSize = 10;
        graphics.setColor(color);
        graphics.fillOval(customPoint.getX(), customPoint.getY(), circleSize, circleSize);
    }
}
