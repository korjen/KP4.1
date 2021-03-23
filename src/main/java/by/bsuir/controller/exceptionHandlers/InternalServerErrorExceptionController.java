package by.bsuir.controller.exceptionHandlers;

import org.hibernate.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InternalServerErrorExceptionController {
    @ExceptionHandler({NonUniqueResultException.class,
            NullPointerException.class,
            RequestRejectedException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String showErrorPage() {
        return "/errors/ErrorPage500";
    }
}
