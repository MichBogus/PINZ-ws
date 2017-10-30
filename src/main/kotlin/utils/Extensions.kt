package utils

fun <T> String.actionableNotEmpty(actionIfNotEmpty: (String) -> T, actionIfEmpty: (String) -> T): T =
        if (this.isNotEmpty()) {
            actionIfNotEmpty.invoke(this)
        } else {
            actionIfEmpty.invoke(this)
        }