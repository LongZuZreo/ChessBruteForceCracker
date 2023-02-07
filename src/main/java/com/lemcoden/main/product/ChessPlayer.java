package com.lemcoden.main.product;

import com.lemcoden.huarongdao.entity.ChessBoardLayoutEntity;
import com.lemcoden.huarongdao.entity.ChessPosition;
import com.lemcoden.huarongdao.tree.TreeNode;
import com.lemcoden.main.ChessBruteForcer;

import java.util.List;

public interface ChessPlayer {

    void moveChessToNextStep(List<ChessPosition> movableChessPositions, TreeNode<ChessBoardLayoutEntity> parentNode);

}
