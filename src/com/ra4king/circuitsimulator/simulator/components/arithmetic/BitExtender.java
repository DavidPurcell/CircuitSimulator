package com.ra4king.circuitsimulator.simulator.components.arithmetic;

import com.ra4king.circuitsimulator.simulator.CircuitState;
import com.ra4king.circuitsimulator.simulator.Component;
import com.ra4king.circuitsimulator.simulator.WireValue;
import com.ra4king.circuitsimulator.simulator.WireValue.State;

/**
 * @author Roi Atalla
 */
public class BitExtender extends Component {
	public static final int PORT_INPUT = 0;
	public static final int PORT_OUTPUT = 1;
	
	public enum ExtensionType {
		ZERO,
		ONE,
		SIGN
	}
	
	private final int inputBitSize;
	private final int outputBitSize;
	private final ExtensionType extensionType;
	
	public BitExtender(String name, int inputBitSize, int outputBitSize, ExtensionType extensionType) {
		super(name, new int[] { inputBitSize, outputBitSize });
		
		this.inputBitSize = inputBitSize;
		this.outputBitSize = outputBitSize;
		this.extensionType = extensionType;
	}
	
	public int getInputBitSize() {
		return inputBitSize;
	}
	
	public int getOutputBitSize() {
		return outputBitSize;
	}
	
	public ExtensionType getExtensionType() {
		return extensionType;
	}
	
	@Override
	public void valueChanged(CircuitState state, WireValue value, int portIndex) {
		if(portIndex == PORT_INPUT) {
			WireValue extended = new WireValue(value, outputBitSize);
			if(outputBitSize > inputBitSize) {
				switch(extensionType) {
					case SIGN:
						if(extended.getBit(inputBitSize - 1) != State.ONE) {
							break;
						}
					
					case ONE:
						for(int i = inputBitSize; i < outputBitSize; i++) {
							extended.setBit(i, State.ONE);
						}
						break;
				}
			}
			
			state.pushValue(getPort(PORT_OUTPUT), extended);
		}
	}
}
