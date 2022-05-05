package sg.edu.rp.c346.id21037552.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    //1 - create handles
    // buttons and text view
    ToggleButton tbSvs;
    ToggleButton tbGst;
    Button splitBtn;
    Button resetBtn;
    EditText enterAmount;
    EditText numPax;
    EditText discount;
    TextView tvTotalBill;
    TextView tvEachPay;
    RadioGroup paymentBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //2 - Bridges/Links
        tbSvs = findViewById(R.id.tbSvs);
        tbGst = findViewById(R.id.tbGst);
        splitBtn = findViewById(R.id.splitBtn);
        resetBtn = findViewById(R.id.resetBtn);
        enterAmount = findViewById(R.id.enterAmount);
        numPax = findViewById(R.id.numPax);
        discount = findViewById(R.id.discount);
        tvTotalBill = findViewById(R.id.tvTotalBill);
        tvEachPay = findViewById(R.id.tvEachPay);
        paymentBtn = findViewById(R.id.paymentBtn); //Radio Group


        //3 - Set event listener
        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterAmount.getText().toString().length() == 0 || numPax.getText().toString().length() == 0) {
                    //do nothing
                } else {
                    String data1 = enterAmount.getText().toString();
                    String data2 = numPax.getText().toString();

                    double amount = Double.parseDouble(data1);
                    double newAmount = 0;
                    int pax = Integer.parseInt(data2);

                    if (tbGst.isChecked() == true && tbSvs.isChecked() == true) {
                        newAmount = amount * 1.10 * 1.07;
                    } else if (tbGst.isChecked() == false && tbSvs.isChecked() == true) {
                        newAmount = amount * 1.10;
                    } else if (tbGst.isChecked() == true && tbSvs.isChecked() == false) {
                        newAmount = amount * 1.07;
                    } else {
                        newAmount = amount;
                    }

                    double eachPay = newAmount / pax;

                    tvTotalBill.setText("Total amount: $" + newAmount + "");

                    int selectedId = paymentBtn.getCheckedRadioButtonId();
                    String msg = String.format("%.2f" , eachPay);

                    if (selectedId == R.id.cashBtn){
                        tvEachPay.setText("Each pays: $" + msg + " in cash");
                    } else {
                        tvEachPay.setText("Each pays: $" + msg + " via PayNow to 92773648");
                    }


                }
            }
        });

        //Setup reset button
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reset enterAmount
                enterAmount.setText(null);
                enterAmount.dispatchDisplayHint(View.VISIBLE);

                //Reset pax
                numPax.setText(null);

                //Reset discount
                discount.setText(null);

                //reset buttons
                tbSvs.setChecked(false);
                tbGst.setChecked(false);

            }
        });


    }
}