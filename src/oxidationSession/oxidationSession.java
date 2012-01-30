package oxidationSession;

import java.util.ArrayList;
import oxidationModel.*;

/**
 * Class for calculating silicon wafer oxidation
 * @author ollivanhoja
 */
public class oxidationSession
{
    private ArrayList<oxidationSession.oxidationPhase> phases;
    private int i;
    private double t_tot;
    
    public double get_t_tot()
    {
        return t_tot;
    }
    public double get_Xo()
    {
        return phases.get(i).oModel.getWafer().getXo();
    }
    /**
     * Get h of wafer after oxidation
     * @return 
     */
    public double get_hW()
    {
        return phases.get(i).oModel.getWafer().get_h();
    }
    public int getI()
    {
        return i;
    }
    public void setI(int i)
    {
        this.i = i;
    }

    public class oxidationPhase
    {
        /**
        * Temperature in Kelvin degrees
        */
        public double T;
        /**
        * Time t
        */
        public double t;
        
        /**
        * Initial thickness of oxide on wafer
        */
        public double getXi()
        {
            return this.Xi;
        }
        /**
        * Initial thickness of oxide on wafer
        */
        public void setXi(double Xi)
        {
            this.Xi = Xi;
        }
        
        
        public double XoSet;
        
        public double XoPhase;

        /**
         * Oxidation process model
         */
        public SiOxidationModel oModel;
        
                /**
        * Initial thickness of oxide on wafer
        */
        private double Xi;
        private boolean calc_t;

        /**
         * Create oxidation phase
         * @param T Temperature in Kelvin degrees
         * @param var Variable X_o [um] or t [hr]
         * @param Xi Initial thickness of the wafer
         * @param oModel Oxidation process model
         * @param calc_t calculate t if true else X_o
         */
        public oxidationPhase(double T, double var, double Xi, SiOxidationModel oModel, boolean calc_t)
        {
            this.T = T;
            this.Xi = Xi;
            this.oModel = oModel;
            this.calc_t = calc_t;
            if (calc_t)
            {
                this.t = 0.0;
                XoSet = var;
            }
            else
            {
                this.t = var;
                XoSet = 0.0;
            }
        }

        /**
         * Calculate parameters for the phase
         */
        public void calculate()
        {
            if (calc_t)
            {
                oModel.getWafer().setXo(oModel.getWafer().getXo() + XoSet);
                if (oModel.getWafer().getXo() < Xi)
                    throw new IllegalArgumentException("Xo can't be zero or < Xi.");
                t = Math.pow(oModel.getWafer().getXo(), 2) / oModel.B.getD(T) + oModel.getWafer().getXo() / oModel.BA.getD(T) - tau();
                XoPhase = XoSet;
            }
            else
            {
                if (t <= 0)
                    throw new IllegalArgumentException("t can't be zero or negative.");
                XoPhase = 0.5 * ((1/oModel.BA.getD(T))*oModel.B.getD(T)) * (Math.sqrt(1 + 4*oModel.B.getD(T) / Math.pow(((1/oModel.BA.getD(T))*oModel.B.getD(T)), 2) * (t + tau())) - 1);
                oModel.getWafer().setXo(XoPhase);
            }
        }

        /**
         * Get correction factor for oxidation time
         * @return Correction factor tau
         */
        private double tau()
        {
            return Math.pow(Xi, 2) / oModel.B.getD(T) + Xi / oModel.BA.getD(T);
        }
    }

    /**
     * Create oxidation session
     */
    public oxidationSession()
    {
        phases = new ArrayList<oxidationSession.oxidationPhase>();
        i = 0;
        t_tot = 0;
    }

    /**
     * Add new oxidation phase
     * @param T Temperature in Kelvin degrees
     * @param var Variable X_o [um] or t [hr]
     * @param oModel Oxidation process model
     * @param calc_t Calculate t if true else X_o
     */
    public void addPhase(double T, double var, SiOxidationModel oModel, boolean calc_t)
    {
        phases.add(new oxidationSession.oxidationPhase(T, var, oModel.getDefault_Xi(), oModel, calc_t));
    }
    
    /**
     * Remove phase
     * @param ind Index of phase to be removed
     */
    public void removePhase(int ind)
    {
        phases.remove(ind);
        
        // Update initial oxidation thickness parameter
        if (phases.size() >= 1)
            phases.get(0).Xi = phases.get(0).oModel.getDefault_Xi();
    }
    
    /**
     * Remove last phase
     */
    public void removePhase()
    {
        phases.remove(phases.size() - 1);
    }
    
    /**
     * Trim array of phases
     */
    public void trim()
    {
        phases.trimToSize();
    }
    
    /**
     * 
     */
    public int getSize()
    {
        return phases.size();
    }
    
    public oxidationPhase getPhase(int ind)
    {
        return phases.get(ind);
    }
    
    public oxidationPhase getPhase()
    {
        return phases.get(i);
    }
    
    /**
     * Calculate phase i and increment i
     */
    public void calculateNextPhase()
    {
        //TODO Throw exeception if last
        
        phases.get(i).calculate();
        t_tot += phases.get(i).t;
        i++;
    }
    
    /**
     * Calculate all phases
     */
    public void calculate()
    {           
        t_tot = 0;
        phases.get(0).oModel.getWafer().setXo(0.0);
        if (phases.size() >= 1)
            phases.get(0).setXi(phases.get(0).oModel.getDefault_Xi());
                
        for (i=0; i < phases.size(); i++)
        {
            oxidationPhase phase = phases.get(i);
            if (i > 0)
                phase.Xi = phases.get(i - 1).oModel.getWafer().getXo();
            phase.calculate();
            t_tot += phase.t;
        }
        i--;
    }
}
