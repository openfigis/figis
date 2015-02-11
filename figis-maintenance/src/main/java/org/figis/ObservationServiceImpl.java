package org.figis;

import javax.inject.Inject;

import org.figis.mapping.ObservationMapper;
import org.mybatis.cdi.Mapper;

public class ObservationServiceImpl implements ObservationService {

	@Inject
	@Mapper
	private ObservationMapper observationMapper;

	public Observation doSomeStuff(String userId) {
		return this.observationMapper.getUser(userId);
	}
}