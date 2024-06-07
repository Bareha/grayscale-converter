package grayscaleConvertor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class grayscaleConvertor {

	public static void main(String[] args) {
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("jeff.jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		int height = img.getHeight();
		int width = img.getWidth();
		
		for(int y = 0; y<height; y++) {
			for(int x = 0; x<width; x++) {
				int pixel = img.getRGB(x, y);
				int a = (pixel >> 24) & 0xff;
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel) & 0xff;
				int avg = (r + b + g) / 3;
				int grayscale = (a << 24) | (avg << 16) | (avg << 8) | avg;
                img.setRGB(x, y, grayscale);
			}
		}
		
		try {
			File output = new File("newjeff.jpg");
			ImageIO.write(img, "jpg", output);
			System.out.println("Image converted to grayscale successfully");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}