package com.demo.demo.service;


import com.demo.demo.model.BedManage;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BedManageService {

    List<BedManage> selAll(BedManage bedManage);

    BedManage selById(Integer id);

    void del(Integer id);

    void add(BedManage bedManage);

    void update(BedManage bedManage);

    PageInfo<BedManage> query(Integer currPage, BedManage bedManage);

}
