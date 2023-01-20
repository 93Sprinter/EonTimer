import { writable } from "svelte/store";
import { timerMode } from "../constants/TimerMode";
import { handheld } from "../constants/Handheld";
import { actionMode } from "../constants/ActionMode";
import { sound } from "../constants/Sound";
import { gen3TimerMode } from "../constants/Gen3TimerMode";
import { gen4TimerMode } from "../constants/Gen4TimerMode";
import { gen5TimerMode } from "../constants/Gen5TimerMode";

export const configStore = writable(null);

export const defaultConfig = {
  currentTimerMode: timerMode.GEN3,
  timerSettings: {
    handheld: handheld.GBA,
    refreshInterval: 8,
    precisionCalibration: false,
  },
  actionSettings: {
    mode: actionMode.AUDIO,
    sound: sound.BEEP,
    color: "#00FFFF",
    interval: 500,
    count: 5,
  },
  custom: {
    stages: [],
  },
  flowTimer: {
    value: "",
  },
  gen3: {
    timerMode: gen3TimerMode.DEFAULT,
    calibration: null,
    preTimer: 5000,
    targetFrame: 700,
    frameHit: null,
  },
  gen4: {
    timerMode: gen4TimerMode.DEFAULT,
    calibratedDelay: 500,
    calibratedSecond: 14,
    targetDelay: 600,
    targetSecond: 50,
    delayHit: null,
  },
  gen5: {
    timerMode: gen5TimerMode.DEFAULT,
    calibration: -95,
    targetDelay: 1200,
    targetSecond: 50,
    entralinkCalibration: 256,
    frameCalibration: 0,
    targetAdvances: 100,
    secondHit: null,
    delayHit: null,
    actualAdvances: null,
  },
};
