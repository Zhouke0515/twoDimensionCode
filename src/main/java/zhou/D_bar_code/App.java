package zhou.D_bar_code;


/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		TwoDBarCode twoDBarCode = new TwoDBarCode();
		twoDBarCode.setColor(Color.GREEN.getRgb());
		twoDBarCode.createBarCode("这是一个二维码");
		
		//Thread.sleep(1);
		TwoDBarCode twoDBarCodeWithLogo = new TwoDBarCode();
		twoDBarCodeWithLogo.setFormat("png");
		twoDBarCodeWithLogo.setColor(Color.BLUE.getRgb());
		twoDBarCodeWithLogo.createBarCode("小九九", "D:\\小九九\\小九九.jpg" );
	}


}
