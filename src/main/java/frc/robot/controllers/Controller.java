package frc.robot.controllers;

/**
 * A parent class for the different controllers, used to standardize code and make it easier to support all of them
 */
public abstract class Controller {

    /**
     * A parent function for Controller objects, specifically to support twisting the Hotas controller
     * @return The controllers twist value as a double
     */
    public abstract double getZ();

    /**
     * A parent function for Controller objects
     * @return The controllers y axis value as a double
     */
    public abstract double getYAxis();

    /**
     * A parent function for Controller objects
     * @return The controllers x axis value as a double
     */
    public abstract double getXAxis();
}