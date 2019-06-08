package by.talstaya.task08.web.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class ShowResultInfoTag extends BodyTagSupport {

    private String name;
    private String dateOfBirth;
    private String education;
    private String experience;

    @Override
    public int doAfterBody() throws JspException {
        BodyContent content = this.getBodyContent();
        String body = content.getString();

        JspWriter out = content.getEnclosingWriter();
        try {
            out.write("<h1>" + body + "</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.write("<p>" + "Name: " + name +"</p>");
            out.write("<br>");
            out.write("<p>" + "Date of birth: " + dateOfBirth +"</p>");
            out.write("<br>");
            out.write("<p>" + "Education: " + education +"</p>");
            out.write("<br>");
            out.write("<p>" + "Experience: " + experience +"</p>");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return EVAL_PAGE;
    }

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
