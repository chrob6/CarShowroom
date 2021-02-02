package com.lab7;

public enum ItemConditions {
        NEW {
            @Override
            public String toString() {
                return "NEW";
            }
        },
        USED {
            @Override
            public String toString() {
                return "USED";
            }
        },
        DAMAGED {
            @Override
            public String toString() {
                return "DAMAGED";
            }
        }
}
