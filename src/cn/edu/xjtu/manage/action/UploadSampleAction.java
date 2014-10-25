package cn.edu.xjtu.manage.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import cn.edu.xjtu.manage.business.Sample;
import cn.edu.xjtu.manage.dao.SampleDao;
import cn.edu.xjtu.tools.HTMLParser;
import cn.edu.xjtu.tools.MD5Filter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadSampleAction  extends ActionSupport{
	private static final long serialVersionUID = 837481714629791752L;
	int ID;
	String name;
	String path;
	String type;
	long filesize;
	String md5; 
	String analysis;
	Date firsttime;
	Date lasttime;
	private String remark;
	
	private File samplefile;
	private String relativePath;
	private String absolutePath;
	public static String UPLOADPATH="uploadSample/";
	public static String REPORTPATH="reports/";
	private static String WEB_ROOT_PATH;
	private Boolean exist;
	

	public String getWebFileRoot(){
		 ActionContext ac = ActionContext.getContext();     
	     ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);     
	     WEB_ROOT_PATH = sc.getRealPath("/"); 
	     return WEB_ROOT_PATH;
	}
	public String uploadSample() throws Exception {	 
	     BufferedReader reader = null;
	        try {	                 
	        	SampleDao dao=new SampleDao();	
	        	reader = new BufferedReader(new FileReader(samplefile)); 	           
            	String absolutePath=getWebFileRoot()+this.UPLOADPATH;
            	md5= MD5Filter.getMd5ByFile(samplefile);
	        	int cou=dao.countSample(md5);
	        	if(cou>0){
	        		exist=true;
	        		int res=dao.updateSample(md5, new Date());
	        	}else{
	        		exist=false;
			        relativePath=this.UPLOADPATH+md5+type;	        		              
		            absolutePath=absolutePath+md5+type; 
		            	               
	                File f=new File(absolutePath);
	                copyFile(samplefile,f);	       
		            reader.close();
         
		            filesize=f.length();
		            analysis="0";
		            firsttime=new Date();
		            lasttime=firsttime;
	        		ID=dao.addSample(this.name,this.relativePath,this.type,this.filesize,this.md5,this.analysis,this.firsttime,this.lasttime);
	        	}
	        	dao.close();    	
	    		
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
		return "SUCCESS";
		
	}
	
	private void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
	
	private List<Sample> dataList;
	private int records;
	private int page;
	private int rows;
	private int rowNum;
	private int total;
	private String sidx;
	private String sord;
	private int id;
	private List<Integer> ids;
	public String list(){		

		SampleDao dao=new SampleDao();
		
		dataList = dao.getSampleList();
	
		records=dao.getCountSample();

		if(records!=0&&rows!=0){
			total=records/rows;
			if(records%rows!=0){
				total++;
			}
		}
		return "SUCCESS";
	}
	public String delete(){
		SampleDao dao=new SampleDao();
		if(!ids.isEmpty()){
			for(int id:ids){
				dao.deleteSample(id);
			}
		}
		dao.commit();
		return "SUCCESS";
	}
	
	private String report="";
	private String regulation;
	private String fileMD5;
	public String viewReport(){
		String absolutePath=getWebFileRoot()+this.REPORTPATH+fileMD5+".txt";
		File reportFile=new File(absolutePath);
		String str=null;	
		try {
			report = HTMLParser.parse(reportFile, regulation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}
		/*if(regulation.endsWith("net")){
			try {
				BufferedReader br=new BufferedReader(new FileReader(reportFile));
				while((str=br.readLine())!=null){//未到文本结尾
					if(str.startsWith("Communicate with IRC sever (")||str.startsWith("Download file:")||str.startsWith("Try to connect domain:")
							||str.startsWith("Try to connect domain:")||str.startsWith("UDP Connection:")){
						report+="\n"+str+"\n";										
						while((str=br.readLine())!=null&&str.length()!=0){//执行 直到某一行为空行""
							report+=str+"\n";
						}
					}
				}
			} catch (IOException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(regulation.endsWith("register")){
			try {
				BufferedReader br=new BufferedReader(new FileReader(reportFile));
				while((str=br.readLine())!=null){//未到文本结尾
					if(str.startsWith("Create key:")||str.startsWith("Delete key:")||str.startsWith("Set value key:")
							||str.startsWith("Delete value key:")){
						report+="\n"+str+"\n";					
						while((str=br.readLine())!=null&&str.length()!=0){//执行 直到某一行为空行""
							report+=str+"\n";
						}
					}
				}
			} catch (IOException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(regulation.endsWith("file")){
			try {
				BufferedReader br=new BufferedReader(new FileReader(reportFile));
				while((str=br.readLine())!=null){//未到文本结尾
					if(str.startsWith("Create file:")||str.startsWith("Overwrite file:")||str.startsWith("Delete file:")){
						report+="\n"+str+"\n";					
						while((str=br.readLine())!=null&&str.length()!=0){//执行 直到某一行为空行""
							report+=str+"\n";
						}
					}
				}
			} catch (IOException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(regulation.endsWith("process")){
			try {
				BufferedReader br=new BufferedReader(new FileReader(reportFile));
				while((str=br.readLine())!=null){//未到文本结尾
					if(str.startsWith("Create process:")||str.startsWith("Create remote thread:")||str.startsWith("Terminate other process:")){
						report+="\n"+str+"\n";						
						while((str=br.readLine())!=null&&str.length()!=0){//执行 直到某一行为空行""
							report+=str+"\n";
						}
					}
				}
			} catch (IOException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(regulation.endsWith("all")){
			try {
				BufferedReader br=new BufferedReader(new FileReader(reportFile));
				while((str=br.readLine())!=null){//未到文本结尾
						report+=str+"\n";												
				}
			} catch (IOException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	
	
	
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getRegulation() {
		return regulation;
	}
	public void setRegulation(String regulation) {
		this.regulation = regulation;
	}
	public String getFileMD5() {
		return fileMD5;
	}
	public void setFileMD5(String fileMd5) {
		this.fileMD5 = fileMd5;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public Date getFirsttime() {
		return firsttime;
	}
	public void setFirsttime(Date firsttime) {
		this.firsttime = firsttime;
	}
	public Date getLasttime() {
		return lasttime;
	}
	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public File getSamplefile() {
		return samplefile;
	}
	public void setSamplefile(File samplefile) {
		this.samplefile = samplefile;
	}
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public static String getUPLOADPATH() {
		return UPLOADPATH;
	}
	public static void setUPLOADPATH(String uPLOADPATH) {
		UPLOADPATH = uPLOADPATH;
	}
	
	public Boolean getExist() {
		return exist;
	}
	public void setExist(Boolean exist) {
		this.exist = exist;
	}
	public List<Sample> getDataList() {
		return dataList;
	}
	public void setDataList(List<Sample> dataList) {
		this.dataList = dataList;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
}
