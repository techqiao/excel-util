package com.sargeraswang.util.ExcelUtil.Domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;

import static com.sargeraswang.util.ExcelUtil.Domain.ResponseCode.*;

/**
 * Description : file-server
 * Date : 2018/1/9 16:04
 *
 * @author : Matrix [xhyrzldf@foxmail.com]
 */

@SuppressWarnings("unused")
@Getter
public class FileServerResponse<T> {
    /**
     * 错误码
     */
    @JSONField(ordinal = 1)
    private Integer code;

    /**
     * 提示信息
     */
    @JSONField(ordinal = 2)
    private String msg;

    /**
     * 具体的内容
     */
    @JSONField(ordinal = 3)
    private T data;


    //操作成功的相关方法
    public static <T> FileServerResponse<T> success(){
        return new FileServerResponse<>(OK.getCode(), OK.getMsg());
    }

    public static <T> FileServerResponse<T> success(T data){
        return new FileServerResponse<>(OK.getCode(), OK.getMsg(), data);
    }

    public static <T> FileServerResponse<T> success(String msg, T data){
        return new FileServerResponse<>(OK.getCode(), msg, data);
    }

    public static <T> FileServerResponse<T> saveSuccess(T data){
        return new FileServerResponse<>(CREATED.getCode(), CREATED.getMsg(),data);
    }

    public static <T> FileServerResponse<T> deleteSuccess(){
        return new FileServerResponse<>(NO_CONTENT.getCode(), NO_CONTENT.getMsg());
    }

    public static <T> FileServerResponse<T> uploadSuccess(){
        return new FileServerResponse<>(UPLOADED.getCode(), UPLOADED.getMsg());
    }

    public static <T> FileServerResponse<T> messageSuccess(String msg){
        return new FileServerResponse<>(OK.getCode(), msg);
    }

    //操作错误的相关方法
    public static <T> FileServerResponse<T> error(){
        return new FileServerResponse<>(INTERNAL_SERVER_ERROR.getCode(),INTERNAL_SERVER_ERROR.getMsg());
    }

    public static <T> FileServerResponse<T> error(String errorMessage){
        return new FileServerResponse<>(INTERNAL_SERVER_ERROR.getCode(),errorMessage);
    }

    public static <T> FileServerResponse<T> error(ResponseCode responseCode){
        return new FileServerResponse<>(responseCode.getCode(), responseCode.getMsg());
    }

    public static <T> FileServerResponse<T> error(ResponseCode responseCode,String errorMessage){
        return new FileServerResponse<>(responseCode.getCode(), errorMessage);
    }


    /**
     *  私有构造方法，外部通过静态方法调用
     */
    private FileServerResponse() {
    }

    private FileServerResponse(Integer code) {
        this.code = code;
    }

    private FileServerResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private FileServerResponse(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    private FileServerResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
