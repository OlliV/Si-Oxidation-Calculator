/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationcalculator;

import java.util.Scanner;
import oxidationSession.SiWafer;
import oxidationModel.*;
import oxidationSession.*;

/**
 *
 * @author ollivanhoja
 */
public class OxidationCalculator {  
    static Session session = new Session();
    
    static class Session
    {
        oxidationSession oxs;
        SiWafer wafer;
        dryOxidationModel procDry;
        wetOxidationModel procWet;
        boolean no_data;
        
        public Session()
        {
            clear();
        }
        public void clear()
        {
            no_data = true;
            procDry = null;
            procWet = null;
            oxs = new oxidationSession();
        }
                
    }
    
    static void setWafer(SiWafer.eWaferOrientation orientation, double h)
    {
        session.clear();
        session.wafer = new SiWafer(orientation, h);
        session.procDry = new dryOxidationModel(session.wafer);
        session.procWet = new wetOxidationModel(session.wafer);
        //SiWafer wafer = new SiWafer(SiWafer.eWaferOrientation.mi100, 10);
        //dryOxidationModel procDry = new dryOxidationModel(wafer);
        //wetOxidationModel procWet = new wetOxidationModel(wafer);
    }
    
    static void calculateSession()
    {
        //oxs.addPhase(1273.15, 1.0, procWet, false);
        //oxs.addPhase(1273.15, (float)1.0, procDry, false);
        //oxs.addPhase(1373.15, (float)5.0, procWet, false);
        session.oxs.calculate();
        System.out.println("Xo=" + session.oxs.get_Xo());
        double t_tot = session.oxs.get_t_tot();
        System.out.println("t_tot=" + t_tot + " h, " + t_tot*60 + " min");
    }
    
    static void printPhases()
    {
        System.out.println("Si wafer selected: " + session.wafer.getOrientation() + ", " + session.wafer.get_h() + " um");
        calculateSession();
        System.out.println("---");
        int i=0;
        for (oxidationSession.oxidationPhase phase : session.oxs.phases)
        {
            System.out.println("#" + i + " : T=" + phase.T + " K, Xi=" 
                    + phase.Xi + " um, Xo=" + phase.oModel.getWafer().getXo()
                    + ", t=" + phase.t*60 + " min"
                    + ", model=" + phase.oModel.toString());
            i++;
        }
    }

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
                        setWafer(orientation, Double.parseDouble(cmd[2]));
                        System.out.println("Si wafer selected: " + session.wafer.getOrientation() + ", h=" + session.wafer.get_h() + " um");
                    }
                }
            }
            else if (cmd[0].equals("addphase"))
            {
                oxidationModel.SiOxidationModel model = null;
                if (session.procDry != null && session.procWet != null)
                {
                    if (!(cmd.length < 4))
                    {
                        if (cmd[4].equals("wet"))
                            model = session.procWet;
                        else if (cmd[4].equals("dry"))
                            model = session.procDry;
                        if (model != null)
                        {
                            if (cmd[1].equals("t"))
                            {
                                session.oxs.addPhase(Double.parseDouble(cmd[2]), Double.parseDouble(cmd[3]), model, false);
                                System.out.println("Added phase: T=" + cmd[2]
                                        + " K, t=" + cmd[3] + " hr"
                                        + ", model=" + model.toString());
                                session.no_data = false;
                            }
                            else if (cmd[1].equals("Xo"))
                            {
                                session.oxs.addPhase(Double.parseDouble(cmd[2]), Double.parseDouble(cmd[3]), model, true);
                                System.out.println("Added phase: T=" + cmd[2]
                                        + " K, Xo=" + cmd[3] + " um"
                                        + ", model=" + model.toString());
                                session.no_data = false;
                            }
                            else
                                System.out.println("Unknown command!");
                        }
                        else
                            System.out.println("Unknown oxidation model!");
                    }
                    else
                        System.out.println("Unknown command!");
                }
                else
                    System.out.println("Wafer not set!");
            }
            else if (cmd[0].equals("clear"))
                session.clear();
            else if (cmd[0].equals("printphases"))
            {
                if(!session.no_data)
                    printPhases();
                else
                    System.out.println("There is no oxidation session!");
            }
            else if (cmd[0].equals("help"))
            {
                System.out.println("setwafer <100>/<111> h\n"
                        + "addphase t/Xo [T in Kelvins] [time in hr / Xo in um] wet/dry\n"
                        + "clear\n"
                        + "calculate\n"
                        + "printphases\n"
                        + "quit");
            }
            else if (cmd[0].equals("calculate"))
            {
                if (!session.no_data)
                    calculateSession();
                else
                    System.out.println("No data to calculate!");
            }
            else
                System.out.println("No such command!");
        }
    }
}