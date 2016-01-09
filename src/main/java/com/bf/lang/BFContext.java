package com.bf.lang;

import com.oracle.truffle.api.ExecutionContext;

/**
 * Created by moritz on 06.01.2016.
 */
public final class BFContext extends ExecutionContext {

    public final Object heap;

    public BFContext(final Object heap) {

        this.heap = heap;


    }

}
