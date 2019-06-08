package by.talstaya.task08.web.tag;

import javax.servlet.jsp.tagext.TagSupport;

public class ShowInfoTag extends TagSupport {

    private String name;
    private String dateOfBirth;
    private String education;
    private String experience;

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
