package service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;

@Service
@Component("AliImpl")
public class MsgServiceAliImpl implements MsgService{
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MsgServiceAliImpl.class);
	private String url = "http://gw.api.taobao.com/router/rest";// 应用地址
	private String account = "23471139";// 账号
	private String pswd = "a5e69a2f94ac1d2eaed01a3de2558ce7";// 密码
	public String batchSend(String mobile, String username,String phonecode) {
		return batchSend(mobile,"{username:'"+username+"',phonecode:'"+phonecode+"'}" );
	}
	@Override
	public String batchSend(String mobile, String msg) {
		TaobaoClient client = new DefaultTaobaoClient(url, account, pswd);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend( "" );
		req.setSmsType( "normal" );
		req.setSmsFreeSignName( "录屏" );
		req.setSmsParamString( msg );
		req.setRecNum( mobile );
		req.setSmsTemplateCode( "SMS_16756367" );
		try {
			client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getErrMsg());
			return "0";
		}
		return "1";
	}

}
