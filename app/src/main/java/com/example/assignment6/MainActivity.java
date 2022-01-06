package com.example.assignment6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
TextView purchaseTV;
TextView redeemTV;
private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
purchaseTV=findViewById(R.id.purshaseDisplayTV);
redeemTV=findViewById(R.id.redeemDisplayTV);

        ViewModelProvider.Factory vmf = new ViewModelProvider.NewInstanceFactory();
        ViewModelProvider vmp = new ViewModelProvider(this, vmf);
        mainViewModel = vmp.get(MainViewModel.class);

        if (savedInstanceState == null) {
            // Initializing the MainViewModel
            mainViewModel.init(0, 0, 0.0, 0.0, 0);
        }

        mainViewModel.getNumberOfGiftCardsPurchasedString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                purchaseTV.setText(s);
            }
        });

        mainViewModel.getNumberOfGiftCardsRedeemedString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                redeemTV.setText(s);
            }
        });

        mainViewModel.getTotalPurchasedValueString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                String cardsP = purchaseTV.getText().toString() + s;
                purchaseTV.setText(cardsP);
            }
        });

        mainViewModel.getTotalRedeemedValueString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                String cardsR = redeemTV.getText().toString() + s;
                redeemTV.setText(cardsR);
            }
        });

        mainViewModel.getSwapToString().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                if (i == 0) {
                    swapToPurchase();
                } else
                    swapToRedeem();
            }
        });

        PurchaseCardFragment frag1 = PurchaseCardFragment.newInstance();
        RedeemFragment frag2 = RedeemFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tran = fm.beginTransaction();
        tran.add(R.id.fragmentContainerView, frag1, "Purchase");
        tran.add(R.id.fragmentContainerView, frag2, "Redeem");

        tran.hide(frag2);
        tran.commit();
    }


    public void swapToRedeem() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tran = fm.beginTransaction();

        Fragment pcf = fm.findFragmentByTag("Purchase");
        Fragment rf = fm.findFragmentByTag("Redeem");

        tran.hide(pcf);
        tran.show(rf);
        tran.commit();

    }


    public void swapToPurchase() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tran = fm.beginTransaction();

        Fragment pcf = fm.findFragmentByTag("Purchase");
        Fragment rf = fm.findFragmentByTag("Redeem");

        tran.hide(rf);
        tran.show(pcf);
        tran.commit();
    }
}