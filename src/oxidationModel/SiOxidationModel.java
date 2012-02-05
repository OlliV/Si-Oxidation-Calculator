/**
 * Oxidation model for silicon wafers
 * @author ollivanhoja
 */
package oxidationModel;

/**
 * Oxidation model for silicon wafers
 * @author ollivanhoja
 */
interface IOxidationModel {
    /**
     * E_A = Activation energy for linear rate constant
     * @return E_a for B/A
     */
    public double getEaBA();
    /**
     * E_A Activation energy for linear rate constant
     * @param EaBA E_a for B/A
     */
    public void setEaBA(double EaBA);
    
    /**
     * E_A Activation energy for parabolic rate constant
     * @return E_a for B
     */   
    public double getEaB();
    /**
     * E_A Activation energy for parabolic rate constant
     * @param EaB E_a for B
     */
    public void setEaB(double EaB);
    
    /**
     * Diffusivity coefficient for linear rate constant
     * @return D_0 for B/A
     */
    public double getD0BA();
    /**
     * Diffusivity coefficient for linear rate constant
     * @param D0BA D_0 for B/A
     */
    public void setD0BA(double D0BA);
    
    /**
     * Diffusivity constant for parabolic rate constant
     * @return D_0 for B
     */
    public double getD0B();
    /**
     * Diffusivity coefficient for parabolic rate constant
     * @param D0B D_0 for B
     */
    public void setD0B(double D0B);
    
    /**
     * Silicon wafer
     * @return 
     */
    public SiWafer getWafer();
    /**
     * Silicon wafer
     * @param wafer 
     */
    public void setWafer(SiWafer wafer);
    
    /**
     * Get start value (default) X_i for selected oxidation model
     * @return Default X_i in um
     */
    public double getDefault_Xi();
    
    public String getName();
    
    public void setName(String name);
}

/**
 *Oxidation process model for silicon wafer
 * @author ollivanhoja
 */
public class SiOxidationModel implements IOxidationModel
{
    /**
     * E_A = Activation energy
     */
    protected double EaBA;
    protected double EaB;
    /**
     * D_0 = Diffusivity coefficient
     */
    protected double D0BA;
    protected double D0B;
    
    protected SiWafer wafer;
    
    protected double _Xi; // Default
    
    protected String name;
    
    @Override
    public double getEaBA()
    {
        return this.EaBA;
    }
    @Override
    public void setEaBA(double EaBA)
    {
        this.EaBA = EaBA;
    }
    
    @Override
    public double getEaB()
    {
        return this.EaB;
    }
    @Override
    public void setEaB(double EaB)
    {
        this.EaB = EaB;
    }
    
    @Override
    public double getD0BA()
    {
        return this.D0BA;
    }
    @Override
    public void setD0BA(double D0BA)
    {
        this.D0BA = D0BA;
    }
    
    @Override
    public double getD0B()
    {
        return this.D0B;
    }
    @Override
    public void setD0B(double D0B)
    {
        this.D0B = D0B;
    }
    
    @Override
    public SiWafer getWafer()
    {
        return this.wafer;
    }
    @Override
    public void setWafer(SiWafer wafer)
    {
        this.wafer = wafer;
    }

    @Override
    public double getDefault_Xi()
    {
        return _Xi;
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Linear rate constant
     */
    public SiOxidationModel.ArrheniusRl BA;
    /**
     * Parabolic rate constant
     */
    public SiOxidationModel.ArrheniusRl B;
    
    /**
     * Arrhenius relationship
     */
    public class ArrheniusRl
    {
        double D0;
        double Ea;
        /**
         * Boltzmann constant
         */
        final double kB = 0.000086173324;

        public ArrheniusRl(double D0, double Ea)
        {
            this.D0 = D0;
            this.Ea = Ea;
        }

        public double getD(double T)
        {
            return D0 * Math.exp(-Ea/(kB*T));
        }
    }
}