package com.eventbrite.action;

import com.eventbrite.domain.Certificate;
import com.eventbrite.mapper.CertificateMapper;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;


public class SaveAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
    private static final long serialVersionUID = 1L;
    private static final int ID_IS_NOT_NEEDED = -1;

    private static final String REGEX_FOR_DATE = "\"date\"";
    private static final String DATE_PATTERN = "yyyy/MM/dd";

    // For access to the raw servlet request / response, eg for cookies
    protected HttpServletResponse servletResponse;
    protected HttpServletRequest servletRequest;

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }


    public String execute() throws UnsupportedEncodingException {

        Enumeration<String> names = servletRequest.getParameterNames();

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

        System.out.println("decode:"+decodeXML);
        Cookie[] cookies = servletRequest.getCookies();
        String eventId = null;
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            String value = cookies[i].getValue();
            if("eventId".equals(name)){
                eventId = value;
                break;
            }
        }
        if(eventId == null){
            return "false";
        }
        CertificateMapper mapper = new CertificateMapper();




        Certificate certificate = new Certificate(ID_IS_NOT_NEEDED, eventId, decodeXML);
        if (null != mapper.search(certificate)) {
            mapper.update(certificate);
        } else {
            mapper.save(certificate);
        }
        return "success";
    }


}
