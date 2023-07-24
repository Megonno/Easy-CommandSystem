package de.megonno.easycommandsystem

abstract class MCommand(val name: String) {
    abstract fun execute(args: List<String>)
    
    abstract fun complete(args: List<String>): List<List<String>>
}
