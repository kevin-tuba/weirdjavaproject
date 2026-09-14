import java.awt.*;
import javax.swing.*;
public class InfoText extends JFrame{   
    
    
    Database db; //database ref val

    int xBoxPos;
    JTextField nameField;//attributes
    JTextField xCartField;
    JTextField yCartField;
    JTextField massField;
    JTextField radField;
    // JTextField xPanField;
    // JTextField yPanField;
    // JTextField scaleField;
    JTextField xVelField;
    JTextField yVelField;
    JTextField xAccField;
    JTextField yAccField;
    JTextField orbitField;

    //boolean isEditable;

    public InfoText(Database dbRefVal){
        this.db = dbRefVal;
        this.xBoxPos = 1100;
        //this.isEditable = false;
        
        this.nameField = new JTextField();
        this.xCartField = new JTextField();
        this.yCartField = new JTextField();
        this.massField = new JTextField();
        this.radField = new JTextField();
        // this.xPanField = new JTextField();
        // this.yPanField = new JTextField();
        // this.scaleField = new JTextField();
        this.xVelField = new JTextField();
        this.yVelField = new JTextField();
        this.xAccField = new JTextField();
        this.yAccField = new JTextField();
        this.orbitField = new JTextField();
    }
    public void setTxt(int category, String text){
        try{
            switch (category){
                case 1:
                    nameField.setText(text);
                    break;
                case 2:
                    xCartField.setText(text);
                    break;
                case 3:
                    yCartField.setText(text);
                    break;
                case 4:
                    massField.setText(text);
                    break;
                case 5:
                    radField.setText(text);
                    break;
                case 6:
                    //xPanField.setText(text);
                    break;
                case 7:
                    //yPanField.setText(text);
                    break;
                case 8:
                    //scaleField.setText(text);
                    break;
                case 9:
                    xVelField.setText(text);
                    break;
                case 10:
                    yVelField.setText(text);
                    break;
                case 11:
                    xAccField.setText(text);
                    break;
                case 12:
                    yAccField.setText(text);
                    break;
                case 13:
                    orbitField.setText(text);
                    break;

            }
        }
        catch(Throwable t){
            nameField.setText("error");
        }
    }

    public void infoFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLocation(xBoxPos-50, 550);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setBackground(new Color(0, 0, 0, 0));

        

        //jlabels
        this.setLayout(null);
        this.add(nameField);
        this.add(xCartField);
        this.add(yCartField);
        this.add(massField);
        this.add(radField);
        // this.add(xPanField);
        // this.add(yPanField);
        // this.add(scaleField);
        this.add(xVelField);
        this.add(yVelField);
        this.add(xAccField);
        this.add(yAccField);
        this.add(orbitField);

        nameField.setForeground(Color.WHITE);
        xCartField.setForeground(Color.WHITE);
        yCartField.setForeground(Color.WHITE);
        massField.setForeground(Color.WHITE);
        radField.setForeground(Color.WHITE);
        // xPanField.setForeground(Color.WHITE);
        // yPanField.setForeground(Color.WHITE);
        // scaleField.setForeground(Color.WHITE);
        xVelField.setForeground(Color.WHITE);
        yVelField.setForeground(Color.WHITE);
        xAccField.setForeground(Color.WHITE);
        yAccField.setForeground(Color.WHITE);
        orbitField.setForeground(Color.WHITE);

        nameField.setOpaque(false); //set transparency
        xCartField.setOpaque(false);
        yCartField.setOpaque(false);
        massField.setOpaque(false);
        radField.setOpaque(false);
        // xPanField.setOpaque(false);
        // yPanField.setOpaque(false);
        // scaleField.setOpaque(false);
        xVelField.setOpaque(false);
        yVelField.setOpaque(false);
        xAccField.setOpaque(false);
        yAccField.setOpaque(false);
        orbitField.setOpaque(false);

        nameField.setEditable(false);
        xCartField.setEditable(false);
        yCartField.setEditable(false);
        massField.setEditable(false);
        radField.setEditable(false);
        // xPanField.setEditable(false);
        // yPanField.setEditable(false);
        // scaleField.setEditable(false);
        xVelField.setEditable(false);
        yVelField.setEditable(false);
        xAccField.setEditable(false);
        yAccField.setEditable(false);
        orbitField.setEditable(false);

        nameField.setBorder(BorderFactory.createEmptyBorder()); //removes borders
        xCartField.setBorder(BorderFactory.createEmptyBorder());
        yCartField.setBorder(BorderFactory.createEmptyBorder());
        massField.setBorder(BorderFactory.createEmptyBorder());
        radField.setBorder(BorderFactory.createEmptyBorder());
        // xPanField.setBorder(BorderFactory.createEmptyBorder());
        // yPanField.setBorder(BorderFactory.createEmptyBorder());
        // scaleField.setBorder(BorderFactory.createEmptyBorder());
        xVelField.setBorder(BorderFactory.createEmptyBorder());
        yVelField.setBorder(BorderFactory.createEmptyBorder());
        xAccField.setBorder(BorderFactory.createEmptyBorder());
        yAccField.setBorder(BorderFactory.createEmptyBorder());
        orbitField.setBorder(BorderFactory.createEmptyBorder());

        nameField.setFont(new Font("OCR A Extended", Font.PLAIN, 15)); //sets font
        xCartField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        yCartField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        massField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        radField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        // xPanField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        // yPanField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        // scaleField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        xVelField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        yVelField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        xAccField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        yAccField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        orbitField.setFont(new Font("OCR A Extended", Font.PLAIN, 15));

        nameField.setBounds(0, 0, 220, 20);
        xCartField.setBounds(0, 20, 220, 20);
        yCartField.setBounds(0, 40, 220, 20);
        massField.setBounds(0, 60, 220, 20);
        radField.setBounds(0, 80, 220, 20);
        // xPanField.setBounds(0, 100, 220, 20);
        // yPanField.setBounds(0, 120, 220, 20);
        // scaleField.setBounds(0, 140, 220, 20);
        xVelField.setBounds(0, 100, 220, 20);
        yVelField.setBounds(0, 120, 220, 20);
        xAccField.setBounds(0, 140, 220, 20);
        yAccField.setBounds(0, 160, 220, 20);
        orbitField.setBounds(0, 180, 220, 20);
        
        this.setSize(300,270);
        //this.setVisible(true);
    }

    public void toggleEdit(boolean mode){
        //if turning on editing mode (true)
        if (mode == true){
            nameField.setForeground(Color.BLACK);
            xCartField.setForeground(Color.BLACK);
            yCartField.setForeground(Color.BLACK);
            massField.setForeground(Color.BLACK);
            radField.setForeground(Color.BLACK);
            xVelField.setForeground(Color.BLACK);
            yVelField.setForeground(Color.BLACK);
            xAccField.setForeground(Color.BLACK);
            yAccField.setForeground(Color.BLACK);
            orbitField.setForeground(Color.BLACK);
            }
        else{
            nameField.setForeground(Color.WHITE);
            xCartField.setForeground(Color.WHITE);
            yCartField.setForeground(Color.WHITE);
            massField.setForeground(Color.WHITE);
            radField.setForeground(Color.WHITE);
            xVelField.setForeground(Color.WHITE);
            yVelField.setForeground(Color.WHITE);
            xAccField.setForeground(Color.WHITE);
            yAccField.setForeground(Color.WHITE);
            orbitField.setForeground(Color.WHITE);
        }
        

        nameField.setOpaque(mode); //set transparency
        xCartField.setOpaque(mode);
        yCartField.setOpaque(mode);
        massField.setOpaque(mode);
        radField.setOpaque(mode);
        xVelField.setOpaque(mode);
        yVelField.setOpaque(mode);
        xAccField.setOpaque(mode);
        yAccField.setOpaque(mode);
        orbitField.setOpaque(mode);

        nameField.setEditable(mode);
        xCartField.setEditable(mode);
        yCartField.setEditable(mode);
        massField.setEditable(mode);
        radField.setEditable(mode);
        xVelField.setEditable(mode);
        yVelField.setEditable(mode);
        xAccField.setEditable(mode);
        yAccField.setEditable(mode);
        orbitField.setEditable(mode);
    }
}
