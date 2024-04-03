package com.mukesh.expenseapp.Exceptions;

import java.util.Date;

public class ErrorResponse {
    private String message;
   private String status;
   private long timestamp;

   private int statuscode;

    public ErrorResponse() {
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                ", statuscode=" + statuscode +
                '}';
    }

    public ErrorResponse(String message, String status, long timestamp, int statuscode) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }
}
