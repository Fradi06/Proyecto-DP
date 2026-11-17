import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides a simple demonstration and simulation environment for the 
 * Electric Bike (EB) and Bike Station model.
 * It sets up the environment and runs the simulation for a fixed number of steps.
 *
 * Scenarios: 
 * <ul>
 * <li>Demo SIMPLE (demo=DemoType.SIMPLE): Two bikes are created.</li>
 * <li>Demo MEDIUM (demo=DemoType.MEDIUM): Five bikes are created.</li>
 * <li>Demo ADVANCED (demo=DemoType.ADVANCED): Eight bikes are created.</li>
 * </ul>
 * 
 * @author DP Classes
 * @version 2026
 */
public class EBikeDemo
{
    /** The maximum X coordinate for the grid (number of rows). */
    public static final int MAXX = 20; 
    /** The maximum Y coordinate for the grid (number of columns). */
    public static final int MAXY = 20; 
    
    /** The maximum number of turns. */
    public static final int MAXSTEPS = 50; 
    
    /** The company managing the EBs and Bike stations. */
    private EBCompany company; 
    
    /** 
     * The simulation's bikes; they are the electric bikes of the company. 
     * @see ElectricBike 
     */
    private List<ElectricBike> bikes; 
    
    /** 
     * The bike stations available in the city. 
     * @see BikeStation 
     */
    private List<BikeStation> stations; 
    
    /** Constant for selecting the demo scenario, using the {@link DemoType} enumeration. */
    private static final DemoType DEMO = DemoType.SIMPLE;

    /**
     * Default constructor for EBikeDemo.
     * Initializes the company and collections, and resets the simulation environment.
     */
    public EBikeDemo()
    {
        company = new EBCompany("EBBike Cáceres");
        bikes = new ArrayList<>();
        stations = new ArrayList<>();
        reset();
    }

    /**
     * Parameterized constructor for EBikeDemo.
     * Initializes the simulation with existing lists of bikes, stations, docks, and a company.
     * 
     * @param eb List of electric bikes.
     * @param bs List of bike stations.
     * @param dk List of docks to assign to their corresponding stations.
     * @param company The operating company.
     */
    public EBikeDemo(ArrayList<ElectricBike> eb, ArrayList<BikeStation> bs, ArrayList<Dock> dk, EBCompany company)
    {  
        this.company = company;
        this.bikes = eb;
        this.stations = bs;
        this.company.reset();
        
        for (ElectricBike bike : bikes) {
            this.company.addElectricBike(bike);
        }

        for (BikeStation station : stations) {
            if (dk != null) {
                for (Dock dock : dk) {
                    if (dock.getIdDock().startsWith(station.getId())) {
                        station.addDock(dock);
                    }
                }
            }
            this.company.addBikeStation(station);
        }

        Collections.sort(bikes, new ComparatorEBPlate());
        Collections.sort(stations, new ComparatorBikeStationId());

        configureRoutes();
        showInitialInfo();
    }

    /**
     * Runs the demo for a fixed number of steps (MAXSTEPS).
     * In each step, all bikes perform their action.
     */
    public void run()
    {        
        for (int step = 0; step < MAXSTEPS; step++) {
            step(step);
        }
        showFinalInfo();
    }

    /**
     * Runs the demo for one step by requesting all bikes to act in ascending order of their ID.
     * Displays the state of each bike after acting.
     * 
     * @param step The current step number of the simulation.
     */
    public void step(int step)
    {
        for (ElectricBike bike : bikes) {
            bike.act();
            // Se muestra el estado de la bici en el paso actual siguiendo el Anexo I
    
            String bikeInfo = bike.toString().replaceFirst("^\\(", "").replaceFirst("\\)$", "");
            System.out.println("(step: " + step + " " + bikeInfo + ")");
        }
    }

    /**
     * Resets the demo to a starting point.
     * Clears existing bikes and stations, resets the company, and re-creates
     * the initial setup.
     */
    public void reset()
    {
        bikes.clear();
        stations.clear();
        company.reset();
        createElectricBikes();
        createStations(); 
        createDocks();
        configureRoutes();
        showInitialInfo();
    }

    /**
     * Creates the {@link ElectricBike}s based on the selected {@link DemoType}, assigns them
     * starting and target {@link Location}s, and adds them to both the local list and the company.
     * The bikes list is sorted by plate using {@link ComparatorEBPlate}.
     */
    private void createElectricBikes() 
    {
        Location[] locations = {
            new Location(10, 13), new Location(8, 4), new Location(8, 4), new Location(15, 10), 
            new Location(1, 1), new Location(2, 2), new Location(11, 13), new Location(14, 16)
        };
        Location[] targetLocations = {
            new Location(1, 1), new Location(19, 19), new Location(12, 17), new Location(4, 4), 
            new Location(1, 10), new Location(5, 5), new Location(8, 7), new Location(19, 19)
        };
                                        
        for (int i = 0; i < DEMO.getNumBikesToCreate(); i++) {
            ElectricBike eb = new ElectricBike(company, locations[i], targetLocations[i], ("EBCC" + i), (i + 1) * 15);
            bikes.add(eb);
            company.addElectricBike(eb);
        }
        
        Collections.sort(bikes, new ComparatorEBPlate());
    }

    /**
     * Creates predefined {@link BikeStation}s and adds them to both the local list and the company.
     * The stations list is sorted by ID using {@link ComparatorBikeStationId}.
     */
    private void createStations() 
    {  
        Location[] locations = {
            new Location(10, 5), new Location(10, 11), new Location(14, 16), new Location(8, 4)
        };
                                
        for (int i = 0; i < DEMO.getNumStationsToCreate(); i++) {
            BikeStation bs = new BikeStation("CC0" + i, locations[i]);
            stations.add(bs);
            company.addBikeStation(bs);
        }
        
        Collections.sort(stations, new ComparatorBikeStationId());
    }

    /**
     * Creates a fixed number of {@link Dock} units for each {@link BikeStation}
     * and adds them to the station (which keeps them ordered).
     */
    private void createDocks() 
    {  
        for (BikeStation station : stations) {
            for (int i = 0; i < DEMO.getNumDocksToCreate(); i++) {
                // Creates Docks with varying speed and fee based on index 'i'.
                Dock d = new Dock(station.getId() , ((i + 1) * 20), ((i + 1) * 0.20f));
                station.addDock(d);
            }
        }    
    }
    
    /**
     * Instructs each {@link ElectricBike} to calculate its initial route.
     * This determines if an intermediate recharging stop is necessary.
     */
    private void configureRoutes() 
    {
        for (ElectricBike bike : bikes) {
            bike.calculateRoute();
        }
    }

    /**
     * Displays the initial information for the simulation, including the company name
     * and the details of all {@link ElectricBike}s, {@link BikeStation}s, and their {@link Dock}s.
     */
    private void showInitialInfo() 
    {
        System.out.println("(Compañía " + company.getCompanyName() + " )");
        System.out.println("(-------------------)");
        System.out.println("( Electric Bikes )");
        System.out.println("(-------------------)");

        for (ElectricBike bike : bikes) {
            System.out.println(bike);
        }

        System.out.println("(-------------------)");
        System.out.println("( Bike Stations )");
        System.out.println("(-------------------)");
       
        for (BikeStation station : stations) {
            System.out.println(station);
            for (Dock dock : station.getDocks()) {
                System.out.println(dock);
            }
        }

        System.out.println("(------------------)");
        System.out.println("( Simulation start )");
        System.out.println("(------------------)");
    }

    /**
     * Displays the final information after the simulation has run.
     * Bikes are sorted by arrival step using {@link ComparatorEBArrivingStep}.
     * Stations are sorted by the number of recharges using {@link ComparatorBikeStationNumerRecharged}.
     */
    private void showFinalInfo() 
    {
        System.out.println("(-------------------)");
        System.out.println("( Final information )");        
        System.out.println("(-------------------)");
        
        System.out.println("(-------------------)");
        System.out.println("( Electric Bikes )");
        System.out.println("(-------------------)");
        
        Collections.sort(bikes, new ComparatorEBArrivingStep());
        for (ElectricBike bike : bikes) {
            System.out.println(bike);

            for (Dock dock : bike.getDocks()) {
                System.out.println("(Dock: " + dock.getIdDock() + ")");
            }
        }

        System.out.println("(-------------------)");
        System.out.println("( Bike Stations )");
        System.out.println("(-------------------)");
        
        Collections.sort(stations, new ComparatorBikeStationNumerRecharged());
        for (BikeStation station : stations) {
            System.out.println(station);
            for (Dock dock : station.getDocks()) {
                System.out.println(dock);
                for (ElectricBike rechargedBike : dock.getNumerEBRecharged()) {
                    System.out.println(rechargedBike);
                }
            }
        }
    }
    
    /**
     * The main entry point for running the EBikeDemo simulation.
     * Creates an instance of {@code EBikeDemo} and starts the simulation.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args)
    {
        EBikeDemo demo = new EBikeDemo();
        demo.run();
    }
}