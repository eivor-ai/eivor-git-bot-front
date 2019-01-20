package io.jgodara.evior.eivorgitfront.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class EivorGitAdminController {

  @GetMapping({"", "/"})
  public ModelAndView home() {
    return new ModelAndView("admin/home");
  }
}
