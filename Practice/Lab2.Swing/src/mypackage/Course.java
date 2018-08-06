package mypackage;

import java.util.*;

public class Course implements Comparable<Course>{
    private int course;
    private Set<Group> groups;

    public Course(int course) {
        this.course = course;
        groups = new TreeSet<Group>();
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    public void setGroupNames(Group group, String name){
        for (Group g:groups) {
            if(g == group)
                g.addName(name);
        }
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public int getCourse() {
        return course;
    }


    @Override
    public int compareTo(Course o) {
        return Integer.compare(getCourse(), o.getCourse());
    }
}
