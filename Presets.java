import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Presets extends JPanel {
    JButton solSysButton;
    JButton threeBodyButton;
    JButton earthButton;
    JButton save;
    JButton load;
    Database db;
    SimulationPanel panel;
    //Objects obj;
    

    public Presets(Database dbRef, SimulationPanel pRef) {
        db = dbRef;
        panel = pRef;

        solSysButton = new JButton("Preset: Solar System");

        solSysButton.addActionListener((ActionEvent a) -> {
            panel.scale = 1;
            panel.currentXPan = 0;
            panel.currentYPan = 0;
            panel.netXPan = 0;
            panel.netYPan = 0;
            sys();
            panel.requestFocus();
        });

        threeBodyButton = new JButton("Preset: 3-Body Problem");

        threeBodyButton.addActionListener((ActionEvent b) -> {
            panel.scale = 1;
            panel.currentXPan = 0;
            panel.currentYPan = 0;
            panel.netXPan = 0;
            panel.netYPan = 0;
            threeBod();
            panel.requestFocus();
        });

        earthButton = new JButton("Preset: Earth and Moon");

        earthButton.addActionListener((ActionEvent c) -> {
            panel.scale = 1;
            panel.currentXPan = 0;
            panel.currentYPan = 0;
            panel.netXPan = 0;
            panel.netYPan = 0;
            earth();
            panel.requestFocus();
        });

        save = new JButton("SAVE");

        save.addActionListener((ActionEvent d) -> {
            saveContents();
            panel.requestFocus();
        });

        load = new JButton("LOAD");

        load.addActionListener((ActionEvent e) -> {
            loadContents();
            panel.requestFocus();
        });

        

        buttonFrame();

    }

    public void buttonFrame() {
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(1030, 30);
      //  this.setUndecorated(true);
     //   this.setResizable(false);
    //    this.setAlwaysOnTop(true);
        this.setBackground(new Color(0, 0, 0, 0));
        //this.setOpacity(0);
        solSysButton.setFocusPainted(false);
        solSysButton.setBackground(new Color(0,0,0));
        solSysButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        solSysButton.setForeground(Color.WHITE);
        solSysButton.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(solSysButton);

        threeBodyButton.setFocusPainted(false);
        threeBodyButton.setBackground(new Color(0,0,0));
        threeBodyButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        threeBodyButton.setForeground(Color.WHITE);
        threeBodyButton.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(threeBodyButton);

        earthButton.setFocusPainted(false);
        earthButton.setBackground(new Color(0,0,0));
        earthButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        earthButton.setForeground(Color.WHITE);
        earthButton.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(earthButton);

        save.setFocusPainted(false);
        save.setBackground(new Color(0,0,0));
        save.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        save.setForeground(Color.WHITE);
        save.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(save);

        load.setFocusPainted(false);
        load.setBackground(new Color(0,0,0));
        load.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        load.setForeground(Color.WHITE);
        load.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(load);

        this.setSize(240, 240);
        this.setLayout(null);

        solSysButton.setBounds(0, 0, 230, 40);
        threeBodyButton.setBounds(0, 50, 230, 40);
        earthButton.setBounds(0, 100, 230, 40);
        save.setBounds(150, 150, 80, 40);
        load.setBounds(150, 200, 80, 40);
        
        //save.setHorizontalAlignment(SwingConstants.RIGHT);
        //load.setHorizontalAlignment(SwingConstants.RIGHT);

        this.setVisible(true);
        
    }

    public void sys() {
        for (int i = 0; i<db.dbSize(); i++){
            if (db.parseDB(i).infoText.isVisible()){
                //db.parseDB(i).labelText.setVisible(false);
                db.parseDB(i).infoText.setVisible(false);
            }
        }
        db.celestialObjects.clear();

        db.addObj(new CelestialObjects(
            panel.vc.xConvert(0,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            130*panel.scale, 
            2E16, 
            "Sun",
            0,
            0,
            0,
            0,
            Color.YELLOW,
            db,
            100
        ));

        db.parseDB(0).infoText.setVisible(true);
        //db.parseDB(0).labelText.setVisible(true);
        db.parseDB(0).updateText_e();
        db.parseDB(0).updateText_p(panel.netXPan, panel.netYPan);

        db.addObj(new CelestialObjects(panel.vc.xConvert(400,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            7*panel.scale, 
            3.3E9, 
            "Mercury",
            0,
            56,
            0,
            0,
            Color.GRAY,
            db,
            45
        ));

        db.addObj(new CelestialObjects(panel.vc.xConvert(900,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            20*panel.scale, 
            5E10, 
            "Venus",
            0,
            35,
            0,
            0,
            Color.ORANGE,
            db,
            120
        ));

        db.addObj(new CelestialObjects(panel.vc.xConvert(1200,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            20*panel.scale, 
            6E10, 
            "Earth",
            0,
            33,
            0,
            0,
            Color.BLUE,
            db,
            220
        ));

        db.addObj(new CelestialObjects(
            panel.vc.xConvert(1700,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            10*panel.scale, 
            6.4E9, 
            "Mars",
            0,
            28,
            0,
            0,
            Color.RED,
            db,
            390
        ));

        db.addObj(new CelestialObjects(
            panel.vc.xConvert(3300,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            50*panel.scale, 
            2E13, 
            "Jupiter",
            0,
            20,
            0,
            0,
            new Color(250,120,20),
            db,
            1010
        ));

        db.addObj(new CelestialObjects(
            panel.vc.xConvert(4900,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            45*panel.scale, 
            5.7E12, 
            "Saturn",
            0,
            17,
            0,
            0,
            new Color(250,230,120),
            db,
            2050
        ));

        db.addObj(new CelestialObjects(
            panel.vc.xConvert(6600,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            40*panel.scale, 
            8.7E11, 
            "Uranus",
            0,
            15,
            0,
            0,
            Color.CYAN,
            db,
            3500
        ));

        db.addObj(new CelestialObjects(
            panel.vc.xConvert(8700,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            40*panel.scale, 
            1E12, 
            "Neptune",
            0,
            13,
            0,
            0,
            new Color(150,0,200),
            db,
            6000
        ));

        panel.repaint();
        
        
    }

    public void threeBod() {
        for (int i = 0; i<db.dbSize(); i++){
            if (db.parseDB(i).infoText.isVisible()){
                //db.parseDB(i).labelText.setVisible(false);
                db.parseDB(i).infoText.setVisible(false);
            }
        }
        db.celestialObjects.clear();

        db.addObj(new CelestialObjects(
            panel.vc.xConvert(0,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(200,panel.h,panel.scale,0, false), 
            25*panel.scale, 
            1E14, 
            "Object A",
            -5,
            0,
            0,
            0,
            Color.RED,
            db,
            500
        ));

        db.parseDB(0).infoText.setVisible(true);
        //db.parseDB(0).labelText.setVisible(true);
        db.parseDB(0).updateText_e();
        db.parseDB(0).updateText_p(panel.netXPan, panel.netYPan);

        db.addObj(new CelestialObjects(
            panel.vc.xConvert(173,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(-100,panel.h,panel.scale,0, false), 
            25*panel.scale, 
            1E14, 
            "Object B",
            0,
            3,
            0,
            0,
            Color.GREEN,
            db,
            500
        ));

        db.addObj(new CelestialObjects(
            panel.vc.xConvert(-173,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(-100,panel.h,panel.scale,0, false), 
            25*panel.scale, 
            1E14, 
            "Object C",
            5,
            0,
            0,
            0,
            Color.BLUE,
            db,
            500
        ));

        panel.repaint();
        
        
    }

    public void earth() {
        for (int i = 0; i<db.dbSize(); i++){
            if (db.parseDB(i).infoText.isVisible()){
                //db.parseDB(i).labelText.setVisible(false);
                db.parseDB(i).infoText.setVisible(false);
            }
        }
        db.celestialObjects.clear();

        db.addObj(new CelestialObjects(panel.vc.xConvert(0,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            50*panel.scale, 
            6E14, 
            "Earth",
            0,
            0,
            0,
            0,
            Color.BLUE,
            db,
            500
        ));

        db.parseDB(0).infoText.setVisible(true);
        //db.parseDB(0).labelText.setVisible(true);
        db.parseDB(0).updateText_e();
        db.parseDB(0).updateText_p(panel.netXPan, panel.netYPan);

        db.addObj(new CelestialObjects(panel.vc.xConvert(400,panel.w,panel.scale,0, false), 
            panel.vc.yConvert(0,panel.h,panel.scale,0, false), 
            20*panel.scale, 
            7E8, 
            "Moon",
            0,
            10,
            0,
            0,
            Color.GRAY,
            db,
            500
        ));

        panel.repaint();
        
        
    }

    public void saveContents() {
		try{
            FileWriter fileWriter = new FileWriter("storage"); 
            PrintWriter printWriter = new PrintWriter(fileWriter);              
            printWriter.close();
        }
        catch(IOException e){
            System.out.println("An error has occurred while instantiating the writer");
        }
        for (int j = 0; j < db.dbSize(); j++) {
            try{

                FileWriter fileWriter = new FileWriter("storage", true); 
                PrintWriter printWriter = new PrintWriter(fileWriter);
                CelestialObjects obj = db.parseDB(j);
                printWriter.println("--------BREAK--------");
                printWriter.println(obj.xPos);
                printWriter.println(obj.yPos);
                printWriter.println(obj.ogRad);
                printWriter.println(obj.mass);
                printWriter.println(obj.name);
                printWriter.println(obj.xVel);
                printWriter.println(obj.yVel);
                printWriter.println(obj.xAcc);
                printWriter.println(obj.yAcc);
                printWriter.println(obj.color.getRed());
                printWriter.println(obj.color.getGreen());
                printWriter.println(obj.color.getBlue());
                printWriter.println(obj.listSize);

                //other attributes
                printWriter.println(obj.id);
                printWriter.println(obj.xCart);
                printWriter.println(obj.yCart);
                printWriter.println(obj.ogRad);
                                
                printWriter.close();
            }
            catch(IOException e){
                System.out.println("An error has occurred while writing data");
            }
        }
    }


    public void loadContents(){

        for (int i = 0; i<db.dbSize(); i++){
            if (db.parseDB(i).infoText.isVisible()){
                //db.parseDB(i).labelText.setVisible(false);
                db.parseDB(i).infoText.setVisible(false);
            }
        }
        db.celestialObjects.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("storage"))){
            panel.scale = 1;
            
            while (reader.readLine() != null){
                
                double xp = Double.parseDouble(reader.readLine());
                double yp = Double.parseDouble(reader.readLine());
                double r = Double.parseDouble(reader.readLine());
                double m = Double.parseDouble(reader.readLine());
                String n = reader.readLine();
                double xv = Double.parseDouble(reader.readLine());
                double yv = Double.parseDouble(reader.readLine());
                double xa = Double.parseDouble(reader.readLine());
                double ya = Double.parseDouble(reader.readLine());

                Color c = new Color(Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()));
                int size = Integer.parseInt(reader.readLine());

                int idRef = Integer.parseInt(reader.readLine());
                double xc = Double.parseDouble(reader.readLine());
                double yc = Double.parseDouble(reader.readLine());
                double or = Double.parseDouble(reader.readLine());

                db.addObj(new CelestialObjects(xp,yp,r,m,n,xv,yv,xa,ya,c,db,size));
                db.parseDB(db.dbSize()-1).id = idRef;
                db.parseDB(db.dbSize()-1).xCart = xc;
                db.parseDB(db.dbSize()-1).yCart = yc;
                db.parseDB(db.dbSize()-1).ogRad = or;

                
            }
            reader.close();
            panel.repaint();
        }
        catch(Throwable t){
            System.out.println("An error has occurred loading data");//, please check if the database is corrupted");
        }
    }

} 



