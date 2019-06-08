package by.talstaya.taskadd1.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class SignatureInnerTag extends BodyTagSupport {
    private String color;

    @Override
    public int doStartTag() throws JspException {
        LetterOuterTag parent = (LetterOuterTag)findAncestorWithClass(this, LetterOuterTag.class);
        if (parent == null) {
            throw new JspTagException("nesting error");
        } else {
            try {
                pageContext.getOut().write("<div>" +
                        "<p style = \"color:" + color + "\"" + ">");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write(
                    "</p>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
