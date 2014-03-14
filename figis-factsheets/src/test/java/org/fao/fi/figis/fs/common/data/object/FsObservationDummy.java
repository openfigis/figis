package org.fao.fi.figis.fs.common.data.object;

import org.fao.fi.figis.util.xml.ReferenceElement;

public class FsObservationDummy extends FsObservation {

	public void setOid(Integer v) {
	}

	public FsName getName() {
		FsName fsName = new FsName();

		return fsName;
	};

	/**
	 * 
	 */
	private static final long serialVersionUID = 5847089057889771825L;

	public Class<? extends ReferenceElement> getRefElemClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends FiRefObject> getParentClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends ReferenceElement> getObsRefElemClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
