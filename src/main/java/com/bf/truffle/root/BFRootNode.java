package com.bf.truffle.root;

import com.bf.lang.BFContext;
import com.bf.lang.BFLanguage;
import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by moritz on 06.01.2016.
 */


@NodeInfo(language = "BrainFuck", description = "Root of all Brain Fucks :)")
public class BFRootNode extends RootNode {

    @Children
    public final BFNode[] nodes;

    private final BFContext context;

    public BFRootNode(BFNode[] nodes, BFContext context, SourceSection src) {

        super(BFLanguage.class, src, BFLanguage.DESCRIPTOR);
        this.nodes = nodes;
        this.context = context;
    }


    @Override
    @ExplodeLoop
    //TODO not sure if final works here
    public Object execute(VirtualFrame frame) {


        frame.setObject(BFLanguage.HEAP_SLOT, context.heap);
        frame.setInt(BFLanguage.COUNTER_SLOT, 0);

        CompilerAsserts.compilationConstant(nodes.length);

        for (BFNode node : nodes) {
            node.executeVoid(frame);
        }

        return null;
    }
}
