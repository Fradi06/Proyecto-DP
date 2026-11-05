import java.util.*;

/**
 * Models the operation of an Electric Bike (EB) Company. 
 * It manages a fleet of {@link ElectricBike}s and a network of {@link BikeStation}s.
 * * @author David J. Barnes and Michael Kölling
 * @author DP classes
 * @version 2026.10.13
*/

public class EBCompany  
{
    
    private String name;
    private ArrayList<ElectricBike> subscribedBikes;
    private ArrayList<BikeStation> bikeStations;

    /**
     * Constructor for objects of class EVCompany.
     * @param name The name of the company.
    */


    public EBCompany(String name, ArrayList<ElectricBike> subscribedBikes, ArrayList<BikeStation> bikeStations)
    {
        this.subscribedBikes = subscribedBikes;
        this.name = name;
        this.bikeStations = bikeStations;
    }

    /**
     * Adds an {@link ElectricBike} to the company's fleet.
     * @param bike The electric bike to add.
     */
    public void addElectricBike(ElectricBike bike)
    {       
        subscribedBikes.add(bike);
    }

    public ArrayList<BikeStation> getBikeStations(){
        return bikeStations;
    }

    public String getCompanyName(){
        return name;
    }

    
    /**
     * Adds a {@link BikeStation} to the company's network.
     * @param station The bike station to add.
     */
    public void addBikeStation(BikeStation station)
    {       
        bikeStations.add(station);
    }
    
    
    /**
     * Retrieves a {@link BikeStation} by its unique ID.
     * @param id The ID of the station to find.
     * @return The {@link BikeStation} with the matching ID, or {@code null} if not found.
     */
    public BikeStation getBikeStation(String id)
    {
        for(BikeStation bs : bikeStations){
            if (bs.getId() == id){
                return bs;
            }
        }
        return null;
    }

    /**
     * Retrieves a {@link BikeStation} by its {@link Location}.
     * @param location The {@link Location} of the station to find.
     * @return The {@link BikeStation} at the matching location, or {@code null} if not found.
     */
    public BikeStation getBikeStationLoc(Location location)
    {

        for(BikeStation bs : bikeStations){
            if (bs.getLocation() == location){
                return bs;
            }
        }
        return null;
    }
    
    
    /**
     * @return The total number of managed {@link BikeStation}s.
     */
    public int getNumberOfStations(){
        return bikeStations.size();
    }
    
    /**
     * Clears all managed bikes and stations, resetting the company to an empty state.
    */
    public void reset(){
        name = "";
        subscribedBikes = new ArrayList<>();
        bikeStations = new ArrayList<>();
    }
    
}