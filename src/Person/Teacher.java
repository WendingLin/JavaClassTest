package Person;

public class Teacher extends BlueDevil{
    private String career;
    private String teachclass;

    public Teacher(String netid, String department, String teachclass) {
        super(netid, department);
        this.teachclass = teachclass;
    }

    public Teacher(String name, String gender, String undergraduate, String nationality, String workexp, String hobby, String netid, String department, String career, String teachclass) {
        super(name, gender, undergraduate, nationality, workexp, hobby, netid, department);
        this.teachclass = teachclass;
        this.career = career;
    }

    @Override
    public void printRecords() {
        System.out.println(name+" is a " + career+" and teaches "+ teachclass);
        super.printRecords();
    }
}
