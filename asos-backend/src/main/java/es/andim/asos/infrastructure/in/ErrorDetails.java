package es.andim.asos.infrastructure.in;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ErrorDetails(HttpStatus status, List<String> errors) {

}