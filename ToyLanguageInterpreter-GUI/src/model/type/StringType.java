package model.type;

import model.value.StringValue;
import model.value.ValueInterface;

public class StringType implements TypeInterface {
    @Override
    public boolean equals(Object another) {
        return (another instanceof StringType);
    }

    @Override
    public ValueInterface defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "string";
    }
}
