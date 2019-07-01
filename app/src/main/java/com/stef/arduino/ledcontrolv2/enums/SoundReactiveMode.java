package com.stef.arduino.ledcontrolv2.enums;

public enum SoundReactiveMode {
    PROGRESSIVE_MODE {
        @Override
        public String toString() {
            return "Progressive mode";
        }
    },
    BRIGHTNESS_MODE {
        @Override
        public String toString() {
            return "Brightness mode";
        }
    };

    /**
     * This method will return an array of all the values inside this enum as strings
     * @return a string array with the toString() of all enum values
     */
    public static String[] getStringValues() {
        String[] data = new String[SoundReactiveMode.values().length];
        SoundReactiveMode[] reactModes = SoundReactiveMode.values();

        for(byte i = 0; i < reactModes.length; i++) {
            data[i] = reactModes[i].toString();
        }

        return data;
    }

    /**
     * This will return the initial value used as led mode for wenever none are selected yeht
     * @return the initial SoundReactiveMode
     */
    public static SoundReactiveMode getInitialValue() {
        return SoundReactiveMode.PROGRESSIVE_MODE;
    }
}
