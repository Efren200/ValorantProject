package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable
import java.util.*

class Season(
        val uuid: String,
        val displayName: String,
        val startTime: Date,
        val endTime: Date,
        val borders: Array<SeasonBorder>,
        val assetPath: String
): Serializable

class SeasonBorder(
    val uuid: String,
    val winsRequired: Int,
    val level: Int,
    val displayIcon: String,
    val smallIcon: String,
    val assetPath: String
):Serializable