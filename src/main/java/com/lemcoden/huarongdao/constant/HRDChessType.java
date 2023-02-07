package com.lemcoden.huarongdao.constant;

/**
 * 棋子类型
 */
public interface HRDChessType {
    //四格棋子（曹操）
    byte SIGE = 0B001;
    //两格单位（五虎将）横放
    byte HENGFANG = 0B000010;
    //两格单位（五虎将）竖放
    byte SHUFANG = 0B000011;
    //一格单位（小兵）
    byte YIGE = 0B100;
    //空格
    byte KONGE = 0B000;

    // 两格单位编号，通过与两格单位横放竖放
    // 两种属性进行异或拼接来获得唯一标识
    byte BIANHAO1 = 0B001000;
    byte BIANHAO2 = 0B010000;
    byte BIANHAO3 = 0B011000;
    byte BIANHAO4 = 0B100000;
    byte BIANHAO5 = 0B101000;


}
