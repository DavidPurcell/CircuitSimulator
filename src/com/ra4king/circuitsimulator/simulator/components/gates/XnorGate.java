package com.ra4king.circuitsimulator.simulator.components.gates;

/**
 * @author Roi Atalla
 */
public class XnorGate extends XorGate {
	public XnorGate(String name, int bitSize, int numInputs) {
		super(name, bitSize, numInputs, true);
	}
}
