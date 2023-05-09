package org.example.server.tools;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class ColorOutput {
    public static Ansi formatStr(String content, Ansi.Color color) {
        return Ansi.ansi().fg(color).a(content);
    }

    public static void output(String content, Ansi.Color color) {
        AnsiConsole.systemInstall();
        System.out.println(formatStr(content, color));
        AnsiConsole.systemUninstall();
    }
}
