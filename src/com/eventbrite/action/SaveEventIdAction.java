package com.eventbrite.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveEventIdAction extends ActionSupport  implements ServletResponseAware, ServletRequestAware {
    private static final long serialVersionUID = 1L;

    // For access to the raw servlet request / response, eg for cookies
    protected HttpServletResponse servletResponse;
    protected HttpServletRequest servletRequest;

    public String execute() {

        // Get the parameter
        String eventId = servletRequest.getParameter("eventId");

        // Save to cookie
        Cookie eventIdCookie = new Cookie("eventId", eventId);
        eventIdCookie.setMaxAge(-1); // Work until browser shutdown
        servletResponse.addCookie(eventIdCookie);
    if(eventId.length() > 0)
        return "success";
    else
        return "error";
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
