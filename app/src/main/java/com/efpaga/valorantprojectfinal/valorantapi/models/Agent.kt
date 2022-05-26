package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable

class Agent(
        val uuid: String,
        val displayName: String,
        val description: String,
        val developerName: String,
        val characterTags: Array<String>?,
        val displayIcon: String,
        val bustPortrait: String,
        val fullPortrait: String,
        val assetPath: String,
        val isFullPortraitRightFacing: Boolean,
        val isPlayableCharacter: Boolean,
        val isAvailableForTest: Boolean,
        val role: AgentRole?,
        val abilities: Array<AgentAbility>,
        val backgroundGradientColors: Array<String>,
        val voiceLine: VoiceLine

):Serializable

class AgentAbility(
    val slot: String,
    val displayName: String?,
    val description: String?,
    val displayIcon: String?
):Serializable

class AgentRole(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val assetPath: String
):Serializable

class VoiceLine(
    val minDuration: Float,
    val maxDuration: Float,
    val mediaList: Array<MediaList>
):Serializable

class MediaList(
    val id: Int,
    val wwise: String,
    val wave: String
):Serializable