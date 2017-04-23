package ru.mera.samples.application.mappings;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import ru.mera.samples.application.dto.ImageDTO;
import ru.mera.samples.domain.entities.ImageEntity;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageToEntityMap extends PropertyMap<ImageDTO, ImageEntity> {

	private static final Log logger = LogFactory.getLog(ImageToEntityMap.class);

	Converter<ImageDTO, byte[]> converter = new AbstractConverter<ImageDTO, byte[]>() {
		protected byte[] convert(ImageDTO source) {
			if (source == null) {
				System.out.println("source==null");
				return null;
			}

			if (source.getImage() == null) {
				System.out.println("source.getImage()==null");
				return null;
			}

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try {
				ImageIO.write(source.getImage(), getImageFormat(source.getName()), bos);
			} catch (IOException e) {
				logger.error("Exception occured while transforming image to bytes");
			}

			return bos.toByteArray();
		}
	};

	@Override
	protected void configure() {		
		using(converter).map(source, destination.getImage());	
	}

	// protected void configure() {
	// ByteArrayOutputStream bos = new ByteArrayOutputStream();
	// try {
	// ImageIO.write(source.getImage(), getImageFormat(source.getName()), bos);
	// } catch (IOException e) {
	// logger.error("Exception occured while transforming image to bytes");
	// }
	// map().setImage(bos.toByteArray());
	// }

	private String getImageFormat(String name) {
		String s = name.substring(name.lastIndexOf(".") + 1);
		System.out.println("s=" + s);
		return s;
	}
}
