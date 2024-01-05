package com.dmm.tfg.engine.dao;

import lombok.Getter;

@Getter
    public class TargetPositionDTO {
        private double x;
        private double y;

        public void setX(double x) {
            this.x = x;
        }
        public void setY(double y) {
            this.y = y;
        }
    }
