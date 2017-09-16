package com.eventbrite.action;

import com.eventbrite.domain.Certificate;
import com.eventbrite.mapper.CertificateMapper;
import com.eventbrite.utility.PdfGenerateHelper;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.eventbrite.utility.FileUtil.encodeBase64File;
import static org.apache.struts2.ServletActionContext.getServletContext;


public class getFileAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
    private static final long serialVersionUID = 1L;

    public InputStream getInputStream() {
        return inputStream;
    }

    private InputStream inputStream;


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




    public String execute() throws Exception {
        String path = servletRequest.getParameter("path");
        String base64Code = encodeBase64File(path);
        inputStream = new StringBufferInputStream(base64Code);
        return "success";
    }






}
