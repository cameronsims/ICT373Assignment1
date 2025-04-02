package ICT373Asn1.object;

import ICT373Asn1.exceptions.InvalidCostException;
import ICT373Asn1.exceptions.InvalidNameException;
import java.util.ArrayList;

/**
 * <p>A Magazine that can be subscribed to</p>
 *
 * @author Cameron Sims
 * @since 11/03/2025
 * 
 *
 */
public class Magazine extends Consumable {
    /**
     * <p>Creates a magazine</p>
     *
     * @param p_name The name of the magazine
     * @param p_cost The weekly cost of the magazine
     * @throws InvalidNameException Thrown if the name is not valid.
	 * @throws InvalidCostException Throws if cost is not valid.
     */
    public Magazine(final String p_name, final float p_cost) throws InvalidNameException, InvalidCostException {
        // Set important values.
        super(p_name, p_cost);
        
        // Create empty list...
        this.m_supplements = new ArrayList<>();
    }
    
    /**
     * <p>Adds a supplement to the magazine.</p>
     *
     * @param p_supplement The supplement that is going to be added
     */
    public void addSupplement(final Supplement p_supplement) {
        this.m_supplements.add( p_supplement );
    }
    
    /**
     * <p>Gets all supplements that this magazine owns</p>
     *
     * @return Returns the list of all supplements
     */
    public ArrayList<Supplement> getSupplements() {
        return this.m_supplements;
    }
    
    /// The supplements that the magazine has.
    private ArrayList<Supplement> m_supplements;
}
