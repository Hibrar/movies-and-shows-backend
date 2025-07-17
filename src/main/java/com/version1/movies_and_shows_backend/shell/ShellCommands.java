package com.version1.movies_and_shows_backend.shell;

import com.version1.movies_and_shows_backend.seed.Seed;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class ShellCommands {

    private final Seed seed;

    public ShellCommands(Seed seed) {
        this.seed = seed;
    }

    @ShellMethod("Seed data from CSV")
    public void runSeed() {
        List<String> siteNames = List.of("netflix", "disney", "paramount", "hbo", "prime", "apple");
        seed.seedData(siteNames);
        System.out.println("Seeded successfully.");
    }
}
