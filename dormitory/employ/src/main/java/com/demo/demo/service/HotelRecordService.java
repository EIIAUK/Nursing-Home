package com.demo.demo.service;

import com.demo.demo.model.HotelRecord;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HotelRecordService {

    List<HotelRecord> selAll(HotelRecord hotelRecord);


    HotelRecord selById(Integer id);

    void del(Integer id);

    void add(HotelRecord hotelRecord);

    void update(HotelRecord hotelRecord);

    PageInfo<HotelRecord> query(Integer currPage, HotelRecord hotelRecord);

}
