package dao;

import java.util.List;

import model.Record;

public interface RecordDao {
	public Record selectRecordByID(Integer id);

	public int createRecord(Record record);
	
	public List<Record> selectRecordByUserID(Integer userID);
}
