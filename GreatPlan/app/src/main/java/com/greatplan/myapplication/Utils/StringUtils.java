/**
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greatplan.myapplication.Utils;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;

/**
 * 字符串操作工具包 结合android.text.TextUtils使用
 * 
 * @author heng.ma jingle1267@163.com
 */
public final class StringUtils {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private StringUtils() {
		throw new Error("Do not need instantiate!");
	}

	/**
	 * Returns true if the string is null or 0-length.
	 * 
	 * @param str
	 *            the string to be examined
	 * @return true if str is null or zero length
	 */
	public static boolean isEmpty(CharSequence str) {
		return TextUtils.isEmpty(str);
	}

	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0 || "[]".equals(cs.toString())) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static long toLong(String str, long defValue) {
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * byte[]数组转换为16进制的字符串
	 * 
	 * @param data
	 *            要转换的字节数组
	 * @return 转换后的结果
	 */
	public static final String byteArrayToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder(data.length * 2);
		for (byte b : data) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase(Locale.getDefault());
	}

	/**
	 * 16进制表示的字符串转换为字节数组
	 * 
	 * @param s
	 *            16进制表示的字符串
	 * @return byte[] 字节数组
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] d = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return d;
	}

	/**
	 * 将给定的字符串中所有给定的关键字标红
	 * 
	 * @param sourceString
	 *            给定的字符串
	 * @param keyword
	 *            给定的关键字
	 * @return 返回的是带Html标签的字符串，在使用时要通过Html.fromHtml()转换为Spanned对象再传递给TextView对象
	 */
	public static String keywordMadeRed(String sourceString, String keyword) {
		String result = "";
		if (sourceString != null && !"".equals(sourceString.trim())) {
			if (keyword != null && !"".equals(keyword.trim())) {
				result = sourceString.replaceAll(keyword, "<font color=\"red\">" + keyword + "</font>");
			} else {
				result = sourceString;
			}
		}
		return result;
	}

	/**
	 * 为给定的字符串添加HTML红色标记，当使用Html.fromHtml()方式显示到TextView 的时候其将是红色的
	 * 
	 * @param string
	 *            给定的字符串
	 * @return
	 */
	public static String addHtmlRedFlag(String string) {
		return "<font color=\"red\">" + string + "</font>";
	}

	// if string is null, return default
	public static String getString(String string, String defaultStr) {
		if (null == string) {
			return defaultStr;
		}
		return string;
	}

	// 提取字符串中的数字
	public static String getStringNum(String str) {
		if (TextUtils.isEmpty(str)) {
			return "";
		}
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public static byte[] unzip(byte[] zipBytes) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(zipBytes);
		ZipInputStream zis = new ZipInputStream(bais);
		zis.getNextEntry();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final int BUFSIZ = 40960;
		byte inbuf[] = new byte[BUFSIZ];
		int n;
		while ((n = zis.read(inbuf, 0, BUFSIZ)) != -1) {
			baos.write(inbuf, 0, n);
		}
		byte[] data = baos.toByteArray();
		zis.close();
		return data;
	}
}
