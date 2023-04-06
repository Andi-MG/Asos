package es.andim.asos.infrastructure.in.web;

import java.util.List;
import java.util.stream.Collectors;

import es.andim.asos.domain.MemberAlreadyExistsException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, errorList);
        return handleExceptionInternal(exception, errorDetails, headers, errorDetails.status(), request);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(MemberAlreadyExistsException.class)
    private ErrorDetails handleMemberAlreadyExistsException(MemberAlreadyExistsException exception) {
        return new ErrorDetails(HttpStatus.CONFLICT, List.of(exception.getMessage()));
    }
}