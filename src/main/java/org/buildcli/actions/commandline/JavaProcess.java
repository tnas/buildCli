package org.buildcli.actions.commandline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaProcess implements CommandLineProcess{
  private final List<String> commands = new ArrayList<>();
  private JavaProcess() {
    commands.add("java");
  }

  public static JavaProcess createRunJarProcess(String jarName) {
    var process = new JavaProcess();

    process.commands.addAll(List.of("-jar", jarName));

    return process;
  }

  @Override
  public int run() {
    try {
      return new ProcessBuilder(commands).inheritIO().start().waitFor();
    } catch (InterruptedException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}
