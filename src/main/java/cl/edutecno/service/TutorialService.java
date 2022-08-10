package cl.edutecno.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import cl.edutecno.dto.TutorialDTO;

public interface TutorialService {
	
	public List<TutorialDTO> findAll();
	public TutorialDTO save(TutorialDTO tutorialDTO);
	public TutorialDTO update(TutorialDTO tutorialDTO);
	public HttpStatus delete(Long id);
	public TutorialDTO findById(Long id);

}
