package org.fao.dataloading.countryrfb;

import javax.inject.Inject;

import org.fao.figis.db.FigisDataBaseProducer;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses(FigisDataBaseProducer.class)
public class CountryRfbProcessTest {

	@Inject
	CountryRfbProcess p;

	@Test
	public void testRun() {
		p.run();
	}

}
