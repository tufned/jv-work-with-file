package core.basesyntax;

@FunctionalInterface
public interface LineProcessor {
    void execute(String line);
}
