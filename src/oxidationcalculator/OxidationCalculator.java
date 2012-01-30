/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationcalculator;

import java.util.Scanner;
import oxidationSession.*;
import oxidationModel.SiOxidationModel;
import oxidationModel.genericOxidationModel;

/**
 *
 * @author ollivanhoja
 */
public class OxidationCalculator {  
    static UISession session = new UISession();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        while(true)
        {
            System.out.print("# ");
            String line = input.nextLine();
            String cmd[] = line.split(" ");
            if (cmd[0].equals("exit") || cmd[0].equals("quit"))
                break;
            else if (cmd[0].equals("setwafer"))
            {
                if (cmd.length < 3)
                    System.out.println("Unknown command!");
                else
                {
                    SiWafer.eWaferOrientation orientation = null;
                    if (cmd[1].equals("<100>"))
                    {
                        orientation = SiWafer.eWaferOrientation.mi100;
                    }
                    else if (cmd[1].equals("<111>"))
                    {
                        orientation = SiWafer.eWaferOrientation.mi111;
                    }
                    else
                        System.out.println("Unknown wafer type!");
                    if (orientation != null)
                    {
                        session.setWafer(orientation, Double.parseDouble(cmd[2]));
                        System.out.println("Si wafer selected: " + session.getWafer().getOrientation() + ", h=" + session.getWafer().get_h() + " um");
                    }
                }
            }
            else if (cmd[0].equals("addphase"))
            {
                addPhase(line);
            }
            else if (cmd[0].equals("addmodel"))
            {
                if (cmd.length == 7)
                {
                    genericOxidationModel model;
                    double D0BA, D0B, EaBA, EaB, Xi;
                    D0BA = Integer.parseInt(cmd[1]);
                    D0B  = Integer.parseInt(cmd[2]);
                    EaBA = Integer.parseInt(cmd[3]);
                    EaB  = Integer.parseInt(cmd[4]);
                    Xi   = Integer.parseInt(cmd[5]);
                    model = new genericOxidationModel(EaBA, EaB, D0BA, D0B, session.getWafer(), Xi);
                    session.oxidationModels.add(model, cmd[6]);
                    System.out.println("Added oxidation model: "
                    + model.getD0BA() + ", " + model.getD0B() + ", "
                    + model.getEaBA() + "eV, " + model.getEaB() + "eV, "
                    + model.getDefault_Xi() + "um, "
                    + cmd[6]);
                }
                else
                    System.out.println("Error in command!");
            }
            else if (cmd[0].equals("clear"))
                session.clear();
            else if (cmd[0].equals("delphase"))
            {
                del(Integer.parseInt(cmd[1]));
            }
            else if (cmd[0].equals("printphases"))
            {
                if(!session.getNoData())
                    printPhases();
                else
                    System.out.println("There is no oxidation session!");
            }
            else if(cmd[0].equals("printmodels"))
            {
                printOxidationModels();
            }
            else if (cmd[0].equals("help"))
            {
                System.out.println("setwafer <100>/<111> h\n"
                        + "addphase t/Xo [time in hr / Xo in um] [T in Kelvins] wet/dry\n"
                        + "addmodel D0BA D0B EaBA EaB Xi name"
                        + "delphase i\n"
                        + "clear\n"
                        + "calculate\n"
                        + "printphases\n"
                        + "printmodels\n"
                        + "quit");
            }
            else if (cmd[0].equals("calculate"))
            {
                if (!session.getNoData())
                    calculateSession();
                else
                    System.out.println("No data to calculate!");
            }
            else
                System.out.println("No such command!");
        }
    }
    
    /**
     * Calculate outcome fro whole process
     */
    static void calculateSession()
    {
        session.oxs.calculate();
        System.out.println("Xo=" + session.oxs.get_Xo() + ", h_wafer=" + session.oxs.get_hW());
        double t_tot = session.oxs.get_t_tot();
        System.out.println("t_tot=" + t_tot + " h, " + t_tot*60 + " min");
    }
    
    static void addPhase(String line)
    {
        String cmd[] = line.split(" ");
        
        if (session.getWafer() != null)
        {
            if (!(cmd.length < 4))
            {
                int model = Integer.parseInt(cmd[4]);
                if (model <= session.oxidationModels.size())
                {
                    if (cmd[1].equals("t"))
                    {
                        session.addConsttPhase(Double.parseDouble(cmd[3]), Double.parseDouble(cmd[2]), model);
                        System.out.println("Added phase: T=" + cmd[3]
                                + " K, t=" + cmd[2] + " hr"
                                + ", model=" + session.oxidationModels.get(model).toString());
                    }
                    else if (cmd[1].equals("Xo"))
                    {
                        session.addConstXoPhase(Double.parseDouble(cmd[3]), Double.parseDouble(cmd[2]), model);
                        System.out.println("Added phase: T=" + cmd[3]
                                + " K, Xo=" + cmd[2] + " um"
                                + ", model=" + session.oxidationModels.get(model).toString());
                    }
                    else
                        System.out.println("Unknown constant value: " + cmd[1]);
                }
                else
                    System.out.println("Unknown oxidation model: " + model);
            }
            else
                System.out.println("Unknown command!");
        }
        else
            System.out.println("Wafer not set!");
    }
    
    static void del(int i)
    {
        if (session.oxs != null)
        {
            try
            {
                session.oxs.removePhase(i);
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Index out of bounds!");
            }
            finally
            {
                System.out.println("Phase #" + i + " removed succesfully.");
            }
        }
    }
    
    /**
     * Print all oxidation phases
     */
    static void printPhases()
    {
        System.out.println("Si wafer selected: " + session.getWafer().getOrientation() + ", " + session.getWafer().get_h() + " um");
        calculateSession();
        System.out.println("---");
   
        System.out.println("#\tT\tXi\tXo\tt\tmodel\n"
                + "\tK\tum\tum\tmin");
        for (int i=0; i < session.oxs.getSize(); i++)
        {
            oxidationSession.oxidationPhase phase = session.oxs.getPhase(i);
            System.out.println(i + "\t" + phase.T + "\t" 
                    + phase.getXi() + "\t" + phase.XoPhase
                    + "\t" + phase.t*60 + "\t"
                    + "\t" + phase.oModel.toString());
        }
    }
    
    /**
     * Print oxidation models
     */
    static void printOxidationModels()
    {
        System.out.println("#\tD_0(BA)\tD_0(B)\tE_a(BA)\tE_a(B)\tX_i(def)\tname");
        System.out.println("\t\t\teV\teV\tum");
        for (int i=0; i < session.oxidationModels.size(); i++)
        {
            SiOxidationModel model = session.oxidationModels.get(i);
            String name = session.oxidationModels.getName(i);
            System.out.println(i + "\t" + model.getD0BA() + "\t" + model.getD0B() + "\t"
                    + model.getEaBA() + "\t" + model.getEaB() + "\t"
                    + model.getDefault_Xi() + "\t"
                    + name);
        }
    }
}