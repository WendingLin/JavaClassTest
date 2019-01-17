import Person.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


import java.util.Scanner;
import java.util.Vector;

/* Main Class */
public class infoModel {
    private static HashMap<String, String> departMap = new HashMap<>();
    private static HashMap<String, Vector<BlueDevil>> devilMap = new HashMap<>(); //Hash Map to find the name

    public static void main(String[] args) {
        demorecords(); //The records we already have (myself, TAs, Profs)
        try {
            while (true) {
                System.out.println("-----Welcome to Blue Devil Directory-----");
                System.out.println("Press 1 to search people by name; Press 2 to add records; Press 3 to list all the members in one department");
                int choice = System.in.read();
                if (choice == 49) { //Search by name
                    searchRecords();
                } else if (choice == 50) { // Add records
                    addRecords();
                } else if (choice == 51) {
                    searchDepartment();
                } else if (choice == 10) { //Handle enter
                    continue;
                } else { // Error input
                    System.out.println("Please enter 1 or 2 or 3 again");
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Search Records by name */
    public static void searchRecords() throws IOException {
        System.out.println("Please type the name of the people you want to search: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String string = bufferedReader.readLine();
            if (string.isEmpty())
                continue;
            else if (string.equals("0")) {
                return;
            } else if (devilMap.containsKey(string)) { //find the name
                Vector<BlueDevil> blueDevils = devilMap.get(string);
                // Only find one record
                if (blueDevils.size() == 1) {
                    BlueDevil blueDevil = blueDevils.get(0);
                    printHelper(blueDevil);
                    return;
                } else { //Find multiple records
                    int size = blueDevils.size();
                    System.out.println("Found " + size + " records, their netids are:");
                    for (BlueDevil blueDevil : blueDevils) {
                        System.out.print(blueDevil.netid + " ");
                    }
                    BufferedReader netbuff = new BufferedReader(new InputStreamReader(System.in));
                    while (true) {
                        System.out.println("Please type the netid to choose: ");
                        String strbuff = netbuff.readLine();
                        for (int i = 0; i < size; i++) {
                            if (blueDevils.get(i).netid.equals(strbuff)) {
                                BlueDevil blueDevil = blueDevils.get(i);
                                printHelper(blueDevil);
                                return;
                            }
                        }
                        System.out.println("Please type the correct netid!");
                    }
                }
            } else {
                System.out.println("Could not find the name, please try again, Press 0 to come back");
            }
        }
    }

    public static void searchDepartment() throws IOException {
        System.out.println("Please type the name of the department you want to list the member: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String string = bufferedReader.readLine();
            if (string.isEmpty())
                continue;
            else if (departMap.containsKey(string)) { //find the name
                System.out.println(departMap.get(string));
                return;
            } else {
                System.out.println("Could not find the department, please try again");
            }
        }
    }

    public static void addDepartment(String department, String name) {
        if (departMap.containsKey(department))
            name = departMap.get(department).concat(" " + name);
        departMap.put(department, name);
    }

    public static void addHash(BlueDevil blueDevil) {
        if (devilMap.containsKey(blueDevil.name)) {
            devilMap.get(blueDevil.name).addElement(blueDevil);
        } else {
            Vector<BlueDevil> blueDevils = new Vector<>();
            blueDevils.addElement(blueDevil);
            devilMap.put(blueDevil.name, blueDevils);
        }
    }

    public static void addRecords() {

        Scanner scanner = new Scanner(System.in);

        String name, gender, nationality, workexp, hobby, netid, department, undergraduate;
        BlueDevil blueDevil;

        /* Input Logic*/
        name = inputHelper("Please input the name", scanner);
        netid=netidGenerator(name);
        gender = inputHelper("Please input the gender, press 1 as male, press 2 as female", scanner, "he", "she");
        nationality = inputHelper("Please input the country the person comes from", scanner);
        workexp = inputHelper("Please input the person's working experience", scanner);
        hobby = inputHelper("Please input the person's hobby", scanner);
        department = inputHelper("Please input the person's department (ECE, BME, etc..)", scanner);
        undergraduate = inputHelper("Please input the person's undergraduate school", scanner);

        String affiliation = inputHelper("Please input the affiliation, press 1 as student, press 2 as teacher", scanner, "student", "teacher");

        if (affiliation.equals("student")) {
            String career;
            System.out.println("Please input the career, press 1 as undergraduate, press 2 as master, press 3 as PhD, press 4 as MEng");
            while (true) {
                career = scanner.nextLine();
                if (career.equals("1")) {
                    career = "undergraduate";
                    break;
                } else if (career.equals("2")) {
                    career = "MS";
                    break;
                } else if (career.equals("3")) {
                    career = "PhD";
                    break;
                } else if (career.equals("4")) {
                    career = "MEng";
                    break;
                } else
                    System.out.println("Wrong input! Please type 1, 2, 3, 4 again!");
            }
            String isTA = inputHelper("Please select if student is TA, press 1 as TA, press 2 as false", scanner, "true", "false");
            if (isTA.equals("true")){
                String teachclass = inputHelper("Please input which class to teach", scanner);
                blueDevil = new TeachAssistant(name, gender,undergraduate, nationality, workexp, hobby, netid, department, career, teachclass);
            }else {
                blueDevil = new Student(name, gender, undergraduate, nationality, workexp, hobby, netid, department, career);
            }
        } else {
            String career = inputHelper("Please input the career (Prof./Assic. Prof. etc..)", scanner);
            String teachclass = inputHelper("Please input which class to teach", scanner);
            blueDevil = new Teacher(name, gender, undergraduate, nationality, workexp, hobby, netid, department, career, teachclass);
        }

        addDepartment(department, name);
        addHash(blueDevil);

        /*
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Enter a String :");
                String val = bufferedReader.readLine();
                System.out.println("Your String is :" + val + "\r");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                bufferedReader = null;
            }
        }*/
    }

    public static void printHelper(BlueDevil blueDevil) {
        String type = blueDevil.getType();
        if (type.equals("Person.Student")) {
            Student student = (Person.Student) blueDevil;
            student.printRecords();
        } else if (type.equals("Person.Teacher")) {
            Teacher teacher = (Person.Teacher) blueDevil;
            teacher.printRecords();
        } else if (type.equals("Person.TeachAssistant")) {
            TeachAssistant teachAssistant = (Person.TeachAssistant) blueDevil;
            teachAssistant.printRecords();
        }
    }

    public static String inputHelper(String hint, Scanner scanner) {
        System.out.println(hint);
        String string;
        while (true) {
            string = scanner.nextLine();
            if (string.isEmpty()) {
                continue;
            } else
                return string;
        }
    }

    public static String inputHelper(String hint, Scanner scanner, String choice1, String choice2){
        System.out.println(hint);
        String string;
        while (true) {
            string = scanner.nextLine();
            if (string.equals("1")) {
                return choice1;
            } else if (string.equals("2")) {
               return choice2;
            } else
                System.out.println("Wrong input! Please type 1 or 2 again!");
        }
    }

    public static String netidGenerator(String name){
        String[] splited = name.split("\\s+");
        String id;
        int i;
        if (devilMap.containsKey(name)) {
            i = devilMap.get(name).size() + 1;
        } else {
            i = 1;
        }
        id = String.format("%03d", i);
        String netid;
        if (splited.length == 1)
            netid= splited[0].substring(0, 1).toLowerCase() + splited[0].substring(splited[0].length() - 1).toLowerCase() + id;
        else
            netid= splited[0].substring(0, 1).toLowerCase() + splited[1].substring(0, 1).toLowerCase() + id;
        System.out.println("Auto Generate Netid >>" + netid);
        return netid;
    }

    public static void demorecords() {
        BlueDevil blueDevil = new Student("Wending Lin", "He", "BUPT",
                "China", "Sina", "DOTA2", "wl177",
                "ECE", "MS");
        BlueDevil blueDevil1 = new TeachAssistant("Yuanyuan Yu", "He", "ECUST",
                "China", "", "Enjoys baseball and fencing", "yy194",
                "ECE", "MEng", "ECE-651");
        BlueDevil blueDevil2 = new TeachAssistant("Lei Chen", "He",
                "KAIST","China",  "Worked on research project aobut image resolution",
                "Enjoy climbing and animals", "lc294",
                "ECE", "MS", "ECE-651");
        BlueDevil blueDevil3 = new TeachAssistant("You Lyu", "She",
                "Not known in Bio", "China",  "Spent a year in the U.K.", "Loves traveling, music and history", "yl489",
                "ECE", "MS", "ECE-651");
        BlueDevil blueDevil4 = new TeachAssistant("Shalin Shah", "She",
                "DA-IICT","India", "", "Enjoys bodybuilding and dancing", "sns37",
                "ECE", "PhD", "ECE-651");
        BlueDevil blueDevil5 = new TeachAssistant("Zhongyu Li", "He",
                "South East University", "China", "", "Love basketball & NBA", "zl158",
                "ECE", "MEng", "ECE-651");
        BlueDevil blueDevil6 = new Teacher("Adel Fahmy", "He",
                "North Carolina State University", "Egypt", "IBM", "Tennis, Biking, Gardening, and Cooking",
                "aff6", "ECE", "Adjunct Assistant Professor", "ECE-651");
        BlueDevil blueDevil7 = new Teacher("Ric Telford", "He",
                "Trinity University", "United States", "IBM", "Golf, Sand Volleyball, Swimming and Biking",
                "rt113", "ECE", "Adjunct Assoc. Professor", "ECE-651");
        addDepartment("ECE", "Wending Lin");
        addDepartment("ECE", "Yuanyuan Yu");
        addDepartment("ECE", "Lei Chen");
        addDepartment("ECE", "You Lyu");
        addDepartment("ECE", "Shalin Shah");
        addDepartment("ECE", "Zhongyu Li");
        addDepartment("ECE", "Adel Fahmy");
        addDepartment("ECE", "Ric Telford");
        addHash(blueDevil);
        addHash(blueDevil1);
        addHash(blueDevil2);
        addHash(blueDevil3);
        addHash(blueDevil4);
        addHash(blueDevil5);
        addHash(blueDevil6);
        addHash(blueDevil7);
    }

}
