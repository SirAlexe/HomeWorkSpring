package ru.mera.samples.application.dto;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.TextNode;

public class ImageDTO extends AbstractDTO {

	private String name;
	@JsonDeserialize(using = BufferedImageDeserializer.class)
	@JsonSerialize(using = BufferedImageSerialize.class)
	private transient BufferedImage image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	

	

	public static void main(String[] args) {
		ImageDTO imageDTO = new ImageDTO();

		BufferedImage buffer = null;
		String fileName = "C:/workspace/1.png";

		try {
			buffer = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(buffer);

		imageDTO.setId(12L);
		imageDTO.setName("Name1");
		imageDTO.setImage(buffer);

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:/workspace/Ser.txt"))) {
			out.writeObject(imageDTO);
			System.out.println("Serializable OK ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:/workspace/Ser.txt"))) {

			ImageDTO imageDTO_read = (ImageDTO) in.readObject();
			System.out.println("id " + imageDTO_read.getId() + " Name " + imageDTO_read.getName() + " Name "
					+ imageDTO_read.getImage());
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
