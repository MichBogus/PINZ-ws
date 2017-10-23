package utils

fun <T> String.actionableNotEmpty(actionIfNotEmpty: (String) -> T, actionIfEmpty: (String) -> T): T =
        if (this.isNotEmpty()) {
            actionIfNotEmpty.invoke(this)
        } else {
            actionIfEmpty.invoke(this)
        }

fun <T> Boolean.actionableTrue(actionIfTrue: (Boolean) -> T, actionIfFalse: (Boolean) -> T): T =
        if (this) {
            actionIfTrue.invoke(this)
        } else {
            actionIfFalse.invoke(this)
        }