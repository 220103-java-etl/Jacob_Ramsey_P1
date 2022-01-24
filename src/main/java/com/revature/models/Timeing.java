package com.revature.models;

public enum Timeing {
    URGENT {
        @Override
        public String toString() {
            return "Urgent";
        }
    },
    NORMAL {
        @Override
        public String toString() {
            return "Normal";
        }
    }
}
