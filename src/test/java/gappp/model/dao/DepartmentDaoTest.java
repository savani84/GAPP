package gappp.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(groups = "DepartmentDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DepartmentDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    DepartmentDao DepartmentDao;
    
    @Test
    public void getUsers()
    {
        //assert DepartmentDao.getDepartment().size() >= 2;
    }

}