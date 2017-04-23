package ru.mera.samples.application.mappings;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import ru.mera.samples.application.dto.AddressDTO;
import ru.mera.samples.domain.entities.AddressEntity;
import ru.mera.samples.domain.entities.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressToDTOMap extends PropertyMap<AddressEntity, AddressDTO> {

	public static final String LITTERAL = ", ";

	Converter<AddressEntity, Map<Long, String>> converter = new AbstractConverter<AddressEntity, Map<Long, String>>() {

		@Override
		protected Map<Long, String> convert(AddressEntity source) {
			StringBuilder fullNameBuilder = new StringBuilder();
			Map<Long, String> map = new HashMap<>();
			if (source == null) {
				return null;
			}
			
			if (source.getResidents() == null) {
				return null;
			}
			
			for (UserEntity userEntity : source.getResidents()) {
				if (userEntity != null) {
					fullNameBuilder.append(userEntity.getLastName()).append(",").append(userEntity.getFirstName());
					map.put(userEntity.getId(), fullNameBuilder.toString());
				}
			}

			return map;
		}
	};

	@Override
	protected void configure() {
		using(converter).map(source, destination.getResidents());
	}

	//
	// protected void configure() {
	// Map<Long, String> residents = new HashMap<>();
	// StringBuilder fullNameBuilder = new StringBuilder();
	// source.getResidents().forEach(userEntity -> {
	// fullNameBuilder.append(userEntity.getLastName()).append(",").append(userEntity.getFirstName());
	// residents.put(userEntity.getId(), fullNameBuilder.toString());
	// });
	// map().setResidents(residents);
	// }
}
