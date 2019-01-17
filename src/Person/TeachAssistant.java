package Person;

public class TeachAssistant extends Student{
    private String teachclass;

    public TeachAssistant(String netid, String department, String career, String teachclass) {
        super(netid, department, career);
        this.teachclass = teachclass;
    }

    public TeachAssistant(String name, String gender, String undergraduate, String nationality, String workexp, String hobby, String netid, String department, String career, String teachclass) {
        super(name, gender, undergraduate, nationality, workexp, hobby, netid, department, career);
        this.teachclass = teachclass;
    }


    @Override
    public void printRecords() {
        super.printRecords();
        System.out.println(gender+" is also a TA and teaches "+ teachclass);
    }
}
