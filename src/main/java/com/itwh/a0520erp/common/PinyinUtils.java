package com.itwh.a0520erp.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
public class PinyinUtils {

	// 返回一个拼音字符串，并且首字母大写
	public static String getPingYin(String inputString) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		String output = "";
		if (inputString != null && inputString.length() > 0 && !"null".equals(inputString)) {
			char[] input = inputString.trim().toCharArray();
			try {
				for (int i = 0; i < input.length; i++) {
					if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
						String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
						output += temp[0];
					} else
						output += Character.toString(input[i]);
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
		} else {
			return "*";
		}
		return output;
	}

	public static void main(String[] args) {
		String yin = getPingYin("老雷");
		System.out.println(yin);
	}
}
