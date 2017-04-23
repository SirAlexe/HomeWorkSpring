package ru.mera.sample.temple;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.modelmapper.ModelMapper;

import ru.mera.samples.application.dto.ImageDTO;
import ru.mera.samples.application.mappings.ImageToDTOMap;
import ru.mera.samples.application.mappings.ImageToEntityMap;
import ru.mera.samples.domain.entities.ImageEntity;

public class TestImageMapNew {
	public static void main(String[] args) {
		BufferedImage buffer = null;
		String fileName = "C:/workspace/1.png";

		try {
			buffer = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(buffer);
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setName(fileName);
		imageDTO.setImage(buffer);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new ImageToDTOMap());
		modelMapper.addMappings(new ImageToEntityMap());

		System.out.println(imageDTO.getImage());
		ImageEntity imageEntity = modelMapper.map(imageDTO, ImageEntity.class);
		System.out.println(imageEntity.getImage());
	}
}
