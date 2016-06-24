package zhou.D_bar_code;


/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		TwoDBarCode twoDBarCode = new TwoDBarCode();
		twoDBarCode.createBarCode("xxxasd");
		
		//Thread.sleep(1);
		TwoDBarCode twoDBarCodeWithLogo = new TwoDBarCode();
		twoDBarCodeWithLogo.setFormat("png");
		twoDBarCodeWithLogo.createBarCode("小九九", "D:\\小九九\\小九九.jpg" );
	}


}
