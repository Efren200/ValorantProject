package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable

class Title(
    val uuid: String,
    val displayName: String,
    val titleText: String,
    val isbHiddenIfNotOwner: Boolean,
    val assetPath: String
): Serializable