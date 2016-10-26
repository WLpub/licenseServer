package thread;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.multipart.MultipartFile;

import service.FileService;  
  
/** 
 * 处理License文件的thread
 */  
public class LicenseThread  extends Thread {  
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LicenseThread.class);
    
    private long sleepTime = 3 * 1000;  
    
    public LicenseThread() {  
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);  
    }  
  
    @Autowired  
    private FileService fileService;  
  
    private List<MultipartFile> tempList = new ArrayList<MultipartFile>();
    
    public void run() {  
        // 防止多個Thread同時進行數據庫操作造成資源消耗過多  
        try {  
            Thread.currentThread();  
            Thread.sleep(5 * 1000);  
        } catch (InterruptedException e1) {  
        	logger.error(e1.getMessage());
        }  
        logger.info("LicenseThread Thread Is Started。。。");  
        List<MultipartFile> standbyList = new ArrayList<MultipartFile>();  
        // 循环执行任务  
        while (true) {  
        	System.out.println("checking file list!");
            try {  
            	getTask(standbyList); 
                if (!standbyList.isEmpty()) {// 如果待处理的文件集合中有數據  
  
                    for (int i = 0; i < standbyList.size(); i++) {  
                        try {  
                            System.out.println(standbyList.get(i).getOriginalFilename()+" dealing with!");
                        } catch (Exception e) {  
                            logger.error(e.toString(), e);  
                            continue;  
                        } finally {  
                            try {  
                                super.finalize();  
                            } catch (Throwable e) {  
                                logger.error(e.toString(), e);  
                            }  
                        }  
                    }  
                }  
                standbyList.clear();  
                Thread.sleep(sleepTime);  
            } catch (Exception e) {  
                logger.error(e.toString(), e);  
                continue;  
            }  
        }  
    } 
    /** 
     * 获取消息任务 
     *  
     * @param standbyList 
     */  
    private void getTask(List<MultipartFile> standbyList) {  
        // 获取高优先级发送任务  
        tempList = new ArrayList<MultipartFile>(ThreadVariables.licenseList);  
        ThreadVariables.licenseList.removeAll(tempList);  
        standbyList.addAll(tempList);  
        // 清空临时列表  
        tempList.clear();  
    }  
}  
