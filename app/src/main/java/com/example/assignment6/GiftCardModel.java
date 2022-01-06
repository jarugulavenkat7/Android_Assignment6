package com.example.assignment6;

import android.widget.Toast;

import java.util.ArrayList;

public class GiftCardModel {
    public int cardsPurchased = 0;
    public int cardsRedeemed = 0;
    public double totalPurchasedValue = 0.00;
    public double totalRedeemedValue = 0.00;

    private static GiftCardModel giftCardModel;
    ArrayList<GiftCard> giftCardsArrayList;


    public void purchaseCard(double amount) {

        GiftCard tmp = new GiftCard(amount, false);

giftCardsArrayList.add(tmp);
        totalPurchasedValue += amount;
        cardsPurchased++;

    }
    public double redeemCard(int code) {

        GiftCard money = giftCardsArrayList.get(code);
        if (money.redeemed == false) {
            cardsRedeemed++;
            totalRedeemedValue += money.amount;
            giftCardsArrayList.get(code).redeemed = true;
            return giftCardsArrayList.get(code).amount;
        }
        return 0.0;
    }
    public Boolean checkCode(int code) {
        if (code >= giftCardsArrayList.size()) {
            return true;
        }
        return false;
    }
    private GiftCardModel() {
giftCardsArrayList=new ArrayList<>();
    }

    public  static GiftCardModel getGiftCardModel() {
        if (giftCardModel == null) {
            giftCardModel = new GiftCardModel();
        }
        return giftCardModel;
    }

    public static class GiftCard {
        double amount;
        boolean redeemed;

        public GiftCard(double amount, Boolean redeemed) {
            this.amount = amount;
            this.redeemed = redeemed;
        }
    }
}
