/**
 * 
 */
package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.lionsardesai.beans.externalModel.ObjectFactory;
import com.lionsardesai.beans.externalModel.Rss;

/**
 * @author shardul
 * 
 */
public class UnmarshallRss {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller u = jc.createUnmarshaller();
			// @SuppressWarnings("unchecked")
			// JAXBElement<Rss> ele = (JAXBElement<Rss>) u
			// .unmarshal(new FileInputStream("bbcfeedexample2.xml"));

			@SuppressWarnings("unchecked")
			JAXBElement<Rss> ele = (JAXBElement<Rss>) u.unmarshal(new URL(
					"http://feeds.bbci.co.uk/news/business/rss.xml")
					.openConnection().getInputStream());

			Rss rss = ele.getValue();

			System.out.println("printing unmarshalled element");
			System.out.println(rss.getChannel().getItem().get(0)
					.getDescription());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
