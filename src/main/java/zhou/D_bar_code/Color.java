package zhou.D_bar_code;

/**
 * 颜色RGB值
 * @author Zhou
 *
 */
public enum Color {
	
	BLACK(0xFF000000),
	WHITE(0xFFFFFFFF),
	BLUE(0xFF104E8B),
	GREEN(0xFF00CD00);
	
	
	private int rgb;
	private Color(int rgb) {
		this.rgb = rgb;
	}
	
	public int getRgb() {
		return rgb;
	}
}
