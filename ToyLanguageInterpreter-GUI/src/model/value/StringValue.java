package model.value;

import model.type.StringType;
import model.type.TypeInterface;

public class StringValue implements ValueInterface {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof StringValue && ((StringValue) another).getValue().equals(value));
    }

    public String getValue() {
        return value;
    }

    @Override
    public TypeInterface getType() {
        return new StringType();
    }

    public String toString() {
        return this.value;
    }
}
