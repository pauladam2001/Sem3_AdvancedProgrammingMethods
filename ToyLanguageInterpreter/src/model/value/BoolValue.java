package model.value;

import model.type.BoolType;
import model.type.TypeInterface;

public class BoolValue implements ValueInterface {
    private final boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof BoolValue && ((BoolValue) another).getValue() == value);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public TypeInterface getType() {
        return new BoolType();
    }

    public String toString() {
        return String.valueOf(value);
    }
}
