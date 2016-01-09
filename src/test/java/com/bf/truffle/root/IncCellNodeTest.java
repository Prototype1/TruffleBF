package com.bf.truffle.root;

import com.bf.lang.BFContext;
import com.bf.truffle.IncCellNode;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by moritz on 06.01.2016.
 */
public class IncCellNodeTest {

    @Test()
    public void TestInc() {
        int[] arr = new int[10];

        BFContext context = new BFContext(arr);


        BFRootNode root = new BFRootNode(new BFNode[]{new IncCellNode(-10, null), new IncCellNode(20, null)}, context, null);

        CallTarget result = Truffle.getRuntime().createCallTarget(root);


        Object callResult = result.call();

        int[] heap = (int[]) context.heap;

        Assert.assertEquals(10, heap[0]);

    }

}