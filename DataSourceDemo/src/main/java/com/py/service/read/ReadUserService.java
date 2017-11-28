package com.py.service.read;

import com.py.entity.export.DataInfo;

import java.util.List;

/**
 * Created by py on 2017/10/16.
 */
public interface ReadUserService {
    String findUserNmae();
    Object getAllUser();

    List<DataInfo> getExportDataList();
}
