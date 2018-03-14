package com.sargeraswang.util.ExcelUtil.Service.Impl;

import com.sargeraswang.util.ExcelUtil.Domain.FileServerResponse;
import com.sargeraswang.util.ExcelUtil.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import com.sargeraswang.util.ExcelUtil.Service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * <p>Description : excel-util
 * <p>Date : 2018\3\13 0013 9:56
 * <p>@author : wjq
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    private final static Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Override
    public FileServerResponse getExcelData(MultipartFile multipartFile, HttpServletRequest request) {
        //检查文件格式
        if (!checkFile(multipartFile)) {
            return FileServerResponse.error("文件格式错误");
        }
        //根路径
        String path = request.getSession().getServletContext().getRealPath("/");
        //本地文件
        File localFile = new File(path, multipartFile.getOriginalFilename());

        try {
            multipartFile.transferTo(localFile);
            InputStream inputStream = new FileInputStream(localFile);
            ExcelLogs logs = new ExcelLogs();
            Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);
            for (Map m : importExcel) {
                System.out.println(m);
            }
            inputStream.close();
            //localFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileServerResponse.success("获取成功");
    }

    private boolean checkFile(MultipartFile file) {
        //判断文件是否存在
        if (null == file) {
            log.error("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            log.error(fileName + "不是excel文件");
            return false;
        }
        return true;
    }

//    private Workbook getWorkBook(MultipartFile file) {
//        //获得文件名
//        String fileName = file.getOriginalFilename();
//        //创建Workbook工作薄对象，表示整个excel
//        Workbook workbook = null;
//        try {
//            //获取excel文件的io流
//            InputStream is = file.getInputStream();
//            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
//            if(fileName.endsWith("xls")){
//                //2003
//                workbook = new HSSFWorkbook(is);
//            }else if(fileName.endsWith("xlsx")){
//                //2007 及2007以上
//                workbook = new XSSFWorkbook(is);
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        }
//        return workbook;
//    }

}
