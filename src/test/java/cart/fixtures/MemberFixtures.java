package cart.fixtures;

import cart.domain.Member;

public class MemberFixtures {

    public static final Member MEMBER1 = new Member(1L, "a@a.com", "1234");
    public static final Member MEMBER1_NULL_PASSWORD = new Member(1L, "a@a.com", null);
    public static final Member MEMBER2 = new Member(2L, "b@b.com", "1234");
    public static final Member MEMBER2_NULL_PASSWORD = new Member(2L, "b@b.com", null);
    public static final Member NEW_MEMBER_TO_INSERT = new Member(null, "c@c.com", "1234");
    public static final Member NEW_MEMBER = new Member(3L, "c@c.com", "1234");
    public static final Member UPDATE_MEMBER1 = new Member(1L, "n@n.com", "1234567");
}
