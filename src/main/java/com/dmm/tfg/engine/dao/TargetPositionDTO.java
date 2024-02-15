package com.dmm.tfg.engine.dao;

import lombok.Getter;

/**
 * Data Transfer Object for representing a target position in a two-dimensional space.
 * This class encapsulates the x and y coordinates for easy transfer over the network or application layers.
 */
@Getter
    public class TargetPositionDTO {
        private double x;
        private double y;

        /**
         * Sets the x-coordinate of the target position.
         *
         * @param x The x-coordinate to be set.
         */
        public void setX(double x) {
            this.x = x;
        }

        /**
         * Sets the y-coordinate of the target position.
         *
         * @param y The y-coordinate to be set.
         */
        public void setY(double y) {
            this.y = y;
        }
    }
