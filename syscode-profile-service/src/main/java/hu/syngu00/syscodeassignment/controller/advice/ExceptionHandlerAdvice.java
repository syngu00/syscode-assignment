package hu.syngu00.syscodeassignment.controller.advice;

import am.ik.yavi.core.ConstraintViolationsException;
import hu.syngu00.syscodeassignment.model.dto.Violation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({ConstraintViolationsException.class})
    public ResponseEntity<List<Violation>> handleAccessDeniedException(ConstraintViolationsException violationsException, WebRequest request) {
        List<Violation> violations = violationsException.violations()
                .stream()
                .map(violation -> new Violation(violation.name(),
                        violation.messageKey(),
                        violation.message(),
                        violation.args()
                )).toList();

        return new ResponseEntity<>(violations, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
