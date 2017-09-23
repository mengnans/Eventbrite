package com.eventbrite.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;


public class ExportAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
    private static final long serialVersionUID = 1L;
    private static final String REGEX_FOR_DATE = "\"date\"";
    private static final String DATE_PATTERN = "yyyy/MM/dd";
    // For access to the raw servlet request / response, eg for cookies
    protected HttpServletResponse servletResponse;
    protected HttpServletRequest servletRequest;

    public String execute() throws UnsupportedEncodingException {

        Enumeration<String> names = servletRequest.getParameterNames();
        while (names.hasMoreElements()) {
            System.out.println(names.nextElement());
        }
        String xml = servletRequest.getParameter("xml");
        String format = servletRequest.getParameter("format");
        String filename = servletRequest.getParameter("filename");
        URLDecoder decoder = new URLDecoder();

        String decodeXML = decoder.decode(xml, "ASCII");

        // get date
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        Date date = new Date();
        String dateString = dateFormat.format(date);

        decodeXML = decodeXML.replaceAll(REGEX_FOR_DATE,dateString);
        return "success";
    }


    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
}
