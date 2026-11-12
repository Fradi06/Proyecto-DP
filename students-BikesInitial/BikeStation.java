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

    public BikeStation(String idStation, Location localizacion, ArrayList<Dock> numDocks) {
        this.idStation = idStation;
        this.localizacion = localizacion;
        this.docks = new ArrayList<>();

        for (Dock d : numDocks) { // num de docs para inicializar
            this.docks.add(d); //no tiene contructor? //No tiene un constructor vacio
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

    //Devuelve el primer dock que está libre del ArryList de docks
    public Dock getFreeDock()
    {
        for(Dock dock : docks){
            if(dock.getFree()){
                return dock;
            }
        }
        return null;        
    }

    /**
     * @return The unique identifier of the charging station.
    */

    public String getId()
    {
        return idStation;
    }
    
    
    /**
     * Adds a new {@link Dock} to the station.
     * @param dock The new Dock unit.
    */

    public void setNewDock(Dock d){
        boolean found = false;
        if(docks.isEmpty()){
            docks.add(d);
            found = true;
        }
        else{
            for(int i = 0; i < docks.size(); i++){
                if(docks.get(i).getChargingSpeed() < d.getChargingSpeed()){
                    docks.add(i,d);
                    found = true;
                    break;
                }
                else if(docks.get(i).getChargingSpeed() == d.getChargingSpeed() && docks.get(i).getChargingFee() > d.getChargingFee()){
                    docks.add(i, d);
                    found = true;
                    break;
                }
                else if(docks.get(i).getChargingSpeed() == d.getChargingSpeed() && docks.get(i).getChargingFee() == d.getChargingFee() && docks.get(i).getIdDock().compareTo(d.getIdDock()) > 0){
                    docks.add(i, d);
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            docks.add(d);
        }
    }
    /**
     * Set the current location of the charging station.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    //TODO no está bien hecho ni de coña Begoña
    public void setLocation(Location location)
    {
        if(location == null){
            throw new NullPointerException();
        }
        else{
            this.localizacion = location;
        }
        
    }

    //TODO complete the rest of the code

}