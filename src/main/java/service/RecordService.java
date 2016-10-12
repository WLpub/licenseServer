package service;

import java.util.List;

import model.Record;

public interface RecordService {
	Record selectRecordByID(Integer id);

	List<Record> selectRecordByUserID(Integer userID,Integer start);

	int createRecord(Record record);

	int getTotalCount(Integer userID);

}
