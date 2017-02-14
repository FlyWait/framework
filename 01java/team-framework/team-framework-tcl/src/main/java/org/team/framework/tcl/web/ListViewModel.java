package org.team.framework.tcl.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ListViewModel<T> implements Serializable {
	private static final long serialVersionUID = -385590263883563777L;

	private Collection<T> list;
	
	public ListViewModel(){
		super();
		setList(new ArrayList<T>());
	}

	public Collection<T> getList() {
		return list;
	}

	public void setList(Collection<T> list) {
		this.list = list;
	}
}
