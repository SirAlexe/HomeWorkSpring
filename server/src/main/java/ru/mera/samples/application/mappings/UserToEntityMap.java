package ru.mera.samples.application.mappings;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

import ru.mera.samples.application.dto.UserDTO;
import ru.mera.samples.domain.dao.AddressRepository;
import ru.mera.samples.domain.entities.AddressEntity;
import ru.mera.samples.domain.entities.UserEntity;

public class UserToEntityMap extends PropertyMap<UserDTO, UserEntity> {

	public static final String LITTERAL = ", ";

	@Autowired
	private AddressRepository addressRepository;

	Converter<UserDTO, UserEntity> converter = new AbstractConverter<UserDTO, UserEntity>() {

		@Override
		protected UserEntity convert(UserDTO source) {

			Long addressId = source.getAddressId();
			AddressEntity address = null;
			if (addressId != null && addressId >= 0) {
				address = addressRepository.findById(addressId);
			}

			UserEntity userEntity = new UserEntity();
			userEntity.setName(source.getLogin());
			userEntity.setFirstName(source.getFirstName());
			userEntity.setLastName(source.getLastName());
			if (address != null) {
				userEntity.setAddress(address);
			}

			return userEntity;
		}

	};

	@Override
	protected void configure() {
		using(converter).map(source, destination);
		// System.out.println(destination);
	}

//	protected void configure() {
//		Long addressId = source.getAddressId();
//		AddressEntity address = null;
//		if (addressId != null && addressId >= 0) {
//			address =  addressRepository.findById(addressId);
//		}
//		map().setAddress(address);
//		map().setName(source.getLogin());
//	}
}
