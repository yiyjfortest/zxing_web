package service;

import clothing.dao.JournalAccoutMapper;
import clothing.vo.JournalAccout;
import clothing.vo.JournalAccoutExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 根据日期查询流水账明细
 * 新增流水账
 */
@Service
public class JournalAccoutService {

    @Resource
    JournalAccoutMapper journalAccoutMapper;

    public List<JournalAccout> getJournalAccoutByDate(Date startDate, Date endDate) {
        JournalAccoutExample example = new JournalAccoutExample();
        example.setOrderByClause("create_time desc");
        if (startDate != null || endDate != null) {
            example.createCriteria().andCreateTimeBetween(startDate, endDate);
        }
        return journalAccoutMapper.selectByExample(example);
    }

    public void insertJournalAccout(JournalAccout journalAccout) {
        journalAccout.setCreateTime(new Date());
        journalAccoutMapper.insertSelective(journalAccout);
    }


}
