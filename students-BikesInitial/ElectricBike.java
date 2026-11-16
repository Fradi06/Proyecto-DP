
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
        private String idBike; // vector de caracteres 6 profesora dijo string.
        private EBCompany company;
        private Location location;
        private Location targetLocation;
        private int idleCount;
        private int batteryCapacity; // asumiendo que esto va del 0-100, igual que battery level, se calculará a partir de de la formula (blevel / bcap) * 100
        private int batteryLevel;
        private int wsCharged;
        private boolean reachedTarget;
        private int chargesCount;
        private float chargestCost; // indica el coste total de las recargas que ha realizado la bicicleta. El 
                                    //coste de cada recarga se calcula como los wh cargados por el coste de la recarga 
                                        //tarifa del dock). 
                                            //docks, colección con
        private List<Dock> docks; // nos lo han dado con list en vez de arraylist?
        private BikeStation rechargingLocation;
        


        /**
         * Constructor of class ElectricBike.
         * @param company The EB's operating company. Must not be null.
         * @param location The EB's starting {@link Location}. Must not be null.
         * @param targetLocation The EB's final destination {@link Location}. Must not be null.
         * @param id The identificator of the bike.
         * @param batteryCapacity The maximum capacity of the battery.
         * @throws NullPointerException If company, location, or targetLocation is null.
         */
        public ElectricBike(EBCompany company, Location location, Location targetLocation, String id, int batteryCapacity, boolean reachedTarged, float cost, List<Dock> docks)
        {
            
            this.company=company;
            this.location=location;
            this.targetLocation=targetLocation;
            this.idBike= id;
            this.batteryCapacity=batteryCapacity;
            batteryLevel = 60; // max
            this.reachedTarget = reachedTarged;
            this.idleCount = 0;
            chargesCount = 0;
            chargestCost = cost;
            this.docks = docks;

            rechargingLocation = null;
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
            if (rechargingLocation != null){
                return rechargingLocation.getLocation();
            }
            else{
                return null;
            }
          
        }

        public int getBatteryCapacity(){
            return batteryCapacity;
        }

        public int getWsCharged(){
            return wsCharged;
        }

        public int getBatteryLevel(){
            return batteryLevel;
        }

        public void setBatteryCapacity(int batteryCap){
            batteryCapacity = batteryCap;
        }

        public void setBatteryLevel(int batteryLev){
            batteryLevel = batteryLev;
        }

        public void addWsCharged(int ws){
            wsCharged += ws;
        }

        /**
         * Set the required final target location.
         * @param location Where to go. Must not be null.
         * @throws NullPointerException If location is null.
        */

        public void setRechargingLocation(BikeStation location)
        {
            rechargingLocation = location;
        }

        /**
         * Calculates the optimal route for the bike. 
         * If there isn't enough battery to reach the target, it attempts to find an intermediate 
         * {@link BikeStation} and sets it as the {@code rechargingLocation}.
        */

        public void calculateRoute() {
            if (enoughBattery()) {
                setRechargingLocation(null);
            } else {

                int menorDistanciaTotal = 20;
                BikeStation mejorEstacion = null;

                for (BikeStation estacion : company.getBikeStations()) {
                    Location locEstacion = estacion.getLocation();
                    int distAEstacion = location.distance(locEstacion);
                    
    
                    if ((batteryLevel / 5) >= distAEstacion) {
                        int distEstacionATarget = locEstacion.distance(targetLocation);
                        int distTotal = distAEstacion + distEstacionATarget;
                        
                        if (distTotal < menorDistanciaTotal) {
                            menorDistanciaTotal = distTotal;
                            mejorEstacion = estacion;
                        }
                    }
                }
    
                setRechargingLocation(mejorEstacion); // null si no encuentra
            }
        }

        /**
         * Gets a string representation of the planned route, including the recharging stop if one exists.
         * @return A string showing the route: {@code currentLocation -> [rechargingLocation ->] targetLocation}.
         */
        public String getStringRoute()
        {
            String s = "Current Location = " + location + "Recharging location = " + rechargingLocation + "Target Location = " + targetLocation + ")";
            return s;
        }

        public boolean enoughBattery(   )
        {
           int totalDistancia = distanceToTheTargetLocation(targetLocation);
           // 5 wh por cada paso que da al moverse por la ciudad.
            return (batteryLevel/5) >= totalDistancia;
        }

        /**
         * Determines the optimal intermediate {@link BikeStation} to visit for recharging
         * if the bike cannot reach the final target directly.
         * Sets {@code rechargingLocation} to the chosen station's location.
         */
        public void calculateRechargingPosition() // esto lo hemos hecho ya
        {
            //TODO: Complete this code
        }

        /**
         * Get the Manhattan-like distance to the final target location from the current location.
         *  If Point A is at (2, 3) and Point B is at (5, 7), the Manhattan distance is |2 - 5| + |3 - 7| = 3 + 4 = 7 units.
         * @return The distance to the target location.
         */
        public int distanceToTheTargetLocation(Location L)
        { // pide usar metodo distance de location.
            return location.distance(L);
        }

        /**
         * Simulates the recharging process when the bike arrives at a {@code rechargingLocation}.
         * The battery is charged to full capacity, the cost is calculated, and the route is recalculated.
         * 
        */
       
        public void recharge(BikeStation bikeStation) // 
        {
            Dock dockLibre = bikeStation.getFreeDock();
            if (dockLibre != null){
                
                float precio = dockLibre.recharge(this); // nos referenciamos a nosotros así? esto recarga la bici y devuelve precio

                this.chargestCost += precio;

                bikeStation.addDock(dockLibre);
                
                // cambiar dock a free de nuevo despues?? se hace aqui o en dock

                this.chargesCount +=1;
                this.calculateRoute();
            }

        } 

        void doPaso() {
            Location loc;
            if (getRechargingLocation() != null){
                loc = getRechargingLocation();
            }   
            else{
                loc = targetLocation;
            }



            if (!location.equals(loc)) {
                if (location.getX() < loc.getX()) {
                    location.addX(1);
                } else if (location.getX() > loc.getX()) {
                    location.addX(-1);
                }

                if (location.getY() < loc.getY()) {
                    location.addY(1);
                } else if (location.getY() > loc.getY()) {
                    location.addY(-1);
                }

                reduceBatteryLevel();
            }
        }

        public void act() {
            if (location.equals(targetLocation)) {
                reachedTarget = true;
                idleCount++;
                return;
            }

            calculateRoute();
            doPaso();

            if (location.equals(targetLocation)) {
                reachedTarget = true;
            } else if (rechargingLocation != null && location.equals(rechargingLocation.getLocation())) {
                recharge(rechargingLocation);
            }
        }

        

        /**
         * Reduces the battery level by the cost of one movement step (defined in {@link EBCompany#MOVINGCOST}).
         * Ensures the battery level does not go below zero.
         */
        public void reduceBatteryLevel(){
            if(this.batteryLevel>4){
                this.batteryLevel -=5;
            }

        }

        /**
         * Returns a detailed string representation of the electric bike.
         * @return A string containing the bike's name, plate, battery info, charge counts, costs, idle count, and route.
         */
        @Override

        //(ElectricBike: EBCC0, 15wh, 15wh, 0, 0.0€, 0, 10-13, 10-11, 1-1) 
        // (ElectricBike: EBCC1, 30wh, 30wh, 0, 0.0€, 0, 8-4, 10-5, 19-19) 

        public String toString(){
            String s = "(ElectricBike : " + idBike + ", " + batteryCapacity + ", " + batteryLevel + ", " + chargesCount + ", " + chargestCost + ", " + location.toString() + ", " + rechargingLocation.toString() + ", " + targetLocation.toString() + ")";
            return s;
        }


    }
