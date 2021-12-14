package model.value;

import model.type.ReferenceType;
import model.type.TypeInterface;

public class ReferenceValue implements ValueInterface {
    private final int heapAddress;
    private final TypeInterface locationType;

    public ReferenceValue(int heapAddress, TypeInterface locationType) {
        this.heapAddress = heapAddress;
        this.locationType = locationType;
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof ReferenceValue && ((ReferenceValue) another).getHeapAddress() == heapAddress);
    }

    public int getHeapAddress() {
        return heapAddress;
    }

    public TypeInterface getLocationType() {
        return locationType;
    }

    @Override
    public TypeInterface getType() {
        return new ReferenceType(locationType);
    }

    public String toString() {
        return "(" + heapAddress + ", " + locationType.toString() + ")";
        // return "(0x" + Integer.toHexString(heapAddress) + ", " + locationType.toString() + ")";
    }
}
