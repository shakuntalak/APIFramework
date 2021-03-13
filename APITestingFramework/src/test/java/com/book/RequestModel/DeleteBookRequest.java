package com.book.RequestModel;



import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookRequest {

    public String ID;


   @JsonProperty("ID")
    public void setDeleteBookID(String ID) {
       this.ID = ID;
    }


    //public String getDeleteBookID() {
       // return ID;
//    }
}


