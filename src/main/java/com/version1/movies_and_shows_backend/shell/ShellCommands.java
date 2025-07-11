package com.version1.movies_and_shows_backend.shell;

import com.version1.movies_and_shows_backend.seed.Seed;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommands {

    private final Seed seed;

    public ShellCommands(Seed seed) {
        this.seed = seed;
    }

    @ShellMethod("Seed data from CSV")
    public void runSeed() {
        seed.seedData();
        System.out.println("Seeded successfully.");
    }
}
