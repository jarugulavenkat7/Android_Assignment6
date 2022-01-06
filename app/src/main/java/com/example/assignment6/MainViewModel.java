package com.example.assignment6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Function;

public class MainViewModel extends ViewModel {



    private MutableLiveData<Integer> numberOfGiftCardsPurchased = new MutableLiveData<>();
    private MutableLiveData<Integer> numberOfGiftCardsRedeemed = new MutableLiveData<>();
    private MutableLiveData<Double> totalPurchasedValue = new MutableLiveData<>();
    private MutableLiveData<Double> totalRedeemedValue = new MutableLiveData<>();
    private LiveData<String> numberOfGiftCardsPurchasedString = Transformations.map(numberOfGiftCardsPurchased,
            (value)-> value +" cards purchased");

    private LiveData<String> numberOfGiftCardsRedeemedString = Transformations.map(numberOfGiftCardsRedeemed,
            (value)-> value +" cards redeemed");
    private LiveData<String> totalPurchasedValueString = Transformations.map(totalPurchasedValue,
            (value)-> "worth "+value);
    private LiveData<String> totalRedeemedValueString = Transformations.map(totalRedeemedValue,
            (value)-> "worth "+value);


    public LiveData<String> getNumberOfGiftCardsPurchasedString() {
        return numberOfGiftCardsPurchasedString;
    }

    public LiveData<String> getNumberOfGiftCardsRedeemedString() {
        return numberOfGiftCardsRedeemedString;
    }

    public LiveData<String> getTotalPurchasedValueString() {
        return totalPurchasedValueString;
    }

    public LiveData<String> getTotalRedeemedValueString() {
        return totalRedeemedValueString;
    }
    private MutableLiveData<Integer> swapTo = new MutableLiveData<>();
    private LiveData<Integer> swapToString = Transformations.map(swapTo,
            (value) -> value);

    public LiveData<Integer> getSwapToString() {
        return swapToString;
    }
    public void redeemed(double amt){
        int count = numberOfGiftCardsRedeemed.getValue() + 1;
        numberOfGiftCardsRedeemed.setValue(count);

        double price = totalRedeemedValue.getValue();
        totalRedeemedValue.setValue(price + amt);

}
    public void purchased(double amt){
        int count = numberOfGiftCardsPurchased.getValue() + 1;
        numberOfGiftCardsPurchased.setValue(count);

        double price = totalPurchasedValue.getValue();
        totalPurchasedValue.setValue(price + amt);
    }
    public void init(int redeemCount, int purchaseCount,
                     double redeemWorth, double purchaseWorth, int swap){
        numberOfGiftCardsPurchased.setValue(purchaseCount);
        numberOfGiftCardsRedeemed.setValue(redeemCount);
        totalPurchasedValue.setValue(purchaseWorth);
        totalRedeemedValue.setValue(redeemWorth);
        swapTo.setValue(swap);

    }


    public void swapToPurchase() {
        swapTo.setValue(0);
    }

    // method to mutate live data for swapping
    public void swapToRedeem() {
        swapTo.setValue(1);

    }

}
