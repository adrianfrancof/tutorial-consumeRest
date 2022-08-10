package cl.edutecno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.edutecno.dto.TutorialDTO;

@Service
public class TutorialServiceImp implements TutorialService{
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<TutorialDTO> findAll() {
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(requestHeaders);
		ResponseEntity<List<TutorialDTO>> response = 
				restTemplate.exchange("http://localhost:8083/api/tutoriales",
				HttpMethod.GET, request, new ParameterizedTypeReference<List<TutorialDTO>>() {
				});
		return response.getBody();
	}

	@Override
	public TutorialDTO save(TutorialDTO tutorialDTO) {
		
		HttpEntity<TutorialDTO> request = new HttpEntity<>(tutorialDTO);
		
		ResponseEntity<TutorialDTO> response = 
				restTemplate.exchange(
						"http://localhost:8083/api/tutoriales",
						HttpMethod.POST, 
						request, 
						new ParameterizedTypeReference<TutorialDTO>() {}
						);
		return response.getBody();
	}

	@Override
	public TutorialDTO update(TutorialDTO tutorialDTO) {
		
//		ResponseEntity<TutorialDTO> response = 
//		restTemplate.exchange("http://localhost:8083/api/tutoriales",
//		HttpMethod.POST, null, new ParameterizedTypeReference<TutorialDTO>() {
//		});
//		return response.getBody();
		
		HttpEntity<TutorialDTO> request = new HttpEntity<>(tutorialDTO);
		
		ResponseEntity<TutorialDTO> response = 
				restTemplate.postForEntity(
						"http://localhost:8083/api/tutoriales/"+tutorialDTO.getId(),
						request, 
						TutorialDTO.class
						);
		
		return response.getBody();
	}

	@Override
	public HttpStatus delete(Long id) {
		
		ResponseEntity<HttpStatus> response = 
				restTemplate.exchange(
						"http://localhost:8083/api/tutoriales/"+id,
						HttpMethod.DELETE, 
						null, 
						new ParameterizedTypeReference<HttpStatus>() {}
						);
		return response.getStatusCode();
	}

	@Override
	public TutorialDTO findById(Long id) {
		ResponseEntity<TutorialDTO> response = 
				restTemplate.exchange(
						"http://localhost:8083/api/tutoriales/"+id,
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<TutorialDTO>() {}
						);
		return response.getBody();
	}

}
