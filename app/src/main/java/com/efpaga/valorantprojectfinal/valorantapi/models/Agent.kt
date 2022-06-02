package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable

class Agent(
        val uuid: String,
        val displayName: String,
        val description: String,
        val displayIcon: String,
        val assetPath: String,
        val abilities: Array<AgentAbility>,
        val backgroundGradientColors: Array<String>,
        val voiceLine: VoiceLine

):Serializable

class AgentAbility(
    val displayName: String?,
    val description: String?,
    val displayIcon: String?
):Serializable

class VoiceLine(
    val mediaList: Array<MediaList>
):Serializable

class MediaList(
    val id: Int,
    val wave: String
):Serializable