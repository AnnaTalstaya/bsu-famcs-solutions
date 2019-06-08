package mypackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Notebook {

    @XmlElement(name = "Person")
    private ArrayList<Person> notebook;

    public Notebook() {
        notebook = new ArrayList<>();
    }

    public Notebook(ArrayList<Person> notebook) {
        this.notebook = notebook;
    }

    public ArrayList<Person> getRecords() {
        return notebook;
    }
}
