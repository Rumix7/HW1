package com.example.homework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Contact extends AppCompatActivity {
    String[] contacts;
    ArrayAdapter<String> aa;
    Spinner sp;
    int pos = 0;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //znajdz widok biezacej aktywnosci i przypisz do odpowiedniego typu danych
        sp= findViewById(R.id.spinner);

        contacts = getResources().getStringArray(R.array.contacts); // odczytaj tablic string-array
                                                                    //do tablicy contacts

        // ArrayAdapter zwraca widok dla kazdego obiektu i używany z widżetami interfejsu takimi jak Spinner czy ListView
        //Utwórz ArrayAdapter przy użyciu tablicy stringów i domyślnego układu spinnera
        aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, contacts);
        sp.setAdapter(aa); //ustaw array do spinnera
    }

    public void cancel(View view){
        finish(); }


    public void ok(View view){
        pos=sp.getSelectedItemPosition(); //Zwraca pozycję aktualnie wybranego elementu w zestawie danych adaptera
        result = contacts[pos]; // tablica pozycji wybranych elementow
        Intent intent = new Intent(); // nowa aktywnosc
        intent.putExtra("contact",result); // Dołącz wartość do Intent za pomocą klucza result
        setResult(RESULT_OK,intent); //ustaw kod wyniku i dołącz wartosc Intentu
        finish(); //zakoncz aktywnosc
    }
}
