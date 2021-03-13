package com.book.ResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddDuplicateBookResponse
{
    String Msg;

    @JsonProperty("msg")
    public void setMsg(String msg) {
        Msg = msg;
    }


    public String getMsg() {
        return Msg;
    }



}
