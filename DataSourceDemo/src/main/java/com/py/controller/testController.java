package com.py.controller;

import com.py.entity.export.DataInfo;
import com.py.service.write.WriteUserService;
import com.py.service.read.ReadUserService;
import com.py.util.ExportUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by py on 2017/11/24.
 */
@Controller
@RequestMapping("/test")
public class testController {
    @Autowired
    private ReadUserService readUserService;
    @Autowired
    private WriteUserService writeUserService;
    @GetMapping("/testRead")
    @ResponseBody
    public String testRead(){
        return readUserService.findUserNmae();
    }

    @GetMapping("/testWrite")
    @ResponseBody
    public String testWrite(){
        return  writeUserService.findUserNmae();
    }
    //资产信息数据导出
    @RequestMapping(value = "/export",method = {RequestMethod.GET,RequestMethod.POST})
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<DataInfo> liquidateList = readUserService.getExportDataList();
        //列名
        String[] excelHeader = { "用户ID", "用户名", "银行卡号","基金产品名称", "总份额", "可用份额","冻结份额",
                "申购在途份额", "赎回在途份额","未付收益金额", "每日收益", "累计收益","备注"};
        HSSFWorkbook wb = ExportUtil.export(liquidateList,excelHeader);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=test.xls");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}
