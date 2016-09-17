package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.ChannelDao;
import model.Channel;

@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired  
    private ChannelDao channelDao;

	@Override
	public Channel selectChannelById(Integer channelId) {
		return channelDao.selectChannelById();
	}

	@Override
	public int createChannel(Channel channel) {
		return channelDao.createChannel(channel);
	}

	@Override
	public List<Channel> getChannelByUserId(Integer start,Integer userId) {
		return channelDao.getChannelByUserId(start,userId);
	}

	@Override
	public List<Channel> getChannelById(int id) {
		return channelDao.getChannelById(id);
	}

	@Override
	public int changeChannel(Channel channel) {
		return channelDao.changeChannel(channel);
	}

	@Override
	public Integer getChannelCount(Integer id) {
		return channelDao.getChannelCount(id);
	}

	@Override
	public int changeChannelStatus(Channel channel) {
		return channelDao.changeChannelStatus(channel);
	}  
  
}
