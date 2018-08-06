package mypackage;

public class Student {
    int course;
    int group;
    String name;

    public Student(int course, int group, String name) {
        this.course = course;
        this.group = group;
        this.name = name;
    }

    public Student() {
    }

    public int getCourse() {
        return course;
    }

    public int getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }
}
