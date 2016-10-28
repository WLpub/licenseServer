package service;

import java.io.File;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	private String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private String path=t.substring(1,t.indexOf(".metadata")).replace('/', '\\')+"LicenseServer\\file\\";
	private Date date = new Date();
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(FileService.class);
	public String keepFile(MultipartFile file){
		String fileName = "";
		if (!file.isEmpty()) {
            try {
            	String oriName = file.getOriginalFilename();
                // 如果名称不为"",说明该文件存在，否则说明该文件不存在  
                if(oriName.trim() !=""){  
                    //重命名上传后的文件名  
                    fileName = date.getTime() + oriName;  
                    //定义上传路径  
                    File localFile = new File(path+fileName);  
                    file.transferTo(localFile);
                }  
            } catch (Exception e) {
            }
		}
		return fileName;
	}
	
	public String keepFile(MultipartFile file,int userID){
		String fileName = "";
		if (!file.isEmpty()) {
            try {
            	String oriName = file.getOriginalFilename();
                //如果名称不为"",说明该文件存在，否则说明该文件不存在  
                if(oriName.trim() !=""){  
                	String prefix=oriName.substring(oriName.lastIndexOf("."));
                    //重命名上传后的文件名  
                    fileName = date.getTime() + userID+prefix;  
                    //定义上传路径  
                    logger.info("fileName:"+fileName);
                    File localFile = new File(path+fileName);  
                    file.transferTo(localFile);
                }  
            } catch (Exception e) {
            }
		}
		return fileName;
	}
	
	public String getPath(){
		return path;
	}
}
