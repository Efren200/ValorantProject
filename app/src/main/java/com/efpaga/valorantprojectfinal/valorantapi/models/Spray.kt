package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable

class Spray(
    val uuid: String,
    val displayName: String,
    val category: String,
    val displayIcon: String,
    val fullIcon: String,
    val fullTransparentIcon: String,
    val assetPath: String
): Serializable