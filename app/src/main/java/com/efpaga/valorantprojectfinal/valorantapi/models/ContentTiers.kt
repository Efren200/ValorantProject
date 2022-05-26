package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable

class ContentTier(
    val uuid: String,
    val devName: String,
    val highlightColor: String,
    val displayIcon: String,
    val assetPath: String
): Serializable