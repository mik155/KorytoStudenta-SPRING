package com.app.learningcards.controllers.security;

import com.app.learningcards.models.User;
import com.app.learningcards.request.RecipesByCategory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class testController {
    @PostMapping
    public String test(@RequestBody RecipesByCategory recipes) {
        return recipes.toString();
    }

    @GetMapping
    public String test2()
    {
        return "Tak mi dobrze, tak mojo, aż rechoce się serce.\n" +
                "Same nogi mnie niosą gdzieś – i po co mi, gdzie?\n" +
                "Idę młody, genialny, niosę BUT W BUTONIERCE,\n" +
                "Tym co za mną nie zdążą echopowiem: – Adieu! –";
    }
}
