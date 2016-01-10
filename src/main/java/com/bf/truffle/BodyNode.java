package com.bf.truffle;

import com.bf.truffle.root.BFNode;
import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by moritz on 06.01.2016.
 */
@NodeInfo(shortName = "block", description = "The node implementing a source code block")
public class BodyNode extends BFNode {

    @Children
    private final BFNode[] nodes;

    public BodyNode(BFNode[] nodes, SourceSection src) {
        super(src);
        this.nodes = nodes;
    }

    @Override
    @ExplodeLoop
    public void executeVoid(VirtualFrame frame) {

        CompilerAsserts.compilationConstant(nodes.length);
        for (BFNode node : nodes) {
            node.executeVoid(frame);
        }
    }
}
