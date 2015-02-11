package org.figis.mapping;

import org.apache.ibatis.annotations.Select;
import org.figis.Observation;

public interface ObservationMapper {

	@Select("SELECT * FROM observation WHERE id = #{}")
	Observation getObservation();
}