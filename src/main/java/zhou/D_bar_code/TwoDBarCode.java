package zhou.D_bar_code;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class TwoDBarCode {
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;	

	private int width = 300;
	private int height = 300;
	private String filePath = System.getProperty("user.dir") + File.separator
			+ "2DBarCode";
	
	private String logoPath = null;
	private String format = "png";
	private String fileName = "zxing" + System.nanoTime() + "." + format;
	
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public void setFormat(String format) {
		this.format = format;
		this.fileName = "zxing" + System.nanoTime() + "." + format;
	}
	

	public void createBarCode(String content, int width, int height,
			String filePath, String fileName, String logoPath,String format) throws Exception {

		File barCodePath = new File(filePath);
		if (!barCodePath.exists()) {
			barCodePath.mkdir();
		}

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
		if (logoPath != null && !logoPath.isEmpty()) {
			File qrcodeFile = new File(filePath, fileName);
			addLogo(bitMatrix, format, qrcodeFile, logoPath);
		}
		System.out.println("输出成功：" + filePath + File.separator + fileName);

	}

	public void createBarCode(String content, String logoPath) throws Exception {
		createBarCode(content, width, height, filePath, fileName, logoPath ,format);
	}

	public void createBarCode(String content) throws Exception {
		createBarCode(content, width, height, filePath, fileName, logoPath , format);
	}

	private void addLogo(BitMatrix bitMatrix, String format, File qrcodeFile,
			String logoPath) throws Exception {
		writeToFile(bitMatrix, format, qrcodeFile, logoPath);
	}

	private void writeToFile(BitMatrix matrix, String format, File file,
			String logoPath) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		Graphics2D gs = image.createGraphics();

		// 载入logo
		Image img = ImageIO.read(new File(logoPath));

		int width = image.getWidth() / 5;
		int height = image.getHeight() / 5;

		int x = (image.getWidth() - width) / 2;
		int y = (image.getHeight() - height) / 2;

		gs.drawImage(img, x, y, width, height, null);
		gs.dispose();
		img.flush();
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + file);
		}
	}

	private BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

}