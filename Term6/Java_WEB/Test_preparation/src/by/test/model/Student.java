package by.talstaya.test.model;

public class Student {
    private int idStudent;
    private String fullName;
    private int course;
    private int group;
    private double averageScore;
    private boolean fromVillage;

    public Student(int idStudent, String fullName, int course, int group, double averageScore, boolean fromVillage) {
        this.idStudent = idStudent;
        this.fullName = fullName;
        this.course = course;
        this.group = group;
        this.averageScore = averageScore;
        this.fromVillage = fromVillage;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public boolean isFromVillage() {
        return fromVillage;
    }

    public void setFromVillage(boolean fromVillage) {
        this.fromVillage = fromVillage;
    }
}
