package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class Base642File {

	static public char[] encode(byte[] data) {
		char[] out = new char[((data.length + 2) / 3) * 4];

		//
		// 3 bytes encode to 4 chars. Output is always an even
		// multiple of 4 characters.
		//
		for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
			boolean quad = false;
			boolean trip = false;

			int val = (0xFF & (int) data[i]);
			val <<= 8;
			if ((i + 1) < data.length) {
				val |= (0xFF & (int) data[i + 1]);
				trip = true;
			}
			val <<= 8;
			if ((i + 2) < data.length) {
				val |= (0xFF & (int) data[i + 2]);
				quad = true;
			}
			out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 1] = alphabet[val & 0x3F];
			val >>= 6;
			out[index + 0] = alphabet[val & 0x3F];
		}
		return out;
	}

	/**
	 * Returns an array of bytes which were encoded in the passed character
	 * array.
	 * 
	 * @param data
	 *            the array of base64-encoded characters
	 * @return decoded data array
	 */
	static public byte[] decode(char[] data) {
		int len = ((data.length + 3) / 4) * 3;
		if (data.length > 0 && data[data.length - 1] == '=')
			--len;
		if (data.length > 1 && data[data.length - 2] == '=')
			--len;
		byte[] out = new byte[len];

		int shift = 0; // # of excess bits stored in accum
		int accum = 0; // excess bits
		int index = 0;

		for (int ix = 0; ix < data.length; ix++) {
			int value = codes[data[ix] & 0xFF]; // ignore high byte of char
			if (value >= 0) { // skip over non-code
				accum <<= 6; // bits shift up by 6 each time thru
				shift += 6; // loop, with new bits being put in
				accum |= value; // at the bottom.
				if (shift >= 8) { // whenever there are 8 or more shifted in,
					shift -= 8; // write them out (from the top, leaving any
					out[index++] = // excess at the bottom for next iteration.
					(byte) ((accum >> shift) & 0xff);
				}
			}
		}
		// if (index != out.length)
		// throw new Error("miscalculated data length!");

		return out;
	}

	//
	// code characters for values 0..63
	//
	static private char[] alphabet = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAFfKj/FAAAACXBIWXMAAAsTAAALEwEAmpwYAAAABGdBTUEAALGOfPtRkwAAACBjSFJNAAB6JQAAgIMAAPn/AACA6QAAdTAAAOpgAAA6mAAAF2+SX8VGAAADAFBMVEUAgMsAi9sAgMpV6f8AWJgRbKP8/v8ec6VV6v8Ks/6F/v+G/v0Do/IAgsYnyfMAmOq0//iR//3T//kSu//H//f8/f4KZ4kNe5stzfwAjNuu4vae1/W47P207/xUhKE93P9b7f80mMmK/P62//larNkAY59T5Pw7f6gbbqCj//xvip2j//qd//td6f84ksR5z+j19vew//tso8DH//lW2vofyP8gxfsmcJ0yyPPD//d3m7C3//fA5PUyeKKT/v4AofOBxt4AkOFl9f+T6v6L0uxb+P8yiLsFrPkAgs4RbKTO//i51+T9/v8Ags8Am+0Quv8Quf/K//kAcbV7+f4AiNiN/v0dx/8Pre8Ab7JertIYbJ4Da5MZZZIEm+W83++arLhg8v+K+fc2eJ8VuPbt+f9n8v86ndALpeE80vkAj+AhfbJG4f8uzPT2+vzA8fshxPJHmcJ3+f/E//c42P8ewfn5/P1O7fs+xPQDmd9QvebV//fp7fAAWZUKsvwpzP6s//vM//in//oAfsgKqO8sbZZRhKOq5PfT7vqX/PwAfcRY6/8AZ6z///+NjY2Ojo6Pj4+QkJCRkZGSkpKTk5OUlJSVlZWWlpaXl5eYmJiZmZmampqbm5ucnJydnZ2enp6fn5+goKChoaGioqKjo6OkpKSlpaWmpqanp6eoqKipqamqqqqrq6usrKytra2urq6vr6+wsLCxsbGysrKzs7O0tLS1tbW2tra3t7e4uLi5ubm6urq7u7u8vLy9vb2+vr6/v7/AwMDBwcHCwsLDw8PExMTFxcXGxsbHx8fIyMjJycnKysrLy8vMzMzNzc3Ozs7Pz8/Q0NDR0dHS0tLT09PU1NTV1dXW1tbX19fY2NjZ2dna2trb29vc3Nzd3d3e3t7f39/g4ODh4eHi4uLj4+Pk5OTl5eXm5ubn5+fo6Ojp6enq6urr6+vs7Ozt7e3u7u7v7+/w8PDx8fHy8vLz8/P09PT19fX29vb39/f4+Pj5+fn6+vr7+/v8/Pz9/f3+/v7///8Ey57QAAAAjXRSTlP//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wDYXlVrAAABU0lEQVR42mLuUQIIICaG0wABxNDTAxBADFJSAAHE2MPwiQ8ggFgYSjhYAAKIoUdKqgcggJgY7D4zAAQQEwOHGANAADExvPdgAAggoCzDaeU1aQwAAcTCwMBweD+QBAggJgaGv97Kio8ZAAIIyJjzwuDYYwaAAAKpeb03goEBIICAIrdWy75lYAAIIKAyrsSPD4QZAAIIKCJzY7ExAwNAAAFFbquovhJjAAggFoY+G6nJ3wSKAAKIhYH3xyceRUEGgABiYhA2eagobM8AEEAgcxgY+uK2soQ/WVcEZAIEEMhOBoaAp9fUtt3iBDEBAogJLLDhf2XSMfmfICZAAEFUaG0ykefl0wQxAQIIJNAnwpE8n4NRd+bXR0UMAAEEEpA4x/nS+abk4/qbbxgYAAIIZEYQk8CHLyovNLbOe8zAABBAIBUcsbeZH951uav8O4iBASDAAMrSSU8m8uPWAAAAAElFTkSuQmCC"
			.toCharArray();
     public static void main(String[] args){
    	byte[] bt= decode(alphabet);
    	File f = new File("C:/aa.png");
    	try {
    	if (!f.exists())
    	f.createNewFile();
    	FileOutputStream fos = new FileOutputStream(f);
    	fos.write(bt);
    	fos.close();
    	System.out.println("转换工具-->转换完毕!");
    	} catch (FileNotFoundException e) {
//    	 TODO 自动生成 catch 块
    	e.printStackTrace();
    	} catch (IOException e) {
//    	 TODO 自动生成 catch 块
    	e.printStackTrace();
    	}
    	 
     }
	//
	// lookup table for converting base64 characters to value in range 0..63
	//
	static private byte[] codes = new byte[256];
	static {
		for (int i = 0; i < 256; i++)
			codes[i] = -1;
		for (int i = 'A'; i <= 'Z'; i++)
			codes[i] = (byte) (i - 'A');
		for (int i = 'a'; i <= 'z'; i++)
			codes[i] = (byte) (26 + i - 'a');
		for (int i = '0'; i <= '9'; i++)
			codes[i] = (byte) (52 + i - '0');
		codes['+'] = 62;
		codes['/'] = 63;
	}

}
