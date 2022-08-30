package org.vdev.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonUtils {
	
	@Autowired
	private ModelMapper mapper;
	
	public <D, S> List<D> mapper(List<S> ins, Class<D> destinationType) {
		List<D> outs = new ArrayList<D>();
		ins.forEach(in -> outs.add(mapper.map(in, destinationType)));
		return outs;
	}
	
	public static <D, S> List<D> mapper(ModelMapper mapper, List<S> ins, Class<D> destinationType) {
		List<D> outs = new ArrayList<D>();
		ins.forEach(in -> outs.add(mapper.map(in, destinationType)));
		return outs;
	}
}
