package service;

import java.util.List;

import model.Record;

public interface RecordService {
	Record selectRecordByID(Integer id);

	List<Record> selectRecordByUserID(Integer userID);

	int createRecord(Record record);

}
