import { getFrameRate } from "../../constants/Handheld";

const createCalibration = (delay, second, handheld) => {
  return toMillis(delay - toDelays(second * 1000, handheld), handheld);
};

const toMillis = (delays, handheld) => {
  return Math.round(delays * getFrameRate(handheld));
};

const toDelays = (millis, handheld) => {
  return Math.round(millis / getFrameRate(handheld));
};

const calibrateToDelays = (value, precisionCalibrationMode, handheld) => {
  return precisionCalibrationMode ? value : toDelays(value, handheld);
};

const calibrateToMillis = (value, precisionCalibrationMode, handheld) => {
  return precisionCalibrationMode ? value : toMillis(value, handheld);
};

export default {
  toMillis,
  toDelays,
  createCalibration,
  calibrateToDelays,
  calibrateToMillis,
};
