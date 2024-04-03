package com.mukesh.expenseapp.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler  {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest req){

        ErrorResponse res=new ErrorResponse();
        res.setMessage(ex.getMessage());
        res.setStatus("failure");
        res.setTimestamp(System.currentTimeMillis());
        res.setStatuscode(400);

        return new ResponseEntity<>(res, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
     public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest req){
        ErrorResponse res=new ErrorResponse();
        res.setMessage(ex.getMessage());
        res.setStatus("failure");
        res.setTimestamp(System.currentTimeMillis());
        res.setStatuscode(400);

        return new ResponseEntity<>(res, HttpStatusCode.valueOf(400));
     }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest req){
        ErrorResponse res=new ErrorResponse();
        res.setMessage(ex.getMessage());
        res.setStatus("failure");
        res.setTimestamp(System.currentTimeMillis());
        res.setStatuscode(400);

        return new ResponseEntity<>(res, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException( MethodArgumentTypeMismatchException ex, WebRequest request) {

        ErrorResponse errorObject = new ErrorResponse();

        errorObject.setStatuscode(400);

        errorObject.setMessage(ex.getMessage());

        errorObject.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<ErrorResponse>(errorObject, HttpStatus.BAD_REQUEST);
    }



   // @ExceptionHandler(MethodArgumentNotValidException.class)
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                 HttpHeaders headers, HttpStatus status, WebRequest request) {

       Map<String, Object> body = new HashMap<String, Object>();

       body.put("statusCode", HttpStatus.BAD_REQUEST.value());

       List<String> errors = ex.getBindingResult()
               .getFieldErrors()
               .stream()
               .map(x -> x.getDefaultMessage())
               .collect(Collectors.toList());

       body.put("messages", errors);

       body.put("timestamp", new Date());

       return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
   }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, WebRequest request) {

        ErrorResponse errorObject = new ErrorResponse();

        errorObject.setStatuscode(500);

        errorObject.setMessage(ex.getMessage());

        errorObject.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<ErrorResponse>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
