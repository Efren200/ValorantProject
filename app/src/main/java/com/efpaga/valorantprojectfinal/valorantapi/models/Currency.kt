package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable

class Currency(
    val uuid: String,
    val displayName: String,
    val displayNameSingular: String,
    val displayIcon: String,
    val largeIcon: String?,
    val assetPath: String
): Serializable