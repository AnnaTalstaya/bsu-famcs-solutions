package by.talstaya.taskadd1.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class LetterOuterTag extends BodyTagSupport {

    private String type;
    private String url;

    @Override
    public int doStartTag() throws JspException {
        pageContext.getOut().print("<div>" +
                "<h1> Letter </h1>" +
                "<h2>" + type + "</h2>" +
                "<img src=" + "\"url\"" + ">" +
                "<div>"
        );
        JspWriter out = pageContext.getOut();
        return super.doStartTag();
    }

    @Override
    public int doAfterBody() throws JspException {
        return super.doAfterBody();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
