package com.eventbrite.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


public class SaveAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
   private static final long serialVersionUID = 1L;

   // For access to the raw servlet request / response, eg for cookies
   protected HttpServletResponse servletResponse;
   protected HttpServletRequest servletRequest;

   public String execute() {

      Enumeration<String> names = servletRequest.getParameterNames();
      while(names.hasMoreElements()){
         System.out.println(names.nextElement());
      }
      Object object = servletRequest.getParameter("xml");
      System.out.println(object.getClass().getName());
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
