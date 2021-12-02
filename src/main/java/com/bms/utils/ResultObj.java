package com.bms.utils;

import lombok.Data;

@Data
public class ResultObj {

    private Object data;

    private boolean resultFlg;

    private String resultMessage;

    private ResultObj(){}

    public ResultObj(Object data, boolean resultFlg, String resultMessage){
        this.data = data;
        this.resultFlg = resultFlg;
        this.resultMessage = resultMessage;
    }

    public ResultObj(Object data){
        this.data = data;
        this.resultFlg = true;
        this.resultMessage = "";
    }

    public ResultObj(boolean resultFlg, String resultMessage){
        this.data = "";
        this.resultFlg = resultFlg;
        this.resultMessage = resultMessage;
    }
}
