package com.example.myheroes.view;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myheroes.R;
import com.example.myheroes.adapter.HeroAdapter;
import com.example.myheroes.model.HeroResults;
import com.example.myheroes.veiwmodel.HeroViewModel;

public class SecondActivity extends AppCompatActivity {

    private HeroViewModel viewModel;
    String hero = "Spiderman";
    EditText text;
    Button button;

    RecyclerView recyclerView;

    Observer<HeroResults> myObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );

        recyclerView = findViewById( R.id.listof_heroes );
        text = (EditText) findViewById( R.id.search );


        text.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN || event.getAction() == KeyEvent.KEYCODE_ENTER) {

                    //execute our method for searching
                    hero = text.getText().toString();
                    viewModel.getResultLiveData().observe( SecondActivity.this, myObserver );
                    viewModel.getHeroRepo( hero );
                }
                return false;
            }
        } );

        viewModel = ViewModelProviders
                .of( this ).get( HeroViewModel.class );

        myObserver = new Observer<HeroResults>() {
            @Override
            public void onChanged(HeroResults result) {
                Log.d( "TAG", "onChanged: hero results changed" );
                setupRV( result );

            }

        };

        viewModel.getResultLiveData().observe( SecondActivity.this, myObserver );
        viewModel.getHeroRepo();
    }

    private void setupRV(HeroResults response) {

        HeroAdapter adapter = new HeroAdapter( response );
        try {
            Log.d( "TAG_X", "setupRV: is empty " + response.getPowerstats().getCombat().toString() );
        } catch (NullPointerException e) {
            Log.e( "TAG", "setupRV: ", e.getCause() );
        }

        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager( new LinearLayoutManager( SecondActivity.this ) );

    }
}
