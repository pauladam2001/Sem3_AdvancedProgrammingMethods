package model.type;

import model.value.BoolValue;
import model.value.ValueInterface;

public class BoolType implements TypeInterface {
    @Override
    public boolean equals(Object another) {
        return (another instanceof BoolType);
    }

    @Override
    public ValueInterface defaultValue() {
        return new BoolValue(true);
    }

    @Override
    public String toString() {
        return "bool";
    }
}
