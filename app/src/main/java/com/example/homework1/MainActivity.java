package com.example.homework1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv;
    String[] pic;
    String name;
    int soundID = R.raw.mario;
    int rand = 0;
    Uri uri;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //znajdz widok biezacej aktywnosci i przypisz do odpowiedniego typu danych
        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);

        tv.setText("Wybierz kontakt");

        pic = getResources().getStringArray(R.array.pictures);// odczytaj tablic string-array
                                                                //do tablicy pictures

        // Użyj metody analizy klasy Uri, aby uzyskać zasoby Uri określone przez tablice pic[]
        uri = Uri.parse(pic[0]);
        //Ustaw drawable jako zawartość ImageView.
        //Zwróć identyfikator zasobu dla podanej nazwy zasobu.
        iv.setImageResource(getApplicationContext().getResources().getIdentifier("" + uri,
                null, getApplicationContext().getPackageName()));
        //iv.setImageResource(R.drawable.avatar_1);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp !=null){ //jesli nie jest wcistniety
                    mp.stop(); // zatrzymaj odtwarzanie
                    mp = null;
                }
                else {
                    //// Utwórz i przygotuj MediaPlayer z soundID jako źródłem strumienia danych
                    mp = MediaPlayer.create(getApplicationContext(), soundID);
                    mp.start(); // rozpocznij odtwarzanie
                    Snackbar.make(view, "Odtwarzanie...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void contact(View view) {
        Intent intent = new Intent(getApplicationContext(), Contact.class); //utworz nowa aktywnosc Contact
        startActivityForResult(intent, 1);//Uruchom aktywnosc Contact, wskazując, że da wynik z powrotem dla kodu żądania 1

    }
    public void sound (View view) {
        Intent intent = new Intent(getApplicationContext(), Sound.class);
        startActivityForResult(intent, 2); //Uruchom aktywnosc Sound, wskazując, że da wynik z powrotem dla kodu żądania 2

    }
        //metoda wyboru losowego zdjecia
    public void choosePic(){
        Random r = new Random(); //generator liczb losowych
        rand = r.nextInt(4); //zwroc losowa wartosc od 0 do 4
        // Użyj metody analizy klasy Uri, aby uzyskać zasoby Uri określone przez tablice pic[rand]
        uri = Uri.parse(pic[rand]);
        //Ustaw drawable jako zawartość ImageView.
        //Zwróć identyfikator zasobu dla podanej nazwy zasobu.
        iv.setImageResource(getApplicationContext().getResources().getIdentifier("" + uri,
                null, getApplicationContext().getPackageName()));

        //iv.setImageDrawable(Drawable.createFromPath(pic[rand]));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        if(resultCode == RESULT_OK){ //Jeśli użytkownik użył przycisku i zwrócił kod RESULT_OK
            if(requestCode == 1){  // jesli zwrocony kod jest 1 (Aktywnosc Contact)
                name = data.getStringExtra("contact"); // pobierz kontakty z Intentu do tablicy name
                tv.setText(name); // ustaw tekst w TextView dla wybranego kontaktu
                choosePic(); //ustaw zdjecie
            }
            else if(requestCode == 2){// jesli zwrocony kod jest 2 (Aktywnosc Sound)
                soundID = data.getIntExtra("sound",0); // pobierz dzwiek z Intentu
            }
        }
        else { //Jeśli użytkownik wrócił bez naciskania przycisku, wyświetlany jest komunikat Toast
            Toast.makeText(getApplicationContext(), "Powrót", Toast.LENGTH_LONG).show();
        }

    }


}
