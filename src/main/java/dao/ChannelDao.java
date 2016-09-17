package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.Channel;

public interface ChannelDao {

	Channel selectChannelById();

	int createChannel(Channel channel);
	
	List<Channel> getChannelByUserId(@Param("start")Integer start,@Param("user_id")Integer user_id);

	List<Channel> getChannelById(int id);
	
	int changeChannel(Channel channel);

	Integer getChannelCount(Integer id);

	int changeChannelStatus(Channel channel);
}
