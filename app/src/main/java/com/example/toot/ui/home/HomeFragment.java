package com.example.toot.ui.home;

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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        EditText edweg,edhei;
        TextView txtRes,txtInter;
        Button btnRes,btnReset;

        edweg=root.findViewById(R.id.edweg);
        edhei=root.findViewById(R.id.edhei);
        txtRes=root.findViewById(R.id.txtinter);
        txtInter=root.findViewById(R.id.txtres);
        btnRes=root.findViewById(R.id.btnres);
        btnReset=root.findViewById(R.id.btnreset);

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strweg=edweg.getText().toString();
                String strhei=edhei.getText().toString();

                if(strweg.equals("")){
                    edweg.setError("Please Enter Your Weight");
                    edweg.requestFocus();
                    return;
                }
                if(strhei.equals("")){
                    edhei.setError("Please Enter Your Height");
                    edhei.requestFocus();
                    return;
                }
                float weight=Float.parseFloat(strweg);
                float height=Float.parseFloat(strhei)/100;

                float bmivalue = BMICalculator(weight,height);
                txtInter.setText(interpreteBmi(bmivalue));
                txtRes.setText("BMI="+bmivalue);

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edhei.setText("");
                edweg.setText("");
                txtInter.setText("");
                txtRes.setText("");
            }
        });
        return root;
    }

    public float BMICalculator(float weight, float height) {
        return weight / (height * height);

    }

    public String interpreteBmi(float bmiValue) {
        if (bmiValue < 16) {
            return "Severely Underweight";
        }
        else if (bmiValue < 18.5) {
            return "Underweight";

        }
        else if (bmiValue < 25) {
            return "Normal";
        }
        else if(bmiValue<30){
            return "Overweight";
        }else
            return "Obese";

    }


}