package com.example.toot.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.toot.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        EditText AGE;
        TextView De,sugg;
        Button RESULT,RESET;

        AGE=root.findViewById(R.id.age);
        De=root.findViewById(R.id.detail);
        sugg=root.findViewById(R.id.sugge);
        RESET=root.findViewById(R.id.reset);
        RESULT=root.findViewById(R.id.result);

        RESULT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age=AGE.getText().toString();

                if (age.equals("")){
                    AGE.setError("Please Enter Your Age");
                    AGE.requestFocus();
                    return;

                }
                int AGe=Integer.parseInt(age);


                int intakeValue = waterintake(AGe);
                De.setText(interpreBMi(intakeValue));
                sugg.setText("Per Day");


                RESET.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        De.setText("");
                        sugg.setText("");
                        AGE.setText("");
                    }
                });
            }
        });
        return  root;
    }

    public int waterintake(int age){
        return age;
    }






    public String interpreBMi(int intakeValue) {
        if (intakeValue < 2) {
            return "14 hours of sleep";
        }
        else if (intakeValue< 5) {
            return "13 hours of sleep";

        }
        else if (intakeValue < 13) {
            return "11 hours of sleep";
        }
        else if(intakeValue<17){
            return "10 hours of sleep";
        }else if (intakeValue<25) {
            return "9 hours of sleep";
        }else if (intakeValue<64) {
            return "11 hours of sleep";
        }else if (intakeValue<65) {
            return "8 hours of sleep";
        }else
            return "just sleep went you want to";

    }
}