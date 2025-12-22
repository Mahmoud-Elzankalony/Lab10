import java.io.IOException;

public class LevelFactory {
    public Level GetLevel(Character level) throws IOException, NotFoundException {
        if (level == 'e') { // Use char literal
            return new Easy();
        } else if (level == 'm') {
            return new Medium();
        } else if (level == 'h') {
            return new Hard();
        }
        throw new NotFoundException("Invalid Level Code");
    }

    public Character GetDifficulty(DifficultyEnum difficulty) {
        if (difficulty == DifficultyEnum.Easy) { // Use Enum comparison
            return 'e';
        } else if (difficulty == DifficultyEnum.Medium) {
            return 'm';
        } else if (difficulty == DifficultyEnum.Hard) {
            return 'h';
        }
        return null;
    }
}