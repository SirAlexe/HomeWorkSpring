package ru.mera.sample.temple;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import ru.mera.samples.application.dto.AbstractDTO;

public class ImageDTOser extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3378655168485772290L;

	private String name;

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

	private void writeObject(ObjectOutputStream out) throws IOException {
		 out.defaultWriteObject();
		 ImageIO.write(image, "png", out);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	    in.defaultReadObject();
	    image = ImageIO.read(in);
	}

	public static void main(String[] args) {
		ImageDTOser imageDTO = new ImageDTOser();

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

			ImageDTOser imageDTO_read = (ImageDTOser) in.readObject();
			System.out.println("id " + imageDTO_read.getId() + " Name " + imageDTO_read.getName() + " Name "
					+ imageDTO_read.getImage());
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
