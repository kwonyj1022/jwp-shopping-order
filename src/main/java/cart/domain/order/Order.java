package cart.domain.order;

import cart.domain.Coupon;
import cart.domain.Member;
import cart.exception.CouponCannotUseException;
import cart.exception.IllegalOrderException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {

    private final Long id;
    private final OrderItems orderItems;
    private final Member member;
    private final Coupon coupon;
    private final int deliveryFee;
    private final int finalPrice;
    private final LocalDateTime orderTime;

    public Order(Long id, OrderItems orderItems, Member member,
                 Coupon coupon, int deliveryFee, int finalPrice,
                 LocalDateTime orderTime) {
        validate(orderItems, coupon, deliveryFee, finalPrice);
        this.id = id;
        this.orderItems = orderItems;
        this.member = member;
        this.coupon = coupon;
        this.deliveryFee = deliveryFee;
        this.finalPrice = finalPrice;
        this.orderTime = orderTime;
    }

    private void validate(OrderItems orderItems, Coupon coupon, int deliveryFee, int finalPrice) {
        validateIsFinalPricePositive(finalPrice);
        validateIsCouponUsable(orderItems.getTotalPrice(), coupon);
        validateIsRightFinalPrice(orderItems, coupon, deliveryFee, finalPrice);

    }

    private void validateIsFinalPricePositive(int finalPrice) {
        if (finalPrice < 0) {
            throw new IllegalOrderException("주문 시 최종 금액은 0원 이상이어야 합니다.");
        }
    }

    private void validateIsCouponUsable(int totalPrice, Coupon coupon) {
        if (coupon == null) {
            return;
        }
        if (totalPrice < coupon.getMinimumOrderAmount()) {
            throw new CouponCannotUseException("상품의 금액이 최소 주문 금액보다 작습니다.");
        }
    }

    private void validateIsRightFinalPrice(OrderItems orderItems, Coupon coupon, int deliveryFee, int finalPrice) {
        int orderItemsTotalPrice = orderItems.getTotalPrice();
        int couponPrice = 0;
        if (coupon != null) {
            couponPrice = coupon.getDiscountValue();
        }
        int expectFinalPrice = orderItemsTotalPrice + deliveryFee - couponPrice;

        if (expectFinalPrice != finalPrice) {
            throw new IllegalOrderException();
        }
    }

    public Long getId() {
        return id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems.getOrderItems();
    }

    public Member getMember() {
        return member;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return deliveryFee == order.deliveryFee && finalPrice == order.finalPrice && Objects.equals(id, order.id) && Objects.equals(orderItems, order.orderItems) && Objects.equals(member, order.member) && Objects.equals(coupon, order.coupon) && Objects.equals(orderTime, order.orderTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderItems, member, coupon, deliveryFee, finalPrice, orderTime);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                ", member=" + member +
                ", coupon=" + coupon +
                ", deliveryFee=" + deliveryFee +
                ", finalPrice=" + finalPrice +
                ", orderTime=" + orderTime +
                '}';
    }
}
