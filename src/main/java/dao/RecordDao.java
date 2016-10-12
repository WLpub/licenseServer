package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.Record;

public interface RecordDao {
	public Record selectRecordByID(Integer id);

	public int createRecord(Record record);
	
	public List<Record> selectRecordByUserID(@Param("userID")Integer userID,@Param("start")Integer start);

	public int getTotalCount(Integer userID);
}
