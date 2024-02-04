package FateCardDuel.FateCard;

import java.util.ArrayList;

/**
 * 基本卡牌：
 * 所有卡牌最原始的父类，包含一个卡牌应有的最基本信息。
 */
public class BasicCard {
    /**
     * 星级：<br/>
     * 1、范围 0~15（遵从原文）；<br/>
     * 2、一般情况下代表所使用该卡所消耗的魂点（即能量）。
     */
    private int cardStar;

    /**
     * 类型：<br/>
     * 0 魂卡；
     * 1 随从卡；
     * 2 法术卡；
     * 3 环境卡；
     * 4 事件卡；
     * 5 装备卡。
     */
    private int cardClassify;

    /**
     * 卡牌名称：<br/>
     * 一般情况下，该卡含什么词缀皆可以从名字直接看出来
     */
    private String cardName;

    /**
     * 额外词缀表：<br/>
     * 受某些卡牌效果影响，使得该卡尽管名字无该词缀，但仍可视为含有一部分词缀
     */
    private ArrayList<String> exAffixes;

    /**
     * 移除词缀表：<br/>
     * 受某些卡牌效果影响，使得该卡尽管名字有该词缀，但仍可视为不含有一部分词缀
     */
    private ArrayList<String> reAffixes;


    /**
     * 词缀添加：<br/>
     * 0.首先将该词缀从移除词缀表里去除；<br/>
     * 1.1.如果该卡目前含有该词缀，不再进行操作；<br/>
     * 1.2.如果该卡目前不含该词缀，将其添加到额外词缀表里。
     *
     * @param affixes 添加的词缀
     */
    void addAffixes(String affixes) {
        reAffixes.remove(affixes);
        if (cardName.contains(affixes) || exAffixes.contains(affixes)) return;
        exAffixes.add(affixes);
    }

    /**
     * 词缀移除：<br/>
     * 0.首先将该词缀从额外词缀表里去除；<br/>
     * 1.1.如果该卡移除词缀表里含有该词缀，不再进行操作；<br/>
     * 1.2.如果该卡本身含有该词缀，将其添加到移除词缀表里。
     *
     * @param affixes 添加的词缀
     */
    void removeAffixes(String affixes) {
        exAffixes.remove(affixes);
        if (reAffixes.contains(affixes)) return;
        if (cardName.contains(affixes)) reAffixes.add(affixes);
    }
}
