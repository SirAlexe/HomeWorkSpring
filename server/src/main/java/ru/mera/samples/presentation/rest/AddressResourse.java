package ru.mera.samples.presentation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.mera.samples.application.dto.AddressDTO;
import ru.mera.samples.application.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressResourse {


	@Autowired
	AddressService addressService;

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
	public AddressDTO getImage(@PathVariable("addressId") Long addressId) {
		AddressDTO address = addressService.read(addressId);
		return address;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<AddressDTO> getImage() {
		List<AddressDTO> address = addressService.readAll();
		return address;
	}

	@RequestMapping(method = RequestMethod.POST)
	public AddressDTO addImage(@RequestBody AddressDTO address) {
		addressService.create(address);
		return address;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String updateImage(@RequestBody AddressDTO address) {
		addressService.update(address);
		return "This is our message PUT request";
	}

	@RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE)
	public String deleteImage(@PathVariable("addressId") Long addressId) {
		addressService.delete(addressId);
		return "This is our message DELETE request";
	}
}
