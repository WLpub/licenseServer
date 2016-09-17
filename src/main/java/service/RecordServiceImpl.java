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
	public List<Record> selectRecordByUserID(Integer userID) {  
		return recordDao.selectRecordByUserID(userID);  
    }

}
