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
import java.util.Enumeration;


public class SaveAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
    private static final long serialVersionUID = 1L;
    private static final int ID_IS_NOT_NEEDED = -1;

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
        int result = -1;
        if (null != mapper.search(certificate)) {
            result = mapper.update(certificate);
        } else {
            result = mapper.save(certificate);
        }
        System.out.println(result);
        return "success";
    }


}
