package io.jgodara.evior.eivorgitfront.controllers;

import io.jgodara.evior.eivorgitfront.exeptions.ResourceNotFoundException;
import io.jgodara.evior.eivorgitfront.model.Integration;
import io.jgodara.evior.eivorgitfront.model.Preferences;
import io.jgodara.evior.eivorgitfront.model.User;
import io.jgodara.evior.eivorgitfront.repository.IntegrationsRepository;
import io.jgodara.evior.eivorgitfront.repository.PreferencesRepository;
import io.jgodara.evior.eivorgitfront.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/integrations/")
public class IntegrationsController {

  @Autowired
  private IntegrationsRepository integrationsRepository;

  @Autowired
  private PreferencesRepository preferencesRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("{id}")
  public ModelAndView viewIntegrationsController(@PathVariable("id") long id) {
    Integration integration = integrationsRepository.getOne(id);
    if (integration == null) {
      throw new ResourceNotFoundException();
    }

    ModelAndView mv = new ModelAndView("integration/view");
    mv.getModel().put("integration", integration);
    return mv;
  }

  @GetMapping("/{id}/edit")
  public ModelAndView editIntegrationsGetController(@PathVariable("id") long id, Model model) {
    ModelAndView mv = new ModelAndView("integration/fields");

    Integration integration = integrationsRepository.getOne(id);
    if (integration == null) {
      throw new ResourceNotFoundException();
    }

    mv.getModel().put("submit", id + "/edit");
    model.addAttribute("integration", integration);
    return mv;
  }

  @PostMapping("/{id}/edit")
  public ModelAndView editIntegrationsPostController(@PathVariable("id") long id,
      @ModelAttribute("integration") Integration inte) {

    Integration integration = integrationsRepository.getOne(id);

    if (integration == null) {
      throw new ResourceNotFoundException();
    }

    copyIntegrationProperties(integration, inte);

    integrationsRepository.saveAndFlush(integration);
    return new ModelAndView("redirect:/integrations/" + id + "/edit");
  }

  @GetMapping("/add")
  public ModelAndView addIntegrationGetController(Model mode) {
    ModelAndView mv = new ModelAndView("integration/fields");

    Integration integration = new Integration();

    mode.addAttribute("integration", integration);
    mv.getModel().put("submit", "add");

    return mv;
  }

  @PostMapping("/add")
  private ModelAndView addIntegrationPostController(HttpServletRequest request,
      @ModelAttribute("integration") Integration integration) {


    User userByUsername = userRepository.getUserByUsername(request.getUserPrincipal().getName());
    integration.setUser(userByUsername);
    Integration inte = integrationsRepository.saveAndFlush(integration);

    Preferences preferences = new Preferences();
    preferences.setIntegration(inte);
    preferences.setMrAcceptedComment("Your MR has been accepted");
    preferences.setMrFailedComment("Your MR has not been accepted");

    preferencesRepository.saveAndFlush(preferences);

    return new ModelAndView("redirect:/integrations/" + inte.getId() + "/edit");
  }

  private void copyIntegrationProperties(Integration to, Integration from) {

    to.setAppName(to.getAppName());
    to.setBotUsername(to.getBotUsername());
    to.setSecret(to.getSecret());
    to.setServerUrl(to.getServerUrl());
    to.setToken(to.getToken());

    to.getPreferences().setMrAcceptedComment(to.getPreferences().getMrAcceptedComment());
    to.getPreferences().setMrFailedComment(to.getPreferences().getMrFailedComment());
    to.getPreferences().setMrPattern(to.getPreferences().getMrPattern());
  }
}
