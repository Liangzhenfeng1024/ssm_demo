package com.smart.service;

import com.smart.dto.AppointExecution;
import com.smart.entity.Book;

import java.util.List;

/**
 * 业务接口：站在“使用者”角度设计接口 三个方面：方法定义粒度，参数，返回类型
 */
public interface BookService {

    Book getById(long bookId);

    List<Book> getList();

    AppointExecution appoint(long bookId, long studentId);
}
