package com.book.ResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBookResponseError {


    @JsonProperty("msg")
    private String Msg;


    public void setMsg(String msg) {
        this.Msg = msg;
    }

    public String getMsg(){
        return Msg;
    }


}
