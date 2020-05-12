package com.emercy.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataCollection {

    // 通过map存放每一个大类，key是大类类别名，value是子类List
    private static HashMap<String, List<String>> mExpandableListData = new HashMap<>();

    private static final String MASTER = "法师";
    private static final String ASSASSINATOR = "刺客";
    private static final String SHOOTER = "射手";
    private static final String TANK = "对抗";
    private static final String ASSIST = "辅助";

    // 类加载的时候初始化数据
    static {
        // 创建子类列表，存放在List当中
        List<String> master = new ArrayList<>();
        master.add("安琪拉");
        master.add("西施");
        master.add("沈梦溪");
        master.add("嫦娥");
        master.add("上官婉儿");
        master.add("不知火舞");

        List<String> assassinator = new ArrayList<>();
        assassinator.add("马超");
        assassinator.add("镜");
        assassinator.add("兰陵王");
        assassinator.add("孙悟空");
        assassinator.add("娜可露露");
        assassinator.add("元歌");

        List<String> shooter = new ArrayList<>();
        shooter.add("狄仁杰");
        shooter.add("伽罗");
        shooter.add("蒙犽");
        shooter.add("鲁班七号");
        shooter.add("孙尚香");
        shooter.add("后羿");

        List<String> tank = new ArrayList<>();
        // 咦？为什么马超出现了两次？
        // 因为作者就叫马超
        tank.add("马超");
        tank.add("盖伦");
        tank.add("芈月");
        tank.add("铠");
        tank.add("典韦");

        List<String> assist = new ArrayList<>();
        assist.add("蔡文姬");
        assist.add("小明");
        assist.add("庄周");
        assist.add("鲁班");
        assist.add("东皇太一");

        // 将所有的子类List作为Value存放到大类中
        mExpandableListData.put(MASTER, master);
        mExpandableListData.put(ASSASSINATOR, assassinator);
        mExpandableListData.put(SHOOTER, shooter);
        mExpandableListData.put(TANK, tank);
        mExpandableListData.put(ASSIST, assist);
    }

    static HashMap<String, List<String>> getData() {
        return mExpandableListData;
    }
}
