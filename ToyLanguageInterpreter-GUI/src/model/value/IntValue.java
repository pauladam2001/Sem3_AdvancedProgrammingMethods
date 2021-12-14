package model.value;

import model.type.IntType;
import model.type.TypeInterface;

public class IntValue implements ValueInterface {
    private final int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof IntValue && ((IntValue) another).getValue() == value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public TypeInterface getType() {
        return new IntType();
    }

    public String toString() {
        return String.valueOf(value);
    }
}
