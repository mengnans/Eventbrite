package com.eventbrite.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ImportActioin extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	protected HttpServletResponse servletResponse;
	protected HttpServletRequest servletRequest;

	private File upfile;
	private String upfileContentType;
	private String upfileFileName;
	private String savePath= ServletActionContext.getServletContext().getRealPath("javascript/examples/grapheditor/www/images/");

	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}

	public void setUpfileContentType(String upfileContentType) {
		this.upfileContentType = upfileContentType;
	}

	public void setUpfileFileName(String upfileFileName) {
		this.upfileFileName = upfileFileName;
	}

	public File getUpfile() {
		return upfile;
	}

	public String getUpfileContentType() {
		return upfileContentType;
	}

	public String getUpfileFileName() {
		return upfileFileName;
	}

	public String execute() throws UnsupportedEncodingException, IOException {
	//System.out.println("importaction in");
		servletRequest.setCharacterEncoding("UTF-8");
		servletResponse.setCharacterEncoding("UTF-8");
		servletResponse.setContentType("text/html; charset=UTF-8");

		upfileFileName=getFileName(upfileFileName);

		FileInputStream fis=new FileInputStream(upfile);

//		while(savePath.endsWith("\\")){
//			savePath = savePath.substring(0,savePath.length() - 1);
//		}
//
//		while(upfileFileName.startsWith("\\")){
//			upfileFileName = upfileContentType.substring(1,upfileFileName.length());
//		}
//		System.out.println(savePath);
//		System.out.println(upfileFileName);
//		System.out.println(savePath+"\\"+upfileFileName);
		FileOutputStream fos=new FileOutputStream(savePath+upfileFileName);
		byte [] b=new byte[4096];
		int length=0;
		while((length=fis.read(b))>0){
			fos.write(b,0,length);
		}
		fis.close();
		fos.close();
		//System.out.println(savePath+"\\"+upfileFileName);
		OutputStream out = servletResponse.getOutputStream();
		String encoding = servletRequest.getHeader("Accept-Encoding");
		PrintWriter writer = new PrintWriter(out);
		writer.println("<html>");
		writer.println("<head>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<script type=\"text/javascript\">");
		String imgurl="images/"+upfileFileName;
		writer.println("window.parent.openFile.setData("+"'"+ imgurl + "');");
		//System.out.println(imgurl);
		writer.println("</script>");
		writer.println("</body>");
		writer.println("</html>");
		writer.flush();
		writer.close();
		return "success";
	}

	private String getFileName(String fileName){
		int position=fileName.lastIndexOf(".");
		String newName=fileName.substring(position);
		return System.currentTimeMillis()+newName;
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
