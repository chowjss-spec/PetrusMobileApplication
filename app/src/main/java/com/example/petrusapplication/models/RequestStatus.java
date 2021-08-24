package com.example.petrusapplication.models;

public enum RequestStatus {
    Pending {
        @Override
        public String toString() {
            return "Pending";
        }
    }, Accepted {
        @Override
        public String toString() {
            return "Accepted";
        }
    }, Rejected {
        @Override
        public String toString() {
            return "Rejected";
        }
    }

}
