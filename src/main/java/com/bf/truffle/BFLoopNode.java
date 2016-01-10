package com.bf.truffle;

import com.bf.truffle.root.BFNode;
import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.LoopNode;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by moritz on 06.01.2016.
 */
public final class BFLoopNode extends BFNode {

    @Child
    @CompilerDirectives.CompilationFinal
    private LoopNode loopNode;

    public BFLoopNode(final BodyNode nodes, SourceSection src) {

        super(src);
        this.loopNode = Truffle.getRuntime().createLoopNode(new BFRepeatingNode((nodes)));
    }

    @Override
    public void executeVoid(VirtualFrame frame) {

        CompilerAsserts.compilationConstant(loopNode);

        loopNode.executeLoop(frame);
    }
}
