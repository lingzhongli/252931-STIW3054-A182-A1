
import java.util.*;

public class EffortEstimation {

    public static void main(String[] args) throws Exception {

        //Weighting Actors for Complexity
        int Actor_Point = Calculate_Actor_Point();

        //Weighting Use Cases for Complexity
        int total_Use_Case = Calculate_Weight_Use_Case();

        //Calculate the Unadjusted Use Case Points (UUCP)
        int UUPC = Actor_Point + total_Use_Case;
        System.out.println("Unadjusted Use Case Points (UUCP) = " + UUPC + "\n");

        //Weighting Technical Factors
        double TFactor = Calculate_Weight_Technical_Factor();

        //Calculate Technical Complexity Factor (TCF)
        double TCF = (0.01 * TFactor) + 0.6;
        System.out.println("");
        System.out.println("Technical Complexity Factor (TCF) = " + TCF + "\n");

        //Calculate the size of the software (use case) project 
        double SzUC = UUPC * TCF;
        System.out.println("Size of the software use case (SzUC) = " + SzUC + "\n");

        //Weighting Experience Factors     
        double EFactor = Calculate_Weighting_Experience_Factors();

        //Calculate the Experience Factor (EF) 
        double EF = (-0.03 * EFactor) + 1.4;
        System.out.println("Experience Factor (EF) = " + EF + "\n");

        //Calculate Use Case Points (UCP)
        double UCP = SzUC * EF;
        System.out.println("Use Case Points (UCP) = " + UCP + "\n");

        //Determine man hours
        int ER = Calculate_Man_Hours();

        //Calculate man-hours 
        double total_man_hours = ER * UCP;
        System.out.println("");
        System.out.println("Total Man Hours: " + total_man_hours + "\n");

        //Identify the assumptions and apply a coefficient as a percentage
        double percentage = Calculate_Adjusted_Man_hours();

        //Adjusted man-hours
        double adjusted_man_hours = (1.0 + percentage / 100) * total_man_hours;
        System.out.println("");
        System.out.println("Adjusted Man Hours: " + adjusted_man_hours + "\n");

        //Weighting Reports for Complexity
        int Total_Report_Man_hour_Estimate = Calculate_Weight_Report();
        System.out.println("Total Report Man Hour with Estimate: " + Total_Report_Man_hour_Estimate + "\n");

        //Calculate man-hours for the project
        double final_total_man_hours = adjusted_man_hours + Total_Report_Man_hour_Estimate;
        System.out.println("Total Man Hour After Estimate: " + final_total_man_hours + "\n");
    }

    public static int Calculate_Actor_Point() {
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Calculate Actor Point\n");
        int Actor_qnty1 = 0, Actor_qnty2 = 0, Actor_qnty3 = 0;
        int total_Actor_Point = 0;
        int x = 1;

        do {
            try {
                System.out.print("Defined Application Programming Interface (API): ");
                Actor_qnty1 = scan.nextInt();

                System.out.print("Interactive or Protocol driven interface(TCP/IP): ");
                Actor_qnty2 = scan.nextInt();

                System.out.print("Graphical User Interface (GUI): ");
                Actor_qnty3 = scan.nextInt();

                System.out.println();

                total_Actor_Point = (Actor_qnty1 * 1) + (Actor_qnty2 * 2) + (Actor_qnty3 * 3);
                System.out.println("Total Actor Point = " + total_Actor_Point + "\n");
                x = 2;
            } catch (InputMismatchException ex) {
                System.out.println("ERROR:Please enter number only!");
                scan.next();
            }
        } while (x == 1);

        return total_Actor_Point;
    }

    public static int Calculate_Weight_Use_Case() {
        Scanner scan = new Scanner(System.in);

        int usecase_qnty1, usecase_qnty2, usecase_qnty3;
        int total_Use_Case = 0;
        int x = 1;

        System.out.println("2. Calculate Weight Use Case\n");
        do {
            try {
                System.out.print("3 or fewer transactions (Simple): ");
                usecase_qnty1 = scan.nextInt();

                System.out.print("4 to 7 transactions (Average): ");
                usecase_qnty2 = scan.nextInt();

                System.out.print("Greater than 7 transactions (Complex): ");
                usecase_qnty3 = scan.nextInt();

                System.out.println();
                total_Use_Case = (usecase_qnty1 * 5) + (usecase_qnty2 * 10) + (usecase_qnty3 * 15);
                System.out.println("Total Use Case Weigth = " + total_Use_Case + "\n");
                x = 2;
            } catch (InputMismatchException ex) {
                System.out.println("ERROR:Please enter number only!");
                scan.next();
            }
        } while (x == 1);

        return total_Use_Case;
    }

    public static double Calculate_Weight_Technical_Factor() throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.println("3. Calculate Weight Technical Factor\n");
        System.out.println("Please Rate the Technical Factor below (1-5 ONLY) ");

        int[] T = new int[13];
        String name[] = {"T1 = Must have distributed solution", "T2 = Must respond to specific performance bjectives", "T3 = Must meet end-user efficiency desires",
            "T4 = Complex internal processing", "T5 = Code must be reusable", "T6 = Must be easy to install", "T7 = Must be easy to use", "T8 = Must be portable",
            "T9 = Must be easy to change", "T10= Must allow concurrent users", "T11= Includes special security features", "T12= Must provide direct access for 3rd parties",
            "T13= training facilities"};
        double[] n = {2, 1, 1, 1, 1, 0.5, 0.5, 2, 1, 1, 1, 1, 1};
        double TFactor = 0;
        int x = 1;

        do {
            try {
                for (int j = 0; j < 13; j++) {
                    System.out.println(name[j]);
                    T[j] = scan.nextInt();
                    if ((T[j] > 5) || (T[j] < 0)) {
                        throw new Exception("1-5 ONLY");
                    }
                    x = 1;
                }
            } catch (InputMismatchException ex) {
                System.out.println("ERROR:Please enter number only!");
                scan.next();
                x = 2;
            } catch (Exception ex) {
                System.out.println("ERROR: 1-5 ONLY");
                x = 2;
            }
        } while (x == 2);

        for (int j = 0; j < 13; j++) {
            TFactor += T[j] * n[j];
        }
        System.out.print("Total T Factor = " + TFactor + "\n");

        return TFactor;
    }

    public static double Calculate_Weighting_Experience_Factors() throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.println("4. Calculate Weighting Experience Factors\n");

        int[] E = new int[8];
        String[] name = {"E1 = Familiar with FPT software process", "E2 = Application experience", "E3 = Paradigm experience (OO)", "E4 = Lead analyst capability",
        		"E5 = Motivation","E6 = Stable Requirements", 
        		"E7 = Part-time workers", "E8 = Difficulty of programming language"};
        double[] n = {1, 0.5, 1, 0.5, 0, 2, -1, -1};
        double EFactor = 0;
        int x = 1;

        System.out.println("Please Rate the Technical Factor below (1-5 ONLY) ");

        do {
            try {
                for (int j = 0; j < 8; j++) {
                    System.out.println(name[j]);
                    E[j] = scan.nextInt();
                    if ((E[j] > 5) || (E[j] < 0)) {
                        throw new Exception("1-5 ONLY");
                    }
                    x = 1;
                }
            } catch (InputMismatchException ex) {
                System.out.println("ERROR:Please enter number only!");
                scan.next();
                x = 2;
            } catch (Exception ex) {
                System.out.println("ERROR: 1-5 ONLY");
                x = 2;
            }
        } while (x == 2);

        for (int j = 0; j < 8; j++) {
            EFactor += E[j] * n[j];
        }
        
        System.out.print("Total E Factor = " + EFactor + "\n");

        return EFactor;

    }

    public static int Calculate_Man_Hours() {
        Scanner scan = new Scanner(System.in);

        System.out.println("5. Calculate Man Hours\n");
        int TFR, ER = 0;
        int x = 1;

        do {
            try {
                System.out.print("Total Number Factor Rating: ");
                TFR = scan.nextInt();

                if (TFR <= 2) {
                    ER = 20;
                } else if (TFR > 2 && TFR <= 4) {
                    ER = 28;
                } else {
                    System.out.println("This project is at significant risk of failure with this team.");
                }
                x = 2;
            } catch (InputMismatchException ex) {
                System.out.println("ERROR:Please enter number only!");
                scan.next();
            }
        } while (x == 1);

        return ER;

    }

    public static double Calculate_Adjusted_Man_hours() {
        Scanner scan = new Scanner(System.in);

        System.out.println("6. Adjusted man-hours\n");
        double percentage = 0;
        int x = 1;
        do {
            try {
                System.out.print("Estimate percentage of risk(in %): ");
                percentage = scan.nextDouble();
                x = 2;
            } catch (InputMismatchException ex) {
                System.out.println("ERROR:Please enter number only!");
                scan.next();
            }
        } while (x == 1);

        return percentage;
    }

    public static int Calculate_Weight_Report() {
        Scanner scan = new Scanner(System.in);

        System.out.println("7. Weighting Reports for Complexity\n");

        int qnty1, qnty2, qnty3;
        int sub_total = 0;
        int x = 1;

        do {
            try {
                System.out.println("Report Type:");
                System.out.print("Simple: ");
                qnty1 = scan.nextInt();
                System.out.print("Average: ");
                qnty2 = scan.nextInt();
                System.out.print("Complex: ");
                qnty3 = scan.nextInt();
                System.out.println("");

                sub_total = (qnty1 * 12) + (qnty2 * 20) + (qnty3 * 40);
                x = 2;
            } catch (InputMismatchException ex) {
                System.out.println("ERROR:Please enter number only!");
                scan.next();
            }
        } while (x == 1);

        return sub_total;

    }

}
