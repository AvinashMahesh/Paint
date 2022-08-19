import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * @author Avinash Mahesh Purdue CS
 * @version 11/17/2021
 */

public class Paint extends JComponent implements Runnable {

    private Image image;
    private Graphics2D graphics2D;
    private int curX; // current mouse x coordinate
    private int curY; // current mouse y coordinate
    private int oldX; // previous mouse x coordinate
    private int oldY; // previous mouse y coordinate
    private Paint paint;

    JButton clearButton;
    JButton fillButton;
    JButton eraseButton;
    JButton randomButton;

    JTextField hexText;
    JButton hexButton;
    JTextField rText;
    JTextField gText;
    JTextField bText;
    JButton rgbButton;

    private Color background;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                paint.clear();
                hexText.setText("#");
                rText.setText("");
                gText.setText("");
                bText.setText("");
            }
            if (e.getSource() == fillButton) {
                paint.fill();
                hexText.setText("#");
                rText.setText("");
                gText.setText("");
                bText.setText("");
            }
            if (e.getSource() == eraseButton) {
                paint.erase();
            }
            if (e.getSource() == randomButton) {
                Random random = new Random();
                int redColor = random.nextInt(256);
                int greenColor = random.nextInt(256);
                int blueColor = random.nextInt(256);
                Color color = new Color(redColor, greenColor, blueColor);
                paint.random(color);
                hexText.setText(String.format("#%02x%02x%02x", redColor, greenColor, blueColor));
                rText.setText(Integer.toString(redColor));
                gText.setText(Integer.toString(greenColor));
                bText.setText(Integer.toString(blueColor));
            }
            if (e.getSource() == hexButton) {
                try {
                    Color hexColor = Color.decode(hexText.getText());
                    rText.setText(String.valueOf(hexColor.getRed()));
                    gText.setText(String.valueOf(hexColor.getGreen()));
                    bText.setText(String.valueOf(hexColor.getBlue()));
                    paint.hex(hexColor);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Not a valid RGB Value", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == rgbButton) {
                try {
                    int rC;
                    int gC;
                    int bC;
                    if (rText.getText() == null || rText.getText().equals("")) {
                        rC = 0;
                        rText.setText("0");
                    } else {
                        rC = Integer.parseInt(rText.getText());
                    }
                    if (gText.getText() == null || gText.getText().equals("")) {
                        gC = 0;
                        gText.setText("0");
                    } else {
                        gC = Integer.parseInt(gText.getText());
                    }
                    if (bText.getText() == null || bText.getText().equals("")) {
                        bC = 0;
                        bText.setText("0");
                    } else {
                        bC = Integer.parseInt(bText.getText());
                    }
                    Color rgbColor = new Color(rC, gC, bC);
                    hexText.setText(String.format("#%02x%02x%02x", rC, gC, bC));
                    paint.rgb(rgbColor);

                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, "Not a valid RGB Value", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    };
