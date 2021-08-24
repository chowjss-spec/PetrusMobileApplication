package com.example.petrusapplication.models;

public enum ApplicationStatus {
    Open {
        @Override
        public String toString() {
            return "Open";
        }
    },
    Closed {
        @Override
        public String toString() {
            return "Closed";
        }
    }
}
