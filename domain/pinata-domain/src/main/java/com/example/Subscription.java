package com.example;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Subscription {

    // [v] 구독 고유번호가 있는지 ?
    // [v] 어떤 상품을 구독하는지 ? need Product
    // [] 정기 구독료는 얼마인지? need Money
    // [] 결제 수단 등록했는지? need BillKey
    // [] 정기 결제일은?
    // [] 다음 정기 결제일은?
    // []

    private final SubscriptionId id;

    private final List<Product> products;

    private final Card card;

    public static Subscription withId(
            SubscriptionId id,
            List<Product> products,
            Card card
    ) {
        return new Subscription(id, products, card);
    }

    public static Subscription withoutId(List<Product> products, Card card) {
        return new Subscription(null, products, card);
    }

    public boolean isRegisteredBillKey() {
        return card.billKey() != null;
    }

    public Money calculateMonthlyTotalPrice() {
        Money total = Money.ZERO;
        products.forEach((product -> total.plus(product.getPrice())));
        return total;
    }

    public static record SubscriptionId(Long value) {
    }
}