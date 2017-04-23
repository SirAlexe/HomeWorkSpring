package ru.mera.samples.application.mappings;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

import ru.mera.samples.application.dto.AddressDTO;
import ru.mera.samples.application.dto.UserDTO;
import ru.mera.samples.domain.dao.UserRepository;
import ru.mera.samples.domain.entities.AddressEntity;
import ru.mera.samples.domain.entities.UserEntity;

public class AddressToEntityMap extends PropertyMap<AddressDTO, AddressEntity> {

	public static final String LITTERAL = ", ";

	@Autowired
	private UserRepository userRepository;

	Converter<AddressDTO, List<UserEntity>> converter = new AbstractConverter<AddressDTO, List<UserEntity>>() {

		@Override
		protected List<UserEntity> convert(AddressDTO source) {
			List<UserEntity> userEntityList = new ArrayList<>();
			
		
			
			if (source == null) {
				return null;
			}			
			if (source.getResidents() == null){
				return null;
			}
				for (Long key : source.getResidents().keySet()) {
					UserEntity userEntity = userRepository.findById(key);
					userEntityList.add(userEntity);
				}
			
			return userEntityList;
		}
	};

	@Override
	protected void configure() {
		using(converter).map(source, destination.getResidents());
	}

	// protected void configure() {
	// List<UserEntity> residents = new ArrayList<>();
	//
	// source.getResidents().forEach((id, fullName) -> {
	// UserEntity userEntity = new UserEntity(); //TODO
	// userRepository.findById(id);
	// residents.add(userEntity);
	// });
	// map().setResidents(residents);
	// }

}
