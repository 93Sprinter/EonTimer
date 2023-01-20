import calibrationUtil from "./util/CalibrationUtil";
import delayTimerFactory from "./factory/DelayTimerFactory";

const getStages = (config) => {
  const gen4 = config.gen4;
  const handheld = config.timerSettings.handheld;
  return delayTimerFactory.createStages(gen4.targetSecond, gen4.targetDelay, getCalibration(gen4.calibratedDelay, gen4.calibratedSecond, handheld), handheld);
};

const calibrate = (config) => {
  const gen4 = config.gen4;
  const handheld = config.timerSettings.handheld;
  const precisionCalibration = config.timerSettings.precisionCalibration;
  return (gen4.calibratedDelay || 0) + calibrationUtil.calibrateToDelays(delayTimerFactory.calibrate(gen4.targetDelay, gen4.delayHit, handheld), precisionCalibration, handheld);
};

const getCalibration = (calibratedDelay = 0, calibratedSecond = 0, handheld) => {
  return calibrationUtil.createCalibration(calibratedDelay, calibratedSecond, handheld);
};

export default {
  getStages,
  calibrate,
};
