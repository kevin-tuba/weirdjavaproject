
import javax.swing.*;
public class SimulationJFrame extends JFrame{
    public void simFrame(){
        //create jframe
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920,1080);
        this.setTitle("Kevin's Orbital Simulator");
        this.setLocation(-10, 0);

        

        //simulation panel
        this.setUndecorated(true);
        SimulationPanel simPanel = new SimulationPanel();
        this.add(simPanel);
        this.pack();
        simPanel.requestFocusInWindow();
        
        

        //set visible
        this.setResizable(false);
        this.setVisible(true);


    }
}