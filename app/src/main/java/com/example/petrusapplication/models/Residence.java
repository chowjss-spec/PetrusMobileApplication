package com.example.petrusapplication.models;

public enum Residence {
    HDB
    {
        @Override
        public String toString() {
            return "HDB";
        }
    }
    , Private
    {
        @Override
        public String toString() {
            return "Private";
        }
    }
}
