import java.util.*; 

/**
 * Compares two {@link Dock} objects based on their **ID** string in **ascending** order.
 * This is typically used as a final tie-breaker in multi-criteria sorting.
 * @author DP Clasess
 * @version 2026.10.13
 */
public class ComparatorDocksId implements Comparator<Dock>
{
    /**
     * Compares its two {@link Dock} arguments for order.
     * The comparison is based on the lexicographical order of their IDs.
     * @param c1 The first dock to be compared.
     * @param c2 The second dock to be compared.
     * @return A negative integer, zero, or a positive integer as the first 
     * dock's ID is lexicographically less than, equal to, or greater than the second.
     */
    public int compare(Dock c1, Dock c2){  
        if (c1.getIdDock().compareTo(c2.getIdDock()) < 0) {
            return -1; // c1's ID is less than c2's ID
        } else if (c1.getIdDock().compareTo(c2.getIdDock()) > 0) {
            return 1; // c1's ID is greater than c2's ID
        }  
        return 0;  
    } 
}
