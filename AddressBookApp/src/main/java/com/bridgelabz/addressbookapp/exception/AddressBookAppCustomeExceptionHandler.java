package com.bridgelabz.addressbookapp.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
@ControllerAdvice
@Slf4j
public class AddressBookAppCustomeExceptionHandler {
    private static final String message="Exception while Processing REST Request";
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO>handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        log.error("Invalid Date Format",exception);
        ResponseDTO responseDTO=new ResponseDTO(message,"Should have date in Format dd MM yyyy");
        return  new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
        List<String>errMsg=errorList.stream().map(objectError ->objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDTO=new ResponseDTO("Exception While Processing REST Request",errMsg);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AddressBookException.class)
    public ResponseEntity<ResponseDTO> handleAddressBookException(AddressBookException exception){
        ResponseDTO responseDTO=new ResponseDTO("Exception while processing REST Request",exception.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
    }
}
