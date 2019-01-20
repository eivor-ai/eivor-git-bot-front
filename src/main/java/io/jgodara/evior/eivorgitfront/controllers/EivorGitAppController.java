package io.jgodara.evior.eivorgitfront.controllers;

import io.jgodara.evior.eivorgitfront.repository.IntegrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class EivorGitAppController {

  @Autowired
  private IntegrationsRepository integrationsRepository;

  @GetMapping({"", "/"})
  public ModelAndView home(HttpServletRequest request) {
    String username = request.getUserPrincipal().getName();
    ModelAndView mv = new ModelAndView("home");
    mv.getModel().put("integrations", integrationsRepository.findByUser_Username(username));
    return mv;
  }

  @GetMapping({"/login", "/register"})
  public ModelAndView login() {

    boolean isLoggedIn = false;
    if (SecurityContextHolder.getContext() != null
        && SecurityContextHolder.getContext().getAuthentication() != null) {
      Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      isLoggedIn = !"anonymousUser".equals(user);
    }

    if (isLoggedIn) {
      return new ModelAndView("redirect:/");
    }

    return new ModelAndView("login");
  }

}
