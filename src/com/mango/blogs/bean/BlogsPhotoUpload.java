package com.mango.blogs.bean;

import java.io.File;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;

public class BlogsPhotoUpload {
	//文件类型
	private String uploadContentType;
	//文件名称
	private String uploadFileName;
	//文件简介
	private String info;
	//文件
	private File upload;
	//文件保存路径
	ServletContext context=ServletActionContext.getServletContext();
	private String savePath=context.getRealPath("/")+"images\\blogs\\userphoto";
	
	
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getSavePath() {
		return savePath;
	}
}
