public class VectorCalc {
    final double g = 6.6734E-11;
    final double multScale = 1;
    

    public double gravCalc(double rSquared, double m){
        if (rSquared == 0){
            return 0;
        }
        double result = (g*m*multScale)/(rSquared);
        return result;
    }   

    public Double[] calcAcceleration(CelestialObjects refObj, CelestialObjects targObj){
        double refMass = refObj.mass*multScale;
        double refX = refObj.xCart*multScale;
        double refY = refObj.yCart*multScale;

        double targMass = targObj.mass*multScale;
        double targX = targObj.xCart*multScale;
        double targY = targObj.yCart*multScale;

        if (refMass == 0){
            return new Double[]{0.0,0.0};
        }


        double distanceSq = (

            (targX - refX)*(targX - refX)
            ) + (
            (targY - refY)*(targY - refY)

        );
        
        double acceleration = gravCalc(distanceSq, targMass);
        double theta = Math.atan2((targY-refY),(targX-refX));

        return new Double[]{(acceleration*Math.cos(theta)), (acceleration*Math.sin(theta))};

    }

    public double xConvert(double xInput, double width, double zoomScale, double xPan, boolean mode){
        // Convert from jFrame position to cartesian coordinates
        double xOutput = 0;
        if (mode == true){
            return (xInput - (width/2) - xPan)/zoomScale;
        }
        
        // Convert from cartesian coordinate to jFrame position
        else if (mode == false){
            return (xInput*zoomScale + (width/2) + xPan); 
        }
        return xOutput;
    }

    public double yConvert(double yInput, double height, double zoomScale, double yPan, boolean mode){
        // Convert from jFrame position to cartesian coordinates
        double yOutput = 0;
        if (mode == true){
            yOutput = -1*(yInput - (height/2) - yPan)/zoomScale;
        }
        
        // Convert from cartesian coordinate to jFrame position
        else if (mode == false){
            yOutput = ((-1*yInput*zoomScale + (height/2) + yPan)); 
        }
        return yOutput;
    }

    public double getMultScale() {
        return multScale;
    }

    public int mathFloor(double input){
        return (int)Math.floor(input);
    }
    public int mathCeil(double input){
        return (int)Math.ceil(input);
    }



}
