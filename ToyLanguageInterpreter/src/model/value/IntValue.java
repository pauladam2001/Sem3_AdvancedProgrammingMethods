package model.value;

import model.type.IntType;
import model.type.TypeInterface;
import model.value.ValueInterface;

public class IntValue implements ValueInterface {
    private final int value;

    public IntValue(int value) {
        this.value = value;
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
