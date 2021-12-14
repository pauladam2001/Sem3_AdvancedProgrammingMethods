package model.type;

import model.value.ValueInterface;

public interface TypeInterface {
    boolean equals(Object another);
    ValueInterface defaultValue();
    String toString();
}
