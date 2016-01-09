package com.bf.truffle;

import com.bf.lang.BFLanguage;
import com.bf.truffle.root.BFNode;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by moritz on 07.01.2016.
 */
public class PrintCellNode extends BFNode {


    public PrintCellNode(SourceSection src) {
        super(src);
    }

    @CompilerDirectives.TruffleBoundary
    //long to allow printing of all primitives
    private static void print(char toPrint) {
        System.out.print(toPrint);

    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        try {
            int[] heap = (int[]) frame.getObject(BFLanguage.HEAP_SLOT);
            int currCounter = frame.getInt(BFLanguage.COUNTER_SLOT);

            print((char) heap[currCounter]);

        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }
    }
}
