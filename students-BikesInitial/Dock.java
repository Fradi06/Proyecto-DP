import java.util.ArrayList;
/**
 * Model a dock unit within a {@link BikeStation}.
 * It tracks its charging capabilities, fee, and the electric bikes it has recharged.
 * @author David J. Barnes and Michael Kölling
 * @author DP classes 
 * @version 2026.10.13
 */
public class Dock
{
    private String idDock;
    private int chargingSpeed;
    private float chargingFee;
    private ArrayList<ElectricBike> bikesRecharged;
    private int amountCollected;
    private Location localizacion;
    private boolean free;


    public Dock(String id, int CSpeed, float fee, ArrayList<ElectricBike> arrayBicisRecargadas, int colected, Location localizacion)
    {
        this.idDock = id;
        this.chargingSpeed = CSpeed;
        this.chargingFee = fee;
        if (fee == 0 ){
            free = true;
        }
        else{
            free = false;
        }

        bikesRecharged = arrayBicisRecargadas;
        amountCollected = colected;
        this.localizacion = localizacion;
        
    }

    public String getIdDock(){
        return idDock;
    }
    
    public int getChargingSpeed(){
        return chargingSpeed;
    }

    public float getChargingFee(){
        return chargingFee;
    }

    public int getAmountCollected(){
        return amountCollected;
    }

    public boolean getFree(){
        return free;
    }

    public void sumarCantidadRecaud(int cantidad){
        this.amountCollected += cantidad;
    }

    public void anadirBicicleta(ElectricBike bici){
        this.bikesRecharged.add(bici);
    }

    public int getX(){
        return localizacion.getX();
    }

    public int getY(){
        return localizacion.getY();
    }

    public Location getLocation(){
        return localizacion;
    }


    /**
     * @return The total number of {@link ElectricBike}s that have been recharged by this dock.
     */
    public int getNumerEBRecharged(){   
        return bikesRecharged.size();
    }

    public void setChargerBikes(){

        //TODO
        
    }

    /**
     * Returns a string representation of the dock, including its ID, speed, fee, and the number of EBs recharged.
     * @return A string representation of the dock.
     */
    public String toString()//Dock: CC00_003, 80wh, 0.8€, 0, 0.0€)
    {
        String cad = "(Dock:"  + getIdDock() + ", " + getChargingSpeed() + "wh, " + getChargingFee() + "€, " + getNumerEBRecharged() + ")";  
        
        return cad;
    }    
    
    /**
     * Simulates the charging process for an {@link ElectricBike}.
     * Increases the amount collected and registers the bike as recharged.
     *
    */
    public float recharge(ElectricBike bici){
        float precio = this.getChargingFee(); // esto luego lo multiplicamos por la cantidad de ws
        anadirBicicleta(bici);
        
        // battery level = (blevel / bcap) * 100

        int sumaBateria = bici.getBatteryCapacity() - bici.getBatteryLevel();
        bici.addWsCharged(sumaBateria);

        int porcentajeCargado = (sumaBateria/bici.getBatteryCapacity()) * 100; // recordar que battery capacity es 60, por lo que si tenemos si hemos cargado un 20/60, hemos cargado un 33.33 %, por eso el 100 que multiplica
        bici.setBatteryLevel(porcentajeCargado);

        return precio;
    }

}
