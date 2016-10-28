package service;

import org.springframework.stereotype.Service;

@Service
public interface MsgService {
	String batchSend(String mobile, String msg);
	String batchSend(String mobile, String username,String phonecode);
}
