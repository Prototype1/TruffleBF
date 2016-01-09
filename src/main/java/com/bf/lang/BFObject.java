package com.bf.lang;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.interop.ForeignAccess;
import com.oracle.truffle.api.interop.Message;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.object.ObjectType;

/**
 * Created by moritz on 08.01.2016.
 */
public class BFObject extends ObjectType implements TruffleObject, ForeignAccess.Factory10, ForeignAccess.Factory {

    private final ForeignAccess access;

    public BFObject() {
        this.access = ForeignAccess.create(null, this);
    }


    public static boolean isBFObject(Object o) {
        return o instanceof int[] || o instanceof short[] || o instanceof long[] || o instanceof char[] || o instanceof byte[];
    }

    @Override
    public boolean canHandle(TruffleObject obj) {
        return false;
    }

    @Override
    public CallTarget accessIsNull() {
        return Truffle.getRuntime().createCallTarget(RootNode.createConstantNode(false));
    }

    @Override
    public CallTarget accessIsExecutable() {
        return Truffle.getRuntime().createCallTarget(RootNode.createConstantNode(false));
    }

    @Override
    public CallTarget accessIsBoxed() {
        return Truffle.getRuntime().createCallTarget(RootNode.createConstantNode(false));
    }

    @Override
    public CallTarget accessHasSize() {
        return null;
    }

    @Override
    public CallTarget accessGetSize() {
        return null;
    }

    @Override
    public CallTarget accessUnbox() {
        return null;
    }

    @Override
    public CallTarget accessRead() {
        return null;
    }

    @Override
    public CallTarget accessWrite() {
        return null;
    }

    @Override
    public CallTarget accessExecute(int argumentsLength) {
        return null;
    }

    @Override
    public CallTarget accessInvoke(int argumentsLength) {
        return null;
    }

    @Override
    public CallTarget accessNew(int i) {
        return null;
    }

    @Override
    public CallTarget accessMessage(Message unknown) {

        return null;
    }

    @Override
    public ForeignAccess getForeignAccess() {
        return access;
    }
}
