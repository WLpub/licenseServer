package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;  
  
/** 
 * 处理License文件的thread
 */  
public class LicenseThreadPool extends Thread{  
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LicenseThreadPool.class);
    
    private static long sleepTime = 10 * 1000;  
    
    public LicenseThreadPool() {  
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);  
    }  
  
    public static ExecutorService fixedThreadPool;
	public static int index = 0;

	public void run() {  
		fixedThreadPool = Executors.newFixedThreadPool(2);
	}
	public static void processLicense(String file) {
		fixedThreadPool.execute(new Runnable() {
			public void run() {
				try {
					System.out.println("current tread in pool get index:" + index++);
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		});
	}
}  
