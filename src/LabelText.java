import java.awt.*;
import javax.swing.*;
public class LabelText extends JFrame{

    int xBoxPos;
    JLabel name;
    JLabel xCart;
    JLabel yCart;
    JLabel mass;
    JLabel rad;
    // JLabel xPan;
    // JLabel yPan;
    // JLabel scale;
    JLabel xVel;
    JLabel yVel;
    JLabel xAcc;
    JLabel yAcc;
    JLabel orbitCount;
    
    public LabelText(){
        this.xBoxPos = 1100;
        this.name = new JLabel("Name of Object: ");
        this.xCart = new JLabel("X: ");
        this.yCart = new JLabel("Y: ");
        this.mass = new JLabel("Mass: ");
        this.rad = new JLabel("Radius: ");
        // this.xPan = new JLabel("x-pan: ");
        // this.yPan = new JLabel("y-pan: ");
        // this.scale = new JLabel("scale: ");
        this.xVel = new JLabel("x-vel: ");
        this.yVel = new JLabel("y-vel: ");
        this.xAcc = new JLabel("x-acc: ");
        this.yAcc = new JLabel("y-acc: ");
        this.orbitCount = new JLabel("Trail Length: ");
        
    }

    public void labelFrame(){
        //create jframe
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLocation(xBoxPos-250, 550);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setSize(200,380);

        

        this.add(name);
        this.add(xCart);
        this.add(yCart);
        this.add(mass);
        this.add(rad);
        // this.add(xPan);
        // this.add(yPan);
        // this.add(scale);
        this.add(xVel);
        this.add(yVel);
        this.add(xAcc);
        this.add(yAcc);
        this.add(orbitCount);

        name.setForeground(Color.WHITE);
        xCart.setForeground(Color.WHITE);
        yCart.setForeground(Color.WHITE);
        mass.setForeground(Color.WHITE);
        rad.setForeground(Color.WHITE);
        // xPan.setForeground(Color.WHITE);
        // yPan.setForeground(Color.WHITE);
        // scale.setForeground(Color.WHITE);
        xVel.setForeground(Color.WHITE);
        yVel.setForeground(Color.WHITE);
        xAcc.setForeground(Color.WHITE);
        yAcc.setForeground(Color.WHITE);
        orbitCount.setForeground(Color.WHITE);

        name.setFont(new Font("OCR A Extended", Font.PLAIN, 15)); //sets font
        xCart.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        yCart.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        mass.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        rad.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        // xPan.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        // yPan.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        // scale.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        xVel.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        yVel.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        xAcc.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        yAcc.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        orbitCount.setFont(new Font("OCR A Extended", Font.PLAIN, 15));


        name.setBounds(0, 0, 200, 20);
        xCart.setBounds(0, 20, 200, 20);
        yCart.setBounds(0, 40, 200, 20);
        mass.setBounds(0, 60, 200, 20);
        rad.setBounds(0, 80, 200, 20);
        // xPan.setBounds(0, 100, 200, 20);
        // yPan.setBounds(0, 120, 200, 20);
        // scale.setBounds(0, 140, 200, 20);
        xVel.setBounds(0, 100, 200, 20);
        yVel.setBounds(0, 120, 200, 20);
        xAcc.setBounds(0, 140, 200, 20);
        yAcc.setBounds(0, 160, 200, 20);
        orbitCount.setBounds(0, 180, 200, 20);

        name.setHorizontalAlignment(SwingConstants.RIGHT);
        xCart.setHorizontalAlignment(SwingConstants.RIGHT);
        yCart.setHorizontalAlignment(SwingConstants.RIGHT);
        mass.setHorizontalAlignment(SwingConstants.RIGHT);
        rad.setHorizontalAlignment(SwingConstants.RIGHT);
        // xPan.setHorizontalAlignment(SwingConstants.RIGHT);
        // yPan.setHorizontalAlignment(SwingConstants.RIGHT);
        // scale.setHorizontalAlignment(SwingConstants.RIGHT);
        xVel.setHorizontalAlignment(SwingConstants.RIGHT);
        yVel.setHorizontalAlignment(SwingConstants.RIGHT);
        xAcc.setHorizontalAlignment(SwingConstants.RIGHT);
        yAcc.setHorizontalAlignment(SwingConstants.RIGHT);
        orbitCount.setHorizontalAlignment(SwingConstants.RIGHT);

        
        //this.setVisible(true);
    }
    

}
