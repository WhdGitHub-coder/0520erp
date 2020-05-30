package com.itwh.a0520erp.common;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
public class TreenNodeBuilder {
    /**
     * 把没有层级关系的集合变成有层级关系的
     * @param treeNodes
     * @param topPid
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes, Integer topPid){
        List<TreeNode> nodes=new ArrayList<>();
        /*
        逻辑是：每次循环找到一个父节点后，再次循环
        找到父节点下的每一个子节点
         */
        for (TreeNode n1 : treeNodes) {
            //一级
            if(n1.getPid()==topPid) {
                nodes.add(n1);
            }
            //二级
            for (TreeNode n2 : treeNodes) {
                if(n1.getId()==n2.getPid()) {
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }
}
