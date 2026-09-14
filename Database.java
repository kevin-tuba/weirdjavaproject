import java.util.*;

public class Database {
    
    public List<CelestialObjects> celestialObjects = new ArrayList<>(); 

    public void addObj(CelestialObjects obj){
        celestialObjects.add(obj);
    }

    public CelestialObjects parseDB(int index){
        return celestialObjects.get(index);
    }

    public int dbSize(){
        return celestialObjects.size();
    }

    public void removeObj (int inputID){
        for (int i = 0; i < celestialObjects.size(); i++){
            if (celestialObjects.get(i).id == inputID){
                celestialObjects.remove(i);
                //return true;
            }
        
        }
        //return false;
    }
}
