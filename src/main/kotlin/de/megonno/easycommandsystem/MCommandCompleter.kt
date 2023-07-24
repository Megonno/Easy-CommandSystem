package de.megonno.easycommandsystem

import org.jline.reader.Candidate
import org.jline.reader.Completer
import org.jline.reader.LineReader
import org.jline.reader.ParsedLine
import org.jline.reader.impl.completer.AggregateCompleter
import org.jline.reader.impl.completer.ArgumentCompleter
import org.jline.reader.impl.completer.StringsCompleter

class MCommandCompleter(private val commands: Map<String, MCommand>) : Completer {
    override fun complete(reader: LineReader, line: ParsedLine, candidates: MutableList<Candidate>) {
        val args = line.line().split(" ").toMutableList().apply { removeFirst(); removeIf { it.isBlank() } }
        val result = mutableListOf<ArgumentCompleter>()

        for ((name, command) in commands) {
            val completions = mutableListOf<StringsCompleter>()

            completions.add(StringsCompleter(name))

            for (completion in command.complete(args)) {
                completions.add(StringsCompleter(completion))
            }

            result.add(ArgumentCompleter(completions.toList()))
        }

        AggregateCompleter(result.toList()).complete(reader, line, candidates)
    }
}
