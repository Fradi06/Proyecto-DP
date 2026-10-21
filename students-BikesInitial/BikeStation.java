
/**
 * Models an Electric Vehicle Charging Station.
 * A charging station contains multiple {@link Dock} units.
 * @author DP classes 
 * @version 2026.10.13
 */
public class BikeStation
{
    //TODO: Complete this code

    /**
     * Constructor for objects of class BikeStation.
     * //TODO complete the JavaDoc comments
     */

    /*
        Requisitos de la clase BikeStation: - cada objeto de la clase BikeStation se caracteriza por tener, al menos, los siguientes 
        campos: - idStation, identificador único de la estación. Está compuesto por 4 caracteres, 2 
        letras que indican la ciudad (e.g. CC) y a continuación dos dígitos. Un ejemplo sería 
        la estación CC01 (estación 01 de la ciudad de Cáceres). - location, localización en la que está la estación de bicicletas (será del tipo 
        Location y en esta primera entrega serán indicadas en la demo que será 
        entregada por los profesores de la asignatura), - docks conjunto de objetos de tipo Dock (dársena) que están en la estación de 
        bicicletas. - 
        esta estructura se mantendrá ordenada de acuerdo a un criterio de 
        comparación de docks, que, en la Entrega Inicial será decreciente por su 
        velocidad de carga. En caso de empate, de manera creciente por su tarifa 
    de carga (céntimos de € / wh) y si hubiera otro empate, en orden creciente 
    por su identificador.
    */
    public BikeStation(  )
    {
        
        //TODO: Complete this code
    }

    /**
     * Constructor for objects of class BikeStation.
     * //TODO complete the JavaDoc comments
     */
    public BikeStation(String id, Location location)
    {
        //TODO: Complete this code
    }
    
    /**
     * Retrieves the first free {@link Dock} found at the station.
     * @return A free {@link Dock}, or possibly throws an exception if none are found (depending on stream implementation details).
     * **Note: The implementation assumes at least one free dock exists if called.**
     */
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
    public void addDock(Dock dock)
    {
       //TODO: Complete this code
    }
    
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
