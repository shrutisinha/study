package java8.gameOfLife;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Field {

    //immutable fields
    private final int x;
    private final int y;
    private final boolean alive;

    public Field(int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean getAlive() {
        return alive;
    }

    public static Predicate<Field> isAlive() {
        return Field::getAlive;
    }

    public static Predicate<Field> isDead() {
        return isAlive().negate();
    }

    //maps(converts) a field to dead field if certain conditions are met
    public static Function<Field, Field> toDeadField(Predicate<Field> isFieldToDie) {
        return (field) -> Optional
                .of(field)
                .filter(isFieldToDie.negate())  //this returns field if alive else returns null
                .orElse(newDeadField(field)); //returns default value for dead field if filter returns null.
    }

    //maps(converts) a field to alive field if certain conditions are met
    public static Function<Field, Field> toAliveField(Predicate<Field> isFieldToLive) {
        return (field) -> Optional
                .of(field)
                .filter(isFieldToLive.negate()) //this returns field if dead else returns null
                .orElse(newAliveField(field)); //returns default value for alive field if filter returns null.
    }

    public static Field newAliveField(Field field) {
        return new Field(field.getX(), field.getY(), true);
    }

    public static Field newDeadField(Field field) {
        return new Field(field.getX(), field.getY(), false);
    }

    public static Predicate<Field> isLivingNeighbour(Field original) {
        return (field) -> (Math.abs(original.getX() - field.getX()) < 2)
                && (Math.abs(original.getY() - field.getY()) < 2)
                && !field.equals(original) && isAlive().test(field);
    }

}
