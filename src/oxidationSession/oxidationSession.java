package oxidationSession;

import java.util.ArrayList;
import oxidationModel.*;

/**
 * Class for calculating silicon wafer oxidation
 * @author ollivanhoja
 */
public class oxidationSession
{
    public ArrayList<oxidationSession.oxidationPhase> phases;
    public int i;
    private double t_tot;
    
    public double get_t_tot()
    {
        return t_tot;
    }
    public double get_Xo()
    {
        int k;
        k = (i > 0) ? i - 1 : i;
        return phases.get(k).oModel.getWafer().getXo();
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
        public double Xi;

        /**
         * Oxidation process model
         */
        public SiOxidationModel oModel;
        
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
                this.oModel.getWafer().setXo(var);
            }
            else
            {
                this.t = var;
                this.oModel.getWafer().setXo(0.0);
            }
        }

        /**
         * Calculate parameters for the phase
         */
        public void calculate()
        {
            if (calc_t)
            {
                if (oModel.getWafer().getXo() < Xi)
                    throw new IllegalArgumentException("Xo can't be zero or < Xi.");
                t = Math.pow(oModel.getWafer().getXo(), 2) / oModel.B.getD(T) + oModel.getWafer().getXo() / oModel.BA.getD(T) - tau();
            }
            else
            {
                if (t <= 0)
                    throw new IllegalArgumentException("t can't be zero or negative.");
                oModel.getWafer().setXo(0.5 * ((1/oModel.BA.getD(T))*oModel.B.getD(T)) * (Math.sqrt(1 + 4*oModel.B.getD(T) / Math.pow(((1/oModel.BA.getD(T))*oModel.B.getD(T)), 2) * (t + tau())) - 1));
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
        if ((phases.size() - 1) < 0)
            phases.add(new oxidationSession.oxidationPhase(T, var, oModel.getDefault_Xi(), oModel, calc_t));
        else
        {
            double Xi = phases.get(phases.size() - 1).oModel.getWafer().getXo();
            phases.add(new oxidationSession.oxidationPhase(T, var, Xi, oModel, calc_t));
        }
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
        i = phases.size();
        t_tot = 0;
        for (oxidationSession.oxidationPhase phase : phases)
        {
                phase.calculate();
                t_tot += phase.t;
        }
    }
}
