package com.bf.truffle;

import com.bf.lang.BFLanguage;
import com.bf.truffle.root.BFNode;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by moritz on 09.01.2016.
 */
public class ReadInputNode extends BFNode {

    public ReadInputNode(SourceSection section) {
        super(section);
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        final InputStream reader = System.in;
        int val;

        try {
            val = reader.read();
        } catch (IOException e) {
            val = 0;
            e.printStackTrace();
        }
        try {
            int[] heap = (int[]) frame.getObject(BFLanguage.HEAP_SLOT);
            int currCounter = frame.getInt(BFLanguage.COUNTER_SLOT);

            heap[currCounter] = val;

        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }

    }
}
