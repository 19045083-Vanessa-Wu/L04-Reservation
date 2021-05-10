package sg.edu.rp.c346.id19045083.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etName = findViewById(R.id.editTextName);
        EditText etPhone = findViewById(R.id.editTextPhone);
        EditText etPax = findViewById(R.id.editTextPax);

        DatePicker dp = findViewById(R.id.datePicker);
        TimePicker tp = findViewById(R.id.timePicker);
        //The default date is 1 Jun 2020 and the default time is 7:30pm. Month starting from 0.
        dp.updateDate(2020, 5, 1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        ToggleButton tgArea = findViewById(R.id.toggleButtonTableArea);

        Button btnReserve = findViewById(R.id.buttonReserve);
        Button btnReset = findViewById(R.id.buttonReset);


        // More Challenges(Challenge 2)
        // Scenario 1: Restrict the reservation time to only between 8AM and 8:59PM inclusive.
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(tp.getCurrentHour()<8){
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                    Toast.makeText(MainActivity.this, "Reservation time only between 8AM to 8:59PM", Toast.LENGTH_SHORT).show();
                }else if(tp.getCurrentHour()>=21){
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(59);
                    Toast.makeText(MainActivity.this,"Reservation time only between 8AM to 8:59PM", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // More Challenges(Challenge 2)
        // Scenario 2: Restrict the reservation to a date and time that is after today.
        Calendar current = Calendar.getInstance();
        dp.setMinDate(current.getTimeInMillis()+86400000); // 24Hours = 8,640,000ms


        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String table;
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String pax = etPax.getText().toString();

                String date = String.format("%d/%d/%d", dp.getDayOfMonth(), dp.getMonth()+1, dp.getYear());
                String time = String.format("%d:%d", tp.getCurrentHour(), tp.getCurrentMinute());

                if (tgArea.isChecked()){
                    table = "Smoking Area";
                } else {
                    table = "Non-Smoking Area";
                }

                // More Challenges
                // (Challenge 1: Check for empty fields. If any EditText field is empty, the reservation should not be made, and an error message should be displayed using Toast.)
                if (name.isEmpty() || phone.isEmpty() || pax.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Inputs cannot be empty!", Toast.LENGTH_LONG).show();

                } else {

                    String output =  String.format("Name:            %s\n", name);
                    output += String.format("Mobile Phone:    %s\n", phone);
                    output += String.format("Size of group:   %s\n", pax);
                    output += String.format("Reserved Date:   %s\n", date);
                    output += String.format("Reserved Time:   %s\n", time);
                    output += String.format("Table Placement: %s", table);
                    Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etName.setText("");
                etPhone.setText("");
                etPax.setText("");
                tgArea.setChecked(false);
                //The default date is 1 Jun 2020 and the default time is 7:30pm. Month starting from 0.
                dp.updateDate(2020, 5, 1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);

            }
        });
    }
}