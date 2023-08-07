package jdbc.utils;

import jdbc.models.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class UserTag implements Tag {

    private PageContext pageContext;
    private User user;
    private Tag parentTag;


    @Override
    public int doStartTag() {
        try {
            JspWriter jspWriter = pageContext.getOut();
            if (user!=null) {
                jspWriter.println("<tr>");
                jspWriter.println("<td>" + user.getId() + "</td>");
                jspWriter.println("<td>" + user.getLogin() + "</td>");
                jspWriter.println("<td>" + user.getFirstName() + "</td>");
                jspWriter.println("<td>" + user.getLastName() + "</td>");
                jspWriter.println("<td>" + user.getEmail() + "</td>");
                jspWriter.println("<td>" + convertToAge(user.getBirthday()) + "</td>");
                jspWriter.println("<td>" + user.getRole().getName() + "</td>");
                jspWriter.println("<td> <a href=\"edituser/" + user.getId() + "\" class=\"btn btn-primary\">Edit</a>");
                jspWriter.println("<a href=\"#\" onclick=\"confirmDelete(" + user.getId() + ")\" class=\"btn btn-danger\">Delete</a> </td>");
                jspWriter.println("</tr>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private int convertToAge(Date birthday) {
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthday.toLocalDate(), currentDate);
        return age.getYears();
    }


    @Override
    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    @Override
    public void release() {
        pageContext = null;
        user = null;
        parentTag = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag tag) {
        parentTag = tag;
    }

    @Override
    public Tag getParent() {
        return parentTag;
    }
}
