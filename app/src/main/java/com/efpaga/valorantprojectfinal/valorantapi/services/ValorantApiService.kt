package com.efpaga.valorantprojectfinal.valorantapi.services

import com.efpaga.valorantprojectfinal.valorantapi.models.*
import retrofit2.Call
import retrofit2.http.GET

interface ValorantApiService {


    companion object {
        const val BASE_URL = "https://valorant-api.com/v1/";
    }

    @GET("/v1/agents?isPlayableCharacter=true&&language=es-ES")
    fun agents(): Call<BaseModel<Array<Agent>>>

    @GET("/v1/bundles?isPlayableCharacter=true&&language=es-ES")
    fun bundles(): Call<BaseModel<Array<Bundles>>>

    @GET("/v1/buddies?language=es-ES")
    fun buddies(): Call<BaseModel<Array<Budy>>>

    @GET("/v1/playercards?language=es-ES")
    fun cards(): Call<BaseModel<Array<Card>>>

    @GET("/v1/contenttiers?language=es-ES")
    fun contentTiers(): Call<BaseModel<Array<ContentTier>>>

    @GET("/v1/currencies?language=es-ES")
    fun currencies(): Call<BaseModel<Array<Currency>>>

    @GET("/v1/gamemodes?language=es-ES")
    fun gamemodes(): Call<BaseModel<Array<Gamemode>>>

    @GET("/v1/gamemodes/equippables?language=es-ES")
    fun gamemodeEquippable(): Call<BaseModel<Array<GamemodeEquippable>>>

    @GET("/v1/maps?language=es-ES")
    fun maps(): Call<BaseModel<Array<ValorantMap>>>

    @GET("/v1/seasons?language=es-ES")
    fun seasons(): Call<BaseModel<Array<Season>>>

    @GET("/v1/themes?language=es-ES")
    fun themes(): Call<BaseModel<Array<Theme>>>

    @GET("/v1/playertitles?language=es-ES")
    fun titles(): Call<BaseModel<Array<Title>>>

    @GET("/v1/weapons?language=es-ES")
    fun weapons(): Call<BaseModel<Array<Weapon>>>

    @GET("/v1/version?language=es-ES")
    fun version(): Call<BaseModel<Version>>

    @GET("/v1/sprays?language=es-ES")
    fun sprays(): Call<BaseModel<Array<Spray>>>


}