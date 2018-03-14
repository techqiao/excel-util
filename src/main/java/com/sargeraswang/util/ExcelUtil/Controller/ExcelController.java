package com.sargeraswang.util.ExcelUtil.Controller;

import com.sargeraswang.util.ExcelUtil.Domain.FileServerResponse;
import com.sargeraswang.util.ExcelUtil.Service.ExcelService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Description : excel-util
 * <p>Date : 2018\3\13 0013 10:34
 * <p>@author : wjq
 */
@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping(value = "test")
    public FileServerResponse test(MultipartFile file, HttpServletRequest request){
        return excelService.getExcelData(file,request);
    }

}
