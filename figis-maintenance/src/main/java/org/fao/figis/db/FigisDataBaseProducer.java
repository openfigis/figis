/**
 * (c) 2014 FAO / UN (project: vme-dao)
 */
package org.fao.figis.db;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Place your class / interface description here.
 *
 * History:
 *
 * ------------- --------------- ----------------------- Date Author Comment ------------- ---------------
 * ----------------------- 19 Feb 2014 Fiorellato Creation.
 *
 * @version 1.0
 * @since 19 Feb 2014
 */

public class FigisDataBaseProducer {

	@Produces
	@FigisDB
	@ApplicationScoped
	public EntityManager produceEntityManager() {
		return Persistence.createEntityManagerFactory("figis-persistence").createEntityManager();
	}

	protected static Logger LOG = LoggerFactory.getLogger(FigisDataBaseProducer.class);

}