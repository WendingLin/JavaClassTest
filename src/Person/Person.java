package Person;

/* Person Class */
 abstract class Person {
    public String name;
    protected String gender;
    protected String undergraduate;
    protected String nationality;
    protected String workexp;
    protected String hobby;


    public Person() {
        System.out.println("Person Created");
    }

    public Person(String name, String gender, String undergraduate, String nationality, String workexp, String hobby) {
        this.name = name;
        this.gender = gender;
        this.undergraduate = undergraduate;
        this.nationality = nationality;
        this.workexp = workexp;
        this.hobby = hobby;
    }
}