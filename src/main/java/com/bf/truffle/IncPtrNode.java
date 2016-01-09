package com.bf.truffle;


import com.bf.lang.BFLanguage;
import com.bf.truffle.root.BFNode;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by moritz on 06.01.2016.
 */
public class IncPtrNode extends BFNode {

    private final int increaseBy;

    public IncPtrNode(int i, SourceSection src) {
        super(src);
        this.increaseBy = i;
    }

    @Override
    public void executeVoid(VirtualFrame frame) {

        try {
            int currCounter = frame.getInt(BFLanguage.COUNTER_SLOT);

            frame.setInt(BFLanguage.COUNTER_SLOT, currCounter + increaseBy);

        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }

    }
}