package com.smart.service.impl;

import com.smart.dao.AppointmentDao;
import com.smart.dao.BookDao;
import com.smart.dto.AppointExecution;
import com.smart.entity.Appointment;
import com.smart.entity.Book;
import com.smart.enums.AppointStateEnum;
import com.smart.exception.AppointException;
import com.smart.exception.NoNumberException;
import com.smart.exception.RepeatAppointException;
import com.smart.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: BookServiceImpl
 * @Auther: Liang
 * @Description:
 * @Date: 2018/6/20 23:22
 * @Version: 1.0
 */
@Service
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AppointmentDao appointmentDao;

    public Book getById(long bookId) {
        return bookDao.queryById(bookId);
    }

    public List<Book> getList() {
        return bookDao.queryAll(0, 100);
    }

    @Transactional
    public AppointExecution appoint(long bookId, long studentId) {
        try {
            //减库存
            int update = bookDao.reduceNumber(bookId);
            if (update <= 0) {
                //库存不足
                throw new NoNumberException("no number");
            } else {
                //执行预约操作
                int insert = appointmentDao.insertAppoint(bookId, studentId);
                if (insert <= 0 ) {
                    //重复预约
                    throw new RepeatAppointException("repeat appoint");
                }
                else {
                    Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
                    return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appointment);
                }
            }
        } catch (NoNumberException e1) {
            throw  e1;
        } catch (RepeatAppointException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new AppointException("appoint inner error" + e.getMessage());
        }
    }
}
