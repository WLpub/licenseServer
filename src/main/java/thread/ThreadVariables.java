package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadVariables {
	/** 
     * callback 任務列表 
     */  
    public static List<String> licenseList = Collections.synchronizedList(new ArrayList<String>());  
      
  
    public static void addnewGameSetList(String file){  
    	licenseList.add(file);  
    } 
}
