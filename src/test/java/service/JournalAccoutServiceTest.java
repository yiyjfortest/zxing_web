package service;

import clothing.vo.JournalAccout;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class JournalAccoutServiceTest {

    static JournalAccoutService journalAccoutService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        journalAccoutService = context.getBean(JournalAccoutService.class);
    }

    @Test
    public void getJournalAccoutByDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();
        Date endDate = new Date();
        List<JournalAccout> journalAccoutByDate = journalAccoutService.getJournalAccoutByDate(startDate, endDate);
        for (JournalAccout journalAccout : journalAccoutByDate) {
            System.out.println(journalAccout.getCreateTime().toString());
        }
    }

    @Test
    public void insertJournalAccout() {
        JournalAccout journalAccout = new JournalAccout();
        journalAccout.setPrice(-29);
        journalAccoutService.insertJournalAccout(journalAccout);
    }
}