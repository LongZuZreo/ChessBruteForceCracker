package com.lemcoden.main.product;

import com.lemcoden.main.entity.ChessBoardLayoutEntity;
import com.lemcoden.main.entity.ChessPosition;
import com.lemcoden.main.tree.TreeNode;

import java.util.List;

public interface ChessPlayer {

    void moveChessToNextStep(List<ChessPosition> movableChessPositions, TreeNode<ChessBoardLayoutEntity> parentNode);

}
