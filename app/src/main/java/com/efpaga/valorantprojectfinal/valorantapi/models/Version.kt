package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable
import java.util.*

data class Version(
    val branch: String,
    val version: String,
    val buildVersion: String,
    val buildDate: Date
): Serializable