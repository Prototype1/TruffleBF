package com.bf.truffle;

import com.bf.lang.BFLanguage;
import com.bf.truffle.root.BFNode;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by moritz on 06.01.2016.
 */
public class IncCellNode extends BFNode {

    private final int value;

    public IncCellNode(int value, SourceSection src) {
        super(src);
        this.value = value;
    }


    @Override
    public void executeVoid(VirtualFrame frame) {

        try {
            int[] heap = (int[]) frame.getObject(BFLanguage.HEAP_SLOT);
            int currCounter = frame.getInt(BFLanguage.COUNTER_SLOT);

            heap[currCounter] += value;

        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }

    }
}
