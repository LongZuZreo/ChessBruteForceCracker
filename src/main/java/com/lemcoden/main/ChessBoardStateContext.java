package com.lemcoden.main;

import com.lemcoden.main.entity.ChessBoardLayoutEntity;
import com.lemcoden.main.tree.TreeNode;
import com.lemcoden.main.product.BitMapOperator;

import java.util.HashMap;

public abstract class ChessBoardStateContext {
    protected final byte[][] initBitMap;
    //是否通关
    public boolean isMissionComplete = false;

    public BitMapOperator bitMapOperator;
    //存储当前所有ID
    public HashMap<String, Boolean> uuids = new HashMap<>(128);

    public ChessBoardStateContext(byte[][] bitMap){
        this.initBitMap = bitMap;
    }

    public abstract byte[][] getBitMap(TreeNode<ChessBoardLayoutEntity> treeNode);

    public abstract TreeNode<ChessBoardLayoutEntity> recordBitMap(byte[][] bitMap, TreeNode<ChessBoardLayoutEntity> parentNode);

    public abstract void outPutAllStep(TreeNode<ChessBoardLayoutEntity> treeNode);
}
