package Person;

public class Student extends BlueDevil {
    protected String career;

    public Student(String netid, String department, String career) {
        super(netid, department);
        this.career = career;
    }

    public Student(String name, String gender, String undergraduate, String nationality, String workexp, String hobby, String netid, String department, String career) {
        super(name, gender, undergraduate, nationality, workexp, hobby, netid, department);
        this.career = career;
    }

    @Override
    public void printRecords() {
        System.out.println(name+" is a "+career+" student");
        super.printRecords();
    }
}
