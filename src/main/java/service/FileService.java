package service;

import java.io.File;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	private static String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static String path=t.substring(1,t.indexOf(".metadata")).replace('/', '\\')+"LicenseServer\\file\\";
	private static Date date = new Date();
	
	public String keepFile(MultipartFile file){
		String fileName = "";
		if (!file.isEmpty()) {
            try {
            	String oriName = file.getOriginalFilename();
                //如果名称不为"",说明该文件存在，否则说明该文件不存在  
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
}
