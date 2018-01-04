import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Program {
    private MyFrame myFrame;
    private DrawPanel drawPanel;
    private JPanel jPanel;
    private ArrayList<Point2D> listOfPoint;
    private int countOfClusters;
    private ArrayList<Point2D> listCentersOfCluster;
    private boolean isDrawing = true;
    private int countOfDrawedPoints;
    private boolean isClustered;

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

        JButton initializeButton = new JButton("Инициализировать");
        initializeButton.setLocation(200, 5);
        initializeButton.setSize(145, 20);
        jPanel.add(initializeButton);

        drawPanel = new DrawPanel();
        drawPanel.setSize(1, 1);

        initializeButton.addActionListener(actionEvent -> {
            try {
                countOfClusters = Integer.parseInt(countOfClustersField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Введите количество центров для кластера",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }


            listCentersOfCluster = new ArrayList<>();
            isDrawing = false;
        });

        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (countOfDrawedPoints < countOfClusters && !isClustered) {
                    listCentersOfCluster.add(e.getPoint());
                    Graphics point = drawPanel.getGraphics();
                    point.setColor(Color.red);
                    point.fillOval(e.getX(), e.getY(), 10, 10);
                    countOfDrawedPoints++;
                }
            }
        });

        JButton clusterButton = new JButton("Кластер");
        clusterButton.setLocation(355, 5);
        clusterButton.setSize(120, 20);
        jPanel.add(clusterButton);
        clusterButton.addActionListener(actionEvent -> {
            // TODO: написать функцию кластеризации точек

        });

        JButton cleanButton = new JButton("Очистить");
        cleanButton.setLocation(485, 5);
        cleanButton.setSize(120, 20);
        jPanel.add(cleanButton);
        cleanButton.addActionListener(actionEvent -> {
            drawPanel.repaint();
            isClustered = false;
            isDrawing = true;
            listCentersOfCluster = new ArrayList<>();
            listOfPoint = new ArrayList<>();
            countOfDrawedPoints = 0;
            countOfClusters = 0;
        });
        myFrame.setContentPane(jPanel);

        listOfPoint = new ArrayList<>();
        drawPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isDrawing) {
                        listOfPoint.add(e.getPoint());
                        Graphics point = drawPanel.getGraphics();
                        point.setColor(Color.black);
                        point.fillOval(e.getX(), e.getY(), 5, 5);
                    }
                }
            });
        jPanel.add(drawPanel);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Program::new);
    }
}
