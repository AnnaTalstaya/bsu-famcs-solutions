package by.talstaya.taskadd1.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;


public class LetterOuterTag extends BodyTagSupport {

    private String type;
    private String image;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write("<div style=\"margin: auto;width:500px\">" +
                    "<h1> Letter </h1>" +
                    "<h2>" + type + "</h2>" +
                    "<img src=" + image + ">"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write(
                    "</div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE; //EVAL_PAGE - разрешается дальнейшая обработка страницы
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
