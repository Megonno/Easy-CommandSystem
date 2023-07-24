package de.megonno.easycommandsystem.commands

import de.megonno.easycommandsystem.MCommand

class TestCommand : MCommand("test") {
    override fun execute(args: List<String>) {
        println("Test ${args.joinToString()}")
    }

    override fun complete(args: List<String>): List<List<String>> {
        return listOf(
            listOf("1", "2", "3")
        )
    }
}
