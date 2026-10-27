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
    private ArrayList bikesRecharged[];
    private int amountCollected;
    private boolean free;

    /**
     * Constructor for objects of class Dock.
     * //TODO complete the JavaDoc comments
    */
    public Dock(String id, int speed, float fee)
    {
        this.idDock = id;
        this.chargingSpeed = speed;
        this.chargingFee = fee;

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

    /**
     * @return The total number of {@link ElectricBike}s that have been recharged by this dock.
     */
    public int getNumerEBRecharged(){
        int bikesRech = 0;
        
        while(bikesRech +1 != bikesRecharged.length){
            bikesRech++;
        }

        return bikesRech;
    }

    public void setChargerBikes(){

        //TODO
        
    }
    /**    (non-Javadoc)
    ●  Método que permite añadir un nuevo objeto bicicleta a la colección de bicicletas cargadas. 
    ●  Método que permite actualizar la cantidad recaudada por el dock.  
    ●  Método que permita realizar la recarga de una bicicleta eléctrica. 
     * 
     */

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
     * //TODO complete the JavaDoc comments
     */
    public float recharge(   ){
        //TODO: Complete this code
        return 0;
    }

    //TODO: Complete this code

}
