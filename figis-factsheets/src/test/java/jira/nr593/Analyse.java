package jira.nr593;

import org.fao.fi.figis.fs.common.data.object.FsObservableObject;
import org.junit.Test;

public class Analyse {

	@Test
	public void test() throws InstantiationException, IllegalAccessException {
		Class<?> classes[] = { org.fao.fi.figis.fs.dataset.landarea.FiRefLandArea.class,
				org.fao.fi.figis.fs.dataset.fishery.FiRefFishery.class,
				org.fao.fi.figis.fs.dataset.species.FiRefSpecies.class,
				org.fao.fi.figis.fs.dataset.eims.EimsObject.class,
				org.fao.fi.figis.fs.dataset.countryprofile.FiRefCountryprofile.class,
				org.fao.fi.figis.fs.dataset.resource.FiRefResource.class,
				org.fao.fi.figis.fs.dataset.naso.FiRefNaso.class,
				org.fao.fi.figis.fs.dataset.organization.FiRefOrganization.class,
				org.fao.fi.figis.fs.dataset.waterarea.FiRefWaterArea.class,
				org.fao.fi.figis.fs.dataset.topic.FiRefTopic.class,
				org.fao.fi.figis.fs.dataset.introsp.IntrospObject.class,
				org.fao.fi.figis.fs.dataset.geartype.FiRefGeartype.class,
				org.fao.fi.figis.fs.dataset.fishtech.FiRefFishtech.class };
		for (Class<?> clazz : classes) {
			if (clazz.newInstance() instanceof FsObservableObject) {
				System.out.println("instanceof FsObservableObject " + clazz);
			} else {
				System.out.println("not an instanceof FsObservableObject " + clazz);
			}
		}

		// fail("Not yet implemented");
	}
}
