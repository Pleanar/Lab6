package data;

import java.io.Serializable;
import java.util.Objects;
/**
 * Supplementary entity for the main entity.
 */
public class Car implements Serializable {
    /**
     * Field is not null
     */
    private boolean cool;

    public  Car(){}

    public Car(boolean _cool){
        cool = _cool;
    }

    /**
     * A method that allows to get the cool field of a Car.
     * @return boolean value.
     */
    public boolean isCool() {
        return cool;
    }
    /**
     * A method that allows to set the cool field.
     */

    public void setCool(Boolean cool) {
        this.cool = cool;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return cool == car.cool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cool);
    }

    @Override
    public String toString() {
        return "Car{" +
                "cool=" + cool +
                "}";
    }
}
