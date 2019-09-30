package studentID;

public class Student {
    private static long m_studentCount = 0;

    private int m_schoolID;
    private int m_studentAge;
    private String m_studentName;
    private long m_studentID;

    public Student(int student_age, String student_name) throws InvalidStudentException {
        this(330, student_age, student_name);
    }

    public Student(int school_id, int student_age, String student_name) throws InvalidStudentException {
        this.m_studentName = student_name;

        // Set the student age
        setAge(student_age);

        // Build the student's ID
        m_studentID = (school_id * 1_000_000L) + m_studentCount;
        m_studentCount++;

    }

    public void setAge(int age) throws InvalidStudentException {
        if (!(14 <= age && age <= 21)) {
            throw new InvalidStudentException("Invalid age supplied");
        }

        this.m_studentAge = age;
    }

    /**
     * @return the m_studentAge
     */
    public int getAge() {
        return m_studentAge;
    }

    /**
     * @param m_studentName the m_studentName to set
     */
    public void setName(String name) {
        this.m_studentName = name;
    }

    /**
     * @return the m_studentName
     */
    public String getName() {
        return m_studentName;
    }

    @Override
    public String toString() {
        return m_studentName+ " [age " +m_studentAge+ " id " +m_studentID+ "]";
    }

    public long getID() {
        return m_studentID;
    }

    public static Student buildStudent(int student_age, String student_name){
        try{
            return new Student(student_age, student_name);
        } catch (InvalidStudentException e){
            return null;
        }
    }
}