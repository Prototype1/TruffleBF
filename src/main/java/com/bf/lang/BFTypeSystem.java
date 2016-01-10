package com.bf.lang;

import com.oracle.truffle.api.dsl.TypeCheck;
import com.oracle.truffle.api.dsl.TypeSystem;

/**
 * Created by moritz on 10.01.2016.
 */

@TypeSystem(value = {int[].class, boolean[].class, long[].class, byte[].class, char[].class, short[].class})
public abstract class BFTypeSystem {

    @TypeCheck(value = int[].class)
    public static boolean isIntHeap(Object o) {
        return o instanceof int[];
    }

    @TypeCheck(value = boolean[].class)
    public static boolean isBoolHeap(Object o) {
        return o instanceof boolean[];
    }

    @TypeCheck(value = long[].class)
    public static boolean isLongHeap(Object o) {
        return o instanceof long[];
    }

    @TypeCheck(value = byte[].class)
    public static boolean isByteHeap(Object o) {
        return o instanceof byte[];
    }

    @TypeCheck(value = char[].class)
    public static boolean isCharHeap(Object o) {
        return o instanceof char[];
    }

    @TypeCheck(value = short[].class)
    public static boolean isShortHeap(Object o) {
        return o instanceof short[];
    }
}
