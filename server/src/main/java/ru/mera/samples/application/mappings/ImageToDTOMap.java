package ru.mera.samples.application.mappings;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import ru.mera.samples.application.dto.ImageDTO;
import ru.mera.samples.domain.entities.ImageEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ImageToDTOMap extends PropertyMap<ImageEntity, ImageDTO> {

  private static final Log logger = LogFactory.getLog(ImageToDTOMap.class);

  Converter< byte[], BufferedImage> converter = new AbstractConverter< byte[], BufferedImage>() {

		@Override
		protected BufferedImage  convert(byte[] source) {
			
			BufferedImage resultImage = null;
			try (ByteArrayInputStream byteArr = new ByteArrayInputStream(source)) {
				resultImage = ImageIO.read(byteArr);
			} catch (IOException e) {
				  logger.error("Exception occured while transforming image from bytes");
			}
		
			return resultImage;
		}		
	};

	@Override
	protected void configure() {
		using(converter).map(source.getImage(), destination.getImage());
	}
	
//  protected void configure() {
//    BufferedImage resultImage = null;
//    try( ByteArrayInputStream byteArr = new ByteArrayInputStream(source.getImage()) ) {
//      resultImage = ImageIO.read(byteArr);
//    } catch( IOException e ) {
//      logger.error("Exception occured while transforming image from bytes");
//    }
//    map().setImage(resultImage);
//  }
}

