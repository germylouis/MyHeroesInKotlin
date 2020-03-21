package com.example.myheroes.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myheroes.R;
import com.example.myheroes.model.HeroResults;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.MyViewHolder> {

    private HeroResults heroResult;
    private Context applicationContext;

    public HeroAdapter(HeroResults heroResult) {
        this.heroResult = heroResult;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        applicationContext = parent.getContext().getApplicationContext();

        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.hero_list, parent, false );

        return new MyViewHolder( itemView );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {
            holder.heroName.setText( Html.fromHtml( "<b> Superhero: </b>" + heroResult.getName(), Html.FROM_HTML_MODE_LEGACY ) );
            holder.weight.setText( Html.fromHtml( "<b> Weight: </b>" + heroResult.getAppearance().getWeight().get( 0 ), Html.FROM_HTML_MODE_LEGACY ) );
            holder.height.setText( Html.fromHtml( "<b> Height: </b>" + heroResult.getAppearance().getHeight().toString().trim(), Html.FROM_HTML_MODE_LEGACY ) );
            holder.power.setText( Html.fromHtml( "<b> Power: </b>" + heroResult.getPowerstats().getPower().toString(), Html.FROM_HTML_MODE_LEGACY ) );
            holder.combat.setText( Html.fromHtml( "<b> Combat: </b>" + heroResult.getPowerstats().getCombat().toString(), Html.FROM_HTML_MODE_LEGACY ) );
            holder.publisher.setText( Html.fromHtml( "<b> Publsiher: </b>" + heroResult.getBiography().getPublisher(), Html.FROM_HTML_MODE_LEGACY ) );
            holder.firstAppear.setText( Html.fromHtml( "<b> First Appearence: </b>" + heroResult.getBiography().getFirstAppearance(), Html.FROM_HTML_MODE_LEGACY ) );
            holder.desc.setText( Html.fromHtml( "<b> Affiliation: </b>" + heroResult.getConnections().getGroupAffiliation(), Html.FROM_HTML_MODE_LEGACY ) );

        } catch (NullPointerException e) {
            Log.e( "TAG", "onBindViewHolder: ", e.getCause() );
        }


        Glide.with( applicationContext ).load( heroResult.getImages().getSm() )
                .apply( RequestOptions.circleCropTransform() ).into( holder.avatarImageView );
    }

    @Override
    public int getItemCount() {

        if (heroResult != null) {
            return 1;
        } else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView heroName, weight, height, power, combat, publisher, firstAppear, desc;
        ImageView avatarImageView;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            heroName = itemView.findViewById( R.id.hero_name );
            weight = itemView.findViewById( R.id.weight );
            height = itemView.findViewById( R.id.height );
            power = itemView.findViewById( R.id.power );
            combat = itemView.findViewById( R.id.combat );
            publisher = itemView.findViewById( R.id.publisher );
            firstAppear = itemView.findViewById( R.id.appeared );
            desc = itemView.findViewById( R.id.heroDesc );
            avatarImageView = itemView.findViewById( R.id.hero_pic );

        }
    }
}
