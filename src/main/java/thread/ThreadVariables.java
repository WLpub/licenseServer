package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ThreadVariables {
	/** 
     * callback 任務列表 
     */  
    public static List<MultipartFile> licenseList = Collections.synchronizedList(new ArrayList<MultipartFile>());  
      
  
    public static void addnewGameSetList(MultipartFile file){  
    	licenseList.add(file);  
    } 
}
