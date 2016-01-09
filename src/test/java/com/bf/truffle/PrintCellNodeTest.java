package com.bf.truffle;

import com.bf.lang.BFContext;
import com.bf.truffle.root.BFNode;
import com.bf.truffle.root.BFRootNode;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by moritz on 08.01.2016.
 */
public class PrintCellNodeTest {


    @Test
    public void printSomething() throws IOException {

        BFRootNode rootNode = new BFRootNode(new BFNode[]{new PrintCellNode(null)}, new BFContext(new int[]{10, 20}), null);


        RootCallTarget callTarget = Truffle.getRuntime().createCallTarget(rootNode);


        callTarget.call();


        //TODO redirect System out or something for test

    }
}