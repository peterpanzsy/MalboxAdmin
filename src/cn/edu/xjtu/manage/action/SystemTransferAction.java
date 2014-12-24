package cn.edu.xjtu.manage.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class SystemTransferAction extends ActionSupport{

	private String sysNum;
	private String result;
	
	public String execute(){
		System.out.println(sysNum);
		if(write2File(sysNum)){
			result = "success";
		}else{
			result = "fail";
		}
		return "SUCCESS";
	}

	public String getSysNum() {
		return sysNum;
	}

	public void setSysNum(String sysNum) {
		this.sysNum = sysNum;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	private File getFileCfg(){
		 String uploadPath = ServletActionContext.getServletContext()  
	                .getRealPath("/Config");  
		 File file = new File(uploadPath+File.separator+"sysConfig.txt");
		 return file;
	}
	
	private boolean write2File(String sysNum2){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(getFileCfg()));
			writer.write(sysNum2);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				if(writer!=null){
					writer.flush();
					writer.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				return false;
			}
			writer = null;
		}
	}
	
}
