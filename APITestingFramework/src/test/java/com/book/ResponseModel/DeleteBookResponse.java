package com.book.ResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookResponse
{
    String Msg;
    @JsonProperty("msg")
    private void setDeleteMsg(String msg) {
        Msg = msg;
    }

    public String getDeleteMsg() {
        return Msg;
    }


}
