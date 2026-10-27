import java.util.ArrayList;

/**
 * Models an Electric Vehicle Charging Station.
 * A charging station contains multiple {@link Dock} units.
 * @author DP classes 
 * @version 2026.10.13
 */
public class BikeStation
{
    /* TODO
    -  esta  estructura  se  mantendrá  ordenada  de  acuerdo  a  un  criterio  de 
    comparación de docks, que, en la Entrega Inicial será decreciente por su 
    velocidad de carga. En caso de empate, de manera creciente por su tarifa 
    de carga (céntimos de € / wh) y si hubiera otro empate, en orden creciente 
    por su identificador. 
    */

    private String idStation;
    private Location localizacion;
    private ArrayList<Dock> docks;

    public BikeStation(String idStation, Location localizacion, int numDocks) {
        this.idStation = idStation;
        this.localizacion = localizacion;
        this.docks = new ArrayList<>();

        for (int i = 0; i < numDocks; i++) { // num de docs para inicializar
            this.docks.add(new Dock()); //no tiene contructor?
        }

    }

    /**
     * Retrieves the first free {@link Dock} found at the station.
     * @return A free {@link Dock}, or possibly throws an exception if none are found (depending on stream implementation details).
     * **Note: The implementation assumes at least one free dock exists if called.**
    */

    public Location getLocation(){
        return localizacion;
    }

    public ArrayList<Dock> getDocks(){
        return docks;
    }

    public void addDock(Dock esteDock){
        docks.add(esteDock);
    }

    public Dock findDock(String dockName){

        for (int i = 0; i < docks.size(); i++) {
            if (docks.get(i).getIdDock().equals(dockName)){ 
                return docks.get(i);
            }  
        }

        return null;
    }


    public Dock getFreeDock()
    {
       //TODO: Complete this code   
        return null;        
    }

    /**
     * @return The unique identifier of the charging station.
    */

    public String getId()
    {
        //TODO: Complete this code   
        return null;
    }
    
    
    /**
     * Adds a new {@link Dock} to the station.
     * @param dock The new Dock unit.
    */
 
    /**
     * Set the current location of the charging station.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
       //TODO: Complete this code   
    }

    //TODO complete the rest of the code

}