package org.fao.dataloading.countryrfb.jaxb;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.fao.fi.figis.devcon.FIGISDoc;

public class JaxbUnmarshal {

	public FIGISDoc parse(String figisDoc) {
		FIGISDoc doc = null;
		try {
			JAXBContext context = JAXBContext.newInstance(FIGISDoc.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JaxbValidationEventHandler handler = new JaxbValidationEventHandler();
			unmarshaller.setEventHandler(handler);

			doc = (FIGISDoc) unmarshaller.unmarshal(new StreamSource(new StringReader(figisDoc)));
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return doc;

	}
}
