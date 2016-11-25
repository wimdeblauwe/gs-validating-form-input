package hello;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/user/{userId}")
    public String showForm(@PathVariable("userId") long userId,
                           PersonForm personForm) {
        // Normally, you would get the actual user from a service/repository here
        personForm.setId(userId);
        return "form";
    }

    @PostMapping("/user/{userId}")
    public String checkPersonInfo(@PathVariable("userId") long userId,
                                  @Valid PersonForm personForm,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/user/{userId}";
    }
}
