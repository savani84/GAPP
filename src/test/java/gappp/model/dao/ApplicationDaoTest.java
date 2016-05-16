package gappp.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(groups = "ApplicationDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ApplicationDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    ApplicationDao ApplicationDao;
    
    @Test
    public void getApplication()
    {
     //   assert ApplicationDao.getApplication("Accounting","Fall 2016").size() == 1;
    }

}