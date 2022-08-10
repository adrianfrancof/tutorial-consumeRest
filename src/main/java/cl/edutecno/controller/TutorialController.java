package cl.edutecno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cl.edutecno.dto.TutorialDTO;
import cl.edutecno.service.TutorialService;

@Controller
@RequestMapping("tutoriales")
public class TutorialController {
	
	@Autowired
	private TutorialService tutorialService;
	
	@GetMapping
	public ModelAndView alumnos(){
		
		ModelAndView modelAndView = new ModelAndView("tutoriales");
		modelAndView.addObject("tutoriales", tutorialService.findAll());
		return modelAndView;
	}
	
	@GetMapping("/crear")
	public ModelAndView crear(){
		
		ModelAndView modelAndView = new ModelAndView("crear-tutorial");
		modelAndView.addObject("tutorial", new TutorialDTO());
		return modelAndView;
	}
	@PostMapping("/crear")
	public RedirectView crear(@ModelAttribute("tutorial") TutorialDTO tutorialDTO){
		tutorialService.save(tutorialDTO);
		//return new ModelAndView("redirect:/alumnos")
		return new RedirectView("/tutoriales");
	}
	
	//agregados
	@GetMapping("/editar")
	public ModelAndView editar(@RequestParam Long id){
		System.out.println(id);
		ModelAndView modelAndView = new ModelAndView("editar-tutorial");
		modelAndView.addObject("tutorial", new TutorialDTO());
		modelAndView.addObject("tutorialVO", tutorialService.findById(id));	
		return modelAndView;
	}
	@PostMapping("/editar")
	public RedirectView editar(@ModelAttribute("tutorial") TutorialDTO tutorialDTO){
		tutorialService.save(tutorialDTO);
		//return new ModelAndView("redirect:/alumnos")
		return new RedirectView("/tutoriales");
	}
	@GetMapping("/eliminar")
	public RedirectView eliminar(@RequestParam Long id){
		TutorialDTO tutorialDTO =  tutorialService.findById(id);
		if (tutorialDTO.getId() != null) {
			tutorialService.delete(tutorialDTO.getId());
		}
		return new RedirectView("/tutoriales");
		
	}

}
