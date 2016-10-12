package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RecordDao;
import model.Record;

@Service
public class RecordServiceImpl implements RecordService {
	@Autowired  
    private RecordDao recordDao;  
  
	@Override
    public Record selectRecordByID(Integer recordID) {  
        return recordDao.selectRecordByID(recordID);  
          
    }

    @Override
	public int createRecord(Record record) {
    	recordDao.createRecord(record);
    	return record.getId();
	}

	@Override
	public List<Record> selectRecordByUserID(Integer userID,Integer start) {  
		return recordDao.selectRecordByUserID(userID,start);  
    }

	@Override
	public int getTotalCount(Integer userID) {
		return recordDao.getTotalCount(userID);
	}

}
