/**
 * 
 */
package com.lionsardesai.dbi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shardul
 * 
 */
public class ScavengerForBloombergTicker {

	// TODO logging and exception handling
	/**
	 * @return ticker from bloomberg site
	 */
	public static String scavenge() {

		try {
			URL url = new URL("http://www.bloomberg.com/");
			URLConnection urlConnection = url.openConnection();
			BufferedReader dis = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			String html = "", tmp = "";
			StringBuffer ret = new StringBuffer();
			// read all HTML source from given URL
			while ((tmp = dis.readLine()) != null) {
				html += " " + tmp;
			}
			dis.close();

			// replace all white spaces region with single space
			html = html.replaceAll("\\s+", " ");
			// build the pattern using regular expression
			Pattern p = Pattern
					.compile("<div id=\"tickerID\" class=\"ticker\" > (.*?)</div> </div>");
			// Match the pattern with given html source
			Matcher m = p.matcher(html);
			// Get all matches that matched my pattern
			while (m.find() == true) {
				// Print the first matched pattern
				System.out.println(m.group(1));
				ret.append(m.group(1));
			}
			return ret.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
