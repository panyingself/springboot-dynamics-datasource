package com.py.mapper;

import com.py.entity.User;
import com.py.entity.export.DataInfo;

import java.util.List;

/**
 * Created by py on 2017/10/17.
 */
public interface UserMapper {
    List<User> getAllUser();
    List<User> findAllUser();

    List<DataInfo> getExportDataList();
}
