
/**
 * Model a location in a two-dimensional grid city using (x, y) coordinates.
 * @author David J. Barnes and Michael Kölling
 * @author DP classes 
 * @version 2026.10.13
 */
public class Location
{
    private int x;
    private int y;

    /**
     * Model a location in the city.
     * @param x The x coordinate. Must be non-negative.
     * @param y The y coordinate. Must be non-negative.
     * @throws IllegalArgumentException If a coordinate is negative.
     */
    public Location(int x, int y)
    {
        if(x < 0) {
            throw new IllegalArgumentException(
                        "Negative x-coordinate: " + x);
        }
        if(y < 0) {
            throw new IllegalArgumentException(
                        "Negative y-coordinate: " + y);
        }        
        this.x = x;
        this.y = y;
    }
    
    /**
     * Generates the next location to visit in a direct line (Manhattan-style diagonal/straight move) 
     * from this location to the destination.
     * @param destination The {@link Location} we want to get to.
     * @return The next {@link Location} one step closer to the destination. Returns the destination
     * if this location is already the destination.
     */
    
    
    public Location nextLocation(Location destination) //obtiene la siguiente, no la cambia.
    {
        int currentLocX = x;
        int currentLocY = y;

        Location siguientePos = new Location(x,y); //predeterminado si ya ha llegado.

        int whereToGoX = destination.x;
        int whereToGoY = destination.y;
        
        if (currentLocX < whereToGoX){
            currentLocX +=1; //imagino que esto no cambia la variable de esta clase y es simplemente una copia.
            siguientePos.setX(currentLocX);
        }
        else if (currentLocX > whereToGoX){
            currentLocX -=1;
            siguientePos.setX(currentLocX);
        }
        
        if (currentLocY < whereToGoY){
            currentLocY += 1;
            siguientePos.setY(currentLocY);
        }
        else if (currentLocY > whereToGoY){
            currentLocY -=1;
            siguientePos.setY(currentLocY);
        }

        return siguientePos;
    }

    /**
     * Determines the number of movements required to get from here to the destination, 
     * using the Chebyshev distance (maximum of the absolute differences of the coordinates).
     * @param destination The required destination {@link Location}.
     * @return The number of movement steps.
    */

    public int distance(Location destination)
    {
        int xAbsoluto = Math.abs(destination.x - x);
        int yAbsoluto = Math.abs(destination.y - y);
        return Math.max(xAbsoluto, yAbsoluto);
    }
    
    /**
     * Implement content equality for locations.
     * @param other The object to compare with.
     * @return {@code true} iddthe other object is a {@link Location} and has the same x and y coordinates, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other)
    {
         //TODO: Complete this code
        return true;
    }
    
    /**
     * @return A string representation of the location in the format "x-y".
     */
    @Override
    public String toString()
    {
        return x + "-" + y;
    }

    /**
     * Generates a hash code for the location.
     * It uses the top 16 bits for the y value and the bottom 16 bits for the x value
     * to ensure a unique hash code for most grid sizes.
     * @return A hashcode for the location.
     */
    @Override
    public int hashCode()
    {
        return (y << 16) + x;
    }

    /**
     * @return The x coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY()
    {
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }
}