package service;

import java.util.List;

import model.Channel;

public interface ChannelService {
	Channel selectChannelById(Integer channelId);

	int createChannel(Channel channel);
	
	List<Channel> getChannelByUserId(Integer start,Integer userId);

	List<Channel> getChannelById(int id);

	int changeChannel(Channel channel);

	Integer getChannelCount(Integer id);

	int changeChannelStatus(Channel channel);

}
