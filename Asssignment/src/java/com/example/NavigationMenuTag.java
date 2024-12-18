package com.example;

import java.io.IOException;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;

public class NavigationMenuTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        // Lấy context path của ứng dụng
        String contextPath = ((PageContext) getJspContext()).getRequest().getServletContext().getContextPath();

        JspWriter out = getJspContext().getOut();
        out.write(
                "<div style='position: fixed; top: 10px; right: 10px; display: inline-block;'>"
                + "<button style='background-color: #009879; color: white; padding: 10px 20px; font-size: 16px; border: none; cursor: pointer; border-radius: 5px;'>Menu</button>"
                + "<div style='display: none; position: absolute; right: 0; background-color: #ffffff; min-width: 160px; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); z-index: 1; border-radius: 5px;'>"
                + "<a href='../home.html' style='color: #333; padding: 12px 16px; text-decoration: none; display: block;'>Home</a>"
                + "<a href='" + contextPath + "/logout' style='color: #333; padding: 12px 16px; text-decoration: none; display: block;'>Logout</a>"
                + "</div>"
                + "</div>"
                + "<script>"
                + "document.addEventListener('DOMContentLoaded', function() {"
                + "    const dropbtn = document.querySelector('button');"
                + "    const dropdownContent = dropbtn.nextElementSibling;"
                + "    dropbtn.addEventListener('click', function() {"
                + "        dropdownContent.style.display = dropdownContent.style.display === 'block' ? 'none' : 'block';"
                + "    });"
                + "    window.addEventListener('click', function(event) {"
                + "        if (!dropbtn.contains(event.target) && !dropdownContent.contains(event.target)) {"
                + "            dropdownContent.style.display = 'none';"
                + "        }"
                + "    });"
                + "});"
                + "</script>"
        );

    }
}
