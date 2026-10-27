
    import java.util.ArrayList;
import java.util.List;


    /**
     * Model the common elements of an Electric bike (EB) that operates 
     * within the simulation, moving towards a target and potentially recharging.
     * * @author David J. Barnes and Michael Kölling
     * @author DP classes 
     * @version 2026.10.13
     */
    public class ElectricBike 
    {
        private char[] idBike = new char[6];
        private EBCompany company;
        private Location location;
        private Location targetLocation;
        private int idleCount;
        private int batteryCapacity;
        private int batteryLevel;
        private int wsCharged;
        private int chargesCount;
        private int chargestCost; // indica el coste total de las recargas que ha realizado la bicicleta. El 
                                    //coste de cada recarga se calcula como los wh cargados por el coste de la recarga 
                                        //tarifa del dock). 
                                            //docks, colección con
        private List<Dock> docks; // nos lo han dado con list en vez de arraylist?
        


        /**
         * Constructor of class ElectricBike.
         * @param company The EB's operating company. Must not be null.
         * @param location The EB's starting {@link Location}. Must not be null.
         * @param targetLocation The EB's final destination {@link Location}. Must not be null.
         * @param id The identificator of the bike.
         * @param batteryCapacity The maximum capacity of the battery.
         * @throws NullPointerException If company, location, or targetLocation is null.
         */
        public ElectricBike(EBCompany company, Location location, Location targetLocation, String id, int batteryCapacity)
        {
            this.company=company;
            this.location=location;
            this.targetLocation=targetLocation;
            this.idBike= id.toCharArray();
            this.batteryCapacity=batteryCapacity;

        }

        /**
         * @return The id of the electric bike.
         */
        public String getIdBike()
        {
            return idBike.toString();    
        }

        /**
         * Set the current location.
         * @param location Where it is. Must not be null.
         * @throws NullPointerException If location is null.
         */
        public void setLocation(Location location)
        {
            this.location=location;
        }

        /**
         * Get the final target location.
         * @return Where this bike is ultimately headed.
         */
        public Location getTargetLocation()
        {
            return targetLocation;    
        }

        /**
         * Get the temporary recharging location.
         * @return The {@link Location} of the next {@link BikeStation} to visit, or null if no recharge is planned.
         */
        public Location getRechargingLocation()
        {
            return rechargingLocation;
        }


        /**
         * Set the required final target location.
         * @param location Where to go. Must not be null.
         * @throws NullPointerException If location is null.
         */
        public void setTargetLocation(Location location)
        {
           this.targetLocation=location;
        }

        /**
         * Calculates the optimal route for the bike. 
         * If there isn't enough battery to reach the target, it attempts to find an intermediate 
         * {@link BikeStation} and sets it as the {@code rechargingLocation}.
        */

        public void calculateRoute()
        {
            int menorDistanciaTotal = 20; //tamaño maximo de nuestra matriz sera nuestra distancia mas pequeña para empezar con algo grande 

            if (!enoughBattery()){
                for(Dock dockEncontrado : docks){

                    // if podemos llegar a este dock(falta meto)
                    int distDoc = distanceToTheTargetLocation(dockEncontrado.getLocation());
                    int distDockATarget = Math.abs(dockEncontrado.getX() - targetLocation.getX()) + Math.abs(dockEncontrado.getX() - targetLocation.getY());
                    int distTotal = distDoc + distDockATarget;

                    if (distTotal < menorDistanciaTotal) {
                        menorDistanciaTotal = distTotal;
                        //supongo que cambiamos el destino a este dock
                        setTargetLocation(dockEncontrado.getLocation());
                    }
                }
            }
        }

        /**
         * Gets a string representation of the planned route, including the recharging stop if one exists.
         * @return A string showing the route: {@code currentLocation -> [rechargingLocation ->] targetLocation}.
         */
        public String getStringRoute()
        {
        //TODO: Complete this code
        return null;
        }

        public boolean enoughBattery(   )
        {
           int totalDistancia = distanceToTheTargetLocation(null);
           // 5 wh por cada paso que da al moverse por la ciudad.
            return wsCharged/5 >= totalDistancia;
        }

        /**
         * Determines the optimal intermediate {@link BikeStation} to visit for recharging
         * if the bike cannot reach the final target directly.
         * Sets {@code rechargingLocation} to the chosen station's location.
         */
        public void calculateRechargingPosition()
        {
            //TODO: Complete this code
        }

        /**
         * Get the Manhattan-like distance to the final target location from the current location.
         *  If Point A is at (2, 3) and Point B is at (5, 7), the Manhattan distance is |2 - 5| + |3 - 7| = 3 + 4 = 7 units.
         * @return The distance to the target location.
         */
        public int distanceToTheTargetLocation(Location L)
        {
            if(L != null){
                int totX = Math.abs(location.getX() - L.getY());
                int totY = Math.abs(location.getY() - L.getY());
                return totX + totY;
            }
            else{
                int totX = Math.abs(location.getX() - targetLocation.getY());
                int totY = Math.abs(location.getY() - targetLocation.getY());
                return totX + totY;
            }  
        }

        /**
         * Simulates the recharging process when the bike arrives at a {@code rechargingLocation}.
         * The battery is charged to full capacity, the cost is calculated, and the route is recalculated.
         * //TODO complete the JavaDoc comments
         */
        public void recharge(   )
        {
            //TODO: Complete this code 
        } 

        /**
         * Increments the count of recharges performed by this bike.
         * //TODO complete the JavaDoc comments
         */
        public void incrementCharges()
        {
            //TODO: Complete this code 
        }

        /**
         * Adds a cost amount to the total charges cost.
         * //TODO complete the JavaDoc comments
         */
        public void incrementChargesCost(   )
        {
            //TODO: Complete this code 
        }   

        /**
         * Carries out a single step of the bike's actions.
         * Moves one step towards the target (recharging or final) or stays idle.
         * //TODO complete the JavaDoc comments
         */
        public void act(   )
        {
        //TODO: Complete this code 
        }

        /**
         * Reduces the battery level by the cost of one movement step (defined in {@link EBCompany#MOVINGCOST}).
         * Ensures the battery level does not go below zero.
         */
        public void reduceBatteryLevel(){
        //TODO: Complete this code 
        }

        /**
         * Returns a detailed string representation of the electric bike.
         * @return A string containing the bike's name, plate, battery info, charge counts, costs, idle count, and route.
         */
        @Override
        public String toString(){
            //TODO: Complete this code 
            return null;
        }

    
    }
