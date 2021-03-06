package com.smart.dao;

import com.smart.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AppointmentDaoTest extends BaseTest{

    @Autowired
    private AppointmentDao appointmentDao;

    @Test
    public void testInsertAppointment() throws Exception {
        long bookId = 1000;
        long studentId = 1234567890L;
        int insert = appointmentDao.insertAppoint(bookId, studentId);
        System.out.println("insert = " + insert);
    }
}
