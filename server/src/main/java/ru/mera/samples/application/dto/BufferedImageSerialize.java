package ru.mera.samples.application.dto;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BufferedImageSerialize extends JsonSerializer<BufferedImage> {

	@Override
	public void serialize(BufferedImage value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(value, "png", os);
		gen.writeObject( Base64.getEncoder().encodeToString(os.toByteArray()));

	}

}