package com.example.myheroesinkotlin.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myheroesinkotlin.R
import com.example.myheroesinkotlin.model.HeroResults

class HeroAdapter(results: HeroResults) : RecyclerView.Adapter<HeroAdapter.MyViewHolder>() {

    lateinit var heroResultList: HeroResults
    lateinit var context: Context

    fun HeroAdapter(heroResults: HeroResults){
        this.heroResultList = heroResults
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        context = parent.context.applicationContext
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.hero_list, parent, false)
        return MyViewHolder(itemView)
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        try {
            holder.heroName.text = Html.fromHtml(
                "<b> Superhero: </b>" + heroResultList.getName(),
                Html.FROM_HTML_MODE_LEGACY
            )
            holder.weight.text = Html.fromHtml(
                "<b> Weight: </b>" + heroResultList.getAppearance().getWeight().get(
                    0
                ), Html.FROM_HTML_MODE_LEGACY
            )
            holder.height.text = Html.fromHtml(
                "<b> Height: </b>" + heroResultList.getAppearance().getHeight().toString().trim(),
                Html.FROM_HTML_MODE_LEGACY
            )
            holder.power.text = Html.fromHtml(
                "<b> Power: </b>" + heroResultList.getPowerstats().getPower().toString(),
                Html.FROM_HTML_MODE_LEGACY
            )
            holder.combat.text = Html.fromHtml(
                "<b> Combat: </b>" + heroResultList.getPowerstats().getCombat().toString(),
                Html.FROM_HTML_MODE_LEGACY
            )
            holder.publisher.text = Html.fromHtml(
                "<b> Publsiher: </b>" + heroResultList.getBiography().getPublisher(),
                Html.FROM_HTML_MODE_LEGACY
            )
            holder.firstAppear.text = Html.fromHtml(
                "<b> First Appearence: </b>" + heroResultList.getBiography().getFirstAppearance(),
                Html.FROM_HTML_MODE_LEGACY
            )
            holder.desc.text = Html.fromHtml(
                "<b> Affiliation: </b>" + heroResultList.getConnections().getGroupAffiliation(),
                Html.FROM_HTML_MODE_LEGACY
            )
        } catch (e: NullPointerException) {
            Log.e("TAG", "onBindViewHolder: ", e.cause)
        }
        Glide.with(context).load(heroResultList.getImages().getSm())
            .apply(RequestOptions.circleCropTransform()).into(holder.avatarImageView)
    }

    override fun getItemCount(): Int {
        return if (heroResultList != null){
            1
        }else{
            0
        }
    }


    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var heroName: TextView
        var weight: TextView
        var height: TextView
        var power: TextView
        var combat: TextView
        var publisher: TextView
        var firstAppear: TextView
        var desc: TextView
        var avatarImageView: ImageView

        init {
            heroName = itemView.findViewById(R.id.hero_name)
            weight = itemView.findViewById(R.id.weight)
            height = itemView.findViewById(R.id.height)
            power = itemView.findViewById(R.id.power)
            combat = itemView.findViewById(R.id.combat)
            publisher = itemView.findViewById(R.id.publisher)
            firstAppear = itemView.findViewById(R.id.appeared)
            desc = itemView.findViewById(R.id.heroDesc)
            avatarImageView = itemView.findViewById(R.id.hero_pic)
        }
    }

}