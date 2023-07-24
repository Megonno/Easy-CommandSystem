package de.megonno.easycommandsystem

import de.megonno.easycommandsystem.commands.TestCommand
import org.jline.reader.*
import org.jline.terminal.TerminalBuilder

class MTerminal(private val prompt: String) : Runnable {
    private var run = false
    private val commands = mutableMapOf<String, MCommand>()
    private var lineReader = LineReaderBuilder.builder()
        .appName("MCloud")
        .terminal(TerminalBuilder.terminal())
        .variable(LineReader.HISTORY_FILE, java.nio.file.Paths.get(".console_history"))
        .option(LineReader.Option.DISABLE_EVENT_EXPANSION, true)
        .option(LineReader.Option.INSERT_TAB, false)
        .completer(MCommandCompleter(commands))
        .build()
    
    init {
        command(TestCommand())
    }
    
    fun start() {
        if (!run){
            run = true
      
            Thread(this).start()
        }
    }
    
    fun stop() {
        if (run) run = false
    }
    
    fun command(vararg commands: MCommand) {
        this.commands.putAll(commands.associateBy { it.name })
    }
    
    override fun run() {
        while (run) {            
            val command = lineReader.readLine(prompt).split(" ").toMutableList().apply { removeIf { it.isBlank() } }
            
            if (command.isEmpty()) continue
            
            commands[command.first()]?.execute(command.apply { removeFirst() })
        }
    }
} 
