package com.stef.arduino.ledcontrolv2;

public enum LedMode {
    SOUND_REACTIVE {
        @Override public String toString() { return "Sound Reactive Mode"; }
    },
    STARS {
        @Override public String toString() { return "Star Mode"; }
    },
    STATIC_COLOR {
        @Override public String toString() { return "Static Color Mode"; }
    },
    COLOR_CYCLE {
        @Override public String toString() { return "Color Cycle Mode"; }
    };

    /**
     * This method will return an array of all the values inside this enum as strings
     * @return a string array with the toString() of all enum values
     */
    public static String[] getStringValues() {
        String[] data = new String[LedMode.values().length];
        LedMode[] ledModes = LedMode.values();

        for(byte i = 0; i < ledModes.length; i++) {
            data[i] = ledModes[i].toString();
        }

        return data;
    }
}
