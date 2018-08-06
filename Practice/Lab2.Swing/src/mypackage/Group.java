package mypackage;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Group implements Comparable<Group>{
    private int group;
    private Set<String> names;

    public Group(int group) {
        this.group = group;
        names = new TreeSet<>();
    }

    public void addName(String name){
        names.add(name);
    }


    public Set<String> getNames() {
        return names;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group1 = (Group) o;
        return group == group1.group &&
                Objects.equals(names, group1.names);
    }

    @Override
    public int hashCode() {

        return Objects.hash(group, names);
    }

    @Override
    public int compareTo(Group o) {
        return Integer.compare(getGroup(), o.getGroup());
    }
}
