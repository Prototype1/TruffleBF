package com.bf.parser;

import com.bf.lang.BFContext;
import com.bf.truffle.*;
import com.bf.truffle.root.BFNode;
import com.bf.truffle.root.BFRootNode;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.SourceSection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * Created by moritz on 06.01.2016.
 */
public final class BFParser {

    private final static char INC_PTR = '>';
    private final static char DEC_PTR = '<';

    private final static char INC_CELL = '+';
    private final static char DEC_CELL = '-';

    private final static char PRINT_CELL = '.';
    private final static char READ_INPUT = ',';

    private final static char LOOP_BEG = '[';
    private final static char LOOP_END = ']';

    //rather stupid but works
    public BFRootNode parse(final BFContext context, final Source source) {


        final InputStreamReader tokenizer = new InputStreamReader(source.getInputStream());

        final Stack<List<BFNode>> instructionStack = new Stack<>();
        instructionStack.push(new ArrayList<>());

        int nextChar;

        int currLine = 1;
        int currPos = 1;
        try {
            while ((nextChar = tokenizer.read()) != -1) {

                char currChar = (char) nextChar;

                //line breaks dont count as Position
                if (currChar != '\n' && currChar != '\r') {
                    currPos++;
                }

                switch (currChar) {
                    case INC_PTR:
                        instructionStack.peek().add(new IncPtrNode(1, source.createSection("Next Cell", currLine, currPos, 1)));
                        break;
                    case DEC_PTR:
                        instructionStack.peek().add(new IncPtrNode(-1, source.createSection("Last Cell", currLine, currPos, 1)));
                        break;
                    case INC_CELL:
                        instructionStack.peek().add(new IncCellNode(1, source.createSection("Increase Cell", currLine, currPos, 1)));
                        break;
                    case DEC_CELL:
                        instructionStack.peek().add(new IncCellNode(-1, source.createSection("Decrease Cell", currLine, currPos, 1)));
                        break;
                    case PRINT_CELL:
                        instructionStack.peek().add(new PrintCellNode(source.createSection("Print Cell", currLine, currPos, 1)));
                        break;
                    case READ_INPUT:
                        instructionStack.peek().add(new ReadInputNode(source.createSection("Print Cell", currLine, currPos, 1)));
                        break;
                    case LOOP_BEG:
                        instructionStack.push(new ArrayList<>());
                        break;
                    case LOOP_END:

                        if (instructionStack.isEmpty()) {
                            throw new IllegalArgumentException("A loop was opened but has never been closed");
                        }

                        List<BFNode> loopNodes = instructionStack.pop();

                        SourceSection src = source.createSection("Loop Node", currLine, currPos, loopNodes.size());

                        BodyNode loopNode = new BodyNode(loopNodes.toArray(new BFNode[loopNodes.size()]), src);

                        instructionStack.peek().add(new BFLoopNode(loopNode, src));
                        break;
                    case '\r':
                    case '\n':
                        currLine++;
                        currPos = 1;
                        break;
                    default:
                        System.out.println("Ignore invalid char: " + currChar);
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new BFRootNode(instructionStack.stream().flatMap(Collection::stream).toArray(BFNode[]::new), context, source.createSection("start", 1));
    }


}
