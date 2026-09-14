import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.util.*;


public class SimulationPanel extends JPanel {

    public Timer simLoop; //simulation timer

    public Database db = new Database();//objects database
    
    public VectorCalc vc = new VectorCalc(); //calculation methods

    public Presets presets = new Presets(db, this); //preset planets

    public Buttons buttons = new Buttons(this, db, vc, presets); //buttons    

    public Overview overview = new Overview(db); //global stats - can't be edited

    public LabelText label = new LabelText();

    public JLabel tips = new JLabel("Welcome to Kevin's Orbital Simulator System! - Current Version v0.1.67");
    public JLabel tips2 = new JLabel("WASD to move, Q/E to zoom, Shift/Ctrl to change time, 1-5 to use buttons, Esc to quit");
    public JLabel crosshair = new JLabel("< + >");
    

    CelestialObjects selectedObj = null; //selected obj

    int currentMouseXPos; //mouse pos
    int currentMouseYPos;

    double currentXPan = 0; //checks the screen pan
    double currentYPan = 0;

    double netXPan=0; //net screen pan over every pan action
    double netYPan = 0;
    
    double scale = 1; //scale
    
    int w = 1280; //width and height
    int h = 800;

    int simSpeed = 100; //simulation speed (10-100)

    int activeWASD = 0; //which wasd key is active
    
    
    public SimulationPanel() {
        {this.setPreferredSize(new Dimension(1920,1080)); //basic layout
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);

        this.add(buttons);//text
        this.add(presets);
        this.add(overview);
       
        label.labelFrame();
        label.setVisible(true);
       


        this.add(tips); //title text 1
        tips.setBounds(300,10,700,30);
        tips.setForeground(Color.WHITE);
        tips.setFont(new Font("OCR A Extended", Font.PLAIN, 15));

        this.add(tips2); //title text 2
        tips2.setBounds(200,30,800,30);
        tips2.setForeground(Color.WHITE);
        tips2.setFont(new Font("OCR A Extended", Font.PLAIN, 15));

        this.add(crosshair); //crosshair
        crosshair.setBounds(w/2-75,h/2-26,400,50);
        crosshair.setForeground(Color.WHITE);
        crosshair.setFont(new Font("OCR A Extended", Font.PLAIN, 50));}
        
        overview.update_o(db.dbSize(), scale, currentXPan, currentYPan, simSpeed); //updates initial global dislay

        addKeyListener(new KeyAdapter() { //key listeners 
            @Override
            public void keyReleased(KeyEvent m){ //checks if the current WASD key is released
                if (
                (m.getKeyCode() == KeyEvent.VK_W)||
                (m.getKeyCode() == KeyEvent.VK_A)||
                (m.getKeyCode() == KeyEvent.VK_S)||
                (m.getKeyCode() == KeyEvent.VK_D)            
                ){
                    refocus();
                    repaint();
                    activeWASD = 0;
                }



            }


            @Override
            public void keyPressed(KeyEvent k) {
                if (buttons.isEditing == false)//buttons in the brackets only work if editing mode is off
                {if (k.getKeyCode() == KeyEvent.VK_SHIFT){ //speed up/slow down
                    if (simSpeed > 10){
                        simSpeed -= 10;
                        overview.update_o(db.dbSize(), scale, currentXPan, currentYPan, simSpeed);
                    }
                }
                else if (k.getKeyCode() == KeyEvent.VK_CONTROL){
                    if (simSpeed < 100){
                        simSpeed += 10;
                        overview.update_o(db.dbSize(), scale, currentXPan, currentYPan, simSpeed);
                    }
                }

                if (k.getKeyCode() == KeyEvent.VK_Q){ //zoom in/out
                    if (buttons.isEditing == false){
                    int notches = 1;
                    if ((scale > 0.05 && scale < 5)
                        ||(scale <= 0.05 && notches < 0)
                        ||(scale >= 5 && notches > 0)
                    ){
                        scale += -0.05 * notches; 
                        for (int m = 0; m<db.dbSize(); m++){
                            CelestialObjects obj = db.parseDB(m);
                            obj.sc = scale;
                            obj.radius = (obj.ogRad) * scale;
                            
                            obj.xPos = vc.xConvert((int)obj.xCart, w, scale, currentXPan, false);
                            obj.yPos = vc.yConvert((int)obj.yCart, h, scale, currentYPan, false);
                            //obj.updateText_p();
                            //obj.updateText_e();
                            
                        }
                        overview.update_o(db.dbSize(), scale, currentXPan, currentYPan, simSpeed);

                    }
                    

                    
                    repaint();
                }

                }
                else if (k.getKeyCode() == KeyEvent.VK_E){
                    if (buttons.isEditing == false){
                    int notches = -1;
                    if ((scale > 0.05 && scale < 5)
                        ||(scale <= 0.05 && notches < 0)
                        ||(scale >= 5 && notches > 0)
                    ){
                        scale += -0.05 * notches; 
                        for (int m = 0; m<db.dbSize(); m++){
                            CelestialObjects obj = db.parseDB(m);
                            obj.sc = scale;
                            obj.radius = (obj.ogRad) * scale;
                            
                            obj.xPos = vc.xConvert((int)obj.xCart, w, scale, currentXPan, false);
                            obj.yPos = vc.yConvert((int)obj.yCart, h, scale, currentYPan, false);
                            //obj.updateText_p();
                            //obj.updateText_e();
                            
                        }
                        overview.update_o(db.dbSize(), scale, currentXPan, currentYPan, simSpeed);

                    }
                    

                    
                    repaint();
                }
                }
                
                switch (k.getKeyCode()) {
                    case KeyEvent.VK_W -> {
                        //wasd
                        if (activeWASD == 0 || activeWASD == 1){
                            activeWASD = 1;
                            currentYPan += 20;
                            netYPan = netYPan - currentYPan/scale + 20/scale;
                            objPosUpdate();
                            repaint();
                        }
                    }
                    case KeyEvent.VK_S -> {
                        if (activeWASD == 0 || activeWASD == 2){
                            activeWASD = 2;
                            currentYPan -= 20;
                            netYPan = netYPan - currentYPan/scale - 20/scale;
                            objPosUpdate();
                            repaint();
                        }
                    }
                    case KeyEvent.VK_A -> {
                        if (activeWASD == 0 || activeWASD == 3){
                            activeWASD = 3;
                            currentXPan += 20;
                            netXPan = netXPan - currentXPan/scale + 20/scale;
                            objPosUpdate();
                            repaint();
                        }
                    }
                    case KeyEvent.VK_D -> {
                        if (activeWASD == 0 || activeWASD == 4){
                            activeWASD = 4;
                            currentXPan -= 20;
                            netXPan = netXPan - currentXPan/scale - 20/scale;
                            objPosUpdate();
                            repaint();
                        }
                    }
                    default -> {
                    }
                }
                
                if (k.getKeyCode() == KeyEvent.VK_2){ //simulation
                    if (db.dbSize() > 0 && buttons.isEditing == false){
                        buttons.toggleSimulation();
                    }
                }

                if (k.getKeyCode() == KeyEvent.VK_3){ //toggle info
                    if (db.dbSize() > 0){//} && buttons.isRunning == false){
                        buttons.toggleInfo();
                    }
                }

                if (k.getKeyCode() == KeyEvent.VK_4){ //new massless obj
                    if (buttons.isRunning == false && buttons.isEditing == false){
                        buttons.newObj();
                        if (db.dbSize() == 1){
                            db.parseDB(0).infoText.setVisible(true);
                            //db.parseDB(0).labelText.setVisible(true);
                            //db.parseDB(0).updateText_e();
                            //db.parseDB(0).updateText_p();
                        }
                    }
                }

                if (k.getKeyCode() == KeyEvent.VK_5){ //remove obj
                    if (buttons.isRunning == false && buttons.isEditing == false){
                        buttons.remObj();
                        if (db.dbSize() > 0){
                            buttons.toggleInfo();
                        }
                    }
                } }

                if (k.getKeyCode() == KeyEvent.VK_ESCAPE){ //esc
                    buttons.exit();
                }

                if (k.getKeyCode() == KeyEvent.VK_1){ //edit
                    if (db.dbSize() > 0 && buttons.isRunning == false){
                        buttons.toggleEdit();
                    }
                }
                requestFocus();//focuses on keybinds                
            }
             
        });

        //test objects
        {db.addObj(new CelestialObjects(vc.xConvert(0,w,scale,0, false), 
            vc.yConvert(0,h,scale,0, false), 
            50*scale, 
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

        db.addObj(new CelestialObjects(vc.xConvert(400,w,scale,0, false), 
            vc.yConvert(0,h,scale,0, false), 
            20*scale, 
            7E8, 
            "Moon",
            0,
            10,
            0,
            0,
            Color.GRAY,
            db,
            500
        ));}

        

        db.parseDB(0).infoText.setVisible(true);//sets first object text to visible
        //db.parseDB(0).labelText.setVisible(true);

        for(int u = 0; u < db.dbSize(); u++){ //updates all objects
            CelestialObjects obj = db.parseDB(u);
            obj.sc = scale;
            obj.updateText_p(netXPan, netYPan);
            obj.updateText_e();
        }

        
            
        
        MouseAdapter mouseHandler = new MouseAdapter() { //mouse listener
            @Override
            
            

            public void mousePressed(MouseEvent mouse) {//if mouse clicked
                currentMouseXPos = mouse.getX(); //get pos
                currentMouseYPos = mouse.getY();
                
                selectedObj = null;

                //goes through the list of objects
                if (buttons.isEditing == false){
                    for (int i = 0; i < db.dbSize(); i++) {
                        CelestialObjects obj = db.parseDB(i);

                        if (obj.isInObject(currentMouseXPos, currentMouseYPos) == true) { //if mouse clicked obj
                            selectedObj = obj;
                            selectedObj.xCart = vc.xConvert(selectedObj.xPos, w, scale, currentXPan, true);//sets obj
                            selectedObj.yCart = vc.yConvert(selectedObj.yPos, h, scale, currentYPan, true);
                            selectedObj.updateText_p(netXPan, netYPan);

                            if (selectedObj.infoText.isVisible() == false){ //swaps out obj info
                                for (int j = 0; j<db.dbSize(); j++){
                                    if (db.parseDB(j) == selectedObj){
                                        //selectedObj.labelText.setVisible(true);
                                        selectedObj.infoText.setVisible(true);
                                    }
                                    else{
                                        //db.parseDB(j).labelText.setVisible(false);
                                        db.parseDB(j).infoText.setVisible(false);
                                    }
                                    
                                }
                            }
                            break;
                        }
                        
                    }
                }
                requestFocus();
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent mouse) {
                if (buttons.isEditing == true){//does nothing if edit mode is on
                    
                }
                else if (selectedObj != null) {
                    selectedObj.xPos = mouse.getX();
                    selectedObj.yPos = mouse.getY();

                    
                    selectedObj.xCart= vc.xConvert(selectedObj.xPos, w, scale, currentXPan, true);
                    selectedObj.yCart= vc.yConvert(selectedObj.yPos, h, scale, currentYPan, true);
                    selectedObj.updateText_p(netXPan, netYPan);
                    //selectedObj.posList.clear();
                    //requestFocus();
                    repaint();
                }
                else{
                    int xPan = (mouse.getX() - currentMouseXPos);
                    int yPan = (mouse.getY() - currentMouseYPos);
                    currentMouseXPos = mouse.getX();
                    currentMouseYPos = mouse.getY();
                    //xPan /= scale;
                    //yPan /= scale;
                    currentXPan += xPan;//scale;
                    currentYPan += yPan;//scale;

                    for (int k = 0; k<db.dbSize(); k++){
                        db.parseDB(k).xPos = vc.xConvert((db.parseDB(k).xCart), w, scale, currentXPan, false);
                        db.parseDB(k).yPos = vc.yConvert((db.parseDB(k).yCart), h, scale, currentYPan, false);
                        //db.parseDB(k).updateText_p();
                        //db.parseDB(k).updateText_e();
                        //db.parseDB(k).posList.clear();
                        
                    }
                    overview.update_o(db.dbSize(), scale, currentXPan, currentYPan, simSpeed);
                    
                    repaint();
                }
                requestFocus();

            }

            @Override
            public void mouseReleased(MouseEvent mouse) {
                if (buttons.isEditing == true){

                }
                else if (selectedObj != null){
                    
                    selectedObj.updateText_p(netXPan, netYPan);
                    selectedObj.updateText_e();
                    selectedObj.posList.clear();
                }
                else{
                    for (int l = 0; l<db.dbSize(); l++){
                        db.parseDB(l).xPos = vc.xConvert((db.parseDB(l).xCart), w,scale, currentXPan, false);
                        db.parseDB(l).yPos = vc.yConvert((db.parseDB(l).yCart), h, scale, currentYPan, false);
                        
                        
                    }
                    netXPan += currentXPan/scale;
                    netYPan += currentYPan/scale;
                    refocus();
                    
                }
                selectedObj = null;
                requestFocus();
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent mouse) {
                if (buttons.isEditing == false){
                    int notches = mouse.getWheelRotation();
                    if ((scale > 0.05 && scale < 5)
                        ||(scale <= 0.05 && notches < 0)
                        ||(scale >= 5 && notches > 0)
                    ){
                        scale += -0.05 * notches; 
                        for (int m = 0; m<db.dbSize(); m++){
                            CelestialObjects obj = db.parseDB(m);
                            obj.sc = scale;
                            obj.radius = (obj.ogRad) * scale;
                            
                            obj.xPos = vc.xConvert((int)obj.xCart, w, scale, currentXPan, false);
                            obj.yPos = vc.yConvert((int)obj.yCart, h, scale, currentYPan, false);
                            //obj.updateText_p();
                            //obj.updateText_e();
                            
                        }

                    }
                    

                    overview.update_o(db.dbSize(), scale, currentXPan, currentYPan, simSpeed);
                    repaint();
                }
            }
        };



        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addMouseWheelListener(mouseHandler);

        simLoop = new Timer(100, e -> {
            if (buttons.isRunning() == false) {
                return;
            }
            simLoop.setDelay(simSpeed);

            for (int j = 0; j < db.dbSize(); j++){
                CelestialObjects obj = db.parseDB(j);
                obj.xAcc = 0;
                obj.yAcc = 0;
                for (int r = 0; r <db.dbSize(); r++){
                    if (r != j){
                        Double[] acceleration = vc.calcAcceleration(db.parseDB(j), db.parseDB(r));


                        obj.xAcc += (acceleration[0]);
                        obj.yAcc += (acceleration[1]);
                    }
                }
                obj.xVel += obj.xAcc;
                obj.yVel += obj.yAcc;
            }

            for (int i = 0; i < db.dbSize(); i++) {
                CelestialObjects obj = db.parseDB(i);

                obj.xCart += obj.xVel;
                obj.yCart += obj.yVel;

                double[] posSet = {obj.xCart, obj.yCart};
                obj.addPos(posSet);
                
            }

            for (int k = 0; k < db.dbSize(); k++){
                CelestialObjects obj = db.parseDB(k);
                obj.xPos = vc.xConvert(obj.xCart, w, scale, currentXPan, false);
                obj.yPos = vc.yConvert(obj.yCart, h, scale, currentYPan, false);

                

                obj.updateText_p(netXPan, netYPan);
                obj.updateText_e();
            }
            overview.update_o(db.dbSize(), scale, currentXPan, currentYPan, simSpeed);

            repaint();
        });

        simLoop.setInitialDelay(0);
        simLoop.start();
    }

    public void objPosUpdate(){
        for (int i = 0; i<db.dbSize(); i++){
            CelestialObjects obj = db.parseDB(i);
            obj.updateText_p(netXPan,netYPan);
            obj.xPos = vc.xConvert(obj.xCart, w, scale, currentXPan, false);
            obj.yPos = vc.yConvert(obj.yCart, h, scale, currentYPan, false);

            overview.update_o(db.dbSize(), scale, currentXPan, currentYPan, simSpeed);           
            
        }
        netXPan += currentXPan/scale;
        netYPan += currentYPan/scale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;   // use Graphics2D for better control
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int j = 0; j<db.dbSize(); j++) {
            CelestialObjects obj = db.parseDB(j);
            int objRadius = (int) obj.radius;

            for (int k = 0; k<obj.posList.size()-1; k++){
                int xPos1 = (int)(vc.xConvert((obj.posList.get(k)[0]), w, scale, currentXPan,false));
                int yPos1 = (int)(vc.yConvert((obj.posList.get(k)[1]), h, scale, currentYPan,false));
                int xPos2 = (int)(vc.xConvert((obj.posList.get(k+1)[0]), w, scale, currentXPan,false));
                int yPos2 = (int)(vc.yConvert((obj.posList.get(k+1)[1]), h, scale, currentYPan,false));

                g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.setColor(obj.color);
                g2.drawLine(xPos1, yPos1, xPos2, yPos2);
            }
           
            
            g2.setColor(new Color(obj.color.getRed(),obj.color.getGreen(),obj.color.getBlue(), 10));
            for (int m = 6; m<10; m++){
                g2.fillOval((int)( obj.xPos - m*0.2*objRadius), (int)(obj.yPos - m*0.2*objRadius), (int)(0.4*m * objRadius), (int)(0.4*m * objRadius));
            }  



            g2.setColor(obj.color);
            g2.fillOval((int) obj.xPos - objRadius, (int)obj.yPos - objRadius, 2 * objRadius, 2 * objRadius);

            g2.setColor(new Color(120, 30, 120,50));
            g2.fillOval((int) obj.xPos - objRadius, (int)obj.yPos - objRadius, 2 * objRadius, 2 * objRadius);

            g2.setColor(obj.color);
            g2.fillOval((int) obj.xPos-(int)(0.95*objRadius), (int)obj.yPos-(int)(0.95*objRadius), (int)(1.7*objRadius), (int)(1.7*objRadius));
            
            if ((obj.name.equals("Saturn")) || obj.name.contains("Ringed")){
                g2.setColor(Color.WHITE);
                g2.fillRect((int)obj.xPos-2*objRadius,(int)obj.yPos-(int)(0.1*objRadius), 4*objRadius, (int)(0.2*objRadius));
            }

            

        }

        

        
        
    }

    public void refocus(){
        for (int i = 0;i<db.dbSize();i++){
                CelestialObjects obj = db.parseDB(i);
                double x = obj.xCart;
                double y = obj.yCart;

                obj.xCart=vc.xConvert(obj.xPos,w,scale,0,true);
                obj.yCart=vc.yConvert(obj.yPos,h,scale,0,true);

                x -= obj.xCart;
                y -= obj.yCart;

                for (int j = 0;j<obj.posList.size();j++){
                    double[] newSet = {obj.posList.get(j)[0] - x, obj.posList.get(j)[1] - y};
                    obj.posList.set(j, newSet);

                obj.updateText_p(netXPan, netYPan);
                obj.updateText_e();
            }

            currentXPan = 0;
            currentYPan = 0;
        }
    }
}
    
