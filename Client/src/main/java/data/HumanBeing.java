package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The main entity used of the program.
 */
public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    /**
     * Field is not null. It's more than 0, unique and is generated automatically.
     */

    private Long id;
    //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    /**
     * Field is not null and not empty String.
     */

    private String name;
    //Поле не может быть null, Строка не может быть пустой
    /**
     * Field is not null
     */

    private Coordinates coordinates;
    //Поле не может быть null
    /**
     * Field is not null and is generated automatically.
     */

    private java.time.LocalDate creationDate;
    //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /**
     * Field is not null
     */

    private Boolean realHero;
    //Поле не может быть null
    /**
     * Field is not null.
     */

    private boolean hasToothpick;
    /**
     * Field is not null.
     */

    private long impactSpeed;
    /**
     * Field can be null.
     */

    private Float minutesOfWaiting;
    //Поле может быть null
    /**
     * Field can be null.
     */

    private WeaponType weaponType;
    //Поле может быть null
    /**
     * Field can be null.
     */

    private Mood mood;
    /**
     * Field is not null.
     */

    private Car car;
    //Поле не может быть null

    public HumanBeing(){}

    public HumanBeing(Long _id, String _name, Long _x, Float _y, Boolean _realHero, boolean _hasToothpick, long _impactSpeed, Float _minutesOfWaiting, String _weaponType, String _mood, boolean _cool){
        id = _id;
        name = _name;
        coordinates = new Coordinates(_x, _y);
        creationDate = LocalDate.now();
        realHero = _realHero;
        hasToothpick = _hasToothpick;
        impactSpeed = _impactSpeed;
        minutesOfWaiting = _minutesOfWaiting;
        weaponType = WeaponType.valueOf(_weaponType);
        mood = Mood.valueOf(_mood);
        car = new Car(_cool);
    }

    /**
     * A method to get the id of this object.
     * @return id of this object.
     */

    public Long getId()
    {
        return id;
    }
    /**
     * A method to get the name of this object.
     * @return name of this object.
     */

    public String getName()
    {
        return name;
    }
    /**
     * A method to get the Coordinates of this object.
     * @return coordinates of this object.
     */

    public Coordinates getCoordinates()
    {
        return coordinates;
    }
    /**
     * A method to get the creation date of this object.
     * @return creationDate of this object.
     */

    public LocalDate getCreationDate()
    {
        return creationDate;
    }
    /**
     * A method to get the real hero field of this object.
     * @return realHero of this object.
     */

    public Boolean getRealHero() {
        return realHero;
    }
    /**
     * A method to get the hasToothpick of this object.
     * @return hasToothpick of this object.
     */

    public boolean isHasToothpick() {
        return hasToothpick;
    }
    /**
     * A method to get the Car of this object.
     * @return Car of this object.
     */

    public Car getCar() {
        return car;
    }
    /**
     * A method to get the minutes of waiting of this object.
     * @return minutesOfWaiting of this object.
     */

    public Float getMinutesOfWaiting() {
        return minutesOfWaiting;
    }
    /**
     * A method to get the impact speed of this object.
     * @return impactSpeed of this object.
     */

    public long getImpactSpeed() {
        return impactSpeed;
    }
    /**
     * A method to get the Mood of this object.
     * @return mood of this object.
     */

    public Mood getMood() {
        return mood;
    }
    /**
     * A method to get the WeaponType of this object.
     * @return weaponType of this object.
     */

    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * A method to set the id of this object.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * A method to set the name of this object.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * A method to set the coordinates of this object.
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    /**
     * A method to set the creationDate of this object.
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * A method to set the realHero of this object.
     */
    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    /**
     * A method to set the hasToothpick of this object.
     */
    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }


    /**
     * A method to set the impactSpeed of this object.
     */
    public void setImpactSpeed(long impactSpeed) {
        this.impactSpeed = impactSpeed;
    }


    /**
     * A method to set the minutesOfWaiting of this object.
     */
    public void setMinutesOfWaiting(Float minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    /**
     * A method to set the weaponType of this object.
     */
    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * A method to set the mood of this object.
     */
    public void setMood(Mood mood) {
        this.mood = mood;
    }

    /**
     * A method to set the car of this object.
     */
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HumanBeing humanBeing = (HumanBeing) obj;
        return id.equals(humanBeing.id) && name.equals(humanBeing.name) && coordinates.equals(humanBeing.coordinates) &&
                creationDate.equals(humanBeing.creationDate) && realHero.equals(humanBeing.realHero) &&
                hasToothpick == humanBeing.hasToothpick && impactSpeed == humanBeing.impactSpeed &&
                minutesOfWaiting.equals(humanBeing.minutesOfWaiting) && weaponType.equals(humanBeing.weaponType)
                && mood.equals(humanBeing.mood) && car.equals(humanBeing.car);
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name=" + name +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", minutesOfWaiting=" + minutesOfWaiting +
                ", weaponType=" + weaponType +
                ", mood=" + mood +
                ", car=" + car + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates,creationDate, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
    }

    @Override
    public int compareTo(HumanBeing humanBeing) {
        if (humanBeing == null) return 1;
        if (humanBeing.getId() == null) return 1;
        if (this.hashCode() - humanBeing.hashCode() < 0) return -1;
        else if (this.hashCode() == humanBeing.hashCode()) return 0;
        else return 1;
    }
}
