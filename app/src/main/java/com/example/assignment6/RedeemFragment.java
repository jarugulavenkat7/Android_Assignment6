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

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RedeemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RedeemFragment extends Fragment {
    /* Removed interface for bonus
    public interface RedeemCallBack {
        public void swapToPurchase();
    }
*/
    public RedeemFragment() {
        // Required empty public constructor
    }


    public static RedeemFragment newInstance() {
        RedeemFragment fragment = new RedeemFragment();

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
        View v=inflater.inflate(R.layout.fragment_redeem, container, false);
        Button redeemBTN=v.findViewById(R.id.redeemBTN);
        EditText redeemET=v.findViewById(R.id.redeemET);
        GiftCardModel giftCardModel=GiftCardModel.getGiftCardModel();
        redeemBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Integer redeemCode = Integer.parseInt(redeemET.getText().toString());
                    if (!giftCardModel.checkCode(redeemCode)) {
                        double result = giftCardModel.redeemCard(redeemCode);
                        if (result != 0.0) {
                            mainViewModel.redeemed(giftCardModel.giftCardsArrayList.get(redeemCode).amount);
                            Toast.makeText(getContext(), "Card Redeemed with Code: " + redeemCode, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Card Already Redeemed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Invalid Code", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    TextView errorTV=v.findViewById(R.id.errorTV);
                    errorTV.setText("Please enter valid code");
                }
            }

        });

        Button swapBTN = v.findViewById(R.id.swapBTN);
        swapBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.swapToPurchase();
            }
        });

        return v;
    }
}