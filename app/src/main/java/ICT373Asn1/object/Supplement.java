package ICT373Asn1.object;

import ICT373Asn1.exceptions.InvalidCostException;
import ICT373Asn1.exceptions.InvalidNameException;

/**
 * <p>A Supplement that can be subscribed to</p>
 * 
 * @author Cameron Sims
 * @since 11/03/2025
 */
public class Supplement extends Consumable {
    /**
     * <p>The supplement that belongs to a magazine.</p>
     *
     * @param p_magazine The magazine that the supplement belongs to
     * @param p_name The name of the magazine
     * @param p_cost The cost of the supplement
     * @throws InvalidNameException Thrown if the name is not valid.
     * @throws InvalidCostException Throws if cost is not valid.
     */
    public Supplement(Magazine p_magazine, String p_name, float p_cost) throws InvalidNameException, InvalidCostException {
        // Set important values.
        super(p_name, p_cost);
        
        // Set as a reference.
        this.m_magazine = p_magazine;
        this.m_magazine.addSupplement(this);
    }
    
    /**
     * <p>Gets the magazine associated with the supplement</p>
     *
     * @returns Magazine representing the master object.
     **/
    Magazine getMagazine() {
        return m_magazine;
    }
    
    
    /// The master magazine, this is a reference.
    private Magazine m_magazine;
}
