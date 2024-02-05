package FateCardDuel.FateCard.CardTemplate;

/**
 * 随从卡模板：
 * 包含一个随从卡应该有的所有属性和方法<br/>
 * 备注：抽象类，继承基本卡牌
 */
public abstract class FollowerCard extends BasicCard {
    /**
     * 卡牌战斗力：<br/>
     * 一般卡牌对战的基本内容，
     * 战斗力高的一方将破坏小的一方，
     * 并对小的一方的玩家造成两者之差绝对值的伤害。
     */
    protected int cardPower;
}
