import java.awt.Color;
import java.util.*;

public class CelestialObjects {
    Database dbRef;
    //int infoPos;//position of the infotext frame
    int id;//object id
    double xPos; //jframe x position - they are derived from the fixed cartesian coords - which in itself are derived from the instatiation pos of the objects
    double yPos; //jframe y position
    double radius; //dynamic radius of circle
    double mass;//mass
    String name;//name
    double ogRad;//static radius
    double xCart;//cartesian coords - these stay FIXED unless object is actually dragged
    double yCart;
    double displayxCart;//used in recentering
    double displayyCart;
    double sc;//scale
    double xVel;//velocity components
    double yVel;
    double xAcc;//acceleration components
    double yAcc;
    Color color;//color

    VectorCalc vc = new VectorCalc();//calculation class
    //LabelText labelText;//info class
    InfoText infoText;
    final double multScale = 1;//constant multiplication scale (since orbital physics occur over a pretty big distance)
    int listSize;
    List<double[]> posList;

    CelestialObjects(double xp, double yp, double r, double m, String n, double xv, double yv, double xa, double ya, Color c, Database db, int size){ 
        //this.infoPos = ip;
        this.id = (int) (Math.random() * 100000);
        this.xPos = xp; 
        this.yPos = yp; 
        this.radius = r; 
        this.mass = m;
        this.name = n;
        
        this.xCart = vc.xConvert(xp,1280,1,0, true);
        this.yCart = vc.yConvert(yp,800,1,0, true);
        this.displayxCart = xCart;
        this.displayyCart = yCart;
        this.sc = 1;
        this.ogRad = r;
        this.xVel = xv;
        this.yVel = yv;
        this.xAcc = xa;
        this.yAcc = ya;
        this.color = c;
        //this.multScale = 1;
        this.dbRef = db;
        
        // labelText = new LabelText();
        // labelText.labelFrame();

        infoText = new InfoText(dbRef);
        infoText.infoFrame();

        listSize = size;
        posList = new ArrayList<>();

        
    }
    

    public void addPos(double[] input){
        double[] posSet = input;
        posList.add(posSet);
        if (posList.size() > listSize){
            posList.remove(0);
        }
    }

    public boolean isInObject(double mouseXPos, double mouseYPos) {
        double xDistance = mouseXPos - xPos;
        double yDistance = mouseYPos - yPos;
        
        
        return xDistance*xDistance + yDistance*yDistance <= radius*radius;
    }

    public void updateText_p(double xp, double yp){
        infoText.setTxt(2,Double.toString(vc.mathFloor(xCart-xp)));
        infoText.setTxt(3,Double.toString(vc.mathFloor(yCart+yp)));
        //infoText.setTxt(6,Double.toString((int)(totalXPan)));
        //infoText.setTxt(7,Double.toString((int)(totalYPan)));
        infoText.setTxt(9,Double.toString(xVel));
        infoText.setTxt(10,Double.toString(yVel));
        infoText.setTxt(11,Double.toString(xAcc));
        infoText.setTxt(12,Double.toString(yAcc));
    }

    public void updateText_e(){
        infoText.setTxt(1,name);
        infoText.setTxt(4,Double.toString(mass));
        infoText.setTxt(5,Double.toString(ogRad));
        //infoText.setTxt(8,Double.toString(sc));
        infoText.setTxt(13,Integer.toString(listSize));
    }

    public void toggleText(boolean state){
        infoText.setVisible(state);
        //labelText.setVisible(state);
    }
}