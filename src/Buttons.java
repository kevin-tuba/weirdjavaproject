import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Buttons extends JPanel {

    boolean isRunning = false;
    boolean isEditing;

    JButton esc;
    JButton editButton;
    JButton simButton;
    JButton infButton;
    JButton newButton;
    JButton removeButton;
    //JButton center;

    Presets presets;

    Database db;
    VectorCalc vc;
    CelestialObjects visibleObj;
    InfoText visibleInfo;
    SimulationPanel panel;

    int objIndex = 0;
    


    public Buttons(SimulationPanel simPanel, Database dbRef, VectorCalc vcRef, Presets presetRef) {
        db = dbRef;
        panel = simPanel;
        vc = vcRef;
        isEditing = false;

        presets = presetRef;

        esc = new JButton("EXIT");
        esc.addActionListener((ActionEvent a) -> {
            exit();
        });

        editButton = new JButton("1. Edit Info");
        editButton.addActionListener((ActionEvent b) -> {
            if (db.dbSize() > 0){
                toggleEdit();
            }
            panel.requestFocus();
        });
        
        simButton = new JButton("2. Start Simulation");
        simButton.addActionListener((ActionEvent c) -> {
            if (db.dbSize() > 0){
                toggleSimulation();
            }
            panel.requestFocus();
        });

        infButton = new JButton("3. Toggle Info");
        infButton.addActionListener((ActionEvent d) -> {
            if (db.dbSize() > 0){
                toggleInfo();
            }
            panel.requestFocus();
        });
        
        newButton = new JButton("4. New Object");
        newButton.addActionListener((ActionEvent e) -> {
            newObj();
            if (db.dbSize() == 1){
                db.parseDB(0).infoText.setVisible(true);
                //db.parseDB(0).labelText.setVisible(true);
                db.parseDB(0).updateText_e();
                db.parseDB(0).updateText_p(panel.currentXPan, panel.currentYPan);
            }
            panel.requestFocus();
            
        });

        removeButton = new JButton("5. Remove Object");
        removeButton.addActionListener((ActionEvent f) -> {
            remObj();
            if (db.dbSize() > 0){
                toggleInfo();
            }
            panel.requestFocus();
        });


        buttonFrame();

    
    }

    public void exit(){
        System.exit(0);
    }

    public void buttonFrame() {
        //this.setDefaultCloseOperation(JPanel.EXIT_ON_CLOSE);
        this.setLocation(30, 30);
        //this.setUndecorated(true);
        //this.setResizable(false);
        //this.setAlwaysOnTop(true);
        this.setBackground(new Color(0, 0, 0, 0));
        //this.setVisible(true)
        //buttons
        esc.setFocusPainted(false);
        esc.setBackground(new Color(0,0,0));
        esc.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        esc.setForeground(Color.WHITE);
        esc.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(esc);

        editButton.setFocusPainted(false);
        editButton.setBackground(new Color(0,0,0));
        editButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(editButton);

        simButton.setFocusPainted(false);
        simButton.setBackground(new Color(0,0,0));
        simButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        simButton.setForeground(Color.WHITE);
        simButton.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(simButton);

        infButton.setFocusPainted(false);
        infButton.setBackground(new Color(0,0,0));
        infButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        infButton.setForeground(Color.WHITE);
        infButton.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(infButton);

        newButton.setFocusPainted(false);
        newButton.setBackground(new Color(0,0,0));
        newButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        newButton.setForeground(Color.WHITE);
        newButton.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(newButton);

        removeButton.setFocusPainted(false);
        removeButton.setBackground(new Color(0,0,0));
        removeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        this.add(removeButton);

        // center.setFocusPainted(false);
        // center.setBackground(new Color(0,0,0));
        // center.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        // center.setForeground(Color.WHITE);
        // center.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        // this.add(center);

        this.setSize(200, 430);
        this.setLayout(null);

        esc.setBounds(0, 0, 80, 40);
        editButton.setBounds(0, 50, 200, 40);
        simButton.setBounds(0, 100, 200, 40);
        infButton.setBounds(0, 150, 200, 40);
        newButton.setBounds(0, 200, 200, 40);
        removeButton.setBounds(0, 250, 200, 40);
        //center.setBounds(0, 300, 200, 40);

        this.setVisible(true);
    }

    public void toggleEdit() {
        
        for (int i = 0; i<db.dbSize(); i++){
            if (db.parseDB(i).infoText.isVisible() == true) {
                visibleObj = db.parseDB(i);
                visibleInfo = db.parseDB(i).infoText;
            }
        }
        
        if (isEditing == false) {
            isEditing = true;            
            editButton.setText("Stop Editing");
            infButton.setVisible(false);
            simButton.setVisible(false);
            newButton.setVisible(false);
            removeButton.setVisible(false);
            //center.setVisible(false);

            presets.setVisible(false);

            visibleInfo.toggleEdit(true);

        } 

        else {
            isEditing = false;    
            editButton.setText("1. Edit Info");
            infButton.setVisible(true);
            simButton.setVisible(true);
            newButton.setVisible(true);
            removeButton.setVisible(true);
            //center.setVisible(true);
            visibleInfo.toggleEdit(false);


            presets.setVisible(true);

            visibleObj.name = (visibleInfo.nameField.getText());

            try {
                visibleObj.xCart = Double.parseDouble(visibleInfo.xCartField.getText())+panel.netXPan;
            } catch (Exception e) {
                System.out.println("edit error! xCart");
            }

            try {
                visibleObj.yCart = Double.parseDouble(visibleInfo.yCartField.getText())-panel.netYPan;
            } catch (Exception e) {
                System.out.println("edit error! yCart");
            }

            try {
                visibleObj.mass = Double.parseDouble(visibleInfo.massField.getText());
            } catch (Exception e) {
                System.out.println("edit error! mass");
            }

            try {
                visibleObj.radius = Double.parseDouble(visibleInfo.radField.getText()) * panel.scale;
                visibleObj.ogRad = Double.parseDouble(visibleInfo.radField.getText());
            } catch (Exception e) {
                System.out.println("edit error! radius");
            }

            try {
                visibleObj.xVel = Double.parseDouble(visibleInfo.xVelField.getText());
            } catch (Exception e) {
                System.out.println("edit error! xVel");
            }

            try {
                visibleObj.yVel = Double.parseDouble(visibleInfo.yVelField.getText());
            } catch (Exception e) {
                System.out.println("edit error! yVel");
            }

            try {
                visibleObj.xAcc = Double.parseDouble(visibleInfo.xAccField.getText());
            } catch (Exception e) {
                System.out.println("edit error! xAcc");
            }

            try {
                visibleObj.yAcc = Double.parseDouble(visibleInfo.yAccField.getText());
            } catch (Exception e) {
                System.out.println("edit error! yAcc");
            }
            try {
                visibleObj.listSize = (int)Double.parseDouble(visibleInfo.orbitField.getText());
            } catch (Exception e) {
                System.out.println("edit error! orbit ");
            }
            
            visibleObj.xPos = vc.xConvert(
                visibleObj.xCart, 
                1280, 
                visibleObj.sc, 
                panel.currentXPan, 
                false);
            
            visibleObj.yPos = vc.yConvert(
                visibleObj.yCart, 
                800, 
                visibleObj.sc, 
                panel.currentYPan, 
                false);

            

            panel.repaint();
            

        }
    
        
        //db.parseDB(objIndex).toggleText(true);
        
        
    }

    public void toggleSimulation() {
        if (isRunning == false) {
            isRunning = true;
            infButton.setVisible(false);
            editButton.setVisible(false);
            newButton.setVisible(false);
            removeButton.setVisible(false);
           // center.setVisible(false);

            presets.setVisible(false);
            simButton.setBounds(0, 50, 200, 40);
            simButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
            
            simButton.setText("2. Pause Simulation");
        } else {
            isRunning = false;
            infButton.setVisible(true);
            editButton.setVisible(true);
            newButton.setVisible(true);
            removeButton.setVisible(true);
           // center.setVisible(true);

            presets.setVisible(true);

            simButton.setBounds(0, 100, 200, 40);
            simButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));

            simButton.setText("2. Start Simulation");
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void toggleInfo() {
        
        for (int i = 0; i<db.dbSize(); i++){
            db.parseDB(i).toggleText(false);
        }
        
        if (objIndex < db.dbSize()-1) {
            objIndex += 1;            
        } 

        else {
            objIndex = 0;
        }
        //System.out.println(objIndex)l
        db.parseDB(objIndex).toggleText(true);
        db.parseDB(objIndex).updateText_p(panel.currentXPan, panel.currentYPan);
        db.parseDB(objIndex).updateText_e();
        panel.repaint();
        infButton.setBorder(BorderFactory.createLineBorder(db.parseDB(objIndex).color, 4));
    }

    public void newObj() {
        Random rand = new Random();
        
        CelestialObjects obj = new CelestialObjects(
            (panel.w)/2, 
            (panel.h)/2, 
            20, 
            0, 
            "New Object",
            0,
            0,
            0,
            0,
            new Color(rand.nextInt(50,256),rand.nextInt(50,256),rand.nextInt(50,256)),
            db,
            100
        );
        obj.radius = obj.ogRad*panel.scale;

        obj.xCart = vc.xConvert(obj.xPos,panel.w,panel.scale,panel.currentXPan,true);
        obj.yCart = vc.yConvert(obj.yPos,panel.h,panel.scale,panel.currentYPan,true);

        db.addObj(obj);

        panel.repaint();
    }

    public void remObj() {
        for (int i = 0; i<db.dbSize();i++){
            if (db.parseDB(i).infoText.isVisible()){
                //db.parseDB(i).labelText.setVisible(false);
                db.parseDB(i).infoText.setVisible(false);
                db.removeObj(db.parseDB(i).id);
            }
        }
        panel.repaint();    
    }

}
