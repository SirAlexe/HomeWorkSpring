package ru.mera.samples.application.mappings;

import java.awt.image.BufferedImage;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

import ru.mera.samples.application.dto.UserDTO;
import ru.mera.samples.domain.dao.AddressRepository;
import ru.mera.samples.domain.entities.AddressEntity;
import ru.mera.samples.domain.entities.UserEntity;

public class UserToDTOMap extends PropertyMap<UserEntity, UserDTO> {

	public static final String LITTERAL = ", ";
	

	Converter<UserEntity, String> converter = new AbstractConverter<UserEntity, String>() {

		@Override
		protected String convert(UserEntity sourse) {
			StringBuilder builder = new StringBuilder();
		
			AddressEntity address = sourse.getAddress();
			builder.append(address.getCountry()).append(LITTERAL).append(address.getRegion()).append(LITTERAL)
					.append(address.getTown()).append(LITTERAL).append(address.getStreet()).append(LITTERAL)
					.append(address.getHouse());

			return builder.toString();
		}

	};

	@Override
	protected void configure() {
		map().setLogin(source.getName());
		using(converter).map(source, destination.getAddress());
	}

	// protected void configure() {
	// StringBuilder builder = new StringBuilder();
	// AddressEntity address = source.getAddress();
	// builder.append(address.getCountry()).append(LITTERAL).append(address.getRegion()).append(LITTERAL).append(
	// address.getTown()).append(LITTERAL).append(address.getStreet()).append(LITTERAL).append(address.getHouse());
	// map().setAddressId(address.getId());
	// map().setAddress(builder.toString());
	// }
}
