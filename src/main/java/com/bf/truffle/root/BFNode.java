package com.bf.truffle.root;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by moritz on 06.01.2016.
 */
@NodeInfo(shortName = "block", description = "The node implementing a source code block")
public abstract class BFNode extends Node {

    public BFNode(SourceSection src) {
        super(src);
    }

    public abstract void executeVoid(VirtualFrame frame);

}
