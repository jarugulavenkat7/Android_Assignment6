package com.example.assignment6;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PurchaseCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PurchaseCardFragment extends Fragment {
    /* Removed interfaces for bonus
    public interface PurchaseCallBack {

        public void swapToRedeem();
    }
*/

    public PurchaseCardFragment() {
        // Required empty public constructor
    }


    public static PurchaseCardFragment newInstance() {
        PurchaseCardFragment fragment = new PurchaseCardFragment();
        return fragment;
    }
private MainViewModel mainViewModel;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


        // Reference to main view model
        ViewModelProvider.Factory vmf = new ViewModelProvider.NewInstanceFactory();
        ViewModelProvider vmp = new ViewModelProvider((MainActivity) context, vmf);
        mainViewModel = vmp.get(MainViewModel.class);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_purchase_card, container, false);
        Button purchaseBTN=v.findViewById(R.id.purchaseBTN);

            EditText amountET = v.findViewById(R.id.amountET);

GiftCardModel giftCardModel=GiftCardModel.getGiftCardModel();
purchaseBTN.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Double amount = Double.parseDouble(amountET.getText().toString());
        GiftCardModel.GiftCard giftCard = new GiftCardModel.GiftCard(amount, false);
        giftCardModel.purchaseCard(amount);
mainViewModel.purchased(amount);
        Toast.makeText(getContext(), "Purchased Card with Code: " + (giftCardModel.giftCardsArrayList.size()
                - 1), Toast.LENGTH_SHORT).show();
    }
});

        Button swapBTN = v.findViewById(R.id.swapBTN);
        swapBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.swapToRedeem();
            }
        });

        return v;

    }
}