package com.sargeraswang.util.ExcelUtil.Service;

import com.sargeraswang.util.ExcelUtil.Domain.FileServerResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>Description : excel-util
 * <p>Date : 2018\3\13 0013 9:53
 * <p>@author : wjq
 */
public interface ExcelService {
    FileServerResponse getExcelData(MultipartFile file, HttpServletRequest request);
}
