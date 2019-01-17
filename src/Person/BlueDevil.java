package Person;

/* Blue Devil Class */
public class BlueDevil extends Person {
    public String netid;
    protected String department;

    public BlueDevil(String netid, String department) {
        this.netid = netid;
        this.department = department;
    }

    public BlueDevil(String name, String gender, String undergraduate, String nationality, String workexp, String hobby, String netid, String department) {
        super(name, gender, undergraduate, nationality, workexp, hobby);
        this.netid = netid;
        this.department = department;
    }

    public void printRecords() {
        System.out.println(name+ " is from "+ nationality+ " and " + gender +
                " is in "+department + " department.");
        System.out.println("Graduated From: "+undergraduate);
        System.out.println("Working Experience: " + workexp);
        System.out.println("Hobby: "+hobby);
    }
    public String getType() {
        return this.getClass().getName(); //com.**.test.SubDao
    }

}