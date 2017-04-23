package ru.mera.samples.presentation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.mera.samples.application.dto.ImageDTO;
import ru.mera.samples.application.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageResourse {

	@Autowired
	ImageService imageService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ImageDTO getImage(@PathVariable("userId") Long imageId) {
		ImageDTO image = imageService.read(imageId);		
		return image;
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public List<ImageDTO> getImage() {
		List<ImageDTO> image = imageService.readAll();		
		return image;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addImage(@RequestBody ImageDTO image) {	
		imageService.create(image);
		return "This is our message POST request";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String updateImage(@RequestBody ImageDTO image) {
		imageService.update(image);		
		return "This is our message PUT request";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public String deleteImage(@PathVariable("userId") Long imageId) {
		imageService.delete(imageId);		
		return "This is our message DELETE request" + imageId;
	}
}
