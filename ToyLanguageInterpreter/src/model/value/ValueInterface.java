package model.value;

import model.type.TypeInterface;

public interface ValueInterface {
    boolean equals(Object another);
    TypeInterface getType();
}
