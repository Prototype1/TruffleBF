package com.bf.truffle;

import com.bf.lang.BFLanguage;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

/**
 * Created by moritz on 06.01.2016.
 */
public class BFRepeatingNode extends Node implements com.oracle.truffle.api.nodes.RepeatingNode {

    @Child
    @CompilerDirectives.CompilationFinal
    private BodyNode bodyNode;

    public BFRepeatingNode(BodyNode bodyNode) {
        this.bodyNode = bodyNode;
    }

    private static boolean evalCondition(VirtualFrame frame) {
        try {
            int[] heap = (int[]) frame.getObject(BFLanguage.HEAP_SLOT);
            int currCounter = frame.getInt(BFLanguage.COUNTER_SLOT);


            return heap[currCounter] != 0;

        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }

        //TODO FIX ME do something usefull
        return true;
    }

    @Override
    public boolean executeRepeating(VirtualFrame frame) {

        if (evalCondition(frame)) {
            bodyNode.executeVoid(frame);

            return true;
        }

        return false;
    }

}
