package com.example.homework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.res.TypedArray;
import android.util.Log;

public class Sound extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    boolean check = false;
    int soundID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        //identyfikator widoku - odniesienie do zasobow
        //znajdz widok biezacej aktywnosci i przypisz do odpowiedniego typu danych
        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        //ustaw tekst do poszczegolnych wyborow przyciskow
        rb1.setText("Sound 1");
        rb2.setText("Sound 2");
        rb3.setText("Sound 3");
        rb4.setText("Sound 4");
        rb5.setText("Sound 5");
    }

    //metoda zakonczenia aktywnosci
    // obiekt View jako argument (tj. bieżący widok)
    public void cancel(View view){

        finish();
    }
    //metoda potwierdzenia wyboru dzwieku
    public void ok(View view){
        if(check){ // jesli wcisniety rb
            Intent i = new Intent(); //Intent to obiekt, którego można użyć do zażądania akcji z
            // innego komponentu aplikacji, w celu rozpoczecia nowej aktywnosci
            i.putExtra("sound", soundID); // Dołącz wartość do Intent za pomocą klucza SOUND_ID.
            setResult(RESULT_OK, i); //ustaw kod wyniku i dołącz wartosc i Intentu
            finish(); // zakoncz aktywnosc
        }
        else { // Jeśli użytkownik wrócił bez naciskania , wyswietlany jest komunikat
            Toast.makeText(getApplicationContext(),"Wybierz dźwięk",Toast.LENGTH_LONG).show();
        }
    }

    public void choose (View view){
        check =((RadioButton) view).isChecked();

        if(check) { // jesli przycisk jest zaznaczony
            switch (view.getId()) { // Sprawdź, który przycisk radiowy został kliknięty
                case R.id.rb1:
                    soundID = R.raw.mario;
                    break;
                case R.id.rb2:
                    soundID = R.raw.ring01;
                    break;
                case R.id.rb3:
                    soundID = R.raw.ring02;
                    break;
                case R.id.rb4:
                    soundID = R.raw.ring03;
                    break;
                case R.id.rb5:
                    soundID = R.raw.ring04;
                    break;
            }
        }
    }
}
