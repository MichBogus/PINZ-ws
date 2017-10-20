package utils

fun <T> String.actionableEmpty(actionIfNotEmpty: (String) -> T, actionIfEmpty: () -> T): T =
        if (this.isNotEmpty()) {
            actionIfNotEmpty.invoke(this)
        } else {
            actionIfEmpty.invoke()
        }