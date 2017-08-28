package com.eventbrite.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AttendeeAction extends ActionSupport  implements ServletResponseAware, ServletRequestAware {
    private static final long serialVersionUID = 1L;
    public String accessToken;

    public String execute() {

        // Load from cookie
        for(Cookie c : servletRequest.getCookies()) {
            if (c.getName().equals("access_token"))
                accessToken = c.getValue();
        }

        // Save to cookie
//        Cookie div = new Cookie("cookieDivision", String.format("%d",division));
//        div.setMaxAge(60*60*24*365); // Make the cookie last a year
//        servletResponse.addCookie(div);

        return "success";
    }

    // For access to the raw servlet request / response, eg for cookies
    protected HttpServletResponse servletResponse;
    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    protected HttpServletRequest servletRequest;
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
}
