package com.example.dummysport.app.laliga.model

import androidx.annotation.DrawableRes
import com.example.dummysport.R

data class ClubModel(
    val name: String,
    @DrawableRes val iconId: Int,
    val power: Int
)

val dummyList = listOf(
    ClubModel(
        name = "Real Madrid",
        iconId = R.drawable.real_madrid_logo_vector,
        power = 98
    ),
    ClubModel(
        name = "Barcelona",
        iconId = R.drawable.barcelona_logo_vector,
        power = 96
    ),
    ClubModel(
        name = "Athletic Bilbao",
        iconId = R.drawable.athletic_bilbao_logo_vector,
        power = 40
    ),
    ClubModel(
        name = "Atletico Madrid",
        iconId = R.drawable.atletico_madrid_logo_vector,
        power = 90
    ),
    ClubModel(
        name = "Valencia",
        iconId = R.drawable.valencia_vector_logo,
        power = 70
    ),
    ClubModel(
        name = "Sevilla",
        iconId = R.drawable.fc_sevilla_logo,
        power = 75
    ),
    ClubModel(
        name = "Villarreal",
        iconId = R.drawable.villarreal_logo_vector,
        power = 80
    ),
    ClubModel(
        name = "Celta",
        iconId = R.drawable.celta_de_vigo_logo_vector,
        power = 40
    ),
    ClubModel(
        name = "Malaga",
        iconId = R.drawable.malaga,
        power = 45
    ),
    ClubModel(
        name = "Deportivo",
        iconId = R.drawable.deportivo_de_la_coruna_logo_vector,
        power = 30
    ),
    ClubModel(
        name = "Getafe",
        iconId = R.drawable.getafe_logo_vector,
        power = 35
    ),
    ClubModel(
        name = "Levante",
        iconId = R.drawable.levante_logo_vector,
        power = 30
    ),
    ClubModel(
        name = "Mallorca",
        iconId = R.drawable.mallorca_logo_vector,
        power = 25
    ),
    ClubModel(
        name = "Sporting de Gijon",
        iconId = R.drawable.sporting_de_gijon_logo_vector,
        power = 25
    ),
    ClubModel(
        name = "Osasuna",
        iconId = R.drawable.osasuna_logo_vector,
        power = 30
    ),
    ClubModel(
        name = "Racing de Santander",
        iconId = R.drawable.racing_de_santander_logo_vector,
        power = 20
    ),
    ClubModel(
        name = "Rayo Vallecano",
        iconId = R.drawable.rayo_vallecano_logo_vector,
        power = 45
    ),
    ClubModel(
        name = "Real Sociedad",
        iconId = R.drawable.real_sociedad_logo_vector,
        power = 75
    )
)