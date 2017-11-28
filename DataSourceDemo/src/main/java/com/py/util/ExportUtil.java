package com.py.util;

import com.py.entity.export.DataInfo;
import org.apache.poi.hssf.usermodel.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by py on 2017/11/28.
 */
public class ExportUtil {

    public static HSSFWorkbook export(List<DataInfo> list, String[] excelHeader) {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Campaign");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            DataInfo dataInfo = list.get(i);
            for( int j = 0; j < excelHeader.length; j++){
                String code = ("data" + j).toLowerCase();
                Object value = getProperties(dataInfo, code);
                if( null != value ) {
                    row.createCell(j).setCellValue(value.toString());
                } else {
                    row.createCell(j).setCellValue("");
                }


            }
        }
        return wb;
    }
    public static Object getProperties(Object provide, String property) {
        Object tempValue = null;
        Method[] mm = provide.getClass().getMethods();

        for(int i = 0; i < mm.length; ++i) {
            if(mm[i].getName().toUpperCase().equals("GET" + property.toUpperCase())) {
                Method cm = mm[i];

                try {
                    Object args = null;
                    tempValue = cm.invoke(provide, (Object[])args);
                } catch (IllegalAccessException var7) {
                    ;
                } catch (IllegalArgumentException var8) {
                    ;
                } catch (InvocationTargetException var9) {
                    ;
                }
                break;
            }
        }

        return tempValue;
    }
}
