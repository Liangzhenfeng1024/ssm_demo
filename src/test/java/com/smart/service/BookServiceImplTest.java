package com.smart.service;

import com.smart.BaseTest;
import com.smart.dto.AppointExecution;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: BookServiceImplTest
 * @Auther: Liang
 * @Description:
 * @Date: 2018/6/20 23:37
 * @Version: 1.0
 */
public class BookServiceImplTest extends BaseTest{

    @Autowired
    private BookService bookService;

    @Test
    public void testAppoint() throws Exception {
        long bookId = 1001;
        long studentId = 1234567890L;
        AppointExecution execution = bookService.appoint(bookId, studentId);
        System.out.println(execution);
    }
}
