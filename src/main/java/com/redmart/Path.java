package com.redmart;


import java.util.ArrayList;
import java.util.List;

public class Path {

	private List<Integer> nodes = new ArrayList<>();

	public List<Integer> getNodes() {
		return nodes;
	}

	public void setNodes(List<Integer> nodes) {
		this.nodes = nodes;
	}

	public int getSteep() {
		if (nodes.isEmpty()) {
			return 0;
		}
		return nodes.get(0) - nodes.get(nodes.size()-1);
	}
}