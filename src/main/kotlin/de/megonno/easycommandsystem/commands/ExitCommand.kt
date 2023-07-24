package de.megonno.easycommandsystem.commands

import de.megonno.easycommandsystem.MCommand
import de.megonno.easycommandsystem.MTerminal

class ExitCommand(private val terminal: MTerminal) : MCommand("exit") {
    override fun execute(args: List<String>) {
        terminal.stop()
    }

    override fun complete(args: List<String>): List<List<String>> {
        return listOf()
    }
}
