import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        setTitle("Кластеризация по улучшенному методу k-средних");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JButton initialBtn = new JButton("Инициализация");
        initialBtn.setSize(100, 30);
        initialBtn.setLocation(5,10);
        panel.add(initialBtn);
        JButton clusterBtn = new JButton("Кластер");
        clusterBtn.setSize(100, 30);
        clusterBtn.setLocation(5,50);
        panel.add(clusterBtn);
        JButton cleanBtn = new JButton("Очистить");
        cleanBtn.setSize(100, 30);
        cleanBtn.setLocation(5,90);
        panel.add(cleanBtn);
        setContentPane(panel);
    }
}
