/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationSession;

/**
 * Silicon wafer material
 * @author ollivanhoja
 */
public class SiWafer {

    /**
     * Crystal orientation of the wafer
     * @return the crystal orientation
     */
    public eWaferOrientation getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the crystal orientation to set
     */
    public void setOrientation(eWaferOrientation orientation) {
        this.orientation = orientation;
    }
    
    /**
     * Possible crystal orientations for silicon wafer
     */
    public enum eWaferOrientation
    {
        mi100,
        mi111;
        
        @Override
        public String toString(){
            String[] splitNames = name().toLowerCase().split("_");
            StringBuffer fixedName = new StringBuffer();

            for(int i = 0; i < splitNames.length; i++)
            {
                String firstLetter = splitNames[i].substring(0, 1).toUpperCase(),
                restOfWord = splitNames[i].substring(1),
                spacer = (i == splitNames.length) ? "" : " ";

                fixedName.append(firstLetter).append(restOfWord).append(spacer);
            }

            return fixedName.toString();
        }
    }
    
    /**
     * X_o = oxidation layer thickness on wafer
     */
    public double getXo()
    {
        return Xo;
    }
    /**
     * X_o = oxidation layer thickness on wafer
     */
    public void setXo(double Xo)
    {
        this.Xo = Xo;
    }
    
     /**
     * Get h of wafer
     * @return 
     */
    public double get_h()
    {
        return h + (0.54 * Xo);
    }
    /**
     * Set h of wafer
     * @param h 
     */
    public void set_h(double h)
    {
        this.h = h;
    }
    
    private eWaferOrientation orientation;
    private double h;
    private double Xo;
   
    
    /**
     * Silicon wafer
     * @param orientation
     * @param h 
     */
    public SiWafer(eWaferOrientation orientation, double h)
    {
        this.orientation = orientation;
        this.h = h;
        Xo = 0.0;
    }
}
