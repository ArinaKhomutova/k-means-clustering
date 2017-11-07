import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class DrawPanel extends JPanel {

    public void paintComponent(Graphics g) {
        BufferedImage imag = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D d2 = (Graphics2D) imag.createGraphics();
        d2.setColor(Color.white);
        d2.fillRect(0, 0, this.getWidth(), this.getHeight());
        super.paintComponent(g);
        g.drawImage(imag, 0, 0, this);
        this.setBounds(50,45,500,260);
    }
}