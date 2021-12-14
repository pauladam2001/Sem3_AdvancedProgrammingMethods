package model.type;

import model.value.ReferenceValue;
import model.value.ValueInterface;

public class ReferenceType implements TypeInterface {
    private final TypeInterface innerType;

    public ReferenceType(TypeInterface innerType) {
        this.innerType = innerType;
    }

    public TypeInterface getInnerType() {
        return innerType;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof ReferenceType)
            return innerType.equals(((ReferenceType) another).getInnerType());
        return false;
    }

    @Override
    public ValueInterface defaultValue() {
        return new ReferenceValue(0, innerType);
    }

    public String toString() {
        return "Ref(" + innerType.toString() + ")";
    }
}
