import java.io.*;
import java.util.ArrayList;

class EmployeeData {

    String name = new String();

    ArrayList<String> returnData = new ArrayList<String>();

    String empfinaldata = new String();

    String role = new String();

    public EmployeeData(String empdata[]) {
        String f_name = empdata[1];
        String l_name = empdata[2];
         role = empdata[5].substring(0, empdata[5].length()-1);
         concatnames(f_name, l_name);
        empfinaldata = empdata[0] +"," + name +"," + empdata[2] +"," + empdata[3] +"," + empdata[4] +"," + empdata[5];

        returnData.add(empfinaldata);
        returnData.add(role);}

    public void concatnames(String f_name, String l_name){
        name =  f_name+l_name;
    }

    public ArrayList returnDet() {
        return returnData;
    }
}

public class DataExtraction {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/archanadevi/IdeaProjects/FileHandling/src/employees.txt"))) {

            String employeetxt;

            FileWriter dev = new FileWriter("/Users/archanadevi/IdeaProjects/FileHandling/src/dev.txt");
            FileWriter qa = new FileWriter("/Users/archanadevi/IdeaProjects/FileHandling/src/qa.txt");
            FileWriter manager = new FileWriter("/Users/archanadevi/IdeaProjects/FileHandling/src/manager.txt");

            String title = br.readLine();

            dev.write(title + "\n");
            qa.write(title + '\n');
            manager.write(title + "\n");

            while ((employeetxt = br.readLine()) != null) {

                String[] employeedet = employeetxt.split(",",6);

                EmployeeData emp = new EmployeeData(employeedet);

                ArrayList<String> returnDet = emp.returnDet();

                String role = returnDet.get(1);

                if (role.contentEquals(" DEV")) {
                    try {
                        System.out.println(returnDet.get(1) + " - " + role);
                        dev.write(returnDet.get(0) + "\n");
                    }
                    catch (Exception e) {
                        System.out.println("Error in Dev file - " + e);
                    }
                }

                else if (role.contentEquals(" QA")) {
                    try {
                        System.out.println(returnDet.get(1) + " - " + role);
                        qa.write(returnDet.get(0) + "\n");
                    }
                    catch (Exception e) {
                        System.out.println("Error in QA file - " + e);
                    }
                }

                else if (role.contentEquals(" MANAGER")) {
                    try {
                        System.out.println(returnDet.get(1) + " - " + role);
                        manager.write(returnDet.get(0) + "\n");
                    }
                    catch (Exception e) {
                        System.out.println("Error in manager file - " + e);
                    }
                }
            }
            manager.close();
            qa.close();
            dev.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
