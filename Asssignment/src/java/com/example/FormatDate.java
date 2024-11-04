package com.example;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author Zeldais
 */
public class FormatDate extends SimpleTagSupport {

    private Date value;

    public void setValue(Date value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (value != null) {
            // Định dạng ngày tháng theo kiểu tiếng Việt
            SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("'Ngày' dd 'Tháng' MM 'Năm' yyyy");
            String formattedDate = dateFormat.format(value);

            // Xuất kết quả ra trang JSP
            JspWriter out = getJspContext().getOut();
            out.print(formattedDate);
        }
    }

}
