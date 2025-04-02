package ICT373Asn1.object;

import ICT373Asn1.exceptions.InvalidCostException;
import ICT373Asn1.exceptions.InvalidNameException;

/**
 * <p>This class has some sort of name and cost.
 * 
 * @author Cameron Sims
 * @since 11/03/2025
 */
abstract class Consumable {
    
    /**
     * <p>Creates a consumable</p>
     *
     * @param p_name The name of the consumable
     * @param p_cost The weekly cost of the consumable
	 * @throws Exception Throws if cost is not valid.
     * @throws InvalidNameException Thrown if the name is not valid.
     * @throws InvalidCostException Throws if cost is not valid.
     */
    public Consumable(final String p_name, final float p_cost) throws InvalidNameException, InvalidCostException {
        // Sets the defaults
        this.setName(p_name);
        this.setCost(p_cost);
    }
    
    /**
     * <p>Change the name of the consumable</p>
     *
     * @param p_name New name of the consumable
     * @throws InvalidNameException Thrown if the name is not valid.
     **/
    public void setName(final String p_name) throws InvalidNameException {
        if (p_name.length() == 0) {
            throw new InvalidNameException();
        }
        this.m_name = p_name;
    } 
    
    /**
     * <p>Get the name of the consumable</p>
     *
     * @return String representing the name of the consumable
     **/
    public String getName() {
        return this.m_name;
    } 
    
    /**
     * <p>Change the cost of the consumable</p>
     *
     * @param p_cost New cost of the consumable
	 * @throws InvalidCostException Throws if cost is not valid.
     **/
    public void setCost(final float p_cost) throws InvalidCostException {
        if (p_cost < 0.00f) {
            throw new InvalidCostException();
        }
        
        this.m_cost = p_cost;
    } 
    
    /**
     * <p>Get the cost of the consumable</p>
     *
     * @return float representing the cost
     **/
    public float getCost() {
        return this.m_cost;
    } 
    
    /// The name of this consumable
    private String m_name;
    
    /// The weekly cost of this consumable 
    private float m_cost;
}
