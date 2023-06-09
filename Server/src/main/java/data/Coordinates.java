package data;

import java.util.Objects;

/**
 * Supplementary entity for the main entity.
 */
public class Coordinates {
    /**
     * Field is not null
     */
    private Long x; //Поле не может быть null
    /**
     * Field is not null
     */
    private Float y; //Поле не может быть null

    public Coordinates(){}

    public Coordinates(Long _x, Float _y){
        x = _x;
        y = _y;
    }

    /**
     * A method that allows to get the X coordinate.
     * @return Long value.
     */
    public Long getX() {
        return x;
    }
    /**
     * A method that allows to get the Y coordinate.
     * @return Float value.
     */

    public Float getY() {
        return y;
    }

    /**
     * A method that allows to set the X coordinate.
     */

    public void setX(Long x) {
        this.x = x;
    }
    /**
     * A method that allows to set the Y coordinate.
     */

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates coordinates = (Coordinates) obj;
        return x.equals(coordinates.x) && y.equals(coordinates.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
