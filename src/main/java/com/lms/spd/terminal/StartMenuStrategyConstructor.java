package com.lms.spd.terminal;

/**
 * Implementation of the strategy pattern
 * Strategy is a behavioral design mechanism that defines a family of similar algorithms and puts
 * each of them in its own class, after which algorithms can be interchanged right at runtime.
 * @see <a href="http://refactoring.guru/ru/design-patterns/strategy">refactoring.guru</a>
 */
public class StartMenuStrategyConstructor {

    private ITerminal terminal;

    public StartMenuStrategyConstructor(ITerminal terminal) {
        this.terminal = terminal;
    }

    public void executeMenu() {
        terminal.showContext();
    }
}
