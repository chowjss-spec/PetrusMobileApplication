package com.example.petrusapplication.models;

public enum Color {
    Black
    {
        @Override
        public String toString() {
            return "Black";
        }
    }, Brown{
        @Override
        public String toString() {
            return "Brown";
        }
    }, Golden{
        @Override
        public String toString() {
            return "Golden";
        }
    }, Yellow{
        @Override
        public String toString() {
            return "Yellow";
        }
    }, Cream{
        @Override
        public String toString() {
            return "Cream";
        }
    }, Gray{
        @Override
        public String toString() {
            return "Gray";
        }
    }, White{
        @Override
        public String toString() {
            return "White";
        }
    }
}
