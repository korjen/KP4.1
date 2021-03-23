package by.bsuir.controller.exceptionHandlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessForbiddenExceptionController {

    @RequestMapping("/403")
    public String showErrorPage() {
        return "/errors/ErrorPage403";
    }
}

