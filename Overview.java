import java.awt.*;
import javax.swing.*;
public class Overview extends JPanel{   
    
    
    Database db; //database ref val

    JLabel numObjects;
    JLabel scale;
    JLabel xPan;
    JLabel yPan;
    JLabel speed;

    //boolean isEditable;

    public Overview(Database dbRefVal){
        db = dbRefVal;
        numObjects = new JLabel("No. of Objects: ");
        scale = new JLabel("Scale: ");
        xPan = new JLabel("x-pan: ");
        yPan = new JLabel("y-pan: ");
        speed = new JLabel("Simulation Speed: ");
        frame();
    }
    public void update_o(int objRef, double sc, double xRef, double yRef, int speedRef){
        numObjects.setText("No. of Objects: " + objRef);        
        scale.setText("Scale: " + Math.round(sc * 100.0) / 100.0 + "x");    
        xPan.setText("x-pan: " + xRef);    
        yPan.setText("y-pan: " + yRef);    
        speed.setText("Simulation Speed: " + (110-speedRef)/10 + "x");
    }

    public void frame(){
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLocation(40, 660);
     //   this.setUndecorated(true);
      //  this.setResizable(false);
      //  this.setAlwaysOnTop(true);
        this.setBackground(new Color(0, 0, 0, 0));

        

        //jlabels
        this.setLayout(null);
        this.add(numObjects);
        this.add(scale);
        this.add(xPan);
        this.add(yPan);
        this.add(speed);

        numObjects.setForeground(Color.WHITE);
        scale.setForeground(Color.WHITE);
        xPan.setForeground(Color.WHITE);
        yPan.setForeground(Color.WHITE);
        speed.setForeground(Color.WHITE);

        numObjects.setOpaque(false); //set transparency
        scale.setOpaque(false);
        xPan.setOpaque(false);
        yPan.setOpaque(false);
        speed.setOpaque(false);

        numObjects.setFont(new Font("OCR A Extended", Font.PLAIN, 15)); //sets font
        scale.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        xPan.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        yPan.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        speed.setFont(new Font("OCR A Extended", Font.PLAIN, 15));

        numObjects.setBounds(0, 0, 200, 20);
        speed.setBounds(0, 20, 200, 20);
        scale.setBounds(0, 40, 200, 20);
        xPan.setBounds(0, 60, 200, 20);
        yPan.setBounds(0, 80, 200, 20);
        

        // numObjects.setHorizontalAlignment(SwingConstants.RIGHT);
        // scale.setHorizontalAlignment(SwingConstants.RIGHT);
        // xPan.setHorizontalAlignment(SwingConstants.RIGHT);
        // yPan.setHorizontalAlignment(SwingConstants.RIGHT);
        // speed.setHorizontalAlignment(SwingConstants.RIGHT);
        
        this.setSize(300,250);
        this.setVisible(true);
    }
}
