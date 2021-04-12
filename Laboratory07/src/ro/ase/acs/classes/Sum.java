package ro.ase.acs.classes;

import ro.ase.acs.interfaces.BinaryOperator;

public class Sum implements BinaryOperator {

	@Override
	public double operate(double op1, double op2) {
		return op1 + op2;
	}

}
